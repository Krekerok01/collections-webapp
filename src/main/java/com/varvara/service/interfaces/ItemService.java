package com.varvara.service.interfaces;

import com.varvara.entity.Collection;
import com.varvara.entity.Item;

import java.util.LinkedList;
import java.util.List;

public interface ItemService {


    public Item getItemById(int id);

    public void deleteItemById(int id);

    public void saveItem(Item item);

    public List<Item> getAllItems();

    public List<String> getLastAddedItems();

    public List<Item> getItemsThatContainsTheSpecificTag(String tagName);

    public void setTagsAndCollectionAndSaveItem(Item item, String tagsString, Collection collection);

    public void setTagsAndCollectionAndOtherFieldsAndSaveItem(Item item, String tagsString, Collection collection, LinkedList<String> enterValues);
}
