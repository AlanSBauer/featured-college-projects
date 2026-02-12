public interface INotificador
{
    void EnviarNotificacao(string destinatario, string assunto, string mensagem);
    bool PodeEnviar(string destinatario); 
    string ObterTipoNotificacao();
}