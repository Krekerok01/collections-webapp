package com.varvara.service;

import com.varvara.entity.Tag;
import com.varvara.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService{

    private TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<String> getStringListOfAllTags() {

        List<Tag> tags = tagRepository.findAll();

        List<String> tagsStringList = new ArrayList<>();

        for (Tag tag: tags){
            String tagString = "<" + tag.getName() + ">";
            tagsStringList.add(tagString);
        }

        return tagsStringList;
    }
}
