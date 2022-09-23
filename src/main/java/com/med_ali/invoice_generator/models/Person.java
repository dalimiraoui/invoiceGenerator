package com.med_ali.invoice_generator.models;

public class Person {
    private String firstname;
    private String lastname;
    private String codeTva;
    private String address;

    public Person(String firstname, String lastname, String codeTva, String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.codeTva = codeTva;
        this.address = address;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCodeTva() {
        return codeTva;
    }

    public void setCodeTva(String codeTva) {
        this.codeTva = codeTva;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
