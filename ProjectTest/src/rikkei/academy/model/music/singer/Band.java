package rikkei.academy.model.music.singer;

import rikkei.academy.model.music.song.PlayList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Band implements Serializable {
    private int id;
    private String name;
    private List<Singer> singerList = new ArrayList<>();
    private List<PlayList> playList = new ArrayList<>();

    public Band() {
    }

    public Band(int id, String name, List<Singer> singerList,List<PlayList> playList) {
        this.id = id;
        this.name = name;
        this.singerList = singerList;
        this.playList = playList;
    }

    public List<PlayList> getPlayList() {
        return playList;
    }

    public void setPlayList(List<PlayList> playList) {
        this.playList = playList;
    }

    public List<Singer> getSingerList() {
        return singerList;
    }

    public void setSingerList(List<Singer> singerList) {
        this.singerList = singerList;
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
        return "Band{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", singerList=" + singerList +
                '}';
    }
}
