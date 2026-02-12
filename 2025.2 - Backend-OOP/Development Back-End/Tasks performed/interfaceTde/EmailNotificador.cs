public class EmailNotificador : INotificador
{
    public void EnviarNotificacao(string destinatario, string assunto, string mensagem)
    {
        Console.WriteLine($"E-MAIL - Enviando e-mail para {destinatario}");
        Console.WriteLine($"Assunto: {assunto}");
        Console.WriteLine($"Mensagem: {mensagem}");
        Console.WriteLine("E-mail enviado com sucesso!\n");
    }

    public bool PodeEnviar(string destinatario)
    {
        return destinatario.Contains("@");
    }

    public string ObterTipoNotificacao()
    {
        return "E-mail";
    }
}