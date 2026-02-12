double saldo = 1000.00;
int opcao;

Console.WriteLine("Bem vindo ao nosso sistema");

do
{
    Console.WriteLine("Digite a opção desejada: ");
    Console.WriteLine("1 - Ver saldo");
    Console.WriteLine("2 - Sacar");
    Console.WriteLine("3 - Depositar");
    Console.WriteLine("4 - Sair");

    opcao = Convert.ToInt32(Console.ReadLine());
    Console.WriteLine();

    switch (opcao)
    {
        case 1:
            Console.WriteLine($"Seu saldo é de: R${saldo}");
            Console.WriteLine();
            break;
        case 2:
            Console.WriteLine("Qual o valor do saque? ");
            double valorSaque = Convert.ToDouble(Console.ReadLine());
            Console.WriteLine();
            if (valorSaque > saldo)
            {
                Console.WriteLine("Saldo Insuficiente");
            }
            else
            {
                saldo -= valorSaque;
                Console.WriteLine($"Você sacou {valorSaque}");
            }
            break;
        case 3:
            Console.WriteLine("Qual o valor do depósito? ");
            double valorDeposito = Convert.ToDouble(Console.ReadLine());
            Console.WriteLine();
            saldo += valorDeposito;
            break;
        case 4:
            Console.WriteLine("Sistema Finalizado!");
            break;
        default:
            Console.WriteLine("Digita uma opção válida!");
            break;
    }
} while (opcao != 4);
