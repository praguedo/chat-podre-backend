package br.com.praguedo.chatpodre;

public class Message {

    private final String message;
    private final String workspace;
    private final String username;

    public Message(String message, String workspace, String username) {
        this.message = message;
        this.workspace = workspace;
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public String getWorkspace() {
        return workspace;
    }

    public String getUsername() {
        return username;
    }
}
