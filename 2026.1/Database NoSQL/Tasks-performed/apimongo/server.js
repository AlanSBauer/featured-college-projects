import OpenAI from "openai";
const client = new OpenAI();

import express from 'express';
import connection from './db.js';

const app = express();
const PORT = process.env.PORT || 3000;
const COLLECTION = 'respostaMensagens';

function chatgptResponse(question, answer) {
    return client.responses.create({
        model: "gpt-5.5",
        input: `Pergunta: ${question} - Resposta: ${answer} - considerando essa pergunta e resposta, retorne metricas ralacionando ao conteudo, em formato JSON, sempre considerando esse modelo:`
        // gerar as metricas e colocar o json stringfy aqui
    })
}

app.get('/usuarios', async (req, res) => {
const db = await connection.getDb();
const docs = await db.collection(COLLECTION).find({}).toArray();
await Promise.all(docs.map(async (doc) => {
    doc.metricas = await chatgptResponse(doc.pergunta, doc.resposta);
    console.log(doc.metricas = JSON.parse(doc.metricas.output_text) || {});
}));
res.json({ total: docs.length, dados: docs });
});

app.listen(PORT, () => {
console.log(`Servidor rodando em http://localhost:${PORT}`);
});