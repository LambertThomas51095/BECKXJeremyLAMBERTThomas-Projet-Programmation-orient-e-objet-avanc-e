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
        agent.setLastname(Security.cryptingMethod(agent.getLastname()));
        agent.setFirstname(Security.cryptingMethod(agent.getFirstname()));
        dao.addAgent(agent);
        if(agent.getEditorial() != null){
            dao.addWill(agent.getEditorial());
        }
        return personnalNumber;
    }
    public Agent getAgent(Integer personnalNumber) throws AgentException, ConnectionException, AccessException {
        Agent agent = dao.getAgent(personnalNumber);
        agent.setFirstname(Security.decryptingMethod(agent.getFirstname()));
        agent.setLastname(Security.decryptingMethod(agent.getLastname()));
        return agent;
    }
    public ArrayList<Agent> getAllAgents() throws AgentException, ConnectionException, AccessException{
        ArrayList<Agent> agents = dao.getAllAgents();
        for(Agent agent : agents){
            agent.setFirstname(Security.decryptingMethod(agent.getFirstname()));
            agent.setLastname(Security.decryptingMethod(agent.getLastname()));
        }
        return agents;
    }
    public void modifyAgent(Agent agent) throws ConnectionException, AccessException{
        agent.setLastname(Security.cryptingMethod(agent.getLastname()));
        agent.setFirstname(Security.cryptingMethod(agent.getFirstname()));
        dao.modifyAgent(agent);
        if(agent.getEditorial() != null){
            dao.modifyWill(agent.getEditorial());
        }
    }
    public void deleteAgent(Agent agent) throws ConnectionException, AccessException{
        dao.deleteAgent(agent);
    }

    public ArrayList<Cell> getAllCells() throws ConnectionException, AccessException{
        return dao.getAllCells();
    }

    public ArrayList<String> getAgentsLanguages(String cellName, LocalDate birthdate) throws ConnectionException, AccessException{
        ArrayList<String> agentsLanguages = dao.getAgentsLanguages(cellName,birthdate);
        for(int iAgentLanguage = 0; iAgentLanguage<agentsLanguages.size();iAgentLanguage++){
            String [] agentLanguage = agentsLanguages.get(iAgentLanguage).split(" ");
            agentLanguage[0] = Security.decryptingMethod(agentLanguage[0]);
            agentLanguage[1] = Security.decryptingMethod(agentLanguage[1]);
            agentsLanguages.set(iAgentLanguage, agentLanguage[0] + " " + agentLanguage[1] + " " + agentLanguage[2]);
        }
        return agentsLanguages;
    }
    public ArrayList<String> getAgentMissions(String lastname, String firstname,Integer personnalNumber) throws ConnectionException, AccessException{
        return dao.getAgentMissions(Security.cryptingMethod(lastname),Security.cryptingMethod(firstname),personnalNumber);
    }
    public ArrayList<String> getContacts(Integer missionCode) throws ConnectionException, AccessException{
        return dao.getContacts(missionCode);
    }

}
