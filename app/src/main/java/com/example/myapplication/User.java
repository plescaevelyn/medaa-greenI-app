package com.example.myapplication;

public class User {
    public String fullName;
    public String email;
    public String password;
    public int score;

    public User(){
    }

    public User(String fullname, String email, String password, int score){
        this.fullName = fullname;
        this.email = email;
        this.password = password;
        this.score = 0;
    }
}
