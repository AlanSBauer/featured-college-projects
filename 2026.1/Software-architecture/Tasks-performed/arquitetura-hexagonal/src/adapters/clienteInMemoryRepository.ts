import type { Cliente } from '../domain/cliente';
import type { IClienteRepository } from './clienteRepository';

export class ClienteInMemoryRepository implements IClienteRepository {
    private clientes: Map<string, Cliente> = new Map();

    async findById(id: string): Promise<Cliente | null> {
        return this.clientes.get(id) || null;
    }

    async save(cliente: Cliente): Promise<void> {
        this.clientes.set(cliente.id, cliente);
    }
}
