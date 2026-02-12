using System;
using System.Collections.Generic;

class Program
{
    static void Main(string[] args)
    {
        Console.WriteLine("=== Sistema de Notificações ===\n");


        var emailNotificador = new EmailNotificador();
        var smsNotificador = new SMSNotificador();
        var pushNotificador = new PushNotificador();


        var notificadores = new List<INotificador> { emailNotificador, smsNotificador, pushNotificador };
        var servicoNotificacao = new ServicoNotificacao(notificadores);


        servicoNotificacao.EnviarNotificacaoPorCanal("E-mail", "usuario@exemplo.com", "Boas-vindas", "Bem-vindo ao sistema!");
        servicoNotificacao.EnviarNotificacaoPorCanal("E-mail", "usuarioexemplo.com", "Falha", "E-mail inválido!");

        servicoNotificacao.EnviarNotificacaoPorCanal("SMS", "12345678", "Código", "Seu código é 9876");
        servicoNotificacao.EnviarNotificacaoPorCanal("SMS", "12a456", "Erro", "Número inválido!");

        servicoNotificacao.EnviarNotificacaoPorCanal("Push", "user123", "Aviso", "Nova atualização disponível");
        servicoNotificacao.EnviarNotificacaoPorCanal("Push", "", "Erro", "Destinatário inválido!");


        Console.WriteLine("\n=== Enviando por todos os canais ===");
        servicoNotificacao.EnviarNotificacaoTodosCanais("usuario@exemplo.com", "Alerta", "Mensagem geral para todos os canais.");

        Console.ReadLine();
    }
}
