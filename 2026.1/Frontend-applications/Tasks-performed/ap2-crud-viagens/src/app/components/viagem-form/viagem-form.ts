import { Component, EventEmitter, inject, Input, OnChanges, Output, SimpleChanges, ViewChild } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Viagem } from '../../models/viagem';
import { ViagemService } from '../../services/viagem.service';

@Component({
  selector: 'app-viagem-form',
  imports: [FormsModule],
  templateUrl: './viagem-form.html',
  styleUrl: './viagem-form.css'
})
export class ViagemForm implements OnChanges {
  @Input() viagemParaEditar: Viagem | null = null;
  @Output() salvo = new EventEmitter<void>();
  @Output() cancelarEdicao = new EventEmitter<void>();

  @ViewChild('formViagem') formViagem!: NgForm;

  private readonly viagemService = inject(ViagemService);

  modoEdicao = false;
  salvando = false;
  mensagem = '';
  tipoMensagem: 'sucesso' | 'erro' = 'sucesso';

  tiposViagem = ['Turismo', 'Negócios', 'Aventura', 'Lazer', 'Mochilão', 'Cruzeiro', 'Intercâmbio'];

  viagem: Viagem = this.viagemVazia();

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['viagemParaEditar'] && this.viagemParaEditar) {
      this.modoEdicao = true;
      this.viagem = { ...this.viagemParaEditar };
    }
  }

  viagemVazia(): Viagem {
    return {
      destino: '',
      pais: '',
      dataPartida: '',
      dataRetorno: '',
      orcamento: 0,
      tipoViagem: 'Turismo',
      realizada: false,
      observacoes: ''
    };
  }

  salvar(): void {
    if (this.salvando) return;
    this.salvando = true;
    this.mensagem = '';

    if (this.modoEdicao && this.viagem.id) {
      this.viagemService.atualizar(this.viagem.id, this.viagem).subscribe({
        next: () => {
          this.exibirMensagem('Viagem atualizada com sucesso!', 'sucesso');
          this.resetar();
          this.salvo.emit();
          this.salvando = false;
        },
        error: (erro) => {
          console.error('Erro ao atualizar viagem:', erro);
          this.exibirMensagem('Erro ao atualizar viagem. Verifique se a API está rodando.', 'erro');
          this.salvando = false;
        }
      });
    } else {
      this.viagemService.criar(this.viagem).subscribe({
        next: () => {
          this.exibirMensagem('Viagem cadastrada com sucesso!', 'sucesso');
          this.resetar();
          this.salvo.emit();
          this.salvando = false;
        },
        error: (erro) => {
          console.error('Erro ao cadastrar viagem:', erro);
          this.exibirMensagem('Erro ao cadastrar viagem. Verifique se a API está rodando.', 'erro');
          this.salvando = false;
        }
      });
    }
  }

  cancelar(): void {
    this.resetar();
    this.cancelarEdicao.emit();
  }

  private resetar(): void {
    this.viagem = this.viagemVazia();
    this.modoEdicao = false;
    // Reseta o estado do formulário (touched, dirty, invalid) sem só limpar os valores
    setTimeout(() => this.formViagem?.resetForm(this.viagemVazia()), 0);
  }

  private exibirMensagem(texto: string, tipo: 'sucesso' | 'erro'): void {
    this.mensagem = texto;
    this.tipoMensagem = tipo;
    setTimeout(() => (this.mensagem = ''), 4000);
  }
}
