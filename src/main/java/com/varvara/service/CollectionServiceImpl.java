package com.varvara.service;

import com.varvara.entity.Collection;
import com.varvara.repository.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CollectionServiceImpl implements CollectionService{

    private CollectionRepository collectionRepository;

    @Autowired
    public CollectionServiceImpl(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    @Override
    public List<Collection> findAll() {
        return collectionRepository.findAll();
    }

    @Override
    public Collection findById(int id) {

        Optional<Collection> result = collectionRepository.findById(id);

        Collection collection = null;

        if (result.isPresent()){
            collection = result.get();
        } else {
            throw new RuntimeException("Didn't find collection id - " + id);
        }

        return collection;
    }

    @Override
    public void save(Collection collection) {
        collectionRepository.save(collection);
    }

    @Override
    public void deleteById(int id) {
        collectionRepository.deleteById(id);
    }
}
