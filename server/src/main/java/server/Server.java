package server;

import dataaccess.MemoryAuthDAO;
import dataaccess.MemoryGameDAO;
import dataaccess.MemoryUserDAO;
import spark.*;

public class Server {

    public Server(){
        MemoryAuthDAO authDao = new MemoryAuthDAO();
        MemoryGameDAO gameDao = new MemoryGameDAO();
        MemoryUserDAO userDao = new MemoryUserDAO();
    }

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        // Register your endpoints and handle exceptions here.
        Spark.delete("/db", this::clearDb);

        //This line initializes the server and can be removed once you have a functioning endpoint 
        Spark.init();

        Spark.awaitInitialization();
        return Spark.port();
    }

    private Object clearDb(Request req, Response res) {
        //call DAO

        return "";
    }











    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }


}
