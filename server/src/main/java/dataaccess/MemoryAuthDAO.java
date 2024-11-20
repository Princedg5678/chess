package dataaccess;

import java.util.HashMap;
import java.util.Map;

import java.util.UUID;

public class MemoryAuthDAO implements AuthDAO{

    Map<String, String> authMap = new HashMap<>();

    public String generateToken(String username){
        String newToken = UUID.randomUUID().toString();
        authMap.put(newToken, username);
        return newToken;
    }

    public boolean checkToken(String authToken){
            return authMap.containsKey(authToken);
    }

    public void deleteToken(String authToken){
        authMap.remove(authToken, authMap.get(authToken));
    }

}
