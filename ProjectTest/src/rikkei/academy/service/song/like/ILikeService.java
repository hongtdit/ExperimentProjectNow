package rikkei.academy.service.song.like;

import rikkei.academy.model.live_comment.Like;
import rikkei.academy.model.music.song.Song;
import rikkei.academy.service.IGeneric;

import java.util.List;

public interface ILikeService extends IGeneric<Like> {
    List<Like> getLikesByPostId(int idSong);
    int getLikeNumberBySongId(int id);
    boolean checkLike(int id);
    int getLastId();
    List<Like> sortByLike();
}
