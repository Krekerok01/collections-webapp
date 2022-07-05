package com.varvara.repository;

import com.varvara.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Integer> {
    boolean existsByUserIdAndItemId(int userId, int itemId);

}
