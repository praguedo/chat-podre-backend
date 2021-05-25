package br.com.praguedo.chatpodre;

import java.util.Map;

import javax.websocket.SendHandler;

/**
 * Classe responsavel por enviar a mensagem aos usuarios.
 */
public class MessageBroadcast {

    private MessageBroadcast() {}

    /**
     * Envia a mensagem para todos os usuarios que estao no mesmo workspace.
     * @param sessionByWorkspace sessoes dos usuarios.
     * @param message mensagem.
     */
    public static void send(Map<String, User> sessionByWorkspace, Message message) {
        final String formattedMessage = message.getUsername() + ":" + message.getMessage();

        sessionByWorkspace.values()
                .forEach(s -> s.getSession().getAsyncRemote().sendObject(formattedMessage, handlerErrorMessage()));
    }

    private static SendHandler handlerErrorMessage() {
        return result -> {
            if (result.getException() != null) {
                result.getException().printStackTrace();
            }
        };
    }

}
