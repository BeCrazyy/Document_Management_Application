package models;

import java.util.UUID;

public class User {
    private final String userId;
    private String name;
    private String email;
    private String password; // hashed password

    public User(String email, String name, String password) {
        this.userId = UUID.randomUUID().toString(); // auto-generated
        this.email = email.toLowerCase();
        this.name = name;
        this.password = password; // <todo> hashed it
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    // toString method
    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
