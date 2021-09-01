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
public class Ucenici {
    private int id;
    private String firstname;
    private String lastname;
    private String gender;
    private String address;
    private String subjects;
    private String profesor;
    private String finalGrade;
    private byte[] image;
    private String username;
    private String password;

    public Ucenici(int id, String firstname
            , String lastname, String gender
            , String address, String subjects
            , String profesor,String finalGrade
            , byte[]  image) {
        
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.address = address;
        this.subjects = subjects;
        this.profesor = profesor;
        this.finalGrade = finalGrade;
        this.image =image;
    }

    public Ucenici(int id, String firstname
            , String lastname, String gender
            , String address, String subjects
            , String profesor, String finalGrade
            , byte[] image, String username
            , String password) {
        
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.address = address;
        this.subjects = subjects;
        this.profesor = profesor;
        this.finalGrade = finalGrade;
        this.image = image;
        this.username = username;
        this.password = password;
    }

    public Ucenici(String subjects, String profesor, String finalGrade) {
        this.subjects = subjects;
        this.profesor = profesor;
        this.finalGrade = finalGrade;
    }
    
    
    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getSubjects() {
        return subjects;
    }

    public String getProfesor() {
        return profesor;
    }
    public String getFinalGrade() {
        return finalGrade;
    }
    public byte[] getImage() {
        return image;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
}

