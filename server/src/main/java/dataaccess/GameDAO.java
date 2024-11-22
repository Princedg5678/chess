package dataaccess;

import model.GameName;

public interface GameDAO {
    public Integer createGame(GameName gameName);
    public void clearGames();

}
