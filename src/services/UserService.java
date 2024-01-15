package services;

import models.User;

import java.util.HashMap;

public class UserService {

    private static UserService userServiceInstance = null;

    private UserService() {

    }
    public static UserService getInstance() {
        if(userServiceInstance == null) {
            userServiceInstance = new UserService();
        }
        return userServiceInstance;
    }

    private HashMap<String, User> userHashMap = new HashMap<>(); // <userId, User>

    public User createUser(String email, String name, String password) {
        validatePassword(password);
        User newUser = new User(email, name, password);
        userHashMap.put(newUser.getUserId(), newUser);
        return newUser;
    }

    public User logInUser(String userId, String password) {
      User user = getUser(userId);
      if(user.getPassword() == password) {
        return user;
      }
        // <todo> - make a ValidationException
        throw new RuntimeException("Password is Incorrect for userID : " + userId);
    }

    public User getUser(String userId) {
        User user = userHashMap.get(userId);
        if(user != null) {
          return user;
        }

        // user is not present, throwing exception
        // <todo> - make a ValidationException
        throw new RuntimeException("UserId is not present : " + userId);
    }

    private void validatePassword(String password) throws RuntimeException {
        // <todo> - make a ValidationException
        if(password.length() < 8) throw new RuntimeException("Password should be at least 8 characters long");
        // can write more validations such as password should contain one uppercase and one numeral
    }
}
