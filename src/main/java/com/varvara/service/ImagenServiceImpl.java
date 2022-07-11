package com.varvara.service;


import com.varvara.entity.Collection;
import com.varvara.entity.Imagen;
import com.varvara.repository.ImagenRepository;
import com.varvara.service.interfaces.CloudinaryService;
import com.varvara.service.interfaces.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;


@Service
public class ImagenServiceImpl implements ImagenService {

    private ImagenRepository imagenRepository;
    private CloudinaryService cloudinaryService;

    @Autowired
    public ImagenServiceImpl(ImagenRepository imagenRepository, CloudinaryService cloudinaryService) {
        this.imagenRepository = imagenRepository;
        this.cloudinaryService = cloudinaryService;
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

        Optional<Imagen> imagen = imagenRepository.findByImagenUrl(imagenUrl);

        if (!imagen.isPresent()){
            throw new RuntimeException("Img not found");
        }
        return imagen.get();
    }

    @Override
    public void deleteImgByCollection(Collection collection) {
        Imagen imagen = getByImageUrl(collection.getImageUrl());

        try {
            Map result = cloudinaryService.delete(imagen.getImagenId());
        } catch (IOException e) {
            e.printStackTrace();
        }
        deleteImageById(imagen.getId());
    }


}
