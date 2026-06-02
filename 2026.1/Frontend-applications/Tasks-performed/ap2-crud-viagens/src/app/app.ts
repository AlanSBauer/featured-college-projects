import { Component, ViewChild } from '@angular/core';
import { Navbar } from './components/navbar/navbar';
import { ViagemForm } from './components/viagem-form/viagem-form';
import { ViagemList } from './components/viagem-list/viagem-list';
import { Viagem } from './models/viagem';

@Component({
  selector: 'app-root',
  imports: [Navbar, ViagemForm, ViagemList],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  @ViewChild(ViagemList) listaComponent!: ViagemList;

  viagemParaEditar: Viagem | null = null;

  iniciarEdicao(viagem: Viagem): void {
    this.viagemParaEditar = { ...viagem };
  }

  aoSalvar(): void {
    this.viagemParaEditar = null;
    if (this.listaComponent) {
      this.listaComponent.carregarViagens();
    }
  }

  aoCancelarEdicao(): void {
    this.viagemParaEditar = null;
  }
}
