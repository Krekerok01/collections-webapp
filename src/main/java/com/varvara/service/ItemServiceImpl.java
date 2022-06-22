package com.varvara.service;

import com.varvara.entity.Item;
import com.varvara.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void deleteItemById(int id) {
        itemRepository.deleteById(id);
    }

    @Override
    public void saveItem(Item item) {
        itemRepository.save(item);
    }
}
