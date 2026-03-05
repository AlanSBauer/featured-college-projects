import type { IClienteRepository } from '../adapters/clienteRepository';
import type { Cliente } from '../domain/cliente';
import { Telefone, type TipoTelefone } from '../domain/telefone';

interface AdicionarTelefoneAoClienteDTO {
    clienteId: string;
    telefoneId: string;
    tipo: TipoTelefone;
    numero: string;
}

export class AdicionarTelefoneAoCliente {
    private clienteRepository: IClienteRepository;

    constructor(clienteRepository: IClienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    async execute(dto: AdicionarTelefoneAoClienteDTO): Promise<Cliente> {
        const { clienteId, telefoneId, tipo, numero } = dto;
        const cliente = await this.clienteRepository.findById(clienteId);

        if (!cliente) {
            throw new Error('Cliente não encontrado');
        }

        const telefone = new Telefone(telefoneId, tipo, numero);
        cliente.adicionarTelefone(telefone);

        await this.clienteRepository.save(cliente);

        return cliente;
    }
}
