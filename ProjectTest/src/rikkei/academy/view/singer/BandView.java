package rikkei.academy.view.singer;

import rikkei.academy.atherfuntion.Menu;
import rikkei.academy.config.Config;
import rikkei.academy.controller.singer.BandController;
import rikkei.academy.controller.singer.SingerController;
import rikkei.academy.controller.song.PlayListController;
import rikkei.academy.model.music.singer.Band;
import rikkei.academy.model.music.singer.Singer;
import rikkei.academy.model.music.song.PlayList;
import rikkei.academy.model.music.song.Song;
import rikkei.academy.view.Main;
import rikkei.academy.view.viewAccount.LoginView;
import rikkei.academy.view.viewAccount.ViewAfterSignIn;
import rikkei.academy.view.viewsong.ViewPlaySong;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class BandView {
    private BandController bandController = new BandController();
    private List<Band> bandList = bandController.showListBand();
    private SingerController singerController = new SingerController();
    private PlayListController playListController = new PlayListController();
    public void createBand(){
        int id;
        while (true){
            if (bandList.size() == 0){
                id = 1;
            }else {
                id = bandList.get(bandList.size()-1).getId()+1;
            }
            System.out.println("Enter Band name to create");
            String name = Config.scanner().nextLine();
            List<Singer> singerList =new ArrayList<>();
            while (true){
                List<Singer> singers = searchSingerList();
                System.out.println(singers);
                System.out.println("Enter id singer to add band or enter 0 not add band");
                int idSinger = Config.getIntegerInput();
                if (idSinger==0){
                    break;
                }
                Singer singer = singerController.detailSinger(idSinger);
                System.out.println(singer);
                singerList.add(singer);
            }
            List<PlayList> playList = new ArrayList<>();
            while (true){
                List<PlayList> playLists = searchPlaylistA();
                System.out.println(playLists);
                System.out.println("Enter id play list to add band or enter 0 to not add band");
                int idPlayList = Config.getIntegerInput();
                if (idPlayList==0){break;}
                PlayList playList1 = playListController.detailPlayList(idPlayList);
                System.out.println(playList1);
            }
            Band band = new Band(id,name,singerList,playList);
            bandController.createBand(band);
            System.out.println("Create success!");
            System.out.println(band);
            System.out.println("Enter quit to Exit");
            String backMenu = Config.scanner().nextLine();
            if(backMenu.equalsIgnoreCase("quit")){
                new ViewAfterSignIn().viewBand();
            }
        }
    }
    public List<Singer> searchSingerList() {
        List<Singer> listSelectSinger = new ArrayList<>();
        System.out.println("Enter name Singer to search or enter 0 to not search");
        String name = Config.scanner().nextLine();
        for (Singer singer : singerController.searchSinger(name)
        ) {
            listSelectSinger.add(singer);
            System.out.println(singer.getName());
        }
        return listSelectSinger;
    }
    public List<PlayList> searchPlaylistA(){
        List<PlayList> listSelectPlaylist = new ArrayList<>();
        System.out.println("Enter name PlayList to search or enter 0 to not search");
        String name = Config.scanner().nextLine();
        for (PlayList playlist : playListController.searchPlayList(name)) {
            listSelectPlaylist.add(playlist);
            System.out.println(playlist.getName());
        }
        return listSelectPlaylist;
    }
    public void deleteBand(){
        System.out.println("Enter id Band to delete");
        int id = Config.getIntegerInput();
        if (bandController.detailBand(id)==null){
            System.out.println("ID Playlist not found");
        }else {
            System.out.println("Enter 1 to delete - Enter 2 not delete");
            int choice = Config.getIntegerInput();
            switch (choice){
                case 1:
                    bandController.deleteBand(id);
                    System.out.println("Delete success!");
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Invalid not found");
                    deleteBand();
            }
            new ViewAfterSignIn().viewBand();
        }
    }
    public void updateBand(){
        System.out.println("Enter id band to update");
        int id = Config.getIntegerInput();
        if (bandController.detailBand(id)==null){
            System.out.println("id ban not found");
        }else {
            Band band = bandController.detailBand(id);
            System.out.println("Enter name Band to update");
            String name = Config.scanner().nextLine();
            if (name.trim().equals("")){
                name = band.getName();
            }
            List<Singer> singerList = new ArrayList<>();
            while (true){
                List<Singer> singers = searchSingerList();
                System.out.println(singers);
                System.out.println("Enter id singer to add band");
                int idSinger = Config.getIntegerInput();
                if (idSinger ==0){
                    break;
                }
                Singer singer = singerController.detailSinger(idSinger);
                System.out.println(singer);
            }
            while (true){
                System.out.println("Enter id singer to delete");
                int idSinger = Config.getIntegerInput();
                if (idSinger!=0){
                    band.getSingerList().remove(singerController.detailSinger(idSinger));
                    singerController.showListSinger();
                }else {
                    break;
                }
            }
            List<PlayList> playList = new ArrayList<>();
            while (true){
                List<PlayList> playLists = searchPlaylistA();
                System.out.println(playLists);
                System.out.println("Enter id play list to add band");
                int idPlayList = Config.getIntegerInput();
                if (idPlayList==0){break;}
                PlayList playList1 = playListController.detailPlayList(idPlayList);
                System.out.println(playList1);
            }
            while (true){
                System.out.println("Enter id play list to delete");
                int idPlayList = Config.getIntegerInput();
                if (idPlayList!=0){
                    band.getPlayList().remove(playListController.detailPlayList(idPlayList));
                    singerController.showListSinger();
                }else {
                    break;
                }
            }
            Band newBan = new Band(id,name,singerList,playList);
            bandController.updateBand(id,newBan);
            System.out.println(newBan);
            System.out.println("Update success!");
        }
        System.out.println("Enter quit to Exit");
        String backMenu = Config.scanner().nextLine();
        if(backMenu.equalsIgnoreCase("quit")){
            new ViewAfterSignIn().viewBand();
        }
    }
    public void showBand(){
        Menu menu = new Menu();
        System.out.printf("%-5s%-30s%-20s%-20s%n", "ID", "NAME", "SINGER", "PLAYLIST");
        bandList.forEach(band -> {
            System.out.printf("%-5s%-30s%-20s%-20s%n", band.getId(), band.getName(),band.getSingerList().stream().map(Singer::getName).collect(Collectors.toList()), band.getPlayList().stream().map(PlayList::getName).collect(Collectors.toList()));
        });
        System.out.println("Login to continue or quit to go back to the page");
        menu.addHeader("Enter choice: ");
        menu.addChoice("1. Go to Login Account");
        menu.addChoice("2. Back Menu");
        menu.addPaddingLeft(4);
        menu.print();
        int choice = Config.getIntegerInput();
        switch (choice) {
            case 1:
                new LoginView().menuLogin();
                break;
            case 2:
                new Main().menu();
                break;
            default:
                System.out.println("Invalid not found");
                showBand();
        }
    }
    public void showBandSignIn() {
        System.out.printf("%-5s%-30s%-20s%-20s%n", "ID", "NAME", "SINGER", "PLAYLIST");
        bandList.forEach(band -> {
            System.out.printf("%-5s%-30s%-20s%-20s%n", band.getId(), band.getName(),band.getSingerList().stream().map(Singer::getName).collect(Collectors.toList()), band.getPlayList().stream().map(PlayList::getName).collect(Collectors.toList()));
        });
        new ViewPlaySong().playSongSignIn();
    }
    public void searchBand(){
        System.out.println("Enter name band to search");
        String name = Config.scanner().nextLine();
        for (Band band: bandController.searchBand(name)){
            System.out.println(band.getName());
        }
        new ViewAfterSignIn().viewBand();
    }
}
