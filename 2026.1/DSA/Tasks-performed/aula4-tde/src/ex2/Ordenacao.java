package ex2;

public class Ordenacao {
    public void blubbleSortSalario(Funcionario[] funcionarios, boolean isDesc) {
        int n = funcionarios.length;

        for(int i = 0; i < n - 1; i++) {
            for(int j = 0; j < n - 1 - i; j++) {
                if(funcionarios[j].getSalario() > funcionarios[j + 1].getSalario() && !isDesc) {
                    Funcionario temp = funcionarios[j];
                    funcionarios[j] = funcionarios[j + 1];
                    funcionarios[j + 1] = temp;
                }
                if(funcionarios[j].getSalario() < funcionarios[j + 1].getSalario() && isDesc) {
                    Funcionario temp = funcionarios[j];
                    funcionarios[j] = funcionarios[j + 1];
                    funcionarios[j + 1] = temp;
                }
            }
        } 
    }

    public void blubbleSortOrdemAlfabetica(Funcionario[] funcionarios) {
        int n = funcionarios.length;
        for(int i = 0; i < n - 1; i++) {
            for(int j = 0; j < n - 1 - i; j++) {
                if(funcionarios[j].getName().compareTo(funcionarios[j + 1].getName()) > 0) {
                    Funcionario temp = funcionarios[j];
                    funcionarios[j] = funcionarios[j + 1];
                    funcionarios[j + 1] = temp;
                }
            }
        }
    }
}
