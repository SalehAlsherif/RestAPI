package com.example.demo.controllers;

import database.DataBase;
import models.CountryPhoneTemplate;
import models.PhoneNumber;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static utilities.BooleanHelpers.isValidBooleanString;

@RestController
public class PhoneNumbersController {
    public static HashMap<String,CountryPhoneTemplate> countriesTemplates;

    @GetMapping("/phoneNumbers")
    @ResponseBody
    public List<PhoneNumber> getPhoneNumbers(@RequestParam(required = false) String country,@RequestParam(required = false) String state){
        if(country==null){
            List<PhoneNumber> allPhoneNumbers=DataBase.getPhoneNumbers();
            matchByAllCountriesTemplates(allPhoneNumbers);
            return (isValidBooleanString(state))?filterBy(allPhoneNumbers,Boolean.parseBoolean(state)):allPhoneNumbers;
        }else{
            List<PhoneNumber> countryPhoneNumbers=filterBy(DataBase.getPhoneNumbers(),countriesTemplates.get(country));
            return  (isValidBooleanString(state))?filterBy(countryPhoneNumbers,Boolean.parseBoolean(state)):countryPhoneNumbers;
        }
    }

    public static List<PhoneNumber> filterBy(List<PhoneNumber> allPhoneNumbers, boolean state){
        List<PhoneNumber> filteredPhoneNumbers= new ArrayList();
        for(PhoneNumber phoneNumber:allPhoneNumbers){
            if(state==phoneNumber.isValid()){
                filteredPhoneNumbers.add(phoneNumber);
            }
        }
        return filteredPhoneNumbers;
    }

    public static List<PhoneNumber> filterBy(List<PhoneNumber> allPhoneNumbers, CountryPhoneTemplate countryPhoneTemplate){
        List<PhoneNumber> filteredPhoneNumbers=new ArrayList();
        matchByCountryTemplate(allPhoneNumbers,countryPhoneTemplate);
        for(PhoneNumber phoneNumber:allPhoneNumbers){
            if(phoneNumber.getCountry()!=null){
                filteredPhoneNumbers.add(phoneNumber);
            }
        }
        return filteredPhoneNumbers;
    }

    private static void matchByAllCountriesTemplates(List<PhoneNumber> allPhoneNumbers){
        for(CountryPhoneTemplate countryPhoneTemplate:countriesTemplates.values()){
            matchByCountryTemplate(allPhoneNumbers,countryPhoneTemplate);
        }
    }

    public static void matchByCountryTemplate(List<PhoneNumber> allPhoneNumbers, CountryPhoneTemplate countryPhoneTemplate){
        for(PhoneNumber phoneNumber:allPhoneNumbers){
            phoneNumber.matches(countryPhoneTemplate);
        }
    }

}
