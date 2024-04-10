package business;

import model.*;
import exception.*;

import dataAccess.*;


public class AgentManager {
    private AgentDataAccess dao;

    public AgentManager(){
        setDao(new AgentDBAccess());
    }

    public void setDao(AgentDataAccess dao) {
        this.dao = dao;
    }

    public int addAgent(Agent agent) throws ConnectionException, AccessException{
        int personnalNumber = dao.countExistingPersonnalNumber()+1;
        agent.setPersonnalNumber(personnalNumber);
        agent.setLastName(Security.cryptingeMethod1(agent.getLastName()));
        agent.setFirstName(Security.cryptingeMethod1(agent.getFirstName()));
        dao.addAgent(agent);
        return personnalNumber;
    }

    public void modifyAgent(Agent agent) throws ConnectionException, AccessException{
        dao.modifyAgent(agent);
    }

    public void deleteAgent(Agent agent) throws ConnectionException, AccessException{
        dao.deleteAgent(agent);
    }
}
