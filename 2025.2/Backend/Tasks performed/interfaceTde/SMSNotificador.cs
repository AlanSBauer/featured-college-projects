public class SMSNotificador : INotificador
{
    public void EnviarNotificacao(string destinatario, string assunto, string mensagem)
    {
        Console.WriteLine($"SMS - Enviando SMS para {destinatario}");
        Console.WriteLine($"Mensagem: {mensagem}");
        Console.WriteLine("SMS enviado com sucesso!\n");
    }

    public bool PodeEnviar(string destinatario)
    {
        return destinatario.All(char.IsDigit) && destinatario.Length >= 8;
    }

    public string ObterTipoNotificacao()
    {
        return "SMS";
    }
}