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

        //This line initializes the server and can be removed once you have a functioning endpoint 
        Spark.init();

        Spark.awaitInitialization();
        return Spark.port();
    }

    private Object registerUser(Request req, Response res) throws DataAccessException {
        RegisterUser registerUser = new Gson().fromJson(req.body(), RegisterUser.class);

        userService.registerUser(registerUser);

        return "";
    }


    private Object clearDb(Request req, Response res) {


        return "";
    }











    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }


}
