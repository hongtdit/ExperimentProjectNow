package rikkei.academy.service.song.playlist;

import rikkei.academy.config.Config;
import rikkei.academy.model.music.song.PlayList;

import java.util.List;

public class PlayListServiceIMPL implements IPlayListService{
    private static final String PATH_FILE_PLAYLIST ="src/rikkei/academy/data/song/playlist.txt";
    private static List<PlayList> playList = new Config<PlayList>().readFile(PATH_FILE_PLAYLIST);
    @Override
    public List<PlayList> findAll() {
        new Config<PlayList>().writeFile(PATH_FILE_PLAYLIST,playList);
        return playList;
    }

    @Override
    public void save(PlayList playList1) {
        playList.add(playList1);
        new Config<PlayList>().writeFile(PATH_FILE_PLAYLIST,playList);
    }

    @Override
    public void remove(int id) {
        for (int i = 0; i < playList.size(); i++) {
            if (id==playList.get(i).getId()){
                playList.remove(i);
            }

        }

    }

    @Override
    public PlayList findById(int id) {
        for (int i = 0; i < playList.size(); i++) {
            if (id==playList.get(i).getId()){
                return playList.get(i);
            }

        }
        return null;
    }
}
