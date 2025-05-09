package conexionBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connect {
	
	public static void main(String[] args)
	{
		
		
	}
private Connection conexion() {
		
		String url = "jdbc:mysql://localhost:3306/LOGRONO";

		String username = "root";

		String password = "";
		
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error conectando a la base de datos");
			e.printStackTrace();
		}
		
		return con;
	}
}
