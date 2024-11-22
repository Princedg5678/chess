package service;

import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import model.GameName;

public class GameService {

    AuthDAO authDAO;
    GameDAO gameDAO;

    public GameService(AuthDAO authDao, GameDAO gameDao){
        this.gameDAO = gameDao;
        this.authDAO = authDao;
    }


    public void listGames(String authToken) throws DataAccessException {
        if (!authDAO.checkToken(authToken)){
            throw new DataAccessException("Error: unauthorized");
        }
        //finish after completing createGame;

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
