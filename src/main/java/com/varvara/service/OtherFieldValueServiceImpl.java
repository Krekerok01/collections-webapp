package com.varvara.service;

import com.varvara.entity.OtherFieldValue;
import com.varvara.repository.OtherFieldValueRepository;
import com.varvara.service.interfaces.OtherFieldValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OtherFieldValueServiceImpl implements OtherFieldValueService {

    private OtherFieldValueRepository otherFieldValueRepository;

    @Autowired
    public OtherFieldValueServiceImpl(OtherFieldValueRepository otherFieldValueRepository) {
        this.otherFieldValueRepository = otherFieldValueRepository;
    }

    @Override
    public void saveOtherFieldValue(OtherFieldValue otherFieldValue) {
        otherFieldValueRepository.save(otherFieldValue);
    }
}
