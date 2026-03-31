# Manipulação de Matrizes e Algoritmos de Ordenação em Java

## 🔹 Classe Matriz

Responsável por representar a matriz bidimensional.

**Atributos:**

* `int[][] matriz`

**Métodos principais:**

* Preenchimento manual: `preencherMatriz(int lin, int col, int value)` (passando valores)
* Preenchimento automático (valores aleatórios): `preencherMatriz()` (sem parâmetros)
* Exibição da matriz: `exibirMatriz()`
* Getters (linhas, colunas e matriz)
* Setter para a matriz

---

## 🔹 Classes de Algoritmos

Foram criadas duas classes: `BubbleSort` e `MergeSort`, cada uma responsável por um tipo de ordenação.

* **BubbleSort:** responsável por ordenar por linhas e por colunas.
* **MergeSort:** responsável por ordenar a matriz completa.

---

## 🔹 Classe Ordenacao

Responsável por centralizar as opções de ordenação.

**Método:**

* `ordenar(matriz, metodo)`

**Opções (via switch case):**

* `"linha"`
* `"coluna"`
* `"completa"`

---

## 🔹 Classe Main

Utilizada para realizar os testes do sistema.

**Testes realizados:**

* Criação de matriz via construtor (informando quantidade de linhas e colunas)
* Matriz 1 preenchida manualmente
* Matriz 2 preenchida automaticamente
* Ordenações da matriz 2 por linha, coluna e completa
