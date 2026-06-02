import OpenAI from "openai";
const client = new OpenAI({ apiKey: "" });

import express from 'express';
import connection from './db.js';
import { fileURLToPath } from 'url';
import { dirname } from 'path';

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);

const app = express();
const PORT = process.env.PORT || 3000;
const COLLECTION = 'respostaMensagens';

app.use((req, res, next) => {
    res.header('Access-Control-Allow-Origin', '*');
    res.header('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept');
    next();
});

app.use(express.static(__dirname));

async function chatgptResponse(question, answer) {
    const response = await client.responses.create({
        model: "gpt-4o",
        input: `Pergunta: ${question} - Resposta: ${answer} - considerando essa pergunta e resposta, retorne métricas relacionadas ao conteúdo, em formato JSON, sempre considerando esse modelo (os campos numéricos devem ser de 0.0 a 1.0 com incrementos de 0.1):
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
}`
    });

    return response;
}

app.get('/rodaETL', async (req, res) => {
    const db = await connection.getDb();
    const docs = await db.collection(COLLECTION).find({}).toArray();
    await Promise.all(docs.map(async (doc) => {
        doc.metricas = await chatgptResponse(doc.question, doc.message);
        let responseChatgpt = JSON.parse(doc.metricas.output_text.replace(/```json|```/g, '').trim());

        await db.collection(COLLECTION).updateOne({_id: doc._id}, { $set: {metricas: responseChatgpt}});
        console.log(responseChatgpt);
    }));
    res.json({ total: docs.length, dados: docs });
});

app.get("/relatorio", async (req, res) => {
  const db = await connection.getDb();

  const docs = await db.collection(COLLECTION).aggregate([
    {
      $group: {
        _id: null,

        total_respostas: {
          $sum: 1
        },

        media_relevancia: {
          $avg: "$metricas.relevancia_com_a_pergunta"
        },

        media_clareza: {
          $avg: "$metricas.clareza_da_resposta"
        },

        media_completude: {
          $avg: "$metricas.completude_da_resposta"
        },

        polaridade_media: {
          $avg: "$metricas.polaridade"
        }
      }
    },

    {
      $project: {
        _id: 0,

        total_respostas: 1,

        media_relevancia: {
          $round: ["$media_relevancia", 2]
        },

        media_clareza: {
          $round: ["$media_clareza", 2]
        },

        media_completude: {
          $round: ["$media_completude", 2]
        },

        polaridade_media: {
          $round: ["$polaridade_media", 2]
        }
      }
    }
  ]).toArray();

  res.json(docs);
});

app.listen(PORT, () => {
    console.log(`Servidor rodando em http://localhost:${PORT}`);
});