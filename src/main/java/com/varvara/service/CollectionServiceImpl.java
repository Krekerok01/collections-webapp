package com.varvara.service;

import com.varvara.entity.Collection;
import com.varvara.repository.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CollectionServiceImpl implements CollectionService{

    @Autowired
    private CollectionRepository collectionRepository;


    @Override
    public List<Collection> getAllCollections() {
        return collectionRepository.findAll();
    }

    @Override
    public Collection getCollectionById(int id) {

        Optional<Collection> optionalCollection = collectionRepository.findById(id);

        if (!optionalCollection.isPresent()){
            throw new RuntimeException("Collection didn't find");
        }

        return optionalCollection.get();
    }
}
