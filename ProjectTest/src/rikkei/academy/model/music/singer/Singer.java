package rikkei.academy.model.music.singer;

import rikkei.academy.model.music.song.Song;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Singer implements Serializable {
    private  int id;
    private String name;
    private String sex;
    private List<Song> song = new ArrayList<>();
    private int age;

    public Singer() {
    }

    public Singer(int id, String name, String sex, int age, List<Song> song) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.song = song;

    }

    public Singer(int id, String name, String gender, String age) {
        this.id = id;
        this.name = name;
        this.sex = gender;
        this.age = Integer.parseInt(age);
    }

    public List<Song> getSong() {
        return song;
    }

    public void setSong(List<Song> song) {
        this.song = song;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", song=" +
                '}';
    }
}
