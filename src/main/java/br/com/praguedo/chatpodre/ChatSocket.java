package br.com.praguedo.chatpodre;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat/{workspace}/{username}")
@ApplicationScoped
public class ChatSocket {

    private final Map<String, User> sessionByWorkspace = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("workspace") String workspace, @PathParam("username") String username) {
        this.sessionByWorkspace.put(workspace, new User(username, session));
    }

    @OnClose
    public void onClose(Session session, @PathParam("workspace") String workspace, @PathParam("username") String username) {
        this.sessionByWorkspace.remove(workspace);
    }

    @OnError
    public void onError(Session session, @PathParam("workspace") String workspace, @PathParam("username") String username, Throwable throwable) {
        this.sessionByWorkspace.remove(workspace);
    }

    @OnMessage
    public void onMessage(String message, @PathParam("workspace") String workspace, @PathParam("username") String username) {
        MessageBroadcast.send(this.sessionByWorkspace, new Message(message, workspace, username));
    }


}