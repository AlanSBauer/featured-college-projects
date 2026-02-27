Console.WriteLine("Digite o primeiro número: ");
double num1 = Convert.ToDouble(Console.ReadLine());

Console.WriteLine("Digite outro número");
double num2 = Convert.ToDouble(Console.ReadLine());

double soma = num1 + num2;
double subtracao = num1 - num2;
double multiplicacao = num1 * num2;
double divisao = num1 / num2;

Console.WriteLine("A soma de " + num1 + " e " + num2 + " é de " + soma);
Console.WriteLine($"A subtração de {num1} e {num2} é de {subtracao}");
Console.WriteLine("A multiplicação de {0} e {1} é de {2}", num1, num2, multiplicacao);
Console.WriteLine("A divisão de {0} e {1} é de {2}", num1, num2, divisao);