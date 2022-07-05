package com.varvara.service.interfaces;

import com.varvara.entity.Collection;
import com.varvara.entity.OtherField;

import java.util.LinkedList;
import java.util.List;

public interface CollectionService {

    public List<Collection> getAllCollections();

    public Collection getCollectionById(int id);

    public void saveCollection(Collection collection);

    public void deleteCollectionById(int id);

    public Collection getCollectionByName(String name);

    public List<Collection> getLargestCollections();

    public List<String> getThemesNamesList();

    public Collection getCollectionByNameAndUserId(String collectionName, int userId);

    public LinkedList<OtherField> getFieldsWithoutCheckBox(Collection collection);

    public LinkedList<OtherField> getCheckBoxesFields(Collection collection);
}
