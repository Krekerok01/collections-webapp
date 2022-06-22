package com.varvara.service;

import com.varvara.entity.Item;

public interface ItemService {

    public void deleteItemById(int id);

    public void saveItem(Item item);
}
