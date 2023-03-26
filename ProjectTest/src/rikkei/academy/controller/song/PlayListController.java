package rikkei.academy.controller.song;

import rikkei.academy.model.music.song.Category;
import rikkei.academy.model.music.song.PlayList;
import rikkei.academy.service.song.playlist.IPlayListService;
import rikkei.academy.service.song.playlist.PlayListServiceIMPL;

import java.util.ArrayList;
import java.util.List;

public class PlayListController {
    private IPlayListService playListService = new PlayListServiceIMPL();


    public List<PlayList> showPlayList(){
        return playListService.findAll(); }
    public void createPlayList(PlayList playList){playListService.save(playList);}
    public PlayList detailPlayList(int id){return  playListService.findById(id);}
    public void updatePlayPlist(int id , PlayList newPlayList){
        PlayList playList = playListService.findById(id);
        playList.setId(newPlayList.getId());
        playList.setName(newPlayList.getName());
        playList.setSongList(newPlayList.getSongList());
    }
    public void deletePlayList(int id){playListService.remove(id);}
    public List<PlayList> searchPlayList(String namePlaylist){
        List<PlayList> playLists = new ArrayList<>();
        for (PlayList playlist : playListService.findAll()) {
            if (playlist.getName().toLowerCase().contains(namePlaylist.toLowerCase())){
                playLists.add(playlist);
            }
        }
        return playLists;
    }
}
