package rikkei.academy.view.viewsong;

import rikkei.academy.config.Config;
import rikkei.academy.controller.account.AccountController;
import rikkei.academy.controller.song.CommentController;
import rikkei.academy.controller.song.LikeController;
import rikkei.academy.controller.song.SongController;
import rikkei.academy.model.account.Account;
import rikkei.academy.model.music.song.Song;

import java.util.List;
import java.util.stream.Collectors;

public class ViewSongTest {
    private SongController songController = new SongController();
    private CommentController commentController = new CommentController();
    private AccountController accountController = new AccountController();
    private LikeController likeController = new LikeController();
    public void testShowSong(List<Song> songList) {
        for (Song song : songList) {
            Account accountUser = accountController.findById(song.getIdUser());
            int likeNumber = likeController.getLikesBySongId(song.getId()).size();
            int commentNumber = commentController.getCommentBySongId(song.getId()).size();
            boolean isLiked = likeController.findLikeSong(song.getId()) != 1;
            String likeComment = isLiked ? "Like: %4d" + "Comment:  %4d" :  "Like:  %4d" +     "Comment:  %4d";
            System.out.println(accountUser.getName());
            System.out.printf(likeComment, likeNumber, commentNumber);
        }
    }
    public void formShowUserPlaySong(){
        List<Song> userPlay = songController.getUserPlaySong();
        testShowSong(userPlay);
        System.out.println("Enter song id to show detail / 0 to back");
        int id = Config.getIntegerInput();
        if (id == 0) return;
        if (!userPlay.stream().map(Song::getId).collect(Collectors.toList()).contains(id)){
            System.out.println("ID mismatch: " + id);
        }else { songController.detailSong(id);}
    }
}
