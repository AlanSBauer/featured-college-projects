package playlistmusical;

public class Playlist {
    private No inicio;
    private No fim;
    private No atual;
    private int tamanho;

    public Playlist() {
        this.inicio = null;
        this.fim = null;
        this.atual = null;
        this.tamanho = 0;
    }

    public No getInicio() {
        return inicio;
    }

    public void setInicio(No inicio) {
        this.inicio = inicio;
    }

    public No getFim() {
        return fim;
    }

    public void setFim(No fim) {
        this.fim = fim;
    }

    public No getAtual() {
        return atual;
    }

    public void setAtual(No atual) {
        this.atual = atual;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public void adicionarNoInicio(Musica musica) {
        No novoNo = new No(musica, null, null);

        if(tamanho == 0) {
            inicio = novoNo;
            fim = novoNo;
            atual = novoNo;
        } else {
            novoNo.setProximo(inicio);
            inicio.setAnterior(novoNo);
            inicio = novoNo;
        }
        tamanho++;
        System.out.println("Música adicionada ao inicio.");
    }


    public void adicionarNoFim(Musica musica) {
        No novoNo = new No(musica, null, null);
        if(tamanho == 0) {
            inicio = novoNo;
            fim = novoNo;
            atual = novoNo;
        } else {
            fim.setProximo(novoNo);
            novoNo.setAnterior(fim);
            fim = novoNo;
        }
        tamanho++;
        System.out.println("Musica adicionada ao final.");
    }

    public void adicionarNaPosicao(Musica musica, int pos) {
        if(pos < 0 || pos > tamanho) {
            System.out.println("Posição inválida!");
            return;
        }

        if(pos == 0) {
            adicionarNoInicio(musica);
            return;
        }

        if(pos == tamanho) {
            adicionarNoFim(musica);
            return;
        }


        No atualLista = inicio;
        for(int i = 0; i < pos; i++) {
            atualLista = atualLista.getProximo();
        }

        No anterior = atualLista.getAnterior();
        No novoNo = new No(musica, anterior, atualLista);
        anterior.setProximo(novoNo);
        atualLista.setAnterior(novoNo);

        tamanho++;
        System.out.println("Música adicionada na posição " + pos + ".");
    }

    private void removerNoNo(No atualLista) {

        if (tamanho == 1) {
            inicio = null;
            fim = null;
            atual = null;
            return;
        }

        if (atualLista == inicio) {
            inicio = inicio.getProximo();
            inicio.setAnterior(null);
            if (atual == atualLista) atual = inicio;
            return;
        }

        if (atualLista == fim) {
            fim = fim.getAnterior();
            fim.setProximo(null);
            if (atual == atualLista) atual = fim;
            return;
        }

        No anterior = atualLista.getAnterior();
        No proximo = atualLista.getProximo();

        anterior.setProximo(proximo);
        proximo.setAnterior(anterior);

        if (atual == atualLista) {
            atual = proximo;
        }
    }

    public void removerMusicaPorTitulo(String titulo) {

        if (inicio == null) {
            System.out.println("Lista vazia!");
            return;
        }

        No atualLista = inicio;

        while (atualLista != null) {

            if (atualLista.getMusica().getTitulo().equalsIgnoreCase(titulo)) {

                removerNoNo(atualLista);
                System.out.println("Música removida: " + atualLista.getMusica().getTitulo());
                tamanho--;
                return;
            }

            atualLista = atualLista.getProximo();
        }

        System.out.println("Música não encontrada!");
    }

    public void removerPorPosicao(int pos) {

        if (inicio == null) {
            System.out.println("Lista vazia!");
            return;
        }

        if (pos < 0 || pos >= tamanho) {
            System.out.println("Posição inválida!");
            return;
        }

        No atualLista = inicio;

        for (int i = 0; i < pos; i++) {
            atualLista = atualLista.getProximo();
        }

        System.out.println("Música removida: " + atualLista.getMusica().getTitulo());

        removerNoNo(atualLista);
        tamanho--;
    }

    public void proximaMusica() {
        if(atual == null) {
            System.out.println("A playlist está vazia!");
            return;
        }
        if(atual.getProximo() == null) {
            System.out.println("Você já está na última música da playlist.");
            return;
        }
        atual = atual.getProximo();
        tocarMusica();
    }

    public void musicaAnterior() {
        if(atual == null) {
            System.out.println("A playlist está vazia!");
            return;
        }

        if(atual.getAnterior() == null) {
            System.out.println("Esta é a primeira música da playlist.");
            return;
        }

        atual = atual.getAnterior();
        tocarMusica();
    }

    public void tocarMusica() {
        if(atual == null) {
            System.out.println("Playlist vazia!");
            return;
        }
        Musica musicaAtual = atual.getMusica();
        System.out.println("Musica atual: " + musicaAtual.getTitulo() + " | " + "Artista: " + musicaAtual.getArtista() + " | " + "Álbum: " + musicaAtual.getAlbum() + " | " + "Duração: " + musicaAtual.getDuracao() + "s.");
    }

    public void listarMusicas() {
        if(inicio == null) {
            System.out.println("Playlist Vazia!");
            return;
        }

        No atualLista = inicio;
        while (atualLista != null) {
            Musica musicaAtual = atualLista.getMusica();
            System.out.println("Titulo: " + musicaAtual.getTitulo() + " | " + "Artista: " + musicaAtual.getArtista() + " | " + "Álbum: " + musicaAtual.getAlbum() + " | " + "Duração: " + musicaAtual.getDuracao() + "s.");
            atualLista = atualLista.getProximo();
        }
    }

    public void ordenar(boolean porTitulo) {
        if(inicio == null || inicio.getProximo() == null) {
            System.out.println("Não há músicas suficientes para ordenar.");
            return;
        }

        boolean trocou = true;

        while(trocou) {
            trocou = false;
            No atualLista = inicio;

            while(atualLista.getProximo() != null) {
                String valorAtual;
                String valorProximo;

                if(porTitulo) {
                    valorAtual = atualLista.getMusica().getTitulo();
                    valorProximo = atualLista.getProximo().getMusica().getTitulo();
                } else {
                    valorAtual = atualLista.getMusica().getArtista();
                    valorProximo = atualLista.getProximo().getMusica().getArtista();
                }

                if(valorAtual.compareToIgnoreCase(valorProximo) > 0) {
                    Musica temp = atualLista.getMusica();
                    atualLista.setMusica(atualLista.getProximo().getMusica());
                    atualLista.getProximo().setMusica(temp);
                    trocou = true;
                }
                atualLista = atualLista.getProximo();
            }
        }

        System.out.println("Playlist ordenada com sucesso!");
    }




}
