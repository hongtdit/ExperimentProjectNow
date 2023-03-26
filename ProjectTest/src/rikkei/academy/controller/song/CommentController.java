package rikkei.academy.controller.song;

import rikkei.academy.model.account.Account;
import rikkei.academy.model.live_comment.Comment;
import rikkei.academy.service.account.AccountServiceIMPL;
import rikkei.academy.service.account.IAccountService;
import rikkei.academy.service.song.comment.CommentServiceIMPL;
import rikkei.academy.service.song.comment.ICommentService;
import rikkei.academy.service.song.song.ISongService;
import rikkei.academy.service.song.song.SongServiceIMPL;

import java.util.List;

public class CommentController {
    ISongService songService = new SongServiceIMPL();
    ICommentService commentService = new CommentServiceIMPL();
    IAccountService accountService = new AccountServiceIMPL();
    Account account = accountService.getCurrentAccount();
    public void createComment(int idSong,String comment){
        commentService.save(new Comment(commentService.getLastId(),idSong,accountService.getCurrentAccount().getName(),comment));
    }
    public List<Comment> getCommentBySongId(int id){return commentService.getCommentGetBySongId(id);}

    public List<Comment> getListComment() {return commentService.findAll();}

}
