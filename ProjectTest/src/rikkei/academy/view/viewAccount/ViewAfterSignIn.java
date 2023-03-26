package rikkei.academy.view.viewAccount;

import rikkei.academy.atherfuntion.Menu;
import rikkei.academy.config.Config;
import rikkei.academy.controller.account.AccountController;
import rikkei.academy.controller.song.SongController;
import rikkei.academy.model.account.Account;
import rikkei.academy.model.account.Role;
import rikkei.academy.model.music.song.Song;
import rikkei.academy.view.singer.BandView;
import rikkei.academy.view.singer.ViewSinger;
import rikkei.academy.view.viewsong.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ViewAfterSignIn {
    private AccountController accountController = new AccountController();
    SongController songController = new SongController();
    List<Song> songs = songController.showSongList();
    Menu menu = new Menu();

    public void afterMenu() {
        menu = new Menu();
        Account account = accountController.getCurrenAccount();
        String role = null;
        Iterator<Role> iterator = account.getRoles().iterator();
        while (iterator.hasNext()) {
            role = String.valueOf(iterator.next().getName());
        }
        menu.addHeader("Welcome " + role + " " + account.getName());
        menu.addChoice("1. Search Music");
        menu.addChoice("2. Singer Manage");
        menu.addChoice("3. Band Manage");
        menu.addChoice("4. Category Manage");
        menu.addChoice("5. Song Manage");
        menu.addChoice("6. Play List Manage");
        menu.addChoice("7. My Profile Manage");
        menu.addPaddingLeft(4);
        menu.print();
        int choice = Config.getIntegerInput();
        switch (choice) {
            case 1:
                new ViewPlaySong().searchMusicSigIn();
            case 2:
                viewSinger();
                break;
            case 3:
                viewBand();
                break;
            case 4:
                viewCategory();
                break;
            case 5:
                viewSong();
                break;
            case 6:
                viewPlaylist();
                break;
            case 7:
                new ProfileView().menuView();
                break;
            default:
                System.out.println("Invalid Choice");
        }
    }

    public void viewCategory() {
        Menu menu = new Menu();
        Account account = accountController.getCurrenAccount();
        String role = null;
        Iterator<Role> iterator = account.getRoles().iterator();
        while (iterator.hasNext()) {
            role = String.valueOf(iterator.next().getName());
        }
        if (role.equals("ADMIN")) {
            menu.addHeader("Welcome " + role + " " + account.getName());
            menu.addChoice("1. Show Category");
            menu.addChoice("2. Detail Category");
            menu.addChoice("3. Add Category");
            menu.addChoice("4. Delete Category");
            menu.addChoice("5. Update Category");
            menu.addChoice("6. Back to menu");
            menu.addPaddingLeft(4);
            menu.print();
            int choice = Config.getIntegerInput();
            switch (choice) {
                case 1:
                    new ViewCategory().showCategory();
                    break;
                case 2:
                    new ViewCategory().searchCategory();
                    break;
                case 3:
                    new ViewCategory().createCategory();
                    break;
                case 4:
                    new ViewCategory().deleteCategory();
                    break;
                case 5:
                    new ViewCategory().updateCategory();
                    break;
                case 6:
                    afterMenu();
                    break;
                default:
                    System.out.println("Invalid not found");
                    viewCategory();
            }
        } else if (role.equals("PM")) {
            menu.addHeader("Welcome " + role + " " + account.getName());
            menu.addChoice("1. Show Category");
            menu.addChoice("2. Search Category");
            menu.addChoice("3. Add Category");
            menu.addChoice("4. Update Category");
            menu.addChoice("5. Back to menu");
            menu.addPaddingLeft(4);
            menu.print();
            int choice = Config.getIntegerInput();
            switch (choice) {
                case 1:
                    new ViewCategory().showCategory();
                    break;
                case 2:
                    new ViewCategory().searchCategory();
                    break;
                case 3:
                    new ViewCategory().createCategory();
                    break;
                case 4:
                    new ViewCategory().updateCategory();
                    break;
                case 5:
                    afterMenu();
                    break;
                default:
                    System.out.println("Invalid not found");
                    viewCategory();
            }
        } else if (role.equals("USER")) {
            menu.addHeader("Welcome " + role + " " + account.getName());
            menu.addChoice("1. Show Category");
            menu.addChoice("2. Search Category");
            menu.addChoice("3. Back to menu");
            menu.addPaddingLeft(4);
            menu.print();
            int choice = Config.getIntegerInput();
            switch (choice) {
                case 1:
                    new ViewCategory().showCategory();
                    break;
                case 2:
                    new ViewCategory().searchCategory();
                    break;
                case 3:
                    afterMenu();
                    break;
                default:
                    System.out.println("Invalid not found");
                    viewCategory();
            }

        }

    }
    public void viewSinger(){
        Menu menu = new Menu();
        Account account = accountController.getCurrenAccount();
        String role = null;
        Iterator<Role> iterator = account.getRoles().iterator();
        while (iterator.hasNext()) {
            role = String.valueOf(iterator.next().getName());
        }
        if (role.equals("ADMIN")){
            menu.addHeader("Welcome " + role + " " + account.getName());
            menu.addChoice("1. Show Singer");
            menu.addChoice("2. Search Singer");
            menu.addChoice("3. Add Singer");
            menu.addChoice("4. Delete Singer");
            menu.addChoice("5. Update Singer");
            menu.addChoice("6. Back to menu");
            menu.addPaddingLeft(4);
            menu.print();
            int choice = Config.getIntegerInput();
            switch (choice) {
                case 1:
                    new ViewSinger().showSinger();
                    break;
                case 2:
                    new ViewSinger().searchSinger();
                    break;
                case 3:
                    new ViewSinger().createSinger();
                    break;
                case 4:
                    new ViewSinger().deleteSinger();
                    break;
                case 5:
                    new ViewSinger().updateSinger();
                    break;
                case 6:
                    afterMenu();
                    break;
                default:
                    System.out.println("Invalid not found");
                    viewSinger();
            }
        }else if (role.equals("PM")){
            menu.addHeader("Welcome " + role + " " + account.getName());
            menu.addChoice("1. Show Singer");
            menu.addChoice("2. Search Singer");
            menu.addChoice("3. Add Singer");
            menu.addChoice("4. Update Singer");
            menu.addChoice("5. Back to menu");
            menu.addPaddingLeft(4);
            menu.print();
            int choice = Config.getIntegerInput();
            switch (choice){
                case 1:
                    new ViewSinger().showSinger();
                    break;
                case 2:
                    new ViewSinger().searchSinger();
                    break;
                case 3:
                    new ViewSinger().createSinger();
                    break;
                case 4:
                    new ViewSinger().updateSinger();
                    break;
                case 5:
                    afterMenu();
                    break;
                default:
                    System.out.println("Invalid not found");
                    viewSinger();
            }
        }else if (role.equals("USER")){
            menu.addHeader("Welcome " + role+ " " + account.getName());
            menu.addChoice("1. Show Singer");
            menu.addChoice("2. Search Singer");
            menu.addChoice("3. Back to menu");
            menu.addPaddingLeft(4);
            menu.print();
            int choice = Config.getIntegerInput();
            switch (choice) {
                case 1:
                    new ViewSinger().showSinger();
                    break;
                case 2:
                    new ViewSinger().searchSinger();
                    break;
                case 3:
                    afterMenu();
                    break;
                default:
                    System.out.println("Invalid not found");
                    viewSinger();
            }
        }
    }
    public void viewSong(){
        Menu menu = new Menu();
        Account account = accountController.getCurrenAccount();
        String role = null;
        Iterator<Role> iterator = account.getRoles().iterator();
        while (iterator.hasNext()) {
            role = String.valueOf(iterator.next().getName());
        }
        if (role.equals("ADMIN")){
            menu.addHeader("Welcome " + role + " " + account.getName());
            menu.addChoice("1. Show Song");
            menu.addChoice("2. Search Song");
            menu.addChoice("3. Add Song");
            menu.addChoice("4. Delete Song");
            menu.addChoice("5. Update Song");
            menu.addChoice("6. Back to menu");
            menu.addPaddingLeft(4);
            menu.print();
            int choice = Config.getIntegerInput();
            switch (choice) {
                case 1:
                    new ViewSong().showSongSigIn();;
                    break;
                case 2:
                    new ViewSong().detailSong();
                    break;
                case 3:
                    new ViewSong().createSong();
                    break;
                case 4:
                    new ViewSong().deleteSong();
                    break;
                case 5:
                    new ViewSong().updateSong();
                    break;
                case 6:
                    afterMenu();
                    break;
                default:
                    System.out.println("Invalid not found");
                    viewSong();
            }
        }else if (role.equals("PM")){
            menu.addHeader("Welcome " + role + " " + account.getName());
            menu.addChoice("1. Show Song");
            menu.addChoice("2. Search Song");
            menu.addChoice("3. Add Song");
            menu.addChoice("4. Update Song");
            menu.addChoice("5. Back to menu");
            menu.addPaddingLeft(4);
            menu.print();
            int choice = Config.getIntegerInput();
            switch (choice){
                case 1:
                    new ViewSong().showSongSigIn();
                    break;
                case 2:
                    new ViewSong().detailSong();
                    break;
                case 3:
                    new ViewSong().createSong();
                    break;
                case 4:
                    new ViewSong().updateSong();
                    break;
                case 5:
                    afterMenu();
                    break;
                default:
                    System.out.println("Invalid not found");
                    viewSong();
            }
        }else if (role.equals("USER")){
            menu.addHeader("Welcome " + role+ " " + account.getName());
            menu.addChoice("1. Show Song");
            menu.addChoice("2. Detail Song");
            menu.addChoice("3. Back to menu");
            menu.addPaddingLeft(4);
            menu.print();
            int choice = Config.getIntegerInput();
            switch (choice) {
                case 1:
                    new ViewSong().showSongSigIn();
                    break;
                case 2:
                    new ViewSong().detailSong();
                    break;
                case 3:
                    afterMenu();
                    break;
                default:
                    System.out.println("Invalid not found");
                    viewSong();
            }
        }
    }
    public void viewPlaylist(){
        Menu menu = new Menu();
        Account account = accountController.getCurrenAccount();
        String role = null;
        Iterator<Role> iterator = account.getRoles().iterator();
        while (iterator.hasNext()) {
            role = String.valueOf(iterator.next().getName());
        }
        if (role.equals("ADMIN")){
            menu.addHeader("Welcome " + role + " " + account.getName());
            menu.addChoice("1. Show play ist");
            menu.addChoice("2. Search play list");
            menu.addChoice("3. Add play list");
            menu.addChoice("4. Delete play list");
            menu.addChoice("5. Update play list");
            menu.addChoice("6. Back to menu");
            menu.addPaddingLeft(4);
            menu.print();
            int choice = Config.getIntegerInput();
            switch (choice) {
                case 1:
                    new ViewPlayList().showPlayListSignIn();
                    break;
                case 2:
                    new ViewPlayList().searchPlayList();
                    break;
                case 3:
                    new ViewPlayList().createPlayList();
                    break;
                case 4:
                    new ViewPlayList().deletePlayList();
                    break;
                case 5:
                    new ViewPlayList().updatePlayList();
                    break;
                case 6:
                    afterMenu();
                    break;
                default:
                    System.out.println("Invalid not found");
                    viewPlaylist();
            }
        }else if (role.equals("PM")){
            menu.addHeader("Welcome " + role + " " + account.getName());
            menu.addChoice("1. Show play list");
            menu.addChoice("2. Search play list");
            menu.addChoice("3. Add play list");
            menu.addChoice("4. Update play list");
            menu.addChoice("5. Back to menu");
            menu.addPaddingLeft(4);
            menu.print();
            int choice = Config.getIntegerInput();
            switch (choice){
                case 1:
                    new ViewPlayList().showPlayListSignIn();
                    break;
                case 2:
                    new ViewPlayList().searchPlayList();
                    break;
                case 3:
                    new ViewPlayList().createPlayList();
                    break;
                case 4:
                    new ViewPlayList().updatePlayList();
                    break;
                case 5:
                    afterMenu();
                    break;
                default:
                    System.out.println("Invalid not found");
                    viewPlaylist();
            }
        }else if (role.equals("USER")){
            menu.addHeader("Welcome " + role+ " " + account.getName());
            menu.addChoice("1. Show play list");
            menu.addChoice("2. Search play list");
            menu.addChoice("3. Back to menu");
            menu.addPaddingLeft(4);
            menu.print();
            int choice = Config.getIntegerInput();
            switch (choice) {
                case 1:
                    new ViewPlayList().showPlayListSignIn();
                    break;
                case 2:
                    new ViewPlayList().searchPlayList();
                    break;
                case 3:
                    afterMenu();
                    break;
                default:
                    System.out.println("Invalid not found");
                    viewPlaylist();
            }
        }
    }
    public void viewBand() {
        Menu menu = new Menu();
        Account account = accountController.getCurrenAccount();
        String role = null;
        Iterator<Role> iterator = account.getRoles().iterator();
        while (iterator.hasNext()) {
            role = String.valueOf(iterator.next().getName());
        }
        if (role.equals("ADMIN")){
            menu.addHeader("Welcome " + role + " " + account.getName());
            menu.addChoice("1. Show Band");
            menu.addChoice("2. Search Band");
            menu.addChoice("3. Add Band");
            menu.addChoice("4. Delete Band");
            menu.addChoice("5. Update Band");
            menu.addChoice("6. Back to menu");
            menu.addPaddingLeft(4);
            menu.print();
            int choice = Config.getIntegerInput();
            switch (choice) {
                case 1:
                    new BandView().showBandSignIn();
                    break;
                case 2:
                    new BandView().showBand();
                    break;
                case 3:
                    new BandView().createBand();
                    break;
                case 4:
                    new BandView().deleteBand();
                    break;
                case 5:
                    new BandView().updateBand();
                    break;
                case 6:
                    afterMenu();
                    break;
                default:
                    System.out.println("Invalid not found");
                    viewBand();
            }
        }else if (role.equals("PM")){
            menu.addHeader("Welcome " + role + " " + account.getName());
            menu.addChoice("1. Show Band");
            menu.addChoice("2. Search Band");
            menu.addChoice("3. Add Band");
            menu.addChoice("4. Update Band");
            menu.addChoice("5. Back to menu");
            menu.addPaddingLeft(4);
            menu.print();
            int choice = Config.getIntegerInput();
            switch (choice){
                case 1:
                    new BandView().showBandSignIn();
                    break;
                case 2:
                    new BandView().searchBand();
                    break;
                case 3:
                    new BandView().createBand();
                    break;
                case 4:
                    new BandView().updateBand();
                    break;
                case 5:
                    afterMenu();
                    break;
                default:
                    System.out.println("Invalid not found");
                    viewBand();
            }
        }else if (role.equals("USER")){
            menu.addHeader("Welcome " + role+ " " + account.getName());
            menu.addChoice("1. Show Band");
            menu.addChoice("2. Search Band");
            menu.addChoice("3. Back to menu");
            menu.addPaddingLeft(4);
            menu.print();
            int choice = Config.getIntegerInput();
            switch (choice) {
                case 1:
                    new BandView().showBandSignIn();
                    break;
                case 2:
                    new BandView().searchBand();
                    break;
                case 3:
                    afterMenu();
                    break;
                default:
                    System.out.println("Invalid not found");
                    viewBand();
            }
        }
    }
}