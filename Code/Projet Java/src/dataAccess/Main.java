package dataAccess;

import model.*;
import exception.*;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException,ConnectionException{
        Connection connection = SingletonConnection.getInstance();
        String sqlInstruction = "insert into book (isbn, titles, pages, for_adult) values (?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
        preparedStatement.setString(1, "9875");
        preparedStatement.setString(2, "Java test2");
        preparedStatement.setInt(3, 70);
        preparedStatement.setBoolean(4, true);

        int nbUpdatedLines = preparedStatement.executeUpdate();
        System.out.println(nbUpdatedLines);
    }
}
