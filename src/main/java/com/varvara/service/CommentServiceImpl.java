package com.varvara.service;

import com.varvara.entity.Comment;
import com.varvara.entity.Item;
import com.varvara.entity.User;
import com.varvara.repository.CommentRepository;
import com.varvara.service.interfaces.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public void saveComment(Comment comment, User user, Item item) {
        comment.setUser(user);
        comment.setItem(item);
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(int commentId) {
        commentRepository.deleteById(commentId);
    }
}
