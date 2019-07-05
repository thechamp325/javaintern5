package com.example.demo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Map;

public class Officeinfo {
	
	DB db =new DB();
	
	public String entry(Map<String, Object> payload,String log) throws Exception{
		String bankaccno = (String) payload.get("bankaccno");
		String banknamebr = (String) payload.get("banknamebr");
		String designation = (String) payload.get("designation");
		String dep = (String) payload.get("dep");
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = sdf1.parse((String)payload.get("date_join"));
		java.sql.Date date_join = new java.sql.Date(date.getTime()); 
		System.out.println(date_join);
		

		java.util.Date date2 = sdf1.parse((String)payload.get("date_conf"));
		java.sql.Date date_conf = new java.sql.Date(date2.getTime()); 
		
		String uni_apprnumber = (String) payload.get("uni_apprnumber");
		int uni_apprperiodpg = (int) payload.get("uni_apprperiodpg");

		java.util.Date date3 = sdf1.parse((String)payload.get("date_retire"));
		java.sql.Date date_retire = new java.sql.Date(date3.getTime()); 
		System.out.println(date_retire);
		
		java.util.Date date4 = sdf1.parse((String)payload.get("app_expiry_temp"));
		java.sql.Date app_expiry_temp = new java.sql.Date(date4.getTime()); 
			
		
		String uni_apprnumberpg = (String) payload.get("uni_apprnumberpg");
		
		int uni_apprperiodphd= (int) payload.get("uni_apprperiodphd");
		
		String uni_apprnumberphd= (String) payload.get("uni_apprnumberphd");
		String ug_pg= (String) payload.get("ug_pg");
		
		String payband= (String) payload.get("payband");
		int pay = (int) payload.get("pay");
		
		int paygrade = (int) payload.get("paygrade");
		String uan= (String) payload.get("uan");
		
		String pfaccno= (String) payload.get("pfaccno");
		
		boolean presentstaff= (boolean) payload.get("presentstaff");
		
		java.util.Date date5 = sdf1.parse((String)payload.get("resign_date"));
		java.sql.Date resign_date = new java.sql.Date(date5.getTime()); 
		System.out.println(resign_date);
		
		java.util.Date date6 = sdf1.parse((String)payload.get("relieve_date"));
		java.sql.Date relieve_date = new java.sql.Date(date6.getTime()); 
		System.out.println(relieve_date);
		
		String staff_type= (String) payload.get("staff_type");//date
		String increment_month= (String) payload.get("increment_month");
		
		boolean vacation= (boolean) payload.get("vacation");
		
		String remarks= (String) payload.get("remarks");
		
		
		System.out.println("1office here");
		String sql ="INSERT INTO public.officeinfo(\r\n" + 
				"	\"Employee_ID\", bankaccno, banknamebr, designation, dep, date_join, date_conf, uni_apprnumber, uni_apprperiodpg, uni_apprnumberpg, uni_apprperiodphd, uni_apprnumberphd, date_retire, app_expiry_temp, ug_pg, payband, pay, paygrade, \"UAN\", pfaccno, presentstaff, resign_date, relieve_date, staff_type, increment_month, vacation, remarks)\r\n" + 
				"	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			try {
			PreparedStatement stmt = db.connect().prepareStatement(sql);
			stmt.setString(1, log);
			stmt.setString(2,bankaccno);
			stmt.setString(3, banknamebr);
			stmt.setString(4, designation);
			stmt.setString(5, dep);
			stmt.setDate(6, date_join);
			stmt.setDate(7, date_conf);
			stmt.setString(8, uni_apprnumber);
			stmt.setInt(9, uni_apprperiodpg);
			stmt.setString(10, uni_apprnumberpg);
			stmt.setInt(11, uni_apprperiodphd);
			stmt.setString(12, uni_apprnumberphd);
			stmt.setDate(13, date_retire);
			stmt.setDate(14, app_expiry_temp);
			stmt.setString(15, ug_pg);
			stmt.setString(16, payband);
			stmt.setInt(17, pay);
			stmt.setInt(18, paygrade);
			stmt.setString(19, uan);
			stmt.setString(20, pfaccno);
			stmt.setBoolean(21,presentstaff);
			stmt.setDate(22, resign_date);
			stmt.setDate(23, relieve_date);
			stmt.setString(24, staff_type);
			stmt.setString(25, increment_month);
			stmt.setBoolean(26,vacation);
			stmt.setString(27, remarks);
			
			
			System.out.println("2office here");
			stmt.executeUpdate();
			System.out.println("3office here");

		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());

		}
		
		return "Hello";
	}

}
