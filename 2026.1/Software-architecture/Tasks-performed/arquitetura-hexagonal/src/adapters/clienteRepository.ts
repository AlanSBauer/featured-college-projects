import type { Cliente } from '../domain/cliente';

export interface IClienteRepository {
    findById(id: string): Promise<Cliente | null>;
    save(cliente: Cliente): Promise<void>;
}
