package com.varvara.service;

import com.varvara.entity.Tag;
import com.varvara.entity.User;
import com.varvara.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TagServiceImpl implements TagService{

    private TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }



    @Override
    public Set<Tag> getSetOfAllTags() {

        List<Tag> tags = tagRepository.findAll();
        Set<Tag> tagsSet = new HashSet<>();

        fillTagsFromTheListToTheSet(tags, tagsSet);


        return tagsSet;
    }

    @Override
    public Tag getTagById(int id) {

        Optional<Tag> tag = tagRepository.findById(id);

        if(!tag.isPresent()) {
            throw new UsernameNotFoundException("User Not Found");
        }

        return tag.get();
    }

    private void fillTagsFromTheListToTheSet(List<Tag> tags, Set<Tag> tagsSet){
        for (Tag tag: tags){
            tagsSet.add(tag);
        }
    }
}
