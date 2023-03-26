package rikkei.academy.view.viewsong;

import rikkei.academy.atherfuntion.Menu;
import rikkei.academy.config.Config;
import rikkei.academy.controller.account.AccountController;
import rikkei.academy.controller.singer.SingerController;
import rikkei.academy.controller.song.CategoryController;
import rikkei.academy.controller.song.CommentController;
import rikkei.academy.controller.song.LikeController;
import rikkei.academy.controller.song.SongController;
import rikkei.academy.model.account.Account;
import rikkei.academy.model.live_comment.Comment;
import rikkei.academy.model.live_comment.Like;
import rikkei.academy.model.music.singer.Singer;
import rikkei.academy.model.music.song.Song;
import rikkei.academy.view.Main;
import rikkei.academy.view.viewAccount.LoginView;
import rikkei.academy.view.viewAccount.ViewAfterSignIn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ViewPlaySong {
    private CategoryController categoryController = new CategoryController();
    private SingerController singerController = new SingerController();
    private List<Singer> singerList = singerController.showListSinger();
    SongController songController = new SongController();
    LikeController likeController = new LikeController();
    CommentController commentController = new CommentController();
    AccountController accountController = new AccountController();
    List<Like> likeList = likeController.getListLike();
    List<Comment> commentList = commentController.getListComment();

    public void searchSongSignIn() {
        Menu menu = new Menu();
        System.out.println("Enter name song to search");
        String songName = Config.scanner().nextLine();
        for (Song song : songController.searchSong(songName)) {
            System.out.printf("%-5s%-30s%-20s%-20s%-20s%-20s%-20s%n", "ID", "NAME", "CATEGORY", "SINGER", "LISTEN", "LIKE", "COMMENT");
            System.out.printf("%-5s%-30s%-20s%-20s%-20s%-20s%-20s%n", song.getId(), song.getName(), song.getCategory().getName(), song.getSingerList().stream().map(Singer::getName).collect(Collectors.toList()), song.getListen(), likeController.getLikeNumberById(song.getId()), commentController.getCommentBySongId(song.getId()).size());
        }
        System.out.println("Enter id song to play song");
        int songId = Config.getIntegerInput();
        Song songPlay = songController.detailSong(songId);
        System.out.println(songPlay);
        int likeNumber = likeController.getLikeNumberById(songId);
        boolean likeCheck = likeController.checkLike(songId);
        System.out.println(likeCheck ? "LIKED" : "LIKE");
        System.out.println("Enter 1 to like or else to back");
        int choice = Integer.parseInt(Config.scanner().nextLine());
        if (choice == 1) {
            if (likeCheck) {
                likeController.deleteLike(songId);
            } else {
                int idLike;
                if (likeList.isEmpty()) {
                    idLike = 1;
                } else {
                    idLike = likeList.get(likeList.size() - 1).getId() + 1;
                }
                likeController.createLike(new Like(idLike, songId, accountController.getCurrenAccount().getId()));
            }
        }
        System.out.println("Enter comment: ");
        String comment = Config.scanner().nextLine();
        commentController.createComment(songId, comment);
        songPlay.setListen(songPlay.getListen() + 1);
        songController.showSongList();
        System.out.printf("%-5s%-30s%-20s%-20s%-20s%-20s%-20s%n", "ID", "NAME", "CATEGORY", "SINGER", "LISTEN", "LIKE", "COMMENT");
        System.out.printf("%-5s%-30s%-20s%-20s%-20s%-20s%-20s%n", songPlay.getId(), songPlay.getName(), songPlay.getCategory().getName(), songPlay.getSingerList().stream().map(Singer::getName).collect(Collectors.toList()), songPlay.getListen(), likeController.getLikeNumberById(songPlay.getId()), commentController.getCommentBySongId(songId).size());
        System.out.println("Comment: ");
        System.out.printf("%-5s%-30s%-20s%n", "ID", "CONTAIN", "USER COMMENT");
        commentController.getCommentBySongId(songId).forEach(comment1 -> System.out.printf("%-5s%-30s%-20s%n", comment1.getId(), comment1.getContent(), comment1.getIdUser()));
        new ViewAfterSignIn().viewSong();

    }

    public void playSongSignIn() {
        System.out.println("Enter id song to play");
        int songId = Config.getIntegerInput();
        playSong(songId);
    }

    private void playSong(int songId) {
        Song songPlay = songController.detailSong(songId);
        songPlay.setListen(songPlay.getListen() + 1);
        songController.showSongList();
        Menu menu = new Menu();
        menu.addHeader(songPlay.getName());
        menu.addChoice("               The song is playing");
        menu.addChoice("           ┏ ╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍┓");
        menu.addChoice("           ╏━━━━━━━━━━━ ▶ ━━━━━━━━━━━╏");
        menu.addChoice("           ┗╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍┛");
        menu.addChoice("");
        menu.addChoice("      You are listening to music at Zing MP3");
        menu.addChoice("");
        menu.addChoice(" Listen: " + songPlay.getListen() + "  ━━━━ Like Song: " + likeController.getLikeNumberById(songPlay.getId()) + "  ━━━━ Comment: " + commentController.getCommentBySongId(songPlay.getId()).size());
        menu.print();
        System.out.println("Comment: ");
        commentController.getCommentBySongId(songId).forEach(comment1 -> System.out.printf("%-50s%-30s%n", comment1.getContent(), comment1.getIdUser()));
        System.out.println("━━━━━━━━ Choice ━━━━━━━━");
        System.out.println("1. like  ━━━━━━━━ 2. Comment ━━━━━━━━ 3. Back Menu Song");
        int choice = Config.getIntegerInput();
        switch (choice) {
            case 1:
                boolean likeCheck = likeController.checkLike(songId);
                System.out.println(likeCheck ? "LIKED" : "LIKE");
                System.out.println("Enter 1 to like or else to back");
                int choice1 = Integer.parseInt(Config.scanner().nextLine());
                if (choice1 == 1) {
                    if (likeCheck) {
                        likeController.deleteLike(songId);
                    } else {
                        int idLike;
                        if (likeList.isEmpty()) {
                            idLike = 1;
                        } else {
                            idLike = likeList.get(likeList.size() - 1).getId() + 1;
                        }
                        likeController.createLike(new Like(idLike, songId, accountController.getCurrenAccount().getId()));
                    }
                }
                playSong(songId);
                break;
            case 2:
                System.out.println("Enter comment: ");
                String comment = Config.scanner().nextLine();
                commentController.createComment(songId, comment);
                playSong(songId);
                break;
            case 3:
                new ViewAfterSignIn().afterMenu();
                break;
            default:
                System.out.println("Invalid not found");
                playSong(songId);
        }

    }




    public void playSongNotSignIn() {
        System.out.println("Enter id song to play");
        int songId = Config.getIntegerInput();
        Song songPlay = songController.detailSong(songId);
        songPlay.setListen(songPlay.getListen() + 1);
        songController.showSongList();
        Menu menu = new Menu();
        menu.addHeader(songPlay.getName());
        menu.addChoice("               The song is playing");
        menu.addChoice("           ┏ ╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍┓");
        menu.addChoice("           ╏━━━━━━━━━━━ ▶ ━━━━━━━━━━━╏");
        menu.addChoice("           ┗╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍╍┛");
        menu.addChoice("");
        menu.addChoice("      You are listening to music at Zing MP3");
        menu.addChoice("");
        menu.addChoice(" Listen: " + songPlay.getListen() + "  ━━━━ Like Song: " + likeController.getLikeNumberById(songPlay.getId()) + "  ━━━━ Comment: " + commentController.getCommentBySongId(songPlay.getId()).size());
        menu.print();
        System.out.println("Comment: ");
        commentController.getCommentBySongId(songId).forEach(comment1 -> System.out.printf("%-30s%-30s%n", comment1.getContent(), comment1.getIdUser()));
        System.out.println("━━━━━━━━ Choice ━━━━━━━━");
        System.out.println("1. like  ━━━━━━━━ 2. Comment ━━━━━━━━ 3. Back Menu Song");
        System.out.println("SIGN IN to continue...");
        System.out.println("━━━━━━━━ Choice ━━━━━━━━");
        System.out.println("1. Sign in  ━━━━━━━━ 2. Back Menu");
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
                playSongNotSignIn();
        }
    }
    public void searchMusic() {
        System.out.println("Enter name Song - category - singer to search");
        String name = Config.scanner().nextLine();
        System.out.printf("%-5s%-30s%-20s%-20s%-20s%-20s%-20s%n", "ID", "NAME", "CATEGORY", "SINGER", "LISTEN", "LIKE", "COMMENT");
        for (Song song : songController.searchSong(name)) {
            System.out.printf("%-5s%-30s%-20s%-20s%-20s%-20s%-20s%n", song.getId(), song.getName(), song.getCategory().getName(), song.getSingerList().stream().map(Singer::getName).collect(Collectors.toList()), song.getListen(), likeController.getLikeNumberById(song.getId()), commentController.getCommentBySongId(song.getId()).size());
        }
        playSongNotSignIn();
    }

    public void topListen() {
        List<Song> songs = songController.showSongList();
        List<Song> sorted = songs.stream().sorted((s1, s2) -> s2.getListen() - s1.getListen()).collect(Collectors.toList());
        System.out.println("TOP LISTEN");
        System.out.printf("%-5s%-30s%-20s%n", "ID", "NAME SONG", "LISTEN");
        sorted.forEach(song -> System.out.printf("%-5s%-30s%-20s%n", song.getId(), song.getName(), song.getListen()));
        playSongNotSignIn();
    }
    public void topLike() {
        List<Integer> id = likeController.topLike();
        System.out.println("TOP LIKE");
        System.out.printf("%-5s%-5s%-30s%-20s%n","STT", "ID", "NAME SONG", "LIKE");
        for (int i = 0; i < 10; i++) {
            Song song = songController.detailSong(id.get(i));
            System.out.printf("%-5s%-5s%-30s%-20s%n",i+1, song.getId(), song.getName(), likeController.getLikesBySongId(song.getId()).size());
        }
        playSongNotSignIn();
    }
    public void searchMusicSigIn() {
        System.out.println("Enter name Song - category - singer to search");
        String name = Config.scanner().nextLine();
        System.out.printf("%-5s%-30s%-20s%-20s%-20s%-20s%-20s%n", "ID", "NAME", "CATEGORY", "SINGER", "LISTEN", "LIKE", "COMMENT");
        for (Song song : songController.searchSong(name)) {
            System.out.printf("%-5s%-30s%-20s%-20s%-20s%-20s%-20s%n", song.getId(), song.getName(), song.getCategory().getName(), song.getSingerList().stream().map(Singer::getName).collect(Collectors.toList()), song.getListen(), likeController.getLikeNumberById(song.getId()), commentController.getCommentBySongId(song.getId()).size());
        }
       playSongSignIn();
    }
}
