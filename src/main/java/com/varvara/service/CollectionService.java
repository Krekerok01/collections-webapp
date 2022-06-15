package com.varvara.service;

import com.varvara.entity.Collection;

import java.util.List;

public interface CollectionService {

    public List<Collection> findAll();

    public Collection findById(int id);

    public void save(Collection collection);

    public void deleteById(int id);
}
