package com.varvara.service.interfaces;

import com.varvara.entity.Like;

import java.util.List;

public interface LikeService {

    public List<Like> getAllLikes();

    public void likeOrDislikeItem(int userId, int itemId);

    public void deleteLikeByUserIdAndItemId(int userId, int itemId);

}
