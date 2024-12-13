package dataaccess;

import model.GameData;
import model.GameName;

import java.util.Map;

public class SQLGameDAO implements GameDAO{
    @Override
    public Integer createGame(GameName gameName) {
        return 0;
    }

    @Override
    public Map<Integer, GameData> listGames() {
        return Map.of();
    }

    @Override
    public GameData findGame(Integer gameID) {
        return null;
    }

    @Override
    public void addPlayer(GameData gameData, String playerColor, String username) {

    }

    @Override
    public void clearGames() {

    }
}
