package rikkei.academy.model.music.song;

import rikkei.academy.model.music.singer.Band;
import rikkei.academy.model.music.singer.Singer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Song implements Serializable {
    private int id;
    private String name;
    private int idUser;
    private List<Singer> singerList = new ArrayList<>();
    private Category category;
    private int listen;

    public Song() {
    }


    public Song(int id, String name, int idUser, List<Singer> singerList, Category category, int listen) {
        this.id = id;
        this.name = name;
        this.idUser = idUser;
        this.singerList = singerList;
        this.category = category;
        this.listen = listen;
    }

    public Song(int id, String name, List<Singer> singerList, Category category, int listen) {
        this.id = id;
        this.name = name;
        this.singerList = singerList;
        this.category = category;
        this.listen = listen;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Song(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Singer> getSingerList() {
        return singerList;
    }

    public void setSingerList(List<Singer> singerList) {
        this.singerList = singerList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getListen() {
        return listen;
    }

    public void setListen(int listen) {
        this.listen = listen;
    }


    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", singerList=" + singerList +
                ", categoryList=" + category +
                ", listen=" + listen +
                '}';
    }
}
