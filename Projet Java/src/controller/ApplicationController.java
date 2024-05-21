package controller;

import business.AgentManager;
import model.*;
import exception.*;

import java.time.LocalDate;
import java.util.ArrayList;


public class ApplicationController {
    private AgentManager manager;

    public ApplicationController(){
        this.manager = new AgentManager();
    }


    public Integer addAgent(Agent agent) throws ConnectionException, AccessException, AgentException {
        return manager.addAgent(agent);
    }
    public Agent getAgent(Integer personnalNumber) throws AgentException, ConnectionException, AccessException, WillException{
        return manager.getAgent(personnalNumber);
    }
    public ArrayList<Agent> getAllAgents() throws AgentException, ConnectionException, AccessException, WillException{
        return manager.getAllAgents();
    }
    public void modifyAgent(Agent agent) throws ConnectionException, AccessException, AgentException{
        manager.modifyAgent(agent);
    }
    public void deleteAgent(Agent agent) throws ConnectionException, AccessException{
        manager.deleteAgent(agent);
    }

    public void deleteWill(Will will) throws ConnectionException, AccessException{
        manager.deleteWill(will);
    }

    public ArrayList<Cell> getAllCells( ) throws ConnectionException, AccessException{
        return manager.getAllCells();
    }

    public ArrayList<Integer> getAllPersonnalNumbers() throws ConnectionException, AccessException{
        return manager.getAllPersonnalNumbers();
    }
    public ArrayList<ArrayList<String>> getAllAgentsName() throws ConnectionException, AccessException{
        return manager.getAllAgentsName();
    }
    public ArrayList<Integer> getAllMissionsCode() throws ConnectionException, AccessException{
        return manager.getAllMissionsCode();
    }

    public ArrayList<ArrayList<String>> getAgentsLanguages(String cellName, LocalDate birthdate) throws ConnectionException, AccessException{
        return manager.getAgentsLanguages(cellName,birthdate);
    }
    public ArrayList<ArrayList<String>> getAgentMissions(String lastName, String firstName,Integer personnalNumber) throws ConnectionException, AccessException{
        return manager.getAgentMissions(lastName,firstName,personnalNumber);
    }
    public ArrayList<String> getContacts(Integer missionCode) throws ConnectionException, AccessException{
        return manager.getContacts(missionCode);
    }
}