import type { Telefone } from './telefone';

export interface ICliente {
    id: string;
    nome: string;
    telefones: Telefone[];
    adicionarTelefone(telefone: Telefone): void;
    removerTelefone(telefoneId: string): void;
}

export class Cliente implements ICliente {
    id: string;
    nome: string;
    telefones: Telefone[];

    constructor(id: string, nome: string) {
        this.id = id;
        this.nome = nome;
        this.telefones = [];
    }

    adicionarTelefone(telefone: Telefone): void {
        this.telefones.push(telefone);
    }

    removerTelefone(telefoneId: string): void {
        this.telefones = this.telefones.filter(telefone => telefone.id !== telefoneId);
    }
}
