package com.varvara.service;


import com.varvara.entity.Imagen;
import com.varvara.repository.ImagenRepository;
import com.varvara.service.interfaces.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


@Service
@Transactional
public class ImagenServiceImpl implements ImagenService {

    private ImagenRepository imagenRepository;

    @Autowired
    public ImagenServiceImpl(ImagenRepository imagenRepository) {
        this.imagenRepository = imagenRepository;
    }

    @Override
    public String getUrlAndSaveImage(Map resultMap){
        Imagen imagen = new Imagen((String)resultMap.get("original_filename"), (String)resultMap.get("url"), (String)resultMap.get("public_id"));
        saveImage(imagen);
        return (String)resultMap.get("url");
    }

    @Override
    public void saveImage(Imagen imagen){
        imagenRepository.save(imagen);
    }


    @Override
    public void deleteImageById(int id){
        imagenRepository.deleteById(id);
    }


    @Override
    public Imagen getByImageUrl(String imagenUrl){
        return imagenRepository.findByImagenUrl(imagenUrl).get();
    }
}
