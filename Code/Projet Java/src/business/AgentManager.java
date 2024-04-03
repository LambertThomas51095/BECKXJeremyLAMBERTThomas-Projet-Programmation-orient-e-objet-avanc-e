package business;

import dataAccess.*;

public class AgentManager {
    private AgentDataAccess dao;

    public AgentManager(){
        //setDao();
    }

    public void setDao(AgentDataAccess dao) {
        this.dao = dao;
    }
}
