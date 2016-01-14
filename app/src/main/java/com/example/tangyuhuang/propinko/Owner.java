package com.example.tangyuhuang.propinko;

/**
 * Created by Thais on 17/12/2015.
 */
public class Owner {

    private String id, lastname, mail, defaultPhone, password, firstname, description, streetNumber, adress, city, postalCode, civility, accountDate;

    Owner(String id, String lastname, String mail, String defaultPhone, String password, String firstname, String description, String streetNumber, String adress, String city, String postalCode, String civility, String accountDate){
        this.id = id;
        this.lastname = lastname;
        this.mail = mail;
        this.defaultPhone = defaultPhone;
        this.password = password;
        this.firstname = firstname;
        this.description = description;
        this.streetNumber = streetNumber;
        this.adress = adress;
        this.city = city;
        this.postalCode = postalCode;
        this.civility = civility;
        this.accountDate = accountDate;

    }

    public String toString()
    {
        return id + "_" + lastname + "_" + mail + "_" + defaultPhone + "_" + password + "_" + firstname + "_" + description + "_" +streetNumber+ "_" + adress + "_" + city + "_" + postalCode + "_" + civility + "_" +accountDate;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return lastname;
    }

    public String getMail(){
        return mail;
    }

    public String getDefaultPhone(){
        return defaultPhone;
    }

    public String getPassword(){
        return password;
    }

    public String getFirstname(){
        return firstname;
    }

    public String getDescription(){
        return description;
    }

    public String getStreetNumber(){
        return streetNumber;
    }
    public String getAdress(){
        return adress;
    }

    public String getCity(){
        return city;
    }

    public String getPostalCode(){
        return postalCode;
    }

    public String getCivility(){
        return civility;
    }

    public String getAccountDate(){
        return accountDate;
    }



}
