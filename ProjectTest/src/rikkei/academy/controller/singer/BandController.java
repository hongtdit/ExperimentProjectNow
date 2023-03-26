package rikkei.academy.controller.singer;

import rikkei.academy.model.music.singer.Band;
import rikkei.academy.service.singer.band.BandServiceIMPL;
import rikkei.academy.service.singer.band.IBandService;

import java.util.ArrayList;
import java.util.List;

public class BandController {
    IBandService bandService = new BandServiceIMPL();
    public List<Band> showListBand(){return bandService.findAll();}
    public void createBand(Band band){bandService.save(band);}
    public Band detailBand(int id){return bandService.findById(id);}
    public void deleteBand(int id){bandService.remove(id);}
    public void updateBand(int id,Band newBand){
        Band band = bandService.findById(id);
        band.setId(newBand.getId());
        band.setName(newBand.getName());
        band.setSingerList(newBand.getSingerList());
        band.setPlayList(newBand.getPlayList());
    }
    public List<Band> searchBand(String searchBand){
        List<Band> bandList = new ArrayList<>();
        for (Band band : bandService.findAll()) {
            if (band.getName().toLowerCase().contains(searchBand.toLowerCase())) {
                bandList.add(band);
            }
        }
        return bandList;
    }
}
