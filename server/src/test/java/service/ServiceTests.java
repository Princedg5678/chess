package service;

import dataaccess.DataAccessException;
import dataaccess.MemoryAuthDAO;
import dataaccess.MemoryGameDAO;
import dataaccess.MemoryUserDAO;
import model.GameName;
import model.RegisterUser;
import model.LoginUser;
import model.UserData;
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
    GameService gameService = new GameService(authDao, gameDao);
    RegisterUser newUser = new RegisterUser("Testing", "is", "fun!");


    @Test
    @Order(1)
    @DisplayName("registerUserTest")
    public void register() throws DataAccessException {
        userService.registerUser(newUser);
        assertTrue(userDao.checkUser(newUser.username()));
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

    @Test
    @Order(4)
    @DisplayName("loginTest")
    public void loginUser() throws DataAccessException {
        RegisterUser user = new RegisterUser("Cool", "Awesome", "Crazy");
        userService.registerUser(user);
        LoginUser loginUser =  new LoginUser("Cool", "Awesome");
        userService.loginUser(loginUser);
        assertTrue(userDao.checkUser(loginUser.username()));
    }

    @Test
    @Order(5)
    @DisplayName("loginTest2")
    public void failedLogin() throws DataAccessException {
        LoginUser loginUser =  new LoginUser("Oh", "No");
        assertThrows(DataAccessException.class, ()-> userService.loginUser(loginUser));
    }

    @Test
    @Order(6)
    @DisplayName("logoutTest")
    public void logoutUser() throws DataAccessException {
        RegisterUser user = new RegisterUser("Crispy", "Chicken", "Nuggets");
        UserData registerResult = userService.registerUser(user);
        userService.logoutUser(registerResult.authToken());
        assertFalse(authDao.checkToken(registerResult.authToken()));
    }

    @Test
    @Order(7)
    @DisplayName("logoutTest2")
    public void logoutFail() throws DataAccessException {
        RegisterUser user = new RegisterUser("Cheeseburger", "Fries", "Milkshake");
        userService.registerUser(user);
        String badToken = "McDonald's";
        assertThrows(DataAccessException.class, ()-> userService.logoutUser(badToken));

    }

    @Test
    @Order(8)
    @DisplayName("createGameTest")
    public void createGame() throws DataAccessException {
        RegisterUser user = new RegisterUser("Yet", "Another", "Test");
        UserData loginResult = userService.registerUser(user);

        GameName gameName = new GameName("FIGHT ME");

        gameService.createGame(loginResult.authToken(), gameName);
        assertNotNull(gameService.listGames(loginResult.authToken()));
    }

    @Test
    @Order(9)
    @DisplayName("createGameTest2")
    public void CreateGameFail() throws DataAccessException {
        RegisterUser user = new RegisterUser("Mac", "And", "Cheese");
        userService.registerUser(user);
        String badToken = "Bowl";
        GameName gameName = new GameName("SHOWTIME");

        assertThrows(DataAccessException.class, ()-> gameService.createGame(badToken, gameName));

    }

}
