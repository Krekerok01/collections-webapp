package com.varvara.service;

import com.varvara.entity.Collection;
import com.varvara.entity.Item;
import com.varvara.entity.Tag;
import com.varvara.entity.User;
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

        List<Item> allItems = reverseItemLinkedList(getLinkedListOfItems());

        List<String> lastAddedItemsStringList = new LinkedList<>();
        fillLastAddedItemsStringListFromAllItemsList(allItems, lastAddedItemsStringList);

        return lastAddedItemsStringList;
    }

    @Override
    public List<Item> getItemsThatContainsTheSpecificTag(String tagName) {

        List<Item> result = new ArrayList<>();

        for (Item item: getAllItems()){
            for (Tag t: item.getTags()){
                if (tagName.equals(t.getName())){
                    result.add(item);
                }
            }
        }

        return result;
    }


    public LinkedList<Item> getLinkedListOfItems(){
        LinkedList<Item> allItems = new LinkedList<>();
        addItemsToTheLinkedList(getAllItems(), allItems);

        return allItems;
    }

    private void  addItemsToTheLinkedList(List<Item> allItems, LinkedList<Item> linkedListOfTheaAllItems){
        for (Item i: allItems){
            linkedListOfTheaAllItems.add(i);
        }
    }

    public LinkedList<Item> reverseItemLinkedList(LinkedList<Item> items){
        Collections.reverse(items);
        return items;
    }

    private void fillLastAddedItemsStringListFromAllItemsList(List<Item> allItems,  List<String> lastAddedItemsStringList){
        for (int i = 0; i < 5; i++){
            String itemName = allItems.get(i).getName();
            String collectionName = allItems.get(i).getCollection().getName();
            List<User> users = allItems.get(i).getCollection().getUsers();

            findUsernameAndMergeAllData(itemName, collectionName, users, lastAddedItemsStringList);
        }
    }


    private void findUsernameAndMergeAllData(String itemName, String collectionName, List<User> users, List<String> lastAddedItemsStringList){
        for (User u: users){
            String username = u.getUsername();

            String resultItemString = "Item name - " + itemName  + ", collection name - " + collectionName + ", owner username - " + username;
            lastAddedItemsStringList.add(resultItemString);
        }
    }
}
