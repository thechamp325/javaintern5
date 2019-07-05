package com.example.demo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Map;

public class Others {
	DB db = new DB();

	public String entry(Map<String, Object> payload,String log) throws Exception{
		System.out.println("LOGIN ID IS"+log);
		int nop = (int) payload.get("nop");		
		System.out.println("LOGIN ID IS1"+log);

		int nop_int = (int) payload.get("nop_int");
		System.out.println("LOGIN ID IS2"+log);

		int nop_conf = (int) payload.get("nop_conf");
		System.out.println("LOGIN ID IS3"+log);

		int nop_intconf = (int) payload.get("nop_intconf");	
		System.out.println("LOGIN ID IS4"+log);

		int nob = (int) payload.get("nob");		
		System.out.println("LOGIN ID IS5"+log);

		int nopatents = (int) payload.get("nopatents");	
		System.out.println("LOGIN ID IS6"+log);

		int pggrant = (int) payload.get("pggrant");
		System.out.println("LOGIN ID IS7"+log);

		String awarddets = (String) payload.get("awarddets");
		System.out.println("LOGIN ID IS8"+log);

		String grantr = (String) payload.get("grantr");
		System.out.println("LOGIN ID IS9"+log);

		//int empid = (int) payload.get("empid");
		System.out.println("nop is"+nop);

		
		
		
		
		
		
		String sql ="INSERT INTO public.otherinfo1(\r\n" + 
				"	nop, nop_int, nop_conf, nop_intconf, nob, nopatents, pgrant, awarddets, grantr, \"Employee_ID\")\r\n" + 
				"	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try {
			PreparedStatement stmt = db.connect().prepareStatement(sql);
			stmt.setInt(1, nop);
			stmt.setInt(2, nop_int);
			stmt.setInt(3, nop_conf);
			stmt.setInt(4, nop_intconf);
			stmt.setInt(5, nob);
			stmt.setInt(6, nopatents);
			stmt.setInt(7, pggrant);
		    stmt.setString(8,awarddets);
			stmt.setString(9, grantr);
			stmt.setString(10,log);
		

			System.out.println("LOGIN ID IS10"+log);

			
			stmt.executeUpdate();
			System.out.println("done");
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());

		}
		//Officeinfo oi = new Officeinfo();
		//oi.entry(payload,log);
		return "Hello";
	}
}
