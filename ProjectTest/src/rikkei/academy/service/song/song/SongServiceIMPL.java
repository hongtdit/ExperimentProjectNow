package rikkei.academy.service.song.song;

import rikkei.academy.config.Config;
import rikkei.academy.model.music.song.Song;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SongServiceIMPL implements ISongService{
    private static final String PATH_FILE_SONG = "src/rikkei/academy/data/song/song.txt";
    private static List<Song> songList = new Config<Song>().readFile(PATH_FILE_SONG);
    @Override
    public List<Song> findAll() {
        new Config<Song>().writeFile(PATH_FILE_SONG,songList);
        return songList;
    }

    @Override
    public void save(Song song) {
        songList.add(song);
        new Config<Song>().writeFile(PATH_FILE_SONG,songList);
    }
    @Override
    public void remove(int id) {
        for (int i = 0; i < songList.size(); i++) {
            if (id == songList.get(i).getId()){
                songList.remove(i);
            }
        }

    }

    @Override
    public Song findById(int id) {
        for (int i = 0; i < songList.size(); i++) {
            if (id == songList.get(i).getId()){
               return songList.get(i);
            }
        }
        return null;
    }

}
