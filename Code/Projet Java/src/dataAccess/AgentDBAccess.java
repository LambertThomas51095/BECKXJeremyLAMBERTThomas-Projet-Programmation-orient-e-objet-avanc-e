package dataAccess;

import model.*;
import exception.*;

import java.sql.*;
import java.util.ArrayList;

/*public class AgentDBAccess implements AgentDataAccess{

    public AgentDBAccess(){

    }


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

    public Agent getAgent(Integer personnalNumber, String fisrtName, String lastName) throws AgentException, ConnectionException, AccessException {
        try{
            Connection connection = SingletonConnection.getInstance();
            String sqlInstruction = "Select * From Agent Where first_name = ? AND last_name = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            preparedStatement.setString(1,fisrtName);
            preparedStatement.setString(2,lastName);
            ResultSet data = preparedStatement.executeQuery();
            // chaque valeur de agent à mettre dedans
            Agent agent = new Agent(data.getInt("personnal_number"),data.getString("last_name"),data.getString("first_name"), data.getDate("birthday").toLocalDate(),data.getString("gsm"),data.getString("gender"),data.getBoolean("is_alone"));
            String cellName = data.getString("affectation");
            // fct getCell(String cellName)

            String pseudonym = data.getString("pseudonym");
            if(!data.wasNull()){
                agent.setPseudonym(pseudonym);
            }

            Integer editorial = data.getInt("editorial");
            // fct getEditorial(Integer editorialCode)
        }catch (SQLException sqlException){
            throw  new AccessException(sqlException.getMessage());
        }

    }
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

}*/
