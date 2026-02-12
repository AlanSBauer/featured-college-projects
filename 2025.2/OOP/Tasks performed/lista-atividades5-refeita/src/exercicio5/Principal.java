package exercicio5;

import java.util.ArrayList;
import java.util.Comparator;

public class Principal {
    public static void main(String[] args) {
        ArrayList<Midia> feed = new ArrayList<>();

        Podcast podcast = new Podcast();
        Video video = new Video();
        ArtigoNarrado artigoNarrado = new ArtigoNarrado();

        podcast.setAutor("Autor do podcast");
        podcast.setUrl("www.podcast123.com.br");
        podcast.setTitulo("Titulo do podcast");
        podcast.setDuracao(1500);

        video.setAutor("Autor do video");
        video.setTitulo("Titulo do video");
        video.setUrl("www.video.com.br");
        video.setDuracao(1800);

        artigoNarrado.setAutor("Autor do artigo");
        artigoNarrado.setTitulo("Titulo do artigo");
        artigoNarrado.setUrl("www.artigo.com.br");
        artigoNarrado.setDuracao(2500);

        feed.add(artigoNarrado);
        feed.add(podcast);
        feed.add(video);

        feed.sort(Comparator.comparing(Midia::duracao));

        System.out.println("--- Feed ---");

        for(Midia midia : feed) {
            System.out.println(midia);
            midia.reproduzir();
            System.out.println("Duração: " + midia.duracao() + " segundos\n");
        }

    }
}
