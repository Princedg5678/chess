package dataaccess;

public interface UserDAO {

    public boolean checkUser(String username);
    public void createUser(String username, String password, String email);
    public void clearUsers() throws DataAccessException;
    public String getPassword(String username);
}
