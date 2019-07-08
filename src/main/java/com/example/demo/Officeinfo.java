package com.example.demo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Map;

public class Officeinfo {
	
	DB db =new DB();
	
	public String entry(Map<String, Object> payload,String log) throws Exception{
		String bankaccno = (String) payload.get("bankaccno");//1
		
		String bankname = (String) payload.get("bankname");// bank name n branch are separate fields //2
		
		String designation = (String) payload.get("designation");//3
		
		String dep = (String) payload.get("dep");//4
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");//5
		java.util.Date date = sdf1.parse((String)payload.get("date_join"));
		java.sql.Date date_join = new java.sql.Date(date.getTime()); 
		System.out.println(date_join);
		

		java.util.Date date2 = sdf1.parse((String)payload.get("date_conf"));//6
		java.sql.Date date_conf = new java.sql.Date(date2.getTime()); 
		
		String uni_apprnumber = (String) payload.get("uni_apprnumber");//enter university approval date//7
		
		int uni_apprperiodpg = (int) payload.get("uni_apprperiodpg");//8

		java.util.Date date3 = sdf1.parse((String)payload.get("date_retire"));//9
		java.sql.Date date_retire = new java.sql.Date(date3.getTime()); 
		System.out.println(date_retire);
		
		java.util.Date date4 = sdf1.parse((String)payload.get("app_expiry_temp"));//10
		java.sql.Date app_expiry_temp = new java.sql.Date(date4.getTime()); 
			
		
		String uni_apprnumberpg = (String) payload.get("uni_apprnumberpg");//11
		
		int uni_apprperiodphd= (int) payload.get("uni_apprperiodphd");//12
		
		String uni_apprnumberphd= (String) payload.get("uni_apprnumberphd");//13
		
		String ug_pg= (String) payload.get("ug_pg");//14
		
		String payband= (String) payload.get("payband");//15
		
		int pay = (int) payload.get("pay");//16
		
		int paygrade = (int) payload.get("paygrade");//17
		
		String uan= (String) payload.get("uan");//18
		
		String pfaccno= (String) payload.get("pfaccno");//19
		
		boolean presentstaff= (boolean) payload.get("presentstaff");//20
		
		java.util.Date date5 = sdf1.parse((String)payload.get("resign_date"));//21
		java.sql.Date resign_date = new java.sql.Date(date5.getTime()); 
		System.out.println(resign_date);
		
		java.util.Date date6 = sdf1.parse((String)payload.get("relieve_date"));//22
		java.sql.Date relieve_date = new java.sql.Date(date6.getTime()); 
		System.out.println(relieve_date);
		
		String staff_type= (String) payload.get("staff_type");//date//23
		String increment_month= (String) payload.get("increment_month");//24
		
		boolean vacation= (boolean) payload.get("vacation");//25
		
		String remarks= (String) payload.get("remarks");//26
		
		String bankbranch= (String) payload.get("bankbranch");//27
		
		java.util.Date date7 = sdf1.parse((String)payload.get("uniapp_date"));//28
		java.sql.Date uniapp_date = new java.sql.Date(date7.getTime()); 
//		System.out.println(uniapp_date);
		java.util.Date date8 = sdf1.parse((String)payload.get("uniapp_date_phd"));//29
		java.sql.Date uniapp_date_phd = new java.sql.Date(date8.getTime()); 
		
		java.util.Date date9 = sdf1.parse((String)payload.get("uniapp_date_pg"));//30
		java.sql.Date uniapp_date_pg = new java.sql.Date(date9.getTime()); 
		
		String uni_app_period= (String) payload.get("uni_app_period");//31
		String nature_of_appointment= (String) payload.get("nature_of_appointment");//32

		



		
		
		
		System.out.println("1office here");
		String sql ="INSERT INTO public.officeinfo(\r\n" + 
				"	bankaccno, designation, dep, date_join, date_conf, uni_apprnumber, uni_apprperiodpg, uni_apprnumberpg, uni_apprperiodphd, uni_apprnumberphd, date_retire, app_expiry_temp, ug_pg, payband, pay, paygrade, \"UAN\", pfaccno, presentstaff, resign_date, relieve_date, staff_type, increment_month, vacation, remarks, \"Employee_ID\", bankname, bankbranch, uniapp_date, uniapp_date_pg, uniapp_date_phd, uni_app_period, nature_of_appointment)\r\n" + 
				"	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			try {
			PreparedStatement stmt = db.connect().prepareStatement(sql);
			stmt.setString(1,bankaccno);
			stmt.setString(2, designation);
			stmt.setString(3, dep);
			stmt.setDate(4, date_join);
			stmt.setDate(5, date_conf);
			stmt.setString(6, uni_apprnumber);
			stmt.setInt(7, uni_apprperiodpg);
			stmt.setString(8, uni_apprnumberpg);
			stmt.setInt(9, uni_apprperiodphd);
			stmt.setString(10, uni_apprnumberphd);
			stmt.setDate(11, date_retire);
			stmt.setDate(12, app_expiry_temp);
			stmt.setString(13, ug_pg);
			stmt.setString(14, payband);
			stmt.setInt(15, pay);
			stmt.setInt(16, paygrade);
			stmt.setString(17, uan);
			stmt.setString(18, pfaccno);
			stmt.setBoolean(19,presentstaff);
			stmt.setDate(20, resign_date);
			stmt.setDate(21, relieve_date);
			stmt.setString(22, staff_type);
			stmt.setString(23, increment_month);
			stmt.setBoolean(24,vacation);
			stmt.setString(25, remarks);

			stmt.setString(26, log);

			stmt.setString(27, bankname);
			stmt.setString(28, bankbranch);
			stmt.setDate(29, uniapp_date);
			stmt.setDate(30, uniapp_date_pg);
			stmt.setDate(31, uniapp_date_phd);
			stmt.setString(32, uni_app_period);
			stmt.setString(33,nature_of_appointment);



			
			
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
