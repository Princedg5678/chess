package dataaccess;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLUserDAO implements UserDAO{

    public SQLUserDAO() throws DataAccessException {
        DatabaseManager.createDatabase();
        createDataTables();
    }

    private final String[] statements = {
            """
            CREATE TABLE IF NOT EXISTS  users (
              `username` varchar(256) NOT NULL,
              `password` varchar(256) NOT NULL,
              `email` varchar(256) NOT NULL,
              PRIMARY KEY (`username`)
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
            """,
            """
            CREATE TABLE IF NOT EXISTS  games (
              `gameID` int NOT NULL AUTO_INCREMENT,
              `whiteUsername` varchar(256),
              `blackUsername` varchar(256),
              `gameName` varchar(256) NOT NULL,
              `game` longtext NOT NULL,
              PRIMARY KEY (`gameID`)
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
            """,
            """
            CREATE TABLE IF NOT EXISTS  auth (
              `authToken` varchar(256) NOT NULL,
              `username` varchar(256) NOT NULL,
              PRIMARY KEY (`authToken`),
              INDEX (username)
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
            """
    };


    private void createDataTables() throws DataAccessException {
        try (var conn = DatabaseManager.getConnection()){
            for (String currentStatement: statements){
                try (PreparedStatement preparedStatement = conn.prepareStatement(currentStatement)) {
                    preparedStatement.executeUpdate();
                }
            }

        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public boolean checkUser(String username) {
        return false;
    }

    @Override
    public void createUser(String username, String password, String email) {

    }

    @Override
    public void clearUsers() throws DataAccessException {
        try (var conn = DatabaseManager.getConnection()){
            try (PreparedStatement preparedStatement = conn.prepareStatement("TRUNCATE TABLE user")) {
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    //do this for the other functions

    @Override
    public String getPassword(String username) {
        return "";
    }
}
