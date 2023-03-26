package rikkei.academy.service.song.like;

import rikkei.academy.config.Config;
import rikkei.academy.model.account.Account;
import rikkei.academy.model.live_comment.Like;
import rikkei.academy.service.account.AccountServiceIMPL;
import rikkei.academy.service.account.IAccountService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class LikeServiceIMPL implements ILikeService{
    IAccountService account = new AccountServiceIMPL();
    private static String PATH_LIKE = "src/rikkei/academy/data/song/like.txt";
    private static List<Like> likeList = new Config<Like>().readFile(PATH_LIKE);
    static {
        if (likeList == null){
            likeList = new ArrayList<>();
        }
    }
    @Override
    public List<Like> findAll() {
        new Config<Like>().writeFile(PATH_LIKE, likeList);
        return likeList;
    }

    @Override
    public void save(Like like) {
        likeList.add(like);
        new Config<Like>().writeFile(PATH_LIKE, likeList);

    }

    @Override
    public void remove(int id) {
        Like likeDelete = null;
        for (Like like : likeList) {
            if (like.getIdSong() == id && like.getIdUser() == account.getCurrentAccount().getId()) {
                likeDelete = like;
            }
        }
        likeList.remove(likeDelete);
        new Config<Like>().writeFile(PATH_LIKE, likeList);

    }

    @Override
    public Like findById(int id) {
        for (Like like : likeList) {
            if (like.getId() == id){
                return like;
            }
        }
        return null;
    }

    @Override
    public List<Like> getLikesByPostId(int idSong) {
        List<Like> list = new ArrayList<>();

        for (Like like : likeList) {
            if (like.getIdSong()== idSong) list.add(like);
        }
        return list;
    }

    @Override
    public int getLikeNumberBySongId(int id) {
        int count = 0;
        for (Like like : likeList) {
        if (like.getIdSong() == id){
            count++;
        }
        }
        return count;
    }

    @Override
    public boolean checkLike(int id) {
        Account account1 = account.getCurrentAccount();
        for (Like like : likeList) {
            if (like.getIdSong() == id&& like.getIdUser() == account1.getId()){
                return true;
            }
        }
        return false;
    }

    @Override
    public int getLastId() {
        return likeList.isEmpty()?1:likeList.get(likeList.size()-1).getId()+1;
    }

    @Override
    public List<Like> sortByLike() {
        List<Like> likes= new ArrayList<>();
        for (int i = 0; i < likeList.size(); i++) {
            likes.add(likeList.get(i));
        }
        Collections.sort(likes);
        List<Like> topLike= new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            topLike.add(likes.get(i));
        }
        System.out.println(topLike);
        return topLike;
    }
}
