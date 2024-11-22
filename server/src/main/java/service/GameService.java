package service;

import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import model.GameData;
import model.GameList;
import model.GameName;
import model.GameResult;

import java.util.ArrayList;
import java.util.Map;

public class GameService {

    AuthDAO authDAO;
    GameDAO gameDAO;

    public GameService(AuthDAO authDao, GameDAO gameDao){
        this.gameDAO = gameDao;
        this.authDAO = authDao;
    }


    public GameList listGames(String authToken) throws DataAccessException {
        if (!authDAO.checkToken(authToken)){
            throw new DataAccessException("Error: unauthorized");
        }
        Map<Integer, GameData> gameMap = gameDAO.listGames();
        ArrayList<GameResult> gameResults = new ArrayList<>();

        for (GameData gameData: gameMap.values()){
            GameResult tempResult = new GameResult(gameData.gameID(), gameData.whiteUsername(),
                    gameData.blackUsername(), gameData.gameName());
            gameResults.add(tempResult);
        }


        return new GameList(gameResults);
    }

    public Integer createGame(String authToken, GameName gameName) throws DataAccessException {
        if (!authDAO.checkToken(authToken)){
            throw new DataAccessException("Error: unauthorized");
        }
        if (gameName == null){
            throw new DataAccessException("Error: bad request");
        }
        return gameDAO.createGame(gameName);
    }


}
