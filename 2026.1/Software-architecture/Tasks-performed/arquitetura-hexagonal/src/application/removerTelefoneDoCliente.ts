import type { IClienteRepository } from '../adapters/clienteRepository';
import type { Cliente } from '../domain/cliente';

interface RemoverTelefoneDoClienteDTO {
    clienteId: string;
    telefoneId: string;
}

export class RemoverTelefoneDoCliente {
    private clienteRepository: IClienteRepository;

    constructor(clienteRepository: IClienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    async execute(dto: RemoverTelefoneDoClienteDTO): Promise<Cliente> {
        const { clienteId, telefoneId } = dto;
        const cliente = await this.clienteRepository.findById(clienteId);

        if (!cliente) {
            throw new Error('Cliente não encontrado');
        }

        cliente.removerTelefone(telefoneId);

        await this.clienteRepository.save(cliente);

        return cliente;
    }
}
