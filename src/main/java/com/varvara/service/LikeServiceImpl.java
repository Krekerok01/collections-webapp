package com.varvara.service;

import com.varvara.entity.Item;
import com.varvara.entity.Like;
import com.varvara.entity.User;
import com.varvara.repository.LikeRepository;
import com.varvara.service.interfaces.ItemService;
import com.varvara.service.interfaces.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class LikeServiceImpl implements LikeService {

    private LikeRepository likeRepository;


    @Autowired
    public LikeServiceImpl(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }


    @Override
    public List<Like> getAllLikes() {
        return likeRepository.findAll();
    }

    @Override
    public void likeOrDislikeItem(int userId, int itemId) {
        if (likeRepository.existsByUserIdAndItemId(userId, itemId)){
            deleteLikeByUserIdAndItemId(userId, itemId);
        } else {
            likeRepository.save(new Like(userId, itemId));
        }
    }

    @Override
    public void deleteLikeByUserIdAndItemId(int userId, int itemId) {
        int likeId = 0;

        for (Like like: getAllLikes()){
            if (like.getUserId() == userId && like.getItemId() == itemId){
                likeId = like.getId();
            }
        }

        if (likeId != 0){
            likeRepository.deleteById(likeId);
        }
    }


}
