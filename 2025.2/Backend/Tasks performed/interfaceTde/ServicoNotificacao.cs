using System;
using System.Collections.Generic;
using System.Linq;

public class ServicoNotificacao
{
    private List<INotificador> _notificadores;

    public ServicoNotificacao(List<INotificador> notificadores)
    {
        _notificadores = notificadores;
    }


    public void EnviarNotificacaoTodosCanais(string destinatario, string assunto, string mensagem)
    {
        foreach (var notificador in _notificadores)
        {
            if (notificador.PodeEnviar(destinatario))
            {
                notificador.EnviarNotificacao(destinatario, assunto, mensagem);
            }
            else
            {
                Console.WriteLine($"[ERRO] Não foi possível enviar via {notificador.ObterTipoNotificacao()} — destinatário inválido.\n");
            }
        }
    }

    public bool EnviarNotificacaoPorCanal(string tipoCanal, string destinatario, string assunto, string mensagem)
    {
        var notificador = _notificadores.FirstOrDefault(n =>
            n.ObterTipoNotificacao().Equals(tipoCanal, StringComparison.OrdinalIgnoreCase));

        if (notificador == null)
        {
            Console.WriteLine($"Canal '{tipoCanal}' não encontrado.\n");
            return false;
        }

        if (notificador.PodeEnviar(destinatario))
        {
            notificador.EnviarNotificacao(destinatario, assunto, mensagem);
            return true;
        }
        else
        {
            Console.WriteLine($"[ERRO] Destinatário inválido para o canal {tipoCanal}.\n");
            return false;
        }
    }
}
