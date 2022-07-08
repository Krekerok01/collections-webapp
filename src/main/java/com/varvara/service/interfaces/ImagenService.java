package com.varvara.service.interfaces;

import com.varvara.entity.Imagen;

import java.util.List;
import java.util.Map;

public interface ImagenService {

    public String getUrlAndSaveImage(Map resultMap);

    public List<Imagen> list();

    public void save(Imagen imagen);

    public void delete(int id);

    public Imagen getByImagenUrl(String imagenUrl);
}
