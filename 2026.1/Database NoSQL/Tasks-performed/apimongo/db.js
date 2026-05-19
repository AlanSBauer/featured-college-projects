import { MongoClient } from 'mongodb';

const URI = process.env.MONGO_URI || 'mongodb://root:1234@ac-6l3spbx-shard-00-00.wnzibrq.mongodb.net:27017,ac-6l3spbx-shard-00-01.wnzibrq.mongodb.net:27017,ac-6l3spbx-shard-00-02.wnzibrq.mongodb.net:27017/?ssl=true&replicaSet=atlas-tzqegw-shard-0&authSource=admin&appName=Cluster0';
const DB_NAME = process.env.MONGO_DB || 'pesquisa';

let clientPromise;

export function getClient() {
if (!clientPromise) {
const client = new MongoClient(URI, {
serverSelectionTimeoutMS: 8000,
});
clientPromise = client.connect();
}
return clientPromise;
}

export async function getDb() {
const client = await getClient();
return client.db(DB_NAME);
}

async function closeDb() {
if (clientPromise) {
const client = await clientPromise;
await client.close();
clientPromise = null;
}
}

export default { getDb, closeDb, DB_NAME };



