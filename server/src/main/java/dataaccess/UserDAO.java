package dataaccess;

public interface UserDAO {

    public boolean getUser(String username);
    public void createUser(String username, String password, String email);
}
