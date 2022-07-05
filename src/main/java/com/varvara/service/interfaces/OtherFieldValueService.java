package com.varvara.service.interfaces;

import com.varvara.entity.Item;
import com.varvara.entity.OtherFieldValue;

import java.util.Map;

public interface OtherFieldValueService {

    public void saveOtherFieldValue(OtherFieldValue otherFieldValue);

    public Map<String, String> getOtherFieldsValuesMap(Item item);
}
