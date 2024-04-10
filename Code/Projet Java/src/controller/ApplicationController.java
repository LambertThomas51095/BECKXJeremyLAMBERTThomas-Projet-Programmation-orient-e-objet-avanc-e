package controller;

import model.*;
import exception.*;

import business.*;


public class ApplicationController {
    private AgentManager manager;

    public ApplicationController(){
        this.manager = new AgentManager();
    }


    public int addAgent(Agent agent) throws ConnectionException, AccessException {
        return manager.addAgent(agent);
    }

    public void modifyAgent(Agent agent) throws ConnectionException, AccessException{
        manager.modifyAgent(agent);
    }

    public void deleteAgent(Agent agent) throws ConnectionException, AccessException{
        manager.deleteAgent(agent);
    }
}
