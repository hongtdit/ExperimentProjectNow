package rikkei.academy.service.singer.band;

import rikkei.academy.config.Config;
import rikkei.academy.model.account.Account;
import rikkei.academy.model.music.singer.Band;

import java.util.ArrayList;
import java.util.List;

public class BandServiceIMPL implements IBandService {
    public static final String PATH_FILE_BAND = "src/rikkei/academy/data/singer/band.txt";
    public static List<Band> bandList = new Config<Band>().readFile(PATH_FILE_BAND);
    static {
        if (bandList== null){
            bandList = new ArrayList<>();
        }
    }

    @Override
    public List<Band> findAll() {
        new Config<Band>().writeFile(PATH_FILE_BAND, bandList);
        return bandList;
    }

    @Override
    public void save(Band band) {
        bandList.add(band);
        new Config<Band>().writeFile(PATH_FILE_BAND,bandList);
    }

    @Override
    public void remove(int id) {
        for (int i = 0; i < bandList.size(); i++) {
            if (id == bandList.get(i).getId()) {
                bandList.remove(id);
            }
        }
    }

    @Override
    public Band findById(int id) {
        for (int i = 0; i < bandList.size(); i++) {
            if (id == bandList.get(i).getId()) {
                return bandList.get(i);
            }
        }
        return null;
    }
}


