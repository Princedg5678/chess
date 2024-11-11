package dataaccess;

import java.util.HashSet;
import java.util.Set;

public class MemoryUserDAO implements UserDAO{

    Set<String> usernameSet = new HashSet<String>();

    public boolean getUser(String username){
        return usernameSet.contains(username);
    }


}
