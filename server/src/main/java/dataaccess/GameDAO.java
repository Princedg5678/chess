package dataaccess;

import model.GameData;
import model.GameName;

import java.util.Map;

public interface GameDAO {
    public Integer createGame(GameName gameName);
    public Map<Integer, GameData> listGames();
    public void clearGames();

}
