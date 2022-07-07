package com.varvara.service;


import com.varvara.entity.Imagen;
import com.varvara.repository.ImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ImagenService {

    @Autowired
    ImagenRepository imagenRepository;

    public List<Imagen> list(){
        return imagenRepository.findByOrderById();
    }


    public void save(Imagen imagen){
        imagenRepository.save(imagen);
    }

    public void delete(int id){
        imagenRepository.deleteById(id);
    }


    public Imagen getByImagenUrl(String imagenUrl){
        return imagenRepository.findByImagenUrl(imagenUrl).get();
    }
}
