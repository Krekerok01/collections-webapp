package com.varvara.service;

import com.varvara.entity.Item;
import com.varvara.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ItemServiceImpl implements ItemService{

    private ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void deleteItemById(int id) {
        itemRepository.deleteById(id);
    }

    @Override
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public List<String> getLastAddedItems() {

        List<Item> allItems = new LinkedList<>();

        List<Item> items = getAllItems();

        for (Item i: items){
            allItems.add(i);
        }

        Collections.reverse(allItems);

        List<String> lastAddedItemsList = new ArrayList<>();

        for (int i = 0; i < 5; i++){
            lastAddedItemsList.add(allItems.get(i).getName());
        }

        return lastAddedItemsList;
    }
}
