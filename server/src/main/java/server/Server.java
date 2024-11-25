package server;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import dataaccess.MemoryAuthDAO;
import dataaccess.MemoryGameDAO;
import dataaccess.MemoryUserDAO;
import model.*;
import model.Error;
import service.GameService;
import service.UserService;
import service.ClearDataService;
import spark.*;

import java.util.ArrayList;
import java.util.Objects;

public class Server {

    MemoryAuthDAO authDao = new MemoryAuthDAO();
    MemoryGameDAO gameDao = new MemoryGameDAO();
    MemoryUserDAO userDao = new MemoryUserDAO();
    UserService userService = new UserService(authDao, userDao);
    GameService gameService = new GameService(authDao, gameDao);
    ClearDataService clearService = new ClearDataService(authDao, gameDao, userDao);

    public Server(){

    }

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        // Register your endpoints and handle exceptions here.
        Spark.post("/user", this::registerUser);
        Spark.post("/session", this::loginUser);
        Spark.delete("/session", this::logoutUser);
        Spark.get("/game", this::listGames);
        Spark.post("/game", this::createGame);
        Spark.put("/game", this::joinGame);
        Spark.delete("/db", this::clearDb);

        Spark.exception(DataAccessException.class, this::catchError);

        Spark.awaitInitialization();
        return Spark.port();
    }

    private void catchError(Exception ex, Request req, Response res){
        String error = ex.getMessage();
        Error convertedError = new Error(error);

        if (Objects.equals(error, "Error: already taken")){
            res.status(403);
            res.body(new Gson().toJson(convertedError));
        }
        else if (Objects.equals(error, "Error: bad request")){
            res.status(400);
            res.body(new Gson().toJson(convertedError));
        }
        else if (Objects.equals(error, "Error: unauthorized")){
            res.status(401);
            res.body(new Gson().toJson(convertedError));
        }
        else {
            res.status(500);
            res.body(new Gson().toJson(convertedError));
        }
    }

    private Object registerUser(Request req, Response res) throws DataAccessException {
        RegisterUser registerUser = new Gson().fromJson(req.body(), RegisterUser.class);

        UserData newUser = userService.registerUser(registerUser);

        res.status(200);

        return new Gson().toJson(newUser);
    }
    //check tests for wrong password
    private Object loginUser(Request req, Response res) throws DataAccessException {
        LoginUser loginUser = new Gson().fromJson(req.body(), LoginUser.class);

        UserData loggedInUser = userService.loginUser(loginUser);

        res.status(200);

        return new Gson().toJson(loggedInUser);
    }

    private Object logoutUser(Request req, Response res) throws DataAccessException {

        String authToken = req.headers("authorization");

        userService.logoutUser(authToken);

        res.status(200);

        return "";
    }
    private Object listGames(Request req, Response res) throws DataAccessException {
        String authToken = req.headers("authorization");

        GameList ourGames = gameService.listGames(authToken);

        res.status(200);

        return new Gson().toJson(ourGames);
    }
    private Object createGame(Request req, Response res) throws DataAccessException {
        String authToken = req.headers("authorization");
        GameName gameName = new Gson().fromJson(req.body(), GameName.class);

        int gameID = gameService.createGame(authToken, gameName);

        res.status(200);

        return new Gson().toJson(new GameID(gameID));
    }
    private Object joinGame(Request req, Response res) throws DataAccessException {
        String authToken = req.headers("authorization");
        JoinRequest joinRequest = new Gson().fromJson(req.body(), JoinRequest.class);

        gameService.joinGame(authToken, joinRequest.playerColor(), joinRequest.gameID());

        res.status(200);

        return "";
    }
    private Object clearDb(Request req, Response res) {

        clearService.clearData();

        res.status(200);

        return "";
    }











    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }


}
