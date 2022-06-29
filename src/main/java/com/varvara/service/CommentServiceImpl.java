package com.varvara.service;

import com.varvara.entity.Comment;
import com.varvara.entity.Item;
import com.varvara.entity.User;
import com.varvara.repository.CommentRepository;
import com.varvara.service.interfaces.CommentService;
import com.varvara.service.interfaces.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private ItemService itemService;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, ItemService itemService) {
        this.commentRepository = commentRepository;
        this.itemService = itemService;
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

    @Override
    public List<Comment> getCommentsToThisItem(int itemId) {
        Item item = itemService.getItemById(itemId);
        List<Comment> comments = item.getComments();
        return comments;
    }
}
