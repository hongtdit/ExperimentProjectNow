package rikkei.academy.controller.singer;

import rikkei.academy.model.music.singer.Singer;
import rikkei.academy.model.music.song.Category;
import rikkei.academy.service.singer.singer.ISingerService;
import rikkei.academy.service.singer.singer.SingerServiceIMPL;

import java.util.ArrayList;
import java.util.List;

public class SingerController {
    ISingerService singerService = new SingerServiceIMPL();
    public List<Singer> showListSinger(){return singerService.findAll();}
    public void createSinger(Singer singer){singerService.save(singer);}
    public Singer detailSinger(int id){return singerService.findById(id);}
    public void updateSinger(int id, Singer newSinger){
        Singer singer = singerService.findById(id);
        singer.setName(newSinger.getName());
        singer.setName(newSinger.getName());
        singer.setSex(newSinger.getSex());
    }
    public void deleteSinger(int id){singerService.remove(id);}
    public List<Singer> searchSinger(String nameSinger) {
        List<Singer> singers = new ArrayList<>();
        for (Singer singer : singerService.findAll()) {
            if (singer.getName().toLowerCase().contains(nameSinger.toLowerCase())) {
                singers.add(singer);
            }
        }
        return singers;
    }
}
