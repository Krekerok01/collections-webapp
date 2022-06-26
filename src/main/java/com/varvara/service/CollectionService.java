package com.varvara.service;

import com.varvara.entity.Collection;

import java.util.List;

public interface CollectionService {

    public List<Collection> getAllCollections();

    public Collection getCollectionById(int id);

    public void saveCollection(Collection collection);

    public void deleteCollectionById(int id);

    public Collection getCollectionByName(String name);

    public List<String> getLargestCollections();
}
