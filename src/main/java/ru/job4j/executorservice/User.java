package ru.job4j.executorservice;

public class User {

    private final String user;
    private final String email;

    public User(String user, String eMail) {
        this.user = user;
        this.email = eMail;
    }

    public String getUser() {
        return user;
    }

    public String getEmail() {
        return email;
    }
}
