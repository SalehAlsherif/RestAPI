package com.example.demo.controllers;
import models.CountryPhoneTemplate;
import models.PhoneNumber;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {PhoneNumbersController.class})
public class PhoneNumbersControllerTests {
    @Test
    void filterBy_ValidNumbers_ReturnsAFilteredList() {
        //Arrange
        CountryPhoneTemplate countryPhoneTemplate=new CountryPhoneTemplate("Cameroon","\\(237\\)\\ ?[2368]\\d{7,8}$","\\(237\\)\\ .*");
        List<PhoneNumber>phoneNumbers=new ArrayList();
        phoneNumbers.add(new PhoneNumber("(237) 66222840"));
        phoneNumbers.add(new PhoneNumber("(237) 32434233"));
        phoneNumbers.add(new PhoneNumber("(237) 32323233"));
        int expected=phoneNumbers.size();
        //Act
        PhoneNumbersController.matchByCountryTemplate(phoneNumbers,countryPhoneTemplate);
        List<PhoneNumber> actual =PhoneNumbersController.filterBy(phoneNumbers,true);
        //Assert
        assertEquals(expected,actual.size());
    }
    @Test
    void filterBy_InvalidNumbers_ReturnsAnEmptyList(){
        //Arrange
        CountryPhoneTemplate countryPhoneTemplate=new CountryPhoneTemplate("Cameroon","\\(237\\)\\ ?[2368]\\d{7,8}$","\\(237\\)\\ .*");
        List<PhoneNumber>phoneNumbers=new ArrayList();
        phoneNumbers.add(new PhoneNumber("(237) 32323S85"));
        phoneNumbers.add(new PhoneNumber("(237) 323232d337"));
        phoneNumbers.add(new PhoneNumber("(237) 3232323379e7"));
        phoneNumbers.add(new PhoneNumber("(237) 323hfhd7"));
        int expected=0;
        //Act
        PhoneNumbersController.matchByCountryTemplate(phoneNumbers,countryPhoneTemplate);
        List<PhoneNumber> actual =PhoneNumbersController.filterBy(phoneNumbers,true);
        //Assert
        assertEquals(expected,actual.size());
    }
    @Test
    void filterBy_MixedInput_ReturnsFilteredListOfInvalidNumbers(){
        //Arrange
        CountryPhoneTemplate countryPhoneTemplate=new CountryPhoneTemplate("Cameroon","\\(237\\)\\ ?[2368]\\d{7,8}$","\\(237\\)\\ .*");
        List<PhoneNumber>phoneNumbers=new ArrayList();
        phoneNumbers.add(new PhoneNumber("(237) 6622284054"));
        phoneNumbers.add(new PhoneNumber("(237) 32323S45"));

        phoneNumbers.add(new PhoneNumber("(237) 324342333"));
        phoneNumbers.add(new PhoneNumber("(237) 323232343"));
        phoneNumbers.add(new PhoneNumber("(237) 323232344"));
        int expected=2;
        //Act
        PhoneNumbersController.matchByCountryTemplate(phoneNumbers,countryPhoneTemplate);
        List<PhoneNumber> actual = PhoneNumbersController.filterBy(phoneNumbers,false);
        //Assert
        assertEquals(expected,actual.size());
    }
    @Test
    void filterBy_EmptyInput_ReturnsEmptyList(){
        //Arrange
        CountryPhoneTemplate countryPhoneTemplate=new CountryPhoneTemplate("Cameroon","\\(237\\)\\ ?[2368]\\d{7,8}$","\\(237\\)\\ .*");
        List<PhoneNumber>phoneNumbers=new ArrayList();
        int expected=0;
        //Act
        PhoneNumbersController.matchByCountryTemplate(phoneNumbers,countryPhoneTemplate);
        List<PhoneNumber> actual =PhoneNumbersController.filterBy(phoneNumbers,false);
        //Assert
        assertEquals(expected,actual.size());
    }
    @Test
    void filterBy_MixedInput_ReturnsFilteredListOfValidNumbers(){
        //Arrange
        CountryPhoneTemplate countryPhoneTemplate=new CountryPhoneTemplate("Cameroon","\\(237\\)\\ ?[2368]\\d{7,8}$","\\(237\\)\\ .*");
        List<PhoneNumber>phoneNumbers=new ArrayList();
        phoneNumbers.add(new PhoneNumber("(237) 66222840"));
        phoneNumbers.add(new PhoneNumber("(237) 324342334"));
        phoneNumbers.add(new PhoneNumber("(237) 32323233"));
        phoneNumbers.add(new PhoneNumber("(237) 32323S45"));

        phoneNumbers.add(new PhoneNumber("(237) 323232d337"));
        phoneNumbers.add(new PhoneNumber("(237) 3232323379e7"));
        phoneNumbers.add(new PhoneNumber("(237) 323hfhd7"));
        int expected=3;
        //Act
        PhoneNumbersController.matchByCountryTemplate(phoneNumbers,countryPhoneTemplate);
        List<PhoneNumber> actual =PhoneNumbersController.filterBy(phoneNumbers,true);
        //Assert
        assertEquals(expected,actual.size());
    }

}
