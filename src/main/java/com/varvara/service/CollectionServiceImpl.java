package com.varvara.service;

import com.varvara.entity.Collection;
import com.varvara.entity.Item;
import com.varvara.repository.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Table;
import java.util.List;
import java.util.Optional;

@Service
public class CollectionServiceImpl implements CollectionService{

    @Autowired
    private CollectionRepository collectionRepository;


    @Transactional
    @Override
    public List<Collection> getAllCollections() {
        return collectionRepository.findAll();
    }

    @Transactional
    @Override
    public Collection getCollectionById(int id) {

        Optional<Collection> optionalCollection = collectionRepository.findById(id);

        if (!optionalCollection.isPresent()){
            throw new RuntimeException("Collection didn't find");
        }

        return optionalCollection.get();
    }

    @Transactional
    @Override
    public void saveCollection(Collection collection) {
        // айтем попадает сюда но не сохраняется
        System.out.println(collection.getName());


        List<Item> items = collection.getItems();
        for (Item i: items){
            System.out.println("Item: " + i.getName());
        }
        collectionRepository.save(collection);
    }
}