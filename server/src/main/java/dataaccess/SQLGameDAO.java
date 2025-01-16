package dataaccess;

import chess.ChessGame;
import com.google.gson.Gson;
import model.GameData;
import model.GameID;
import model.GameName;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class SQLGameDAO implements GameDAO {
    @Override
    public Integer createGame(GameName gameName) throws DataAccessException {
        try (var conn = DatabaseManager.getConnection()){
            try (PreparedStatement preparedStatement =
                         conn.prepareStatement("INSERT INTO games (whiteUsername, blackUsername, " +
                                 "gameName, game) VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, null);
                preparedStatement.setString(2, null);
                preparedStatement.setString(3, new Gson().toJson(gameName));
                preparedStatement.setString(4, new Gson().toJson(new ChessGame()));
                preparedStatement.executeUpdate();
                var returnKeys = preparedStatement.getGeneratedKeys();
                if (returnKeys.next()){
                    return returnKeys.getInt(1);
                }
            }

        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }

        return null;
    }

    @Override
    public Map<Integer, GameData> listGames() throws DataAccessException {
        Map<Integer, GameData> gameMap = new HashMap<>();
        try (var conn = DatabaseManager.getConnection()) {
            try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM games")) {
                try (var result = preparedStatement.executeQuery()){
                    while (result.next()){
                        int gameID = result.getInt(1);
                        String whiteUsername = result.getString(2);
                        String blackUsername = result.getString(3);
                        String gameName = result.getString(4);
                        ChessGame game = new Gson().fromJson(result.getString(5), ChessGame.class);
                        GameData currentGame = new GameData(gameID, whiteUsername, blackUsername, gameName, game);
                        gameMap.put(gameID, currentGame);
                    }
                }
            }
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }

        return gameMap;
    }

    @Override
    public GameData findGame(Integer gameID) throws DataAccessException {
        return null;
    }

    @Override
    public void addPlayer(GameData gameData, String playerColor, String username) throws DataAccessException {

    }

    @Override
    public void clearGames() throws DataAccessException {
        try (var conn = DatabaseManager.getConnection()){
            try (PreparedStatement preparedStatement = conn.prepareStatement("TRUNCATE TABLE games")) {
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }
}

