package dataAccess;

import model.*;
import exception.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class AgentDBAccess implements AgentDataAccess{

    public AgentDBAccess(){

    }


    @Override
    public int countExistingPersonnalNumber() throws ConnectionException, AccessException{
        int nbExistingPersonnalNumber;
        try{
            Connection connection = SingletonConnection.getInstance();
            String sqlInstruction = "Select Count(*) From Agent";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();
            data.next();
            nbExistingPersonnalNumber = data.getInt(1);
        }catch (SQLException sqlException){
            throw  new AccessException(sqlException.getMessage());
        }
        return nbExistingPersonnalNumber;
    }


    @Override
    public void addAgent(Agent agent) throws ConnectionException, AccessException {
        try{
            Connection connection = SingletonConnection.getInstance();
            String sqlInstruction = "INSERT INTO Agent (personnal_number,lastname,firstname,birthdate,gsm,gender,is_alone,affectation) VALUES(?,?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1,agent.getPersonnalNumber());
            preparedStatement.setString(2, agent.getLastname());
            preparedStatement.setString(3, agent.getFirstname());
            preparedStatement.setDate(4,java.sql.Date.valueOf(agent.getBirthdate()));
            preparedStatement.setString(5, agent.getPhoneNumber());
            preparedStatement.setString(6, agent.getGender());
            preparedStatement.setBoolean(7,agent.getIsAlone());
            preparedStatement.setString(8, agent.getAffectation().getName());
            preparedStatement.executeUpdate();

            if(agent.getPseudonym() != null){
                sqlInstruction = "UPDATE Agent SET pseudonym = ? WHERE personnal_number = ?;";
                preparedStatement = connection.prepareStatement(sqlInstruction);
                preparedStatement.setString(1, agent.getPseudonym());
                preparedStatement.setInt(2,agent.getPersonnalNumber());
                preparedStatement.executeUpdate();
            }

            if(agent.getEditorial() != null){
                sqlInstruction = "UPDATE Agent SET editorial = ? WHERE personnal_number = ?;";
                preparedStatement = connection.prepareStatement(sqlInstruction);
                preparedStatement.setInt(1, agent.getEditorial().getCode());
                preparedStatement.setInt(2,agent.getPersonnalNumber());
                preparedStatement.executeUpdate();
            }
        }catch (SQLException sqlException){
            throw  new AccessException(sqlException.getMessage());
        }

    }
    @Override
    public Agent getAgent(Integer personnalNumber) throws AgentException, ConnectionException, AccessException {
        ArrayList<Cell> cells = getAllCells();
        try{
            Connection connection = SingletonConnection.getInstance();
            String sqlInstruction = "Select * From Agent Where personnal_number = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1,personnalNumber);
            ResultSet data = preparedStatement.executeQuery();
            data.next();
            String cellName = data.getString("affectation");
            Cell cell = cells.stream().filter(c -> c.getName().equals(cellName)).toList().get(0);
            Agent agent = new Agent(data.getInt("personnal_number"),data.getString("lastname"),data.getString("firstname"), data.getDate("birthdate").toLocalDate(),data.getString("gsm"),data.getString("gender"),data.getBoolean("is_alone"),cell);

            String pseudonym = data.getString("pseudonym");
            if(!data.wasNull()){
                agent.setPseudonym(pseudonym);
            }

            Integer editorial = data.getInt("editorial");
            if(!data.wasNull()){
                agent.setEditorial(getWill(editorial));
            }

            return agent;
        }catch (SQLException sqlException){
            throw new AccessException(sqlException.getMessage());
        }
    }
    @Override
    public ArrayList<Agent> getAllAgents() throws AgentException, ConnectionException, AccessException{
        ArrayList<Agent> agents = new ArrayList<>();
        ArrayList<Cell> cells = getAllCells();
        try{
            Connection connection = SingletonConnection.getInstance();
            String sqlInstruction = "Select * From Agent;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();
            while(data.next()) {
                String cellName = data.getString("affectation");
                Cell cell = cells.stream().filter(c -> c.getName().equals(cellName)).toList().get(0);
                Agent agent = new Agent(data.getInt("personnal_number"), data.getString("lastname"), data.getString("firstname"), data.getDate("birthdate").toLocalDate(), data.getString("gsm"), data.getString("gender"), data.getBoolean("is_alone"), cell);

                String pseudonym = data.getString("pseudonym");
                if (!data.wasNull()) {
                    agent.setPseudonym(pseudonym);
                }

                Integer editorial = data.getInt("editorial");
                if (!data.wasNull()) {
                    agent.setEditorial(getWill(editorial));
                }
                agents.add(agent);
            }
            return agents;
        }catch (SQLException sqlException){
            throw  new AccessException(sqlException.getMessage());
        }
    }
    @Override
    public void modifyAgent(Agent agent) throws ConnectionException, AccessException {
        try{
            Connection connection = SingletonConnection.getInstance();
            String sqlInstruction = "UPDATE Agent SET lastname = ?, firstname = ?, birthdate = ?, gsm = ?,gender = ? ,is_alone = ? ,affectation = ? WHERE personnal_number = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, agent.getLastname());
            preparedStatement.setString(2, agent.getFirstname());
            preparedStatement.setDate(3,java.sql.Date.valueOf(agent.getBirthdate()));
            preparedStatement.setString(4, agent.getPhoneNumber());
            preparedStatement.setString(5, agent.getGender());
            preparedStatement.setBoolean(6,agent.getIsAlone());
            preparedStatement.setString(7, agent.getAffectation().getName());
            preparedStatement.setInt(8,agent.getPersonnalNumber());
            preparedStatement.executeUpdate();

            if(agent.getPseudonym() != null){
                sqlInstruction = "UPDATE Agent SET pseudonym = ? WHERE personnal_number = ?;";
                preparedStatement = connection.prepareStatement(sqlInstruction);
                preparedStatement.setString(1, agent.getPseudonym());
                preparedStatement.setInt(2,agent.getPersonnalNumber());
                preparedStatement.executeUpdate();
            }else{
                sqlInstruction = "UPDATE Agent SET pseudonym = null WHERE personnal_number = ?;";
                preparedStatement = connection.prepareStatement(sqlInstruction);
                preparedStatement.setInt(1,agent.getPersonnalNumber());
                preparedStatement.executeUpdate();
            }

            if(agent.getEditorial() != null){
                sqlInstruction = "UPDATE Agent SET editorial = ? WHERE personnal_number = ?;";
                preparedStatement = connection.prepareStatement(sqlInstruction);
                preparedStatement.setInt(1, agent.getEditorial().getCode());
                preparedStatement.setInt(2,agent.getPersonnalNumber());
                preparedStatement.executeUpdate();
            }else{
                sqlInstruction = "UPDATE Agent SET editorial = null WHERE personnal_number = ?;";
                preparedStatement = connection.prepareStatement(sqlInstruction);
                preparedStatement.setInt(1,agent.getPersonnalNumber());
                preparedStatement.executeUpdate();
            }

        }catch (SQLException sqlException){
            throw  new AccessException(sqlException.getMessage());
        }
    }
    @Override
    public void deleteAgent(Agent agent) throws ConnectionException, AccessException {
        try{
            Connection connection = SingletonConnection.getInstance();
            String sqlInstruction = "DELETE FROM Agent WHERE personnal_number = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1,agent.getPersonnalNumber());
            preparedStatement.executeUpdate();
        }catch (SQLException sqlException){
            throw  new AccessException(sqlException.getMessage());
        }
    }


    @Override
    public void addWill(Will will) throws ConnectionException, AccessException{
        try{
            Connection connection = SingletonConnection.getInstance();
            String sqlInstruction = "INSERT INTO Will (code, epitaph, funerals_type) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1,will.getCode());
            preparedStatement.setString(2,will.getEpitaph());
            preparedStatement.setString(3,will.getFuneralsType());
            preparedStatement.executeUpdate();
        }catch (SQLException sqlException){
            throw  new AccessException(sqlException.getMessage());
        }

    }
    @Override
    public Will getWill(Integer code) throws ConnectionException, AccessException{
        try{
            Connection connection = SingletonConnection.getInstance();
            String sqlInstruction = "Select * From Will Where code = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1,code);
            ResultSet data = preparedStatement.executeQuery();
            data.next();
            Will will = new Will(code, data.getString("epitaph"),data.getString("funerals_type"));
            return will;
        }catch (SQLException sqlException){
            throw  new AccessException(sqlException.getMessage());
        }
    }
    @Override
    public void modifyWill(Will will) throws ConnectionException, AccessException{
        try{
            Connection connection = SingletonConnection.getInstance();
            String sqlInstruction = "UPDATE Will SET epitaph = ?, funerals_type = ? WHERE code = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1,will.getEpitaph());
            preparedStatement.setString(2,will.getFuneralsType());
            preparedStatement.setInt(3,will.getCode());
            preparedStatement.executeUpdate();
        }catch (SQLException sqlException){
            throw  new AccessException(sqlException.getMessage());
        }
    }
    @Override
    public void deleteWill(Will will) throws ConnectionException, AccessException{
        try{
            Connection connection = SingletonConnection.getInstance();
            String sqlInstruction = "DELETE FROM Will WHERE code = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1,will.getCode());
            preparedStatement.executeUpdate();
        }catch (SQLException sqlException){
            throw  new AccessException(sqlException.getMessage());
        }
    }


    @Override
    public ArrayList<Cell> getAllCells() throws ConnectionException, AccessException{
        ArrayList<Cell> cells = new ArrayList<>();
        try{
            Connection connection = SingletonConnection.getInstance();
            String sqlInstruction = "Select * From Cell;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();
            while(data.next()){
                cells.add(new Cell(data.getString("name"),data.getString("address"),data.getString("phone_number")));
            }
            return cells;
        }catch (SQLException sqlException){
            throw  new AccessException(sqlException.getMessage());
        }
    }


    @Override
    public ArrayList<String> getAgentsLanguages(String cellName, LocalDate birthdate) throws ConnectionException, AccessException{
        ArrayList<String> agentsLanguages = new ArrayList<>();
        try{
            Connection connection = SingletonConnection.getInstance();
            String sqlInstruction = "SELECT lastname, firstname, name FROM Agent JOIN Ability ON personnal_number = agent JOIN Language ON code = language WHERE affectation = ? AND birthdate < ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1,cellName);
            preparedStatement.setDate(2,java.sql.Date.valueOf(birthdate));
            ResultSet data = preparedStatement.executeQuery();
            while(data.next()) {
                StringBuilder agentLanguage = new StringBuilder();
                agentLanguage.append(data.getString("lastname"));
                agentLanguage.append(" ");
                agentLanguage.append(data.getString("firstname"));
                agentLanguage.append(" ");
                agentLanguage.append(data.getString("name"));
                agentsLanguages.add(agentLanguage.toString());
            }
            return agentsLanguages;
        }catch (SQLException sqlException){
            throw  new AccessException(sqlException.getMessage());
        }
    }
    @Override
    public ArrayList<String> getAgentMissions(String lastname, String firstname,Integer personnalNumber) throws ConnectionException, AccessException{
        ArrayList<String> agentMissions = new ArrayList<>();
        try{
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement;
            ResultSet data;
            if(personnalNumber != null){
                String sqlInstruction = "SELECT m.code, description, c.name FROM Mission m JOIN Attribution a ON m.code = a.mission JOIN Agent ON personnal_number = a.agent JOIN Mission_Location ml ON m.code = ml.mission JOIN Location l ON l.code = ml.location JOIN Country c ON l.position = c.name WHERE personnal_number = ?;";
                preparedStatement = connection.prepareStatement(sqlInstruction);
                preparedStatement.setInt(1,personnalNumber);
            }else{
                String sqlInstruction = "SELECT m.code, description, c.name FROM Mission m JOIN Attribution a ON m.code = a.mission JOIN Agent ON personnal_number = a.agent JOIN Mission_Location ml ON m.code = ml.mission JOIN Location l ON l.code = ml.location JOIN Country c ON l.position = c.name WHERE lastname = ? AND firstname = ?;";
                preparedStatement = connection.prepareStatement(sqlInstruction);
                preparedStatement.setString(1,lastname);
                preparedStatement.setString(2,firstname);
            }
            data = preparedStatement.executeQuery();
            while(data.next()) {
                StringBuilder agentMission = new StringBuilder();
                agentMission.append(data.getString("code"));
                agentMission.append(" ");
                agentMission.append(data.getString("description"));
                agentMission.append(" ");
                agentMission.append(data.getString("name"));
                agentMissions.add(agentMission.toString());
            }
            return agentMissions;
        }catch (SQLException sqlException){
            throw  new AccessException(sqlException.getMessage());
        }
    }
    @Override
    public ArrayList<String> getContacts(Integer missionCode) throws ConnectionException, AccessException{
        ArrayList<String> contacts = new ArrayList<>();
        try{
            Connection connection = SingletonConnection.getInstance();
            String sqlInstruction = "SELECT pseudonym FROM Mission m JOIN Mission_Location ml ON m.code = ml.mission JOIN Location l ON l.code = ml.location JOIN Coverage c ON l.code = c.location JOIN Contact ON personnal_number = c.contact WHERE m.code = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1,missionCode);
            ResultSet data = preparedStatement.executeQuery();
            while(data.next()) {
                contacts.add(data.getString("pseudonym"));
            }
            return contacts;
        }catch (SQLException sqlException){
            throw  new AccessException(sqlException.getMessage());
        }
    }

}
