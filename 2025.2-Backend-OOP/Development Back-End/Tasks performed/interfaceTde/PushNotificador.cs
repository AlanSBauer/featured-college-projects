public class PushNotificador : INotificador
{
    public void EnviarNotificacao(string destinatario, string assunto, string mensagem)
    {
        Console.WriteLine($"PUSH - Enviando push notification para {destinatario}");
        Console.WriteLine($"Mensagem: {mensagem}");
        Console.WriteLine("Push enviado com sucesso!\n");
    }

    public bool PodeEnviar(string destinatario)
    {
        return !string.IsNullOrWhiteSpace(destinatario);
    }

    public string ObterTipoNotificacao()
    {
        return "Push";
    }
}