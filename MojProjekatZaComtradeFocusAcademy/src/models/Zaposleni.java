/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Anta
 */
public class Zaposleni{
    private int id;
    private String name;
    private String lastname;
    private String address;
    private String gender;
    private String subject;
    private String date;

    public Zaposleni(int id, String name, String lastname,String address, String gender, String subject,String date) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.gender = gender;
        this.subject = subject;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public String getSubject() {
        return subject;
    }

    public String getDate() {
        return date;
    }

    public String getLastname() {
        return lastname;
    }
    
    
}
