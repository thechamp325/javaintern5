package com.example.demo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class Salary_certificate {

	public DB db = new DB();
	
public String req(@RequestBody Map<String, Object> payload) throws Exception{
	
	//CREATE A NEW TABLE SUCH THAT IT CONTAINS APPLICATION NUMBER<EMPIDdate>
	 
	
	String emp_id = (String)payload.get("empid");//5
	String from_month = (String)payload.get("from_month");//get month and year only
	String to_month = (String)payload.get("to_month");//get month and year only

	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	LocalDate localDate = LocalDate.now();
	String date = dtf.format(localDate);
	System.out.println(dtf.format(localDate)); //2016/11/16
	boolean fin=false;
	String salary_id=null;
	salary_id=emp_id+date;//1
	boolean hod=false;
	boolean principal=false;
	
	
	
	String sql="INSERT INTO public.salary_certificate(\r\n" + 
	"	salary_id, principal,hod,\"Employee_ID\",date,fin,from_month,to_month)\r\n" + 
"	VALUES ( ?, ?, ?, ?, ?, ?,?,?);";
	
	try {
			PreparedStatement stmt = db.connect().prepareStatement(sql);
			stmt.setString(1, salary_id);
			stmt.setBoolean(2,principal);
			stmt.setBoolean(3, hod);
			stmt.setString(4, emp_id);
			stmt.setString(5, date);
			stmt.setBoolean(6, fin);
			stmt.setString(7, from_month);
			stmt.setString(8, to_month);


			stmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());

		}
		return "done";
}

public String check_req(@RequestBody Map<String, Object> payload) throws Exception{
	
	String emp_id = (String)payload.get("empid");
	
//	String sql2 ="SELECT * FROM public.\"Admin\"";
	Statement stmt = db.connect().createStatement();
	
	String sql="SELECT salary_id, principal, hod, \"Employee_ID\", fin, from_month, to_month, date\r\n" + 
			"	FROM public.salary_certificate;";
	//SALARY TABLE:fields(empid,primarkey,hra,da)
	String sql1="SELECT * FROM public.salary;";
	
	
	ResultSet rs=stmt.executeQuery(sql);
	
	while(rs.next())
	{
		try {
			String emp=rs.getString(4);
			String dat=rs.getString(8);
			String salary_id=rs.getString(1);
			
			//REQUIRED RECORD FOUND
			if( (emp+dat).equals(salary_id) )
			{
				boolean field1=rs.getBoolean(2); //PRINCIPAL APPROVAL
				boolean field2=rs.getBoolean(3); //HOD APPROVAL
				boolean field3=rs.getBoolean(5); //FINAL APPROVAL
				if(   (field1==true && field2==true) && field3==true )
				{
					PreparedStatement stmt1 = db.connect().prepareStatement(sql);
					ResultSet rs1=stmt1.executeQuery(sql1);
					//SEND OBJECT TO FRONTEND
					String name;
					String designation; //from officeinfo
					String dep;
					//ADD BASIC PAY,DA,HRA,CCA,TA
					
					
					
				}
				
			}
			
		}
		catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());

		}
		
	}
	

return "Done";	
}





		
	
	
}
	
	
	
	
