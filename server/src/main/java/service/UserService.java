package service;

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

    public UserData registerUser(RegisterUser newUser) throws DataAccessException{

        String username = newUser.username();
        String password = newUser.password();
        String email = newUser.email();

        if (userDAO.getUser(username)){
            throw new DataAccessException("Username already taken");
        }

        return null;
    }

    private void clear(){

    }
}
