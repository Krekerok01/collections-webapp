package com.varvara.service.interfaces;


import com.varvara.entity.Tag;
import java.util.Set;

public interface TagService {

    public Set<Tag> getSetOfAllTags();

    public Tag getTagById(int id);
}
