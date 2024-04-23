package controller;

import model.*;
import exception.*;

import business.*;

import java.time.LocalDate;
import java.util.ArrayList;


public class ApplicationController {
    private AgentManager manager;

    public ApplicationController(){
        this.manager = new AgentManager();
    }


    public int addAgent(Agent agent) throws ConnectionException, AccessException {
        return manager.addAgent(agent);
    }
    public Agent getAgent(Integer personnalNumber) throws AgentException, ConnectionException, AccessException{
        return manager.getAgent(personnalNumber);
    }
    public ArrayList<Agent> getAllAgents() throws AgentException, ConnectionException, AccessException{
        return manager.getAllAgents();
    }
    public void modifyAgent(Agent agent) throws ConnectionException, AccessException{
        manager.modifyAgent(agent);
    }
    public void deleteAgent(Agent agent) throws ConnectionException, AccessException{
        manager.deleteAgent(agent);
    }

    public ArrayList<Cell> getAllCells() throws ConnectionException, AccessException{
        return manager.getAllCells();
    }

    public ArrayList<String> getAgentsLanguages(String cellName, LocalDate birthdate) throws ConnectionException, AccessException{
        return manager.getAgentsLanguages(cellName,birthdate);
    }
    public ArrayList<String> getAgentMissions(String lastName, String firstName,Integer personnalNumber) throws ConnectionException, AccessException{
        return manager.getAgentMissions(lastName,firstName,personnalNumber);
    }
    public ArrayList<String> getContacts(Integer missionCode) throws ConnectionException, AccessException{
        return manager.getContacts(missionCode);
    }
}
