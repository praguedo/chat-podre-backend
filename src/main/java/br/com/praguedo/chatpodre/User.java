package br.com.praguedo.chatpodre;

import javax.websocket.Session;

public class User {

    private final String username;
    private final Session session;

    public User(String username, Session session) {
        this.username = username;
        this.session = session;
    }

    public String getUsername() {
        return username;
    }

    public Session getSession() {
        return session;
    }
}
