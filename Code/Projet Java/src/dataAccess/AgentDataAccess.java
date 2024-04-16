package dataAccess;

import model.*;
import exception.*;

import java.time.LocalDate;
import java.util.ArrayList;


public interface AgentDataAccess {

    int countExistingPersonnalNumber() throws ConnectionException, AccessException;
    void addAgent(Agent agent) throws ConnectionException, AccessException;
    Agent getAgent(Integer personnalNumber) throws AgentException, ConnectionException, AccessException;
    Will getWill(Integer code) throws ConnectionException, AccessException;
    Cell getCell(String name) throws ConnectionException, AccessException;
    ArrayList<Agent> getAllAgents() throws AgentException, ConnectionException, AccessException;
    void modifyAgent(Agent agent) throws ConnectionException, AccessException;
    void deleteAgent(Agent agent) throws ConnectionException, AccessException;

    // + fct servant aux 3 recherches..
    ArrayList<String> getAgentsLanguages(String cellName, LocalDate birthdate) throws ConnectionException, AccessException;

    ArrayList<String> getAgentMissions(String lastName, String firstName,Integer personnalNumber) throws ConnectionException, AccessException;

    ArrayList<String> getContacts(Integer missionCode) throws ConnectionException, AccessException;
}
