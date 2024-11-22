package dataaccess;

public interface AuthDAO {

    public String generateToken(String username);
    public void deleteToken(String authToken);
    public boolean checkToken(String authToken);
    public void clearAuthData();
}
