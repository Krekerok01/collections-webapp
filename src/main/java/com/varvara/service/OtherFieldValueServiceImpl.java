package com.varvara.service;

import com.varvara.entity.Item;
import com.varvara.entity.OtherFieldValue;
import com.varvara.repository.OtherFieldValueRepository;
import com.varvara.service.interfaces.OtherFieldValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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

    @Override
    public Map<String, String> getOtherFieldsValuesMap(Item item) {
        Map<String, String> otherFieldsValuesMap = new HashMap<>();

        for (OtherFieldValue o: item.getOtherFieldsValues()){
            otherFieldsValuesMap.put(o.getOtherField().getName(),  o.getText());
        }
        return otherFieldsValuesMap;
    }
}
