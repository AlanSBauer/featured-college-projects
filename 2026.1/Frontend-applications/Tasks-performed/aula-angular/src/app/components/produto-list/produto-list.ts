import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { CurrencyPipe } from '@angular/common';
import { Produto } from '../../models/produto';
import { ProdutoService } from '../../services/produto.service';

@Component({
  selector: 'app-produto-list',
  imports: [CurrencyPipe],
  templateUrl: './produto-list.html',
  styleUrl: './produto-list.css',
})
export class ProdutoList {
  private readonly produtosService = inject(ProdutoService);
  private readonly cdr = inject(ChangeDetectorRef);
  produtos : Produto[] = [];
  carregando: boolean = false;
 
  ngOnInit() {
    this.carregarProdutos();
  }

  carregarProdutos() : void{
    this.carregando = true;
    this.produtosService.listar().subscribe({
      next: (dados) => { 
        this.produtos = dados;
        this.carregando = false;
        this.cdr.detectChanges();
      },
      error: (erro) => 
        {
          console.error('Erro ao carregar produtos:', erro)
          this.carregando = false;
          this.cdr.detectChanges();
        }
    })
  }

  editar(produto: Produto): void {
    if(!produto.id){
      return;
    }

    this.produtosService.atualizar(produto , produto.id).subscribe({
        next: () => this.carregarProdutos(),
        error: (erro) => console.error('Erro ao atualizar', erro),
      });
    
    }

    excluir(produto: Produto): void{
      if(!produto.id){
        return;
      }
      this.produtosService.excluir(produto.id).subscribe({
        next: () => this.carregarProdutos(),
        error: (erro) => console.error('Erro ao excluir', erro),
      })
    }

  }

