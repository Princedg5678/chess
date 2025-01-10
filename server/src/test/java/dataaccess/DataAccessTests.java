package dataaccess;

import model.RegisterUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import service.ClearDataService;
import service.GameService;
import service.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DataAccessTests {

    SQLAuthDAO authDao = new SQLAuthDAO();
    SQLGameDAO gameDao = new SQLGameDAO();
    SQLUserDAO userDao = new SQLUserDAO();
    UserService userService = new UserService(authDao, userDao);
    GameService gameService = new GameService(authDao, gameDao);
    ClearDataService clearDataService = new ClearDataService(authDao, gameDao, userDao);

    Connection conn = DatabaseManager.getConnection();

    public DataAccessTests() throws DataAccessException {
    }

    @Test
    @Order(1)
    @DisplayName("clearUsersTest")
    public void clear() throws DataAccessException, SQLException {
        userService.registerUser(new RegisterUser("Im","Just","Junk"));
        userService.registerUser(new RegisterUser("Taking","Out","Trash!"));
        userService.registerUser(new RegisterUser("Going","To","Burn"));

        clearDataService.clearData();

        try (PreparedStatement preparedStatement =
                     conn.prepareStatement("SELECT count(*) FROM users")){
            try (var result = preparedStatement.executeQuery()){
                if (result.next()){
                    assertEquals(0, result.getInt(1));
                }
            }
        }
    }


    @Test
    @Order(2)
    @DisplayName("createUserTest")
    public void create() throws DataAccessException, SQLException {
        clearDataService.clearData();
        userService.registerUser(new RegisterUser("Agh","Not","Again!"));

        try (PreparedStatement preparedStatement =
                     conn.prepareStatement("SELECT username FROM users WHERE username = ?")){
            preparedStatement.setString(1, "Agh");
            try (var result = preparedStatement.executeQuery()){
                if (result.next()){
                    assertEquals("Agh", result.getString(1));
                }
            }
        }
    }
}
