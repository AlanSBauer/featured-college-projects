import express from 'express';
import { AdicionarTelefoneAoCliente } from '../application/adicionarTelefoneAoCliente';
import { RemoverTelefoneDoCliente } from '../application/removerTelefoneDoCliente';
import { ClienteInMemoryRepository } from './clienteInMemoryRepository';

const router = express.Router();
const clienteRepository = new ClienteInMemoryRepository();

router.post('/clientes/:clienteId/telefones', async (req, res) => {
    try {
        const { clienteId } = req.params;
        const { telefoneId, tipo, numero } = req.body;

        const adicionarTelefoneAoCliente = new AdicionarTelefoneAoCliente(clienteRepository);
        const cliente = await adicionarTelefoneAoCliente.execute({ clienteId, telefoneId, tipo, numero });

        res.status(201).json(cliente);
    } catch (error) {
        res.status(400).json({ message: error.message });
    }
});

router.delete('/clientes/:clienteId/telefones/:telefoneId', async (req, res) => {
    try {
        const { clienteId, telefoneId } = req.params;

        const removerTelefoneDoCliente = new RemoverTelefoneDoCliente(clienteRepository);
        const cliente = await removerTelefoneDoCliente.execute({ clienteId, telefoneId });

        res.status(200).json(cliente);
    } catch (error) {
        res.status(400).json({ message: error.message });
    }
});

export default router;
