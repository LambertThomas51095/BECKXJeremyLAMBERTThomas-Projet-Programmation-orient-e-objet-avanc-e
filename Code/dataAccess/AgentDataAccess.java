package dataAccess;

import model.*;
import exception.*;

import java.sql.SQLException;
import java.util.ArrayList;


public interface AgentDataAccess {

    void addAgent(Agent agent);
    Agent getAgent(Integer personnalNumber, String fisrtName, String lastName) throws AgentException, ConnectionException, SQLException;
    ArrayList<Agent> getAllAgents();
    void modifyAgent(Agent agent);
    void deleteAgent(Agent agent); // delete : avec l'agent ou avec matricule ?

    // + fct servant aux 3 recherches..

}
