package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

@Component
public class Emp_temp{

	 private final String url = "jdbc:postgresql://localhost:5432/emp_temp";
	    private final String user = "postgres";
	    private final String password = "7767";
	    public  Connection conn;
	    /**
	     * Connect to the PostgreSQL database
	     *
	     * @return a Connection object
	     */
	    Emp_temp(){
	    	conn = null;
	    }

	    
	    

		public Connection connect() {
			 if(conn == null) {
			        try {
			            conn = DriverManager.getConnection(url, user, password);

			            
			        } catch (SQLException e) {
			            System.out.println(e.getMessage());
			        }
		        }
		        return conn;
		}


}



