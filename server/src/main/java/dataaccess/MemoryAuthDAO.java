package dataaccess;

import java.util.UUID;

public class MemoryAuthDAO implements AuthDAO{

    public String generateToken() {
        return UUID.randomUUID().toString();
    }

}
