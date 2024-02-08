package com.ramanora.stona.bean;

/**
 * Created by abc on 11/27/2017.
 */

public class Qrcode {

    String Registrationno, Name,Email,Phone, Company, Designation, Country, City, Typeofvisitor, Other;

    public Qrcode()
    {}

    public String getRegistrationno() {
        return Registrationno;
    }

    public void setRegistrationno(String registrationno) {
        Registrationno = registrationno;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getTypeofvisitor() {
        return Typeofvisitor;
    }

    public void setTypeofvisitor(String typeofvisitor) {
        Typeofvisitor = typeofvisitor;
    }

    public String getOther() {
        return Other;
    }

    public void setOther(String other) {
        Other = other;
    }
}
