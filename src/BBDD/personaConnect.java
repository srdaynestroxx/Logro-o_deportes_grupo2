package BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class personaConnect {
	
public Connection conexion() {
        
        String url = "jdbc:mysql://localhost:3306/logronodeportes";
        String user = "root";
        String password = "";
        
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,user,password);
            System.out.println("Base de datos conectada!");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
        
    }

}
