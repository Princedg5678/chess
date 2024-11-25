package dataaccess;

import model.GameData;
import model.GameName;

import java.util.Map;

public interface GameDAO {
    public Integer createGame(GameName gameName);
    public Map<Integer, GameData> listGames();
    public GameData findGame(Integer gameID);
    public void addPlayer(GameData gameData, String playerColor, String username);
    public void clearGames();

}
