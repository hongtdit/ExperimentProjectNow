package rikkei.academy.view.singer;

import rikkei.academy.atherfuntion.Menu;
import rikkei.academy.config.Config;
import rikkei.academy.controller.singer.SingerController;
import rikkei.academy.model.music.singer.Singer;
import rikkei.academy.model.music.song.Song;
import rikkei.academy.view.Main;
import rikkei.academy.view.viewAccount.LoginView;
import rikkei.academy.view.viewAccount.ViewAfterSignIn;
import rikkei.academy.view.viewsong.ViewPlaySong;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ViewSinger {
    private SingerController singerController =new SingerController();
    private List<Singer> singerList = singerController.showListSinger();
    public void createSinger() {
        int id;
        while (true){
            if (singerList.size() == 0){
                id = 1;
            }else{
                id = singerList.get(singerList.size()-1).getId() +1;
            }
            System.out.println("Enter singer name");
            String name = Config.scanner().nextLine();
            System.out.println("Enter singer gender");
            String gender = Config.scanner().nextLine();
            System.out.println("Enter singer year");
            int year = Config.getIntegerInput();
            Singer singer = new Singer(id,name,gender,year,new ArrayList<>());
            singerController.createSinger(singer);
            System.out.println("Create Singer success!");
            System.out.println("list singer");
            System.out.println(singerList);
        }
    }
    public void deleteSinger() {
        System.out.println("Enter id singer");
        int id = Config.getIntegerInput();
        if (singerController.detailSinger(id) == null){
            System.out.println("Id singer not found");
        }else {
            System.out.println("Enter 1 to delete - Enter 2 not to delete");
            int choice = Config.getIntegerInput();
            switch (choice) {
                case 1:
                    singerController.deleteSinger(id);
                    System.out.println("Delete successfully");
                    break;
                case 2:
                    new ViewAfterSignIn().viewSinger();
                    break;
                default:
                    System.out.println("Invalid not found");
                    deleteSinger();
            }
        }
    }
    public void showListSinger() {
        Menu menu = new Menu();
        System.out.printf("%-5s%-15s%s%s%n", "ID", "NAME", "GENDER", "AGE");
        singerList.forEach(singer -> {
            System.out.printf("%-5s%-15s%s%s%n", singer.getId(), singer.getName(), singer.getSex(), singer.getAge());
        });
        System.out.println("Log in to continue or quit to go back to the page");
        menu.addHeader("Enter choice: ");
        menu.addChoice("1. Go to Login Account");
        menu.addChoice("2. back Menu");
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
        }
    }
    public void showSinger() {
        System.out.printf("%-5s%-15s%-15s%-15s%-15s%n", "ID",  "NAME",  "GENDER",  "AGE",  "SONG");
        singerList.forEach(singer -> {
            System.out.printf("%-5s%-15s%-15s%-15s%-15s%n", singer.getId(), singer.getName(), singer.getSex(), singer.getAge(), singer.getSong().stream().map(Song::getName).collect(Collectors.toList()));
        });
        new ViewPlaySong().playSongSignIn();

    }
    public void  updateSinger(){
        System.out.println("Enter id singer to update Singer");
        int id = Config.getIntegerInput();
        if (singerController.detailSinger(id) == null){
            System.out.println("Singer not found");
            return;
        }else {
            Singer singer = singerController.detailSinger(id);
            System.out.println("Enter singer name to update");
            String name = Config.scanner().nextLine();
            if (name.trim().equals("")) {
                name = singer.getName();
            }
            System.out.println("Enter singer gender to update");
            String gender = Config.scanner().nextLine();
            if (gender.trim().equals("")) {
                gender = singer.getSex();
            }
            System.out.println("Enter singer age to update");
            String age = Config.scanner().nextLine();
            if (age.trim().equals("")) {
                age = String.valueOf(singer.getAge());
            }
            Singer singerNew = new Singer(id, name, gender, age);
            singerController.updateSinger(id, singerNew);
            System.out.println("Updated singer successfully");
            singerController.showListSinger();
            new ViewAfterSignIn().viewSinger();
        }
    }
    public void searchSinger() {
        System.out.println("Enter name to search");
        String name = Config.scanner().nextLine();
        for (Singer singer: singerController.searchSinger(name)) {
            System.out.println(singer.getName());
        }
        new ViewAfterSignIn().viewSinger();
    }
}