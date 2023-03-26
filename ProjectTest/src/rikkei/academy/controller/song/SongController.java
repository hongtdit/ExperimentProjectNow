package rikkei.academy.controller.song;

import rikkei.academy.controller.account.AccountController;
import rikkei.academy.model.music.singer.Singer;
import rikkei.academy.model.music.song.Song;
import rikkei.academy.service.song.category.ICategoryService;
import rikkei.academy.service.song.song.ISongService;
import rikkei.academy.service.song.song.SongServiceIMPL;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SongController {
    private ISongService songService = new SongServiceIMPL();
    private AccountController accountController = new AccountController();
    public List<Song> showSongList(){return songService.findAll();}
    public void createSong(Song song){songService.save(song);}
    public Song detailSong(int id){return songService.findById(id);}

    public void  updateSong(int id, Song newSong){
        Song song = songService.findById(id);
        song.setId(newSong.getId());
        song.setName(newSong.getName());
        song.setSingerList(newSong.getSingerList());
    }
    public void deleteSong(int id){songService.remove(id);}
    public List<Song> searchSong(String newSong){
        List<Song> songList = new ArrayList<>();
        for (Song song: songService.findAll()) {
            if (song.getName().toLowerCase().contains(newSong.toLowerCase())||song.getCategory().getName().toLowerCase().contains(newSong.toLowerCase())||song.getSingerList().stream().map(Singer::getName).collect(Collectors.toList()).toString().toLowerCase().contains(newSong.toLowerCase())){
                songList.add(song);
            }
        }
        return songList;
    }
    public List<Song> getUserPlaySong(){
        List<Song> songList = new ArrayList<>();
        for (Song song: songService.findAll()) {
            if (song.getIdUser() == accountController.getCurrenAccount().getId()) {
                songList.add(song);
            }
        }
        return songList;
    }


}
