package Classes;

public class Calculadora {
    public float somar(float a, float b) {
        return a + b;
    }

    public float subtrair(float a, float b) {
        return a - b;
    }

    public float multiplicar(float a, float b) {
        return a * b;
    }

    public float dividir(float a, float b) {
        if(b == 0) {
            System.out.println("Não é possível divisão por 0.");
            return Float.NaN;
        } else {
            return a / b;
        }
    }
}
