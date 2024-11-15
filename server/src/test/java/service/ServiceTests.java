package service;

import dataaccess.DataAccessException;
import dataaccess.MemoryAuthDAO;
import dataaccess.MemoryGameDAO;
import dataaccess.MemoryUserDAO;
import model.RegisterUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import server.Server;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceTests {

    MemoryAuthDAO authDao = new MemoryAuthDAO();
    MemoryGameDAO gameDao = new MemoryGameDAO();
    MemoryUserDAO userDao = new MemoryUserDAO();
    UserService userService = new UserService(authDao, userDao);
    RegisterUser newUser = new RegisterUser("Testing", "is", "fun!");


    @Test
    @Order(1)
    @DisplayName("registerUserTest")
    public void register() throws DataAccessException {
        userService.registerUser(newUser);
        assertTrue(userDao.getUser(newUser.username()));
    }

    @Test
    @Order(2)
    @DisplayName("registerUserTest2")
    public void registerBadRequest() throws DataAccessException {
        RegisterUser badUser = new RegisterUser("Hello", "World!", "");
        assertThrows(DataAccessException.class, ()-> userService.registerUser(badUser));
    }

    @Test
    @Order(3)
    @DisplayName("registerUserTest3")
    public void registerRepeatedUser() throws DataAccessException {
        RegisterUser repeatUser = new RegisterUser("Hungry", "For", "Cheese");
        userService.registerUser(repeatUser);
        assertThrows(DataAccessException.class, ()-> userService.registerUser(repeatUser));
    }


}
