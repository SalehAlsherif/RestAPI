package models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumber {
    private String phoneNumber;
    private boolean valid;
    private String country;

    public PhoneNumber(){

    }

    public PhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isValid() {
        return valid;
    }

    public String getCountry() {
        return country;
    }

    public void matches(CountryPhoneTemplate countryPhoneTemplate){
        if(matches(countryPhoneTemplate.getCountryCodeRegex(),countryPhoneTemplate.getCountry())){
            setValid(matches(countryPhoneTemplate.getValidPhoneNumbersRegex(),countryPhoneTemplate.getCountry()));
        }
    }

    private boolean matches(String regex,String country){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher= pattern.matcher(getPhoneNumber());
        if(matcher.find()){
            setCountry(country);
            return true;
        }else {
            return false;
        }
    }
}
