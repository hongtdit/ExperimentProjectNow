package rikkei.academy.service.singer.singer;

import rikkei.academy.config.Config;
import rikkei.academy.model.music.singer.Band;
import rikkei.academy.model.music.singer.Singer;

import java.util.ArrayList;
import java.util.List;

public class SingerServiceIMPL implements ISingerService{
    public static final String PATH_FILE_SINGER= "src/rikkei/academy/data/singer/singer.txt";
    public static List<Singer> singerList =new Config<Singer>().readFile(PATH_FILE_SINGER);

    @Override
    public List<Singer> findAll() {
        new Config<Singer>().writeFile(PATH_FILE_SINGER,singerList);
        return singerList;
    }

    @Override
    public void save(Singer singer) {
        singerList.add(singer);
        new Config<Singer>().writeFile(PATH_FILE_SINGER,singerList);


    }

    @Override
    public void remove(int id) {
        for (int i = 0; i < singerList.size(); i++) {
            if (id==singerList.get(i).getId()){
                singerList.remove(i);
            }

        }

    }

    @Override
    public Singer findById(int id) {
        for (int i = 0; i < singerList.size(); i++) {
            if (id == singerList.get(i).getId()){
                return singerList.get(i);
            }
        }
        return null;
    }

}
