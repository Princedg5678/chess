package dataaccess;

import model.RegisterUser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MemoryUserDAO implements UserDAO{

    Set<String> usernameSet = new HashSet<String>();
    Map<String, RegisterUser> newUserData = new HashMap<>();

    public boolean getUser(String username){
        return usernameSet.contains(username);
    }

    public void createUser(String username, String password, String email){
        usernameSet.add(username);

        newUserData.put(username, new RegisterUser(username, password, email));

    }

    public void clearUsers(){
        usernameSet.clear();
        newUserData.clear();
    }

    //Make sure clear function is finished


}
