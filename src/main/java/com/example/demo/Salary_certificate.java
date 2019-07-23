package com.example.demo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.Map;

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
	
	String emp;
	Date dat;
	String salary_id;
	Date fromdate;
	Date todate;
	
	
	String sql2 ="SELECT * FROM public.\"Admin\"";
	Statement stmt = db.connect().createStatement();
	String sql3="SELECT * from public.officeinfo;";
	ResultSet rs2=stmt.executeQuery(sql3);
	int pay=rs2.getInt(15);
	
	int paygrade=rs2.getInt(16);
	int hra_m1=(int) ((pay+paygrade)*0.2);
	int hra_m2=(int) ((pay+paygrade)*0.21);
	int hra_m3=(int) ((pay+paygrade)*0.22);
	
	int da_m1=(int) ((pay+paygrade)*0.4);
	int da_m2=(int) ((pay+paygrade)*0.41);
	int da_m3=(int) ((pay+paygrade)*0.42);
	
	int dp_m1=(int) ((pay+paygrade)*0.3);
	int dp_m2=(int) ((pay+paygrade)*0.31);
	int dp_m3=(int) ((pay+paygrade)*0.32);
	
	int cca_m1=(int) ((pay+paygrade)*0.2);
	int cca_m2=(int) ((pay+paygrade)*0.21);
	int cca_m3=(int) ((pay+paygrade)*0.22);
	
	int ta_m1=(int) ((pay+paygrade)*0.2);
	int ta_m2=(int) ((pay+paygrade)*0.21);
	int ta_m3=(int) ((pay+paygrade)*0.22);
	
	String dep=(String)rs2.getString(3);
	
	String sql="SELECT salary_id, principal, hod, \"Employee_ID\", fin, from_month, to_month, date FROM public.salary_certificate;";
	
	ResultSet rs=stmt.executeQuery(sql);
	
	while(rs.next())
	{
		try {
			 emp=rs.getString(4);
			 dat=rs.getDate(8);
			 salary_id=rs.getString(1);
			 fromdate=rs.getDate(6);
			todate=rs.getDate(7);
			
			//REQUIRED RECORD FOUND
			if( (emp+dat).equals(salary_id) )
			{
				boolean field1=rs.getBoolean(2); //PRINCIPAL APPROVAL
				boolean field2=rs.getBoolean(3); //HOD APPROVAL
				boolean field3=rs.getBoolean(5); //FINAL APPROVAL
				if(   (field1==true && field2==true) && field3==true )
				{
					
					int gross1=pay+hra_m1+cca_m1+da_m1+cca_m1+dp_m1;
					int gross2=pay+hra_m2+cca_m2+da_m2+cca_m2+dp_m2;
					int gross3=pay+hra_m3+cca_m3+da_m3+cca_m3+dp_m3;
					//pass objects to frontend
					
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
	
	
	
	
