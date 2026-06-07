import OpenAI from "openai";
import express from 'express';
import connection from './db.js';
import { fileURLToPath } from 'url';
import { dirname } from 'path';

const client = new OpenAI({ apiKey: process.env.OPENAI_API_KEY || "" });
const app = express();
const PORT = process.env.PORT || 3000;
const COLLECTION = 'respostaMensagens';

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);

app.use((req, res, next) => {
    res.header('Access-Control-Allow-Origin', '*');
    res.header('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept');
    next();
});

app.use(express.static(__dirname));

async function ensureIndexes() {
    const db = await connection.getDb();
    await db.collection(COLLECTION).createIndex({ question: 1 });
    console.log('Índice verificado no campo "question"');
}

async function analisarResposta(question, answer) {
    const response = await client.responses.create({
        model: "gpt-4o",
        input: `Você é um avaliador especialista em pesquisas acadêmicas sobre qualidade do ensino superior.

Contexto da pesquisa: alunos de ensino superior foram perguntados sobre sua satisfação com os professores do curso, especificamente nos seguintes aspectos:
1. Didática
2. Clareza na explicação dos conteúdos
3. Organização das aulas
4. Proposta de atividades e trabalhos

Pergunta feita ao aluno: ${question}
Resposta do aluno: ${answer}

Avalie a resposta com base nos 4 aspectos acima e retorne SOMENTE um JSON válido, sem texto extra e sem markdown, seguindo exatamente este modelo (campos numéricos de 0.0 a 1.0, incrementos de 0.1):

{
  "resposta_valida": true,
  "relevancia_com_a_pergunta": 0.0,
  "clareza_da_resposta": 0.0,
  "completude_da_resposta": 0.0,
  "sentimento": "positivo | negativo | neutro | indefinido",
  "polaridade": 0.0,
  "satisfacao_estimada": "muito alta | alta | media | baixa | muito baixa | não identificada",
  "avaliacao_geral": "ótimo | bom | regular | ruim | péssimo | não informado",
  "contem_opiniao": false,
  "contem_justificativa": false,
  "possui_reclamacao": false,
  "possui_elogio": false,
  "palavras_chave": [],
  "classificacao": "resposta completa | resposta parcial | resposta ausente",
  "interpretacao": ""
}

Critérios de avaliação:
- relevancia_com_a_pergunta: a resposta trata de professores e de pelo menos um dos 4 aspectos pesquisados?
- clareza_da_resposta: a resposta é objetiva, coesa e sem ambiguidades?
- completude_da_resposta: quantos dos 4 aspectos (didática, clareza, organização, atividades) foram abordados? 1 aspecto = 0.3, 2 = 0.5, 3 = 0.8, 4 = 1.0
- sentimento: percepção geral do aluno sobre os professores (positivo / negativo / neutro / indefinido)
- polaridade: 0.0 = extremamente negativo, 0.5 = neutro, 1.0 = extremamente positivo
- satisfacao_estimada: nível de satisfação do aluno com os professores do curso
- avaliacao_geral: valor da resposta como dado de pesquisa acadêmica
- contem_opiniao: o aluno emitiu opinião própria sobre os professores?
- contem_justificativa: o aluno explicou ou fundamentou sua avaliação?
- possui_reclamacao: há insatisfação, crítica ou queixa sobre algum professor ou aspecto?
- possui_elogio: há elogio ou reconhecimento positivo sobre algum professor ou aspecto?
- palavras_chave: liste os termos mais relevantes citados pelo aluno (ex: didática, explicação, provas, organização, feedback)
- classificacao: "resposta completa" se abordou bem a pergunta | "resposta parcial" se foi superficial ou abordou poucos aspectos | "resposta ausente" se não respondeu ao tema
- interpretacao: síntese em uma frase clara do que o aluno expressou sobre os professores`
    });

    return response;
}

app.get('/rodaETL', async (req, res) => {
    const db = await connection.getDb();
    const docs = await db.collection(COLLECTION).find({}).toArray();

    await Promise.all(docs.map(async (doc) => {
        const resultado = await analisarResposta(doc.question, doc.message);
        const metricas = JSON.parse(resultado.output_text.replace(/```json|```/g, '').trim());
        await db.collection(COLLECTION).updateOne({ _id: doc._id }, { $set: { metricas } });
        console.log(metricas);
    }));

    res.json({ total: docs.length, dados: docs });
});

app.get('/usuarios', async (req, res) => {
    const db = await connection.getDb();
    const { question } = req.query;
    const filtro = question ? { question: { $regex: question, $options: 'i' } } : {};
    const docs = await db.collection(COLLECTION).find(filtro).toArray();
    res.json({ total: docs.length, dados: docs });
});

app.get('/relatorio', async (req, res) => {
    const db = await connection.getDb();
    const { question } = req.query;
    const matchStage = question
        ? { $match: { question: { $regex: question, $options: 'i' } } }
        : { $match: {} };

    const docs = await db.collection(COLLECTION).aggregate([
        matchStage,
        {
            $group: {
                _id: null,
                total_respostas: { $sum: 1 },
                media_relevancia: { $avg: "$metricas.relevancia_com_a_pergunta" },
                media_clareza: { $avg: "$metricas.clareza_da_resposta" },
                media_completude: { $avg: "$metricas.completude_da_resposta" },
                polaridade_media: { $avg: "$metricas.polaridade" }
            }
        },
        {
            $project: {
                _id: 0,
                total_respostas: 1,
                media_relevancia: { $round: ["$media_relevancia", 2] },
                media_clareza: { $round: ["$media_clareza", 2] },
                media_completude: { $round: ["$media_completude", 2] },
                polaridade_media: { $round: ["$polaridade_media", 2] }
            }
        }
    ]).toArray();

    res.json(docs);
});

app.get('/perguntas', async (req, res) => {
    const db = await connection.getDb();
    const perguntas = await db.collection(COLLECTION).distinct('question');
    res.json(perguntas);
});

ensureIndexes().catch(console.error);

app.listen(PORT, () => {
    console.log(`Servidor rodando em http://localhost:${PORT}`);
});