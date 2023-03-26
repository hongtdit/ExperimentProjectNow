package rikkei.academy.model.live_comment;

import java.io.Serializable;

public class Comment implements Serializable {
    private int id;
    private int idSong;
    private String nameUser;
    private String content;

    public Comment() {
    }

    public Comment(int id, int idSong, String nameUser, String content) {
        this.id = id;
        this.idSong = idSong;
        this.nameUser = nameUser;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSong() {
        return idSong;
    }

    public void setIdSong(int idSong) {
        this.idSong = idSong;
    }

    public String getIdUser() {
        return nameUser;
    }

    public void setIdUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", idSong=" + idSong +
                ", idUser=" + nameUser +
                ", content='" + content + '\'' +
                '}';
    }
}
