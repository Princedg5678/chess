package dataaccess;

import java.util.Map;

public class SQLAuthDAO implements AuthDAO{
    @Override
    public String generateToken(String username) {
        return "";
    }

    @Override
    public void deleteToken(String authToken) {

    }

    @Override
    public boolean checkToken(String authToken) {
        return false;
    }

    @Override
    public String getUsername(String token) {
        return "";
    }

    @Override
    public Map<String, String> getAuthData() {
        return Map.of();
    }

    @Override
    public void clearAuthData() {

    }
}
