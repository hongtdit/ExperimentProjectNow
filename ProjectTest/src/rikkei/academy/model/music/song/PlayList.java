package rikkei.academy.model.music.song;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlayList implements Serializable {
    private int id;
    private String name;
    private List<Song> songList = new ArrayList<>();

    public PlayList() {
    }

    public PlayList(int id, String name, List<Song> songList) {
        this.id = id;
        this.name = name;
        this.songList = songList;
    }

    public PlayList(String name, List<Song> songList) {
        this.name = name;
        this.songList = songList;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
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

    @Override
    public String toString() {
        return "PlayList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", songList=" + songList +
                '}';
    }
}
