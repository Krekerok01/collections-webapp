package com.varvara.repository;


import com.varvara.entity.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ImagenRepository extends JpaRepository<Imagen, Integer> {

    Optional<Imagen> findByImagenUrl(String imagenUrl);
}
