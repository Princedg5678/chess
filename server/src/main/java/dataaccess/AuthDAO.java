package dataaccess;

import java.util.Map;

public interface AuthDAO {

    public String generateToken(String username);
    public void deleteToken(String authToken);
    public boolean checkToken(String authToken);
    public String getUsername(String token);
    public Map<String, String> getAuthData();
    public void clearAuthData();
}
