package com.varvara.service;

import com.varvara.entity.Collection;
import com.varvara.entity.OtherField;
import com.varvara.repository.OtherFieldRepository;
import com.varvara.service.interfaces.OtherFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class OtherFieldServiceImpl implements OtherFieldService {

    private OtherFieldRepository otherFieldRepository;

    @Autowired
    public OtherFieldServiceImpl(OtherFieldRepository otherFieldRepository) {
        this.otherFieldRepository = otherFieldRepository;
    }

    @Override
    public void updateOtherField(OtherField otherField) {
        otherFieldRepository.save(otherField);
    }

    @Override
    public void saveOtherField(OtherField otherField, String type, String name, Collection collection) {
        otherField.setType(type);
        otherField.setName(name);
        otherField.setCollection(collection);

        otherFieldRepository.save(otherField);
    }

    @Override
    public void saveCollection(Collection collection, String firstAdditionalStringType, String firstAdditionalStringName, String secondAdditionalStringType, String secondAdditionalStringName, String thirdAdditionalStringType, String thirdAdditionalStringName, String firstAdditionalIntegerType, String firstAdditionalIntegerName, String secondAdditionalIntegerType, String secondAdditionalIntegerName, String thirdAdditionalIntegerType, String thirdAdditionalIntegerName, String firstAdditionalMultilineTextType, String firstAdditionalMultilineTextName, String secondAdditionalMultilineTextType, String secondAdditionalMultilineTextName, String thirdAdditionalMultilineTextType, String thirdAdditionalMultilineTextName, String firstAdditionalCheckboxType, String firstAdditionalCheckboxName, String secondAdditionalCheckboxType, String secondAdditionalCheckboxName, String thirdAdditionalCheckboxType, String thirdAdditionalCheckboxName, String firstAdditionalDateType, String firstAdditionalDateName, String secondAdditionalDateType, String secondAdditionalDateName, String thirdAdditionalDateType, String thirdAdditionalDateName) {
        tryToDoSomething
                (collection,
                        firstAdditionalStringType, firstAdditionalStringName, secondAdditionalStringType, secondAdditionalStringName, thirdAdditionalStringType, thirdAdditionalStringName,
                        firstAdditionalIntegerType, firstAdditionalIntegerName, secondAdditionalIntegerType, secondAdditionalIntegerName, thirdAdditionalIntegerType, thirdAdditionalIntegerName,
                        firstAdditionalMultilineTextType, firstAdditionalMultilineTextName, secondAdditionalMultilineTextType, secondAdditionalMultilineTextName, thirdAdditionalMultilineTextType, thirdAdditionalMultilineTextName,
                        firstAdditionalCheckboxType, firstAdditionalCheckboxName, secondAdditionalCheckboxType, secondAdditionalCheckboxName, thirdAdditionalCheckboxType, thirdAdditionalCheckboxName,
                        firstAdditionalDateType, firstAdditionalDateName, secondAdditionalDateType, secondAdditionalDateName, thirdAdditionalDateType, thirdAdditionalDateName);



    }

    private void tryToDoSomething(Collection collection,
                                  String firstAdditionalStringType, String firstAdditionalStringName,
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
                                  String thirdAdditionalDateType, String thirdAdditionalDateName) {

        LinkedList<String> types = new LinkedList<>();
        LinkedList<String> names = new LinkedList<>();

        checkStrings(types, names, firstAdditionalStringType, firstAdditionalStringName);
        checkStrings(types, names, secondAdditionalStringType, secondAdditionalStringName);
        checkStrings(types, names, thirdAdditionalStringType, thirdAdditionalStringName);
        checkStrings(types, names, firstAdditionalIntegerType,firstAdditionalIntegerName);
        checkStrings(types, names, secondAdditionalIntegerType, secondAdditionalIntegerName);
        checkStrings(types, names, thirdAdditionalIntegerType, thirdAdditionalIntegerName);
        checkStrings(types, names, firstAdditionalMultilineTextType, firstAdditionalMultilineTextName);
        checkStrings(types, names, secondAdditionalMultilineTextType, secondAdditionalMultilineTextName);
        checkStrings(types, names, thirdAdditionalMultilineTextType, thirdAdditionalMultilineTextName);
        checkStrings(types, names, firstAdditionalCheckboxType, firstAdditionalCheckboxName);
        checkStrings(types, names, secondAdditionalCheckboxType, secondAdditionalCheckboxName);
        checkStrings(types, names, thirdAdditionalCheckboxType, thirdAdditionalCheckboxName);
        checkStrings(types, names, firstAdditionalDateType, firstAdditionalDateName);
        checkStrings(types, names, secondAdditionalDateType, secondAdditionalDateName);
        checkStrings(types, names, thirdAdditionalDateType, thirdAdditionalDateName);

        addToTheCollection(collection, types, names);
        enterTypesAndNames(types, names);
    }

    private void addToTheCollection(Collection collection, LinkedList<String> types, LinkedList<String> names) {
        for (int i = 0; i < types.size(); i++){
            OtherField otherField = new OtherField();
            saveOtherField(otherField, types.get(i), names.get(i), collection);
        }

    }

    private void enterTypesAndNames(LinkedList<String> types, LinkedList<String> names) {

        for (int i = 0; i < types.size(); i++){
            System.out.println("Type - " + types.get(i)  + ", name - " + names.get(i));
        }
    }

    private void checkStrings(List<String> types, List<String> names, String type, String name) {
        if (type != null && !type.isEmpty() && name != null && !name.isEmpty()){
            types.add(type);
            names.add(name);
        }
    }


}
