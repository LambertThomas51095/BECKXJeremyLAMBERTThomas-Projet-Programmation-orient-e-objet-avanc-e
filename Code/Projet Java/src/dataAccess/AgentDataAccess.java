package dataAccess;

import model.*;
import exception.*;

import java.time.LocalDate;
import java.util.ArrayList;


public interface AgentDataAccess {

    int getLastIncrementPersonnalNumber() throws ConnectionException, AccessException;

    void addAgent(Agent agent) throws ConnectionException, AccessException;
    Agent getAgent(Integer personnalNumber) throws AgentException, ConnectionException, AccessException;
    ArrayList<Agent> getAllAgents() throws AgentException, ConnectionException, AccessException;
    void modifyAgent(Agent agent) throws ConnectionException, AccessException;
    void deleteAgent(Agent agent) throws ConnectionException, AccessException;


    void addWill(Will will) throws ConnectionException, AccessException;
    Will getWill(Integer code) throws ConnectionException, AccessException;
    void modifyWill(Will will) throws ConnectionException, AccessException;
    void deleteWill(Will will) throws ConnectionException, AccessException;

    ArrayList<Cell> getAllCells() throws ConnectionException, AccessException;


    ArrayList<ArrayList<String>> getAgentsLanguages(String cellName, LocalDate birthdate) throws ConnectionException, AccessException;
    ArrayList<ArrayList<String>> getAgentMissions(String lastName, String firstName,Integer personnalNumber) throws ConnectionException, AccessException;
    ArrayList<String> getContacts(Integer missionCode) throws ConnectionException, AccessException;
}
