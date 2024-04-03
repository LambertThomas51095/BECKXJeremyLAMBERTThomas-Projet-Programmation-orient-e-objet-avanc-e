package dataAccess;

import model.*;
import exception.*;

import java.sql.*;

public class SingletonConnection {
    private static Connection uniqueConnection;

    public static Connection getInstance() throws ConnectionException {
        if(uniqueConnection == null ){
            try{
                // /secretAgency,"root","Tigrou007"
                uniqueConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Tigrou007");
            }catch(SQLException e){
                throw new ConnectionException(e.getMessage());
            }
        }
        return uniqueConnection;
    }
}
