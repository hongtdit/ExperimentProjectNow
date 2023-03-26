package rikkei.academy.view.viewsong;

import rikkei.academy.atherfuntion.Menu;
import rikkei.academy.config.Config;
import rikkei.academy.controller.song.PlayListController;
import rikkei.academy.controller.song.SongController;
import rikkei.academy.model.music.singer.Singer;
import rikkei.academy.model.music.song.PlayList;
import rikkei.academy.model.music.song.Song;
import rikkei.academy.service.song.song.SongServiceIMPL;
import rikkei.academy.view.Main;
import rikkei.academy.view.viewAccount.LoginView;
import rikkei.academy.view.viewAccount.ViewAfterSignIn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ViewPlayList {
    private PlayListController playListController = new PlayListController();
    private List<PlayList> playLists = playListController.showPlayList();
    private SongController songController = new SongController();
    public void createPlayList(){
        int id;
        while (true){
            if (playLists.size()==0){
                id = 1;
            }else {
                id = playLists.get(playLists.size()-1).getId()+1;
            }
            System.out.println("Enter name Play List");
            String name = Config.scanner().nextLine();
            List<Song> songListAdd = new ArrayList<>();
            while (true){
                List<Song> songListSearch = searchSong();
                System.out.println(songListSearch);
                System.out.println("Enter id song to add Playlist - Enter 0 to exit");
                int idSong = Config.getIntegerInput();
                if (idSong==0){
                    break;
                }
                Song add = songController.detailSong(idSong);
                System.out.println(add);
                songListAdd.add(add);
            }
            PlayList playList = new PlayList(id,name,songListAdd);
            playListController.createPlayList(playList);
            System.out.println("Create success!");
            System.out.println(playList);
            System.out.println("Enter quit to Exit");
            String backMenu = Config.scanner().nextLine();
            if(backMenu.equalsIgnoreCase("quit")){
                new ViewAfterSignIn().viewPlaylist();
            }
        }
    }
    public List<Song> searchSong(){
        List<Song> songList = new ArrayList<>();
        System.out.println("Enter song name to search");
        String name = Config.scanner().nextLine();
        for (Song song: songController.searchSong(name)) {
            songList.add(song);
            System.out.println(songList);
        }
        return songList;
    }
    public void deletePlayList(){
        System.out.println("Enter id Play List");
        int id = Config.getIntegerInput();
        if (playListController.detailPlayList(id) == null) {
            System.out.println("Id Playlist not found");
        } else {
            System.out.println("Enter 1 to delete Playlist - Enter 2 not to delete");
            int choice = Config.getIntegerInput();
            switch (choice) {
                case 1:
                    playListController.deletePlayList(id);
                    System.out.println("Delete successfully");
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Invalid not found");
                    deletePlayList();
            }
        }
    }
    public void updatePlayList(){
        System.out.println("Enter id Playlist to update");
        int id = Config.getIntegerInput();
        if (playListController.detailPlayList(id) == null) {
            System.out.println("id play list not found");
        } else {
            PlayList playList = playListController.detailPlayList(id);
            System.out.println("Enter name Play list to update ");
            String name = Config.scanner().nextLine();
            if (name.trim().equals("")) {
                name = playList.getName();
            }
            List<Song> songList = new ArrayList<>();
            while (true){
                List<Song> songListUpdate = searchSong();
                System.out.println(songListUpdate);
                System.out.println("Enter id song to add Playlist - Enter 0 to exit");
                int idSong = Config.getIntegerInput();
                if (idSong==0){
                    break;
                }
                Song add = songController.detailSong(idSong);
                System.out.println(add);
            }
            while (true) {
                System.out.println("Enter id song to delete");
                int idSong = Config.getIntegerInput();
                if (idSong != 0) {
                    playList.getSongList().remove(songController.detailSong(idSong));
                    playListController.showPlayList();
                }
                else {
                    break;
                }
            }
            PlayList newPlaylist = new PlayList(name,songList);
            playListController.updatePlayPlist(id,newPlaylist);
            System.out.println(newPlaylist);
            System.out.println("Update success!");
        }
        System.out.println("Enter quit to Exit");
        String backMenu = Config.scanner().nextLine();
        if(backMenu.equalsIgnoreCase("quit")){
            new ViewAfterSignIn();
        }
    }
    public void showPlayList(){
        Menu menu = new Menu();
        System.out.printf("%-5s%-30s%-20s%n", "ID", "NAME", "SONG");
        playLists.forEach(playList -> {
            System.out.printf("%-5s%-30s%-20s%n", playList.getId(), playList.getName(),playList.getSongList().stream().map(Song::getName).collect(Collectors.toList()));
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
                showPlayList();
        }
    }
    public void showPlayListSignIn(){
        System.out.printf("%-5s%-30s%-20s%n", "ID", "NAME", "SONG");
        playLists.forEach(playList -> {
            System.out.printf("%-5s%-30s%-20s%n", playList.getId(), playList.getName(),playList.getSongList().stream().map(Song::getName).collect(Collectors.toList()));
        });
        new ViewPlaySong().playSongSignIn();
    }
    public void searchPlayList(){
        System.out.println("Enter name play list to search");
        String playList = Config.scanner().nextLine();
        for (PlayList playLista: playListController.searchPlayList(playList)) {
            System.out.println(playLista.getName());
        }
        new ViewAfterSignIn().viewPlaylist();
    }
}
