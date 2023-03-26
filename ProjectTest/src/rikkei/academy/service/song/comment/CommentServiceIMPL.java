package rikkei.academy.service.song.comment;

import rikkei.academy.config.Config;
import rikkei.academy.model.live_comment.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentServiceIMPL implements ICommentService {
    private static final String PATH_COMMENT = "src/rikkei/academy/data/song/comment.txt";
    private static List<Comment> commentList = new Config<Comment>().readFile(PATH_COMMENT);
    @Override
    public List<Comment> findAll() {
        new Config<Comment>().writeFile(PATH_COMMENT,commentList);
        return commentList;
    }

    @Override
    public void save(Comment comment) {
        commentList.add(comment);
        new Config<Comment>().writeFile(PATH_COMMENT,commentList);
    }

    @Override
    public void remove(int id) {
        commentList.remove(findById(id));
        new Config<Comment>().writeFile(PATH_COMMENT,commentList);

    }

    @Override
    public Comment findById(int id) {
        for (Comment comment: commentList){
            if (comment.getId()==id){
                return comment;
            }
        }
        return null;
    }

    @Override
    public List<Comment> getCommentGetBySongId(int id) {
        List<Comment> comments = new ArrayList<>();
        for (Comment comment: commentList) {
            if (comment.getIdSong() == id){
                comments.add(comment);
            }
        }
        return comments;
    }

    @Override
    public int getLastId() {
        return commentList.isEmpty()?1:commentList.get(commentList.size()-1).getId()+1;
    }
}
