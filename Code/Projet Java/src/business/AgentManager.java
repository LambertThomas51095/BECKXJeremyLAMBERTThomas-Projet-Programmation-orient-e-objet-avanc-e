package business;

import model.*;
import exception.*;

import dataAccess.*;

import java.time.LocalDate;
import java.util.ArrayList;


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

    public Agent getAgent(Integer personnalNumber) throws AgentException, ConnectionException, AccessException{
        return dao.getAgent(personnalNumber);
    }

    public ArrayList<Agent> getAllAgents() throws AgentException, ConnectionException, AccessException{
        return dao.getAllAgents();
    }

    public void modifyAgent(Agent agent) throws ConnectionException, AccessException{
        dao.modifyAgent(agent);
    }

    public void deleteAgent(Agent agent) throws ConnectionException, AccessException{
        dao.deleteAgent(agent);
    }


    public ArrayList<String> getAgentsLanguages(String cellName, LocalDate birthdate) throws ConnectionException, AccessException{
        return dao.getAgentsLanguages(cellName,birthdate);
    }

    public ArrayList<String> getAgentMissions(String lastName, String firstName,Integer personnalNumber) throws ConnectionException, AccessException{
        return dao.getAgentMissions(lastName,firstName,personnalNumber);
    }

    public ArrayList<String> getContacts(Integer missionCode) throws ConnectionException, AccessException{
        return dao.getContacts(missionCode);
    }

}
