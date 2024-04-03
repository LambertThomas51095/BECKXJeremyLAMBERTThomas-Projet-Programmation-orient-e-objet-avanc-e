package dataAccess;

import model.*;
import exception.*;

import java.sql.*;
import java.util.ArrayList;

public class AgentDBAccess implements AgentDataAccess{

    public AgentDBAccess(){

    }

    public void addAgent(Agent agent) {

    }

    public Agent getAgent(Integer personnalNumber, String fisrtName, String lastName)throws AgentException, ConnectionException,SQLException{
        Connection connection = SingletonConnection.getInstance();
        String sqlInstruction = "Select * From Agent Where first_name = ? AND last_name = ? ";
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
    }
    public ArrayList<Agent> getAllAgents(){

    }

    public void deleteAgent(Agent agent) {

    }

    public void modifyAgent(Agent agent) {

    }
}
