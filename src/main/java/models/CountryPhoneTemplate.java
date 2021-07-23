package models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountryPhoneTemplate {
    private String country;
    private String validPhoneNumbersRegex;
    private String  countryCodeRegex;
    public CountryPhoneTemplate(String country, String validPhoneNumbersRegex, String countryCodeRegex) {
        this.country = country;
        this.validPhoneNumbersRegex = validPhoneNumbersRegex;
        this.countryCodeRegex=countryCodeRegex;
    }
    public CountryPhoneTemplate(){

    }
    public String getCountryCodeRegex() {
        return countryCodeRegex;
    }

    public String getCountry() {
        return country;
    }

    public String getValidPhoneNumbersRegex() {
        return validPhoneNumbersRegex;
    }
}
