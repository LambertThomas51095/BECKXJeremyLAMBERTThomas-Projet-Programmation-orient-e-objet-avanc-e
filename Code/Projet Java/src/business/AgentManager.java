package business;

import dataAccess.*;
import model.*;
import exception.*;

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

    public Integer addAgent(Agent agent) throws ConnectionException, AccessException, AgentException{
        if(agent.getEditorial() != null){
            dao.addWill(agent.getEditorial());
            Integer willCode = dao.getLastIncrementId();
            agent.getEditorial().setCode(willCode);
        }
        agent.setLastname(Security.cryptingMethod(agent.getLastname()));
        agent.setFirstname(Security.cryptingMethod(agent.getFirstname()));
        if(agent.getPseudonym() != null){
            agent.setPseudonym(Security.cryptingMethod(agent.getPseudonym()));
        }
        dao.addAgent(agent);
        return agent.getPersonnalNumber();
    }
    public Agent getAgent(Integer personnalNumber) throws AgentException, ConnectionException, AccessException, WillException {
        Agent agent = dao.getAgent(personnalNumber);
        agent.setFirstname(Security.decryptingMethod(agent.getFirstname()));
        agent.setLastname(Security.decryptingMethod(agent.getLastname()));
        if(agent.getPseudonym() != null){
            agent.setPseudonym(Security.decryptingMethod(agent.getPseudonym()));
        }
        return agent;
    }
    public ArrayList<Agent> getAllAgents() throws AgentException, ConnectionException, AccessException, WillException{
        ArrayList<Agent> agents = dao.getAllAgents();
        for(Agent agent : agents){
            agent.setFirstname(Security.decryptingMethod(agent.getFirstname()));
            agent.setLastname(Security.decryptingMethod(agent.getLastname()));
            if(agent.getPseudonym() != null){
                agent.setPseudonym(Security.decryptingMethod(agent.getPseudonym()));
            }
        }
        return agents;
    }
    public void modifyAgent(Agent agent) throws ConnectionException, AccessException, AgentException{
        agent.setLastname(Security.cryptingMethod(agent.getLastname()));
        agent.setFirstname(Security.cryptingMethod(agent.getFirstname()));
        if(agent.getPseudonym() != null){
            agent.setPseudonym(Security.cryptingMethod(agent.getPseudonym()));
        }
        if(agent.getEditorial() != null){
            if(agent.getEditorial().getCode() != null){
                dao.modifyWill(agent.getEditorial());
            }else{
                dao.addWill(agent.getEditorial());
                agent.getEditorial().setCode(dao.getLastIncrementId());
            }
        }
        dao.modifyAgent(agent);
    }
    public void deleteAgent(Agent agent) throws ConnectionException, AccessException{
        dao.deleteAgent(agent);
        if(agent.getEditorial() != null){
            dao.deleteWill(agent.getEditorial());
        }
    }

    public void deleteWill(Will will) throws ConnectionException, AccessException{
        dao.deleteWill(will);
    }

    public ArrayList<Cell> getAllCells() throws ConnectionException, AccessException{
        return dao.getAllCells();
    }

    public ArrayList<Integer> getAllPersonnalNumbers() throws ConnectionException, AccessException{
        return dao.getAllPersonnalNumbers();
    }
    public ArrayList<ArrayList<String>> getAllAgentsName() throws ConnectionException, AccessException{
        ArrayList<ArrayList<String>> agentsNames = dao.getAllAgentsName();
        for(ArrayList<String> agentNames : agentsNames){
            agentNames.set(0, Security.decryptingMethod(agentNames.get(0)));
            agentNames.set(1, Security.decryptingMethod(agentNames.get(1)));
        }
        return agentsNames;
    }
    public ArrayList<Integer> getAllMissionsCode() throws ConnectionException, AccessException{
        return dao.getAllMissionsCode();
    }

    public ArrayList<ArrayList<String>> getAgentsLanguages(String cellName, LocalDate birthdate) throws ConnectionException, AccessException{
        ArrayList<ArrayList<String>> agentsLanguages = dao.getAgentsLanguages(cellName,birthdate);
        for(ArrayList<String> agentLanguage : agentsLanguages){
            agentLanguage.set(0, Security.decryptingMethod(agentLanguage.get(0)));
            agentLanguage.set(1, Security.decryptingMethod(agentLanguage.get(1)));
        }
        return agentsLanguages;
    }
    public ArrayList<ArrayList<String>> getAgentMissions(String lastname, String firstname,Integer personnalNumber) throws ConnectionException, AccessException{
        return dao.getAgentMissions(Security.cryptingMethod(lastname),Security.cryptingMethod(firstname),personnalNumber);
    }
    public ArrayList<String> getContacts(Integer missionCode) throws ConnectionException, AccessException{
        ArrayList<String> contacts = dao.getContacts(missionCode);
        for(Integer iContact = 0; iContact < contacts.size(); iContact++){
            contacts.set(iContact, Security.decryptingMethod(contacts.get(iContact)));
        }
        return contacts;
    }

}