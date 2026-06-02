import { Component, EventEmitter, inject, OnInit, Output } from '@angular/core';
import { Viagem } from '../../models/viagem';
import { ViagemService } from '../../services/viagem.service';

@Component({
  selector: 'app-viagem-list',
  imports: [],
  templateUrl: './viagem-list.html',
  styleUrl: './viagem-list.css'
})
export class ViagemList implements OnInit {
  @Output() editarViagem = new EventEmitter<Viagem>();

  private readonly viagemService = inject(ViagemService);

  viagens: Viagem[] = [];
  carregando = false;
  erroCarregamento = false;

  ngOnInit(): void {
    this.carregarViagens();
  }

  carregarViagens(): void {
    this.carregando = true;
    this.erroCarregamento = false;

    this.viagemService.listar().subscribe({
      next: (dados) => {
        this.viagens = dados;
        this.carregando = false;
      },
      error: (erro) => {
        console.error('Erro ao carregar viagens:', erro);
        this.erroCarregamento = true;
        this.carregando = false;
      }
    });
  }

  editar(viagem: Viagem): void {
    this.editarViagem.emit(viagem);
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }

  excluir(viagem: Viagem): void {
    if (!viagem.id) return;

    const confirmou = confirm(`Deseja excluir a viagem para "${viagem.destino}"?\n\nEsta ação não pode ser desfeita.`);
    if (!confirmou) return;

    this.viagemService.excluir(viagem.id).subscribe({
      next: () => this.carregarViagens(),
      error: (erro) => {
        console.error('Erro ao excluir viagem:', erro);
        alert('Erro ao excluir viagem. Tente novamente.');
      }
    });
  }

  formatarData(data: string): string {
    if (!data) return '—';
    const [ano, mes, dia] = data.split('-');
    return `${dia}/${mes}/${ano}`;
  }

  formatarOrcamento(valor: number): string {
    return valor.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
  }

  get totalRealizadas(): number {
    return this.viagens.filter(v => v.realizada).length;
  }
}
