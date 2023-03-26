package rikkei.academy.controller.song;

import rikkei.academy.model.account.Account;
import rikkei.academy.model.live_comment.Like;
import rikkei.academy.model.music.song.Song;
import rikkei.academy.service.account.AccountServiceIMPL;
import rikkei.academy.service.account.IAccountService;
import rikkei.academy.service.song.like.ILikeService;
import rikkei.academy.service.song.like.LikeServiceIMPL;
import rikkei.academy.service.song.song.ISongService;
import rikkei.academy.service.song.song.SongServiceIMPL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LikeController {
    private ISongService songService = new SongServiceIMPL();
    private ILikeService likeService = new LikeServiceIMPL();
    private IAccountService accountService = new AccountServiceIMPL();

    public int getLikeNumberById(int id) {return likeService.getLikeNumberBySongId(id);}
    public List<Like> getLikesBySongId(int idSong) {
        return likeService.getLikesByPostId(idSong);
    }
    public List<Like> getListLike(){return likeService.findAll();}
    public void createLike(Like like) {likeService.save(like);}
    public void deleteLike(int id) {likeService.remove(id);}
    public boolean checkLike(int id) {return likeService.checkLike(id);}
    public int findLikeSong(int idSong){
        for (Like like: getListLike()) {
            if (like.getIdUser()== accountService.getCurrentAccount().getId()&& like.getIdSong()==idSong){
                return like.getId();
            }
        }
        return -1;
    }
    public void createLikeSong(int idSong){
        int idLike = findLikeSong(idSong);
        if (idLike != -1){
            likeService.remove(idLike);
            System.out.println("dislike");
        }else {
            Account currentAccount = accountService.getCurrentAccount();
            likeService.save(new Like(likeService.getLastId(),currentAccount.getId(),idSong));
            System.out.println("like");
        }
    }
   public List<Integer> topLike(){
       Map<Integer, Integer> map = new HashMap<>();
       for (Song song : songService.findAll()) {
           int like = getLikesBySongId(song.getId()).size();
           map.put(song.getId(), like);
//           List<Integer> likeList =new ArrayList<>();
//           for (int i = 0; i < 10; i++) {
//               likeList.add(map.put(song.getId(),like));
//           }
//           System.out.println(likeList);
       }

       return map.keySet().stream().sorted((o1,o2)->map.get(o2).compareTo(map.get(o1))).collect(Collectors.toList());
   }
}
