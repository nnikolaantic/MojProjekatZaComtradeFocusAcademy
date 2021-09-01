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
public class NoviZaposleni {
    private int id;
    private String username,password,rank;

    public NoviZaposleni(int id, String username, String password, String rank) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.rank = rank;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRank() {
        return rank;
    }
    
    
}
