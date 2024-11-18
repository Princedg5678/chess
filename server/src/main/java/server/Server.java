package server;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import dataaccess.MemoryAuthDAO;
import dataaccess.MemoryGameDAO;
import dataaccess.MemoryUserDAO;
import model.Error;
import model.RegisterUser;
import model.UserData;
import service.UserService;
import spark.*;

import java.util.Objects;

public class Server {

    MemoryAuthDAO authDao = new MemoryAuthDAO();
    MemoryGameDAO gameDao = new MemoryGameDAO();
    MemoryUserDAO userDao = new MemoryUserDAO();
    UserService userService = new UserService(authDao, userDao);

    public Server(){

    }

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        // Register your endpoints and handle exceptions here.
        Spark.post("/user", this::registerUser);

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

        return new Gson().toJson(newUser);
    }


    private Object clearDb(Request req, Response res) {


        return "";
    }











    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }


}
