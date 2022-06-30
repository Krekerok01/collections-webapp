package com.varvara.service.interfaces;

import com.varvara.entity.Collection;
import com.varvara.entity.OtherField;

public interface OtherFieldService {

    public void updateOtherField(OtherField otherField);

    public void saveOtherField(OtherField otherField, String type, String name, Collection collection);

    public void  saveCollection(Collection collection, String firstAdditionalStringType, String firstAdditionalStringName,
                                String secondAdditionalStringType, String secondAdditionalStringName,
                                String thirdAdditionalStringType, String thirdAdditionalStringName,
                                String firstAdditionalIntegerType, String firstAdditionalIntegerName,
                                String secondAdditionalIntegerType, String secondAdditionalIntegerName,
                                String thirdAdditionalIntegerType, String thirdAdditionalIntegerName,
                                String firstAdditionalMultilineTextType, String firstAdditionalMultilineTextName,
                                String secondAdditionalMultilineTextType, String secondAdditionalMultilineTextName,
                                String thirdAdditionalMultilineTextType, String thirdAdditionalMultilineTextName,
                                String firstAdditionalCheckboxType, String firstAdditionalCheckboxName,
                                String secondAdditionalCheckboxType, String secondAdditionalCheckboxName,
                                String thirdAdditionalCheckboxType, String thirdAdditionalCheckboxName,
                                String firstAdditionalDateType, String firstAdditionalDateName,
                                String secondAdditionalDateType, String secondAdditionalDateName,
                                String thirdAdditionalDateType, String thirdAdditionalDateName);
}
