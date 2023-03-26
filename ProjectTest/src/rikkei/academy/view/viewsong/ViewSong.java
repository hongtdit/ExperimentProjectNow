package rikkei.academy.view.viewsong;

import rikkei.academy.atherfuntion.Menu;
import rikkei.academy.config.Config;
import rikkei.academy.controller.singer.SingerController;
import rikkei.academy.controller.song.CategoryController;
import rikkei.academy.controller.song.CommentController;
import rikkei.academy.controller.song.LikeController;
import rikkei.academy.controller.song.SongController;
import rikkei.academy.model.music.singer.Singer;
import rikkei.academy.model.music.song.Category;
import rikkei.academy.model.music.song.Song;
import rikkei.academy.view.Main;
import rikkei.academy.view.viewAccount.LoginView;
import rikkei.academy.view.viewAccount.ViewAfterSignIn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public  class ViewSong {
    private LikeController likeController = new LikeController();
    private CommentController commentController = new CommentController();
    private SongController songController = new SongController();
    private List<Song> songs = songController.showSongList();
    private CategoryController categoryController = new CategoryController();
    private SingerController singerController = new SingerController();
    private List<Singer> singerList = singerController.showListSinger();

    public void createSong() {
        int id;
        while (true) {
            if (songs.size() == 0) {
                id = 1;
            } else {
                id = songs.get(songs.size() - 1).getId() + 1;
            }
            System.out.println("Enter name Song to add");
            String name = Config.scanner().nextLine();
            List<Singer> singerList1 = searchSingerList();
            System.out.println(singerList1);
            System.out.println("Enter id Singer to add Song");
            int id1 = Config.getIntegerInput();
            List<Singer> singerList2 = new ArrayList<>();
            Singer e = singerController.detailSinger(id1);
            System.out.println(e);
            singerList2.add(e);
            List<Category> categories = searchCategoryList();
            System.out.println(categories);
            System.out.println("Enter id Category to add Song");
            int id2 = Config.getIntegerInput();
            Category categories1 = categoryController.detailCategory(id2);
            Song song = new Song(id, name, singerList2, categories1, 0);
            songController.createSong(song);
            e.getSong().add(song);
            singerController.showListSinger();
            System.out.println(song);
            System.out.println(e);
            System.out.println("Enter any key to continue - Enter quit to Exit");
            String backMenu = Config.scanner().nextLine();
            if(backMenu.equalsIgnoreCase("quit")){
                new ViewAfterSignIn().viewSong();
                break;
            }
        }
    }

    public List<Singer> searchSingerList() {
        List<Singer> listSelectSinger = new ArrayList<>();
        System.out.println("Enter name Singer to search");
        String name = Config.scanner().nextLine();
        for (Singer singer : singerController.searchSinger(name)
        ) {
            listSelectSinger.add(singer);
            System.out.println(singer.getName());
        }

        return listSelectSinger;
    }

    public List<Category> searchCategoryList() {
        List<Category> listSelectCategory = new ArrayList<>();
        System.out.println("Enter name Category to search");
        String name = Config.scanner().nextLine();
        for (Category category : categoryController.searchCategory(name)
        ) {
            listSelectCategory.add(category);
        }
        return listSelectCategory;
    }

    public void updateSong() {
        System.out.println("Enter id song to update");
        int id = Config.getIntegerInput();
        if (songController.detailSong(id) == null) {
            System.out.println("song not found");
        } else {
            Song song = songController.detailSong(id);
            System.out.println("Enter name song to update ");
            String name = Config.scanner().nextLine();
            if (name.trim().equals("")) {
                name = song.getName();
            }
            Song newSong = new Song(name);
            songController.updateSong(id, newSong);
            System.out.println("Update success!");
            songController.showSongList();
        }
        System.out.println("Enter quit to Exit");
        String backMenu = Config.scanner().nextLine();
        if(backMenu.equalsIgnoreCase("quit")){
            new ViewAfterSignIn().viewSong();
        }
    }

    public void deleteSong() {
        System.out.println("Enter id song");
        int id = Config.getIntegerInput();
        if (songController.detailSong(id) == null) {
            System.out.println("Id song not found");
        } else {
            System.out.println("Enter 1 to delete song - Enter 2 not to delete");
            int choice = Config.getIntegerInput();
            switch (choice) {
                case 1:
                    songController.deleteSong(id);
                    System.out.println("Delete successfully");
                    break;
                case 2:
                    System.out.println("Not delete success!");
                    break;
                default:
                    System.out.println("Invalid not found");
                    deleteSong();
            }
        }
    }
    public void showSong(){
            System.out.printf("%-5s%-30s%-20s%-20s%-20s%n", "ID", "NAME", "SINGER", "CATEGORY","LISTEN");
            songs.forEach(song -> {
                System.out.printf("%-5s%-30s%-20s%-20s%-20s%n", song.getId(), song.getName(),song.getSingerList().stream().map(Singer::getName).collect(Collectors.toList()),song.getCategory().getName(),song.getListen());
            });
            new ViewPlaySong().playSongNotSignIn();
    }
    public void showSongSigIn(){
        System.out.printf("%-5s%-30s%-20s%-20s%-20s%n", "ID", "NAME", "SINGER", "CATEGORY","LISTEN");
        songs.forEach(song -> {
            System.out.printf("%-5s%-30s%-20s%-20s%-20s%n", song.getId(), song.getName(),song.getSingerList().stream().map(Singer::getName).collect(Collectors.toList()),song.getCategory().getName(),song.getListen());
        });
       new ViewPlaySong().playSongSignIn();
    }
    public void detailSong(){
        Menu menu = new Menu();
        menu.addHeader("Detail Song");
        menu.addChoice("ENTER ID SONG TO DETAIL SONG");
        menu.addPaddingLeft(10);
        menu.print();
        int idSong = Config.getIntegerInput();
        Song song = songController.detailSong(idSong);
        System.out.println(song);
        System.out.println("Enter quit to Exit");
        String backMenu = Config.scanner().nextLine();
        if(backMenu.equalsIgnoreCase("quit")){
            new ViewAfterSignIn().viewSong();
       }
   }


}