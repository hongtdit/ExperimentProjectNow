package rikkei.academy.view;

import rikkei.academy.atherfuntion.Menu;
import rikkei.academy.config.Config;
import rikkei.academy.model.music.singer.Band;
import rikkei.academy.view.singer.BandView;
import rikkei.academy.view.singer.ViewSinger;
import rikkei.academy.view.viewAccount.LoginView;
import rikkei.academy.view.viewsong.ViewCategory;
import rikkei.academy.view.viewsong.ViewPlayList;
import rikkei.academy.view.viewsong.ViewPlaySong;
import rikkei.academy.view.viewsong.ViewSong;

public class Main {
    public static void main(String[] args) {
        new Main().menu();
    }

    public void menu() {
        Menu menu = new Menu();
        menu.addHeader("Welcome Zing PM3");
        menu.addChoice("1. Top 10 Like Song");
        menu.addChoice("2. Top 10 Listen Song");
        menu.addChoice("3. Singer Manage");
        menu.addChoice("4. Band Manage");
        menu.addChoice("5. Category Manage");
        menu.addChoice("6. Song Manage");
        menu.addChoice("7. Play List Manage");
        menu.addChoice("8. Search Music");
        menu.addChoice("9. Login and Register");
        menu.addPaddingLeft(4);
        menu.print();
        int choice = Config.getIntegerInput();
        switch (choice) {
            case 1:
                new ViewPlaySong().topLike();
                break;
            case 2:
                new ViewPlaySong().topListen();
                break;
            case 3:
                new ViewSinger().showListSinger();
                break;
            case 4:
                new BandView().showBand();
                break;
            case 5:
                new ViewCategory().showCategoryHome();
                break;
            case 6:
                new ViewSong().showSong();
                break;
            case 7:
                new ViewPlayList().showPlayList();
                break;
            case 8:
               new ViewPlaySong().searchMusic();
                break;
            case 9:
                new LoginView().menuLogin();
                break;
            default:
                System.out.println("Invalid Choice");
                menu();
        }
    }
}