package rikkei.academy.dto.reponse;

public class ResponseMessenger {
    private String messenger;

    public ResponseMessenger() {
    }

    public ResponseMessenger(String messenger) {
        this.messenger = messenger;
    }

    public String getMessenger() {
        return messenger;
    }

    public void setMessenger(String messenger) {
        this.messenger = messenger;
    }
}
