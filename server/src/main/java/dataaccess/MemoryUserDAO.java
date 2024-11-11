package dataaccess;

import java.util.HashSet;
import java.util.Set;

public class MemoryUserDAO implements UserDAO{

    Set<String> usernameSet = new HashSet<String>();

    public boolean getUser(String username){
        return usernameSet.contains(username);
    }

    public void createUser(String username, String password, String email){
        usernameSet.add(username);

        //Add username, password, and email to a new variable

    }


}
