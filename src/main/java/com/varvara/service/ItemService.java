package com.varvara.service;

import com.varvara.entity.Item;

import java.util.List;

public interface ItemService {

    public void deleteItemById(int id);

    public void saveItem(Item item);

    public List<Item> getAllItems();

    public List<String> getLastAddedItems();
}
