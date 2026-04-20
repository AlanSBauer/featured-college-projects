package ex5;

public class Principal {
    public static void main(String[] args) {

        Fila fila = new Fila(10);

        fila.enfileirar(new Documento("Relatorio.pdf", 10));
        fila.enfileirar(new Documento("Trabalho.docx", 5));
        fila.enfileirar(new Documento("Livro.pdf", 50));
        fila.enfileirar(new Documento("Slide.ppt", 20));
        fila.enfileirar(new Documento("Resumo.txt", 3));

        int totalPaginas = 0;
        Documento maiorDocumento = null;
        int contador = 0;

        while (!fila.estaVazia()) {

            Documento atual = fila.desenfileirar();
            contador++;

            System.out.println("Imprimindo: " + atual);

            totalPaginas += atual.getPaginas();

            if (maiorDocumento == null || atual.getPaginas() > maiorDocumento.getPaginas()) {
                maiorDocumento = atual;
            }

            if (contador % 2 == 0 && !fila.estaVazia()) {
                System.out.println("Próximo da fila: " + fila.espiar());
            }

            System.out.println("----------------------");
        }

        System.out.println("\n=== RESUMO ===");
        System.out.println("Maior documento: " + maiorDocumento);
        System.out.println("Total de páginas impressas: " + totalPaginas);
    }
}
