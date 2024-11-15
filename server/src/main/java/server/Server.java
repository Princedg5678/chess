package server;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import dataaccess.MemoryAuthDAO;
import dataaccess.MemoryGameDAO;
import dataaccess.MemoryUserDAO;
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

        //This line initializes the server and can be removed once you have a functioning endpoint 
        Spark.init();

        Spark.awaitInitialization();
        return Spark.port();
    }

    private void catchError(Exception ex, Request req, Response res){
        String error = ex.getMessage();

        if (Objects.equals(error, "Username already taken")){
            res.status(403);
            res.body(new Gson().toJson(error));
        }
        else if (Objects.equals(error, "Bad Request")){
            res.status(400);
            res.body(new Gson().toJson(error));
        }
        else if (Objects.equals(error, "Unauthorized")){
            res.status(401);
            res.body(new Gson().toJson(error));
        }
        else {
            res.status(500);
            res.body(new Gson().toJson(error));
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
