package com.varvara.repository;

import com.varvara.entity.OtherFieldValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtherFieldValueRepository extends JpaRepository<OtherFieldValue, Integer> {
}
