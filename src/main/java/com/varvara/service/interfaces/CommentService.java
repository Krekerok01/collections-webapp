package com.varvara.service.interfaces;

import com.varvara.entity.Comment;
import com.varvara.entity.Item;
import com.varvara.entity.User;

import java.util.List;

public interface CommentService {

    public List<Comment> getAllComments();

    public void saveComment(Comment comment, User user, Item item);

    public void deleteComment(int commentId);
}
