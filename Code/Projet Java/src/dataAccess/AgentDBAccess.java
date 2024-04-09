package dataAccess;

import model.*;
import exception.*;

import java.sql.*;
import java.util.ArrayList;

public class AgentDBAccess implements AgentDataAccess{

    public AgentDBAccess(){

    }

    @Override
    public void addAgent(Agent agent) throws ConnectionException, AccessException {
        try{
            Connection connection = SingletonConnection.getInstance();
            String sqlInstruction = "INSERT INTO Agent (personnal_number,lastName,firstname,birthdate,gsm,gender,is_alone,affectation) VALUES(?,?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1,agent.getPersonnalNumber());
            preparedStatement.setString(2, agent.getLastName());
            preparedStatement.setString(3, agent.getFisrtName());
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
        try{
            Connection connection = SingletonConnection.getInstance();
            String sqlInstruction = "Select * From Agent Where personnal_number = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setInt(1,personnalNumber);
            ResultSet data = preparedStatement.executeQuery();
            // chaque valeur de agent à mettre dedans
            Agent agent = new Agent(data.getInt("personnal_number"),data.getString("last_name"),data.getString("first_name"), data.getDate("birthday").toLocalDate(),data.getString("gsm"),data.getString("gender"),data.getBoolean("is_alone"));
            String cellName = data.getString("affectation");
            agent.setAffectation(getCell(cellName));

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

            Will will = new Will(code, data.getString("epitaph"),data.getString("funerals_type"));
            return will;
        }catch (SQLException sqlException){
            throw  new AccessException(sqlException.getMessage());
        }
    }

    @Override
    public Cell getCell(String name) throws ConnectionException, AccessException{
        try{
            Connection connection = SingletonConnection.getInstance();
            String sqlInstruction = "Select * From Will Where name = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1,name);
            ResultSet data = preparedStatement.executeQuery();

            Cell cell = new Cell(name, data.getString("address"),data.getString("phone_number"));
            return cell;
        }catch (SQLException sqlException){
            throw  new AccessException(sqlException.getMessage());
        }
    }
    @Override
    public ArrayList<Agent> getAllAgents(){
        return null;
    }
    @Override
    public void modifyAgent(Agent agent) throws ConnectionException, AccessException {
        try{
            Connection connection = SingletonConnection.getInstance();
            String sqlInstruction = "UPDATE Agent SET lastName = ?, firstname = ?, birthdate = ?, gsm = ?,gender = ? ,is_alone = ? ,affectation = ? WHERE personnal_number = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1, agent.getLastName());
            preparedStatement.setString(2, agent.getFisrtName());
            preparedStatement.setDate(3,java.sql.Date.valueOf(agent.getBirthdate()));
            preparedStatement.setString(4, agent.getPhoneNumber());
            preparedStatement.setString(5, agent.getGender());
            preparedStatement.setBoolean(6,agent.getIsAlone());
            preparedStatement.setString(7, agent.getAffectation().getName());
            preparedStatement.setInt(8,agent.getPersonnalNumber());
            preparedStatement.executeUpdate();

            // QUID de si on doit supp le pseudonyme, set à NULL dans la BD ? à voir dans else dcp...
            if(agent.getPseudonym() != null){
                sqlInstruction = "UPDATE Agent SET pseudonym = ? WHERE personnal_number = ?;";
                preparedStatement = connection.prepareStatement(sqlInstruction);
                preparedStatement.setString(1, agent.getPseudonym());
                preparedStatement.setInt(2,agent.getPersonnalNumber());
                preparedStatement.executeUpdate();
            }

            // QUID de si on doit supp l'éditorial, set à NULL dans la BD ? à voir dans else dcp...
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

}
