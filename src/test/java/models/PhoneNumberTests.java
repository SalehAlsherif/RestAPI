package models;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes ={PhoneNumber.class,CountryPhoneTemplate.class})
public class PhoneNumberTests {

    @Test
    void matches_ValidRegex_ShouldSucceed(){
        //Arrange
        CountryPhoneTemplate countryPhoneTemplate=new CountryPhoneTemplate("Cameroon","\\(237\\)\\ ?[2368]\\d{7,8}$","\\(237\\)\\ .*");
        PhoneNumber phoneNumber=new PhoneNumber("(237) 66222840");
        //Act
        phoneNumber.matches(countryPhoneTemplate);
        //Assert
        assertNotNull(phoneNumber.getCountry());
        assertTrue(phoneNumber.isValid());
    }
    @Test
    void matches_UnmatchedPhoneNumber_ShouldFail(){
        //Arrange
        CountryPhoneTemplate countryPhoneTemplate=new CountryPhoneTemplate("Cameroon","\\(237\\)\\ ?[2368]\\d{7,8}$","\\(237\\)\\ .*");
        PhoneNumber phoneNumber=new PhoneNumber("(212) 633963130");
        //Act
        phoneNumber.matches(countryPhoneTemplate);
        //Assert
        assertNull(phoneNumber.getCountry());
        assertFalse(phoneNumber.isValid());
    }
    @Test
    void matches_InvalidCountryCodeRegex_ShouldFail(){
        //Arrange
        CountryPhoneTemplate countryPhoneTemplate=new CountryPhoneTemplate("Cameroon","\\(237\\)\\ ?[2368]\\d{7,8}$","\\(238\\)\\ ");
        PhoneNumber phoneNumber=new PhoneNumber("(237) 66222840");
        //Act
        phoneNumber.matches(countryPhoneTemplate);
        //Assert
        assertNull(phoneNumber.getCountry());
        assertFalse(phoneNumber.isValid());
    }

}
