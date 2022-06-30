package com.varvara.repository;

import com.varvara.entity.OtherField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtherFieldRepository extends JpaRepository<OtherField, Integer> {
}
