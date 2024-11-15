package service;

import org.mindrot.jbcrypt.BCrypt;
import dataaccess.DataAccessException;
import dataaccess.MemoryAuthDAO;
import dataaccess.MemoryUserDAO;
import model.RegisterUser;
import model.UserData;

public class UserService {

    MemoryAuthDAO authDAO;
    MemoryUserDAO userDAO;

    public UserService(MemoryAuthDAO authDao, MemoryUserDAO userDao) {
        this.authDAO = authDao;
        this.userDAO = userDao;
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    boolean verifyUser(String username, String password) {
        // read the previously hashed password from the database

        return true;
    }



    public UserData registerUser(RegisterUser newUser) throws DataAccessException{

        String username = newUser.username();
        String password = newUser.password();
        String email = newUser.email();

        if (username == null || password == null || email == null){
            throw new DataAccessException("Bad Request");
        }

        if (userDAO.getUser(username)){
            throw new DataAccessException("Username already taken");
        }

        String hashedPassword = hashPassword(password);

        userDAO.createUser(username, hashedPassword, email);

        String authToken = authDAO.generateToken();

        return new UserData(authToken, username);
    }

    private void clear(){

    }
}
