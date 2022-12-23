package com.example.delivery2;

public class User {
    private String fio;
    private String login;
    private String password;
    private String tel;
    private String email;

    public User(String login,String password) {
        this.login = login;
        this.password = password;

    }

    public User(String fio,String login,String password, String tel, String email) {
     this.fio = fio;
     this.login = login;
     this.password = password;
     this.tel = tel;
     this.email = email;

    }

    public User() {

    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }







}
