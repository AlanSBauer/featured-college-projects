export type TipoTelefone = 'residencial' | 'comercial' | 'celular';

export interface ITelefone {
    id: string;
    tipo: TipoTelefone;
    numero: string;
}

export class Telefone implements ITelefone {
    id: string;
    tipo: TipoTelefone;
    numero: string;

    constructor(id: string, tipo: TipoTelefone, numero: string) {
        this.id = id;
        this.tipo = tipo;
        this.numero = numero;
    }
}
