package service;

import dataaccess.MemoryAuthDAO;
import dataaccess.MemoryGameDAO;
import dataaccess.MemoryUserDAO;

public class ClearDataService {

    MemoryAuthDAO authDao;
    MemoryGameDAO gameDao;
    MemoryUserDAO userDao;

    public ClearDataService(MemoryAuthDAO auth, MemoryGameDAO game, MemoryUserDAO user){
        this.authDao = auth;
        this.gameDao = game;
        this.userDao = user;
    }

    public void clearData(){
        userDao.clearUsers();
    }

}
