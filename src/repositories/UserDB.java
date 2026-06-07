package repositories;

import models.User;

import java.util.HashMap;

public class UserDB {

    private HashMap<String, User> userMap;

    public UserDB(){
        this.userMap = new HashMap<>();
    }

    public void saveUser(User user){
        String email = user.getEmail();
        this.userMap.put(email, user);
    }

    public int generateNewId(){
        return this.userMap.size() + 1;
    }

    public User getUserByEmail(String email){
        return this.userMap.get(email);
    }

}
