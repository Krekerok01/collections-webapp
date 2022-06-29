package com.varvara.service.interfaces;

import com.varvara.entity.Collection;
import com.varvara.entity.Item;

import java.util.List;
import java.util.Set;

public interface ItemService {

    public Item getItemById(int id);

    public void deleteItemById(int id);

    public void saveItem(Item item);

    public List<Item> getAllItems();

    public List<String> getLastAddedItems();

    public List<Item> getItemsThatContainsTheSpecificTag(String tagName);

    public void setTagsAndCollectionAndSaveItem(Item item, String tagsString, Collection collection);
}
