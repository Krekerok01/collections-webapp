package com.varvara.service;

import com.varvara.entity.Collection;
import com.varvara.entity.Item;
import com.varvara.entity.OtherField;
import com.varvara.entity.User;
import com.varvara.repository.CollectionRepository;
import com.varvara.service.interfaces.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

@Service
public class CollectionServiceImpl implements CollectionService {

    private Logger logger = Logger.getLogger(getClass().getName());
    private CollectionRepository collectionRepository;
    private UserServiceImpl userService;

    @Autowired
    public CollectionServiceImpl(CollectionRepository collectionRepository, UserServiceImpl userService) {
        this.collectionRepository = collectionRepository;
        this.userService = userService;
    }


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


    @Override
    public void saveCollection(Collection collection) {
        collectionRepository.save(collection);
    }



    @Override
    public void deleteCollectionById(int id) {
        collectionRepository.deleteById(id);
    }



    @Override
    public Collection getCollectionByName(String name) {
        Optional<Collection> optionalCollection = collectionRepository.findCollectionByName(name);

        if (!optionalCollection.isPresent()){
            throw new RuntimeException("Collection didn't find");
        }

        return optionalCollection.get();
    }

    public List<Collection> getLargestCollections(){

        Map<Collection, Integer> collectionsAndItemsSizeMap = fillCollectionsAndItemsSizeMap(getAllCollections());

        List<Integer> collectionsSizeList = fillCollectionsSizeList(collectionsAndItemsSizeMap);

        Set<String> theNamesOfTheFiveLargestCollections
                = fillSetWithTheNamesOfTheLargestCollections(getFiveNumbersWithTheMaximumSizesOfItemsInTheCollections(collectionsSizeList), collectionsAndItemsSizeMap);

        List<Collection> result = getFiveLargestCollections(theNamesOfTheFiveLargestCollections);


        return result;
    }

    @Override
    public List<String> getThemesNamesList() {
        List<String> themes = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/resources/static/themes.txt")));
            String s;
            while ((s = reader.readLine()) != null){
                themes.add(s);
            }
        } catch (IOException e) {
            logger.info(e.getMessage());
        }

        return themes;
    }


    @Override
    public Collection getCollectionByNameAndUserId(String collectionName, int userId) {
        User user = userService.findById(userId);
        List<Collection> collections = user.getCollections();

        Collection collection = null;

        for (Collection c: collections){
            if (c.getName().equals(collectionName)){
                collection = c;
            }
        }

        return collection;
    }


    @Override
    public LinkedList<OtherField> getFieldsWithoutCheckBox(Collection collection) {
        LinkedList<OtherField> fieldsWithoutCheckBox = new LinkedList<>();


        for (OtherField otherField: collection.getOtherFields()){
            if (!(otherField.getType().equals("checkbox"))) {
                fieldsWithoutCheckBox.add(otherField);
            }
        }

        return fieldsWithoutCheckBox;
    }

    @Override
    public LinkedList<OtherField> getCheckBoxesFields(Collection collection) {
        LinkedList<OtherField> checkBoxesFields = new LinkedList<>();

        for (OtherField otherField: collection.getOtherFields()) {
            if (otherField.getType().equals("checkbox")) {
                checkBoxesFields.add(otherField);
            }
        }

        return checkBoxesFields;
    }


    private Map<Collection, Integer> fillCollectionsAndItemsSizeMap(List<Collection> allCollectionsList){
        Map<Collection, Integer> collectionsAndItemsSizeMap = new HashMap<>();

        for (Collection collection: allCollectionsList){
            int itemsSize = collection.getItems().size();
            collectionsAndItemsSizeMap.put(collection, itemsSize);
        }

        return collectionsAndItemsSizeMap;
    }


    private List<Integer> fillCollectionsSizeList(Map<Collection, Integer> collectionsAndItemsSizeMap) {
        List<Integer> collectionsSizeList = new LinkedList<>();

        for (Map.Entry<Collection, Integer> entry: collectionsAndItemsSizeMap.entrySet()){
            collectionsSizeList.add(entry.getValue());
        }

        Collections.sort(collectionsSizeList);
        Collections.reverse(collectionsSizeList);

        return collectionsSizeList;
    }


    private Set<String> fillSetWithTheNamesOfTheLargestCollections
            (List<Integer> fiveNumbersWithTheMaximumSizesOfItemsInTheCollections, Map<Collection, Integer> collectionsAndItemsSizeMap){

        Set<String> theNamesOfTheFiveLargestCollections = new HashSet<>();

        for (Integer number: fiveNumbersWithTheMaximumSizesOfItemsInTheCollections){
            for (Map.Entry<Collection, Integer> entry: collectionsAndItemsSizeMap.entrySet()){
                if (entry.getValue() == number) theNamesOfTheFiveLargestCollections.add(entry.getKey().getName());
            }
        }

        return theNamesOfTheFiveLargestCollections;
    }

    private List<Integer> getFiveNumbersWithTheMaximumSizesOfItemsInTheCollections(List<Integer> collectionsSizeList) {
        List<Integer> fiveNumbersWithTheMaximumSizesOfItemsInTheCollections = new LinkedList<>();

        for (int i = 0; i < 5; i++){
            fiveNumbersWithTheMaximumSizesOfItemsInTheCollections.add(collectionsSizeList.get(i));
        }

        return fiveNumbersWithTheMaximumSizesOfItemsInTheCollections;
    }

    private List<Collection> getFiveLargestCollections(Set<String> theNamesOfTheFiveLargestCollections) {

        List<Collection> result = new ArrayList<>();
        for (String s: theNamesOfTheFiveLargestCollections){
            result.add(getCollectionByName(s));
        }
        return result;
    }
}
