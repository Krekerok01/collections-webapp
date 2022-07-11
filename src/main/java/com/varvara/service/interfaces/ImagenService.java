package com.varvara.service.interfaces;

import com.varvara.entity.Collection;
import com.varvara.entity.Imagen;

import java.util.Map;

public interface ImagenService {

    public String getUrlAndSaveImage(Map resultMap);

    public void saveImage(Imagen imagen);

    public void deleteImageById(int id);

    public Imagen getByImageUrl(String imagenUrl);

    public void deleteImgByCollection(Collection collection);
}
