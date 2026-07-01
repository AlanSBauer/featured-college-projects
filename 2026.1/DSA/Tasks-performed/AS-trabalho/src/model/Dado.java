package model;

public class Dado {
    public int rolar() {
        return (int)(Math.random() * 6) + 1;
    }

    public int rolarDados() {
        return  rolar() + rolar();
    }
}
