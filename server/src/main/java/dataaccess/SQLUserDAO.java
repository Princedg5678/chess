package dataaccess;

public class SQLUserDAO implements UserDAO{
    @Override
    public boolean checkUser(String username) {
        return false;
    }

    @Override
    public void createUser(String username, String password, String email) {

    }

    @Override
    public void clearUsers() {

    }

    @Override
    public String getPassword(String username) {
        return "";
    }
}
