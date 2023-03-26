package rikkei.academy.service.song.comment;

import rikkei.academy.model.live_comment.Comment;
import rikkei.academy.service.IGeneric;

import java.util.List;

public interface ICommentService extends IGeneric<Comment> {
    List<Comment> getCommentGetBySongId(int id);
    int getLastId();
}
