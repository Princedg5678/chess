package dataaccess;

import chess.ChessGame;
import model.GameData;
import model.GameName;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MemoryGameDAO implements GameDAO{

    Map<Integer, GameData> gameMap = new HashMap<>();

    public Integer createGame(GameName gameName){
        int gameID = generateID();
        GameData newGame = new GameData(gameID, null, null, gameName.gameName(), new ChessGame());
        gameMap.put(gameID, newGame);
        return gameID;
    }

    private Integer generateID(){
        return new Random().nextInt(9000) + 1000;
    }


}
