export interface Viagem {
  id?: number | string;
  destino: string;
  pais: string;
  dataPartida: string;
  dataRetorno: string;
  orcamento: number;
  tipoViagem: string;
  realizada: boolean;
  observacoes: string;
}
