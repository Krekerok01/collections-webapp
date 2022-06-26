package com.varvara.service;

import com.varvara.entity.Tag;
import com.varvara.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TagServiceImpl implements TagService{

    private TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Set<String> getStringListOfAllTags() {

        List<Tag> tags = tagRepository.findAll();

        Set<String> tagsStringSet = new HashSet<>();

        for (Tag tag: tags){
            String tagString = "<" + tag.getName() + ">";
            tagsStringSet.add(tagString);
        }

        return tagsStringSet;
    }
}
