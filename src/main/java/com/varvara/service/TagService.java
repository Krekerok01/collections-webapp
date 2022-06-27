package com.varvara.service;

import com.varvara.entity.Tag;

import java.util.List;
import java.util.Set;

public interface TagService {

    public Set<Tag> getSetOfAllTags();

    public Tag getTagById(int id);
}
