package com.example.demo;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.util.SystemInfo;

import java.util.Date.*;

@RestController
@RequestMapping(value = "/api",  produces = "application/json")
public class PI {
	public String log;
	public boolean admin_log=true;
	public boolean emp_log = false;
	
	@Autowired
	DB db = new DB();
	
	
	//@Autowired
	AdminDB adb = new AdminDB();
	
	


	
	@GetMapping("/pi/emp/t")
	public Map<String, String> personalInfo() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("value", "hello");
		return map;
	}
	
	
	@PostMapping("/pi/emp/enter")//tested
	public String NewUser(@RequestBody Map<String, Object> payload) throws Exception{
		String s;
		if(admin_log) {
		System.out.println(payload);
		Personal p = new Personal();
		 s = p.entry(payload);

String sql="INSERT INTO public.credentials(	\"Login\", password)VALUES (?, ?);";

try {
	PreparedStatement stmt = adb.connect().prepareStatement(sql);
	stmt.setString(1, (String)payload.get("empid"));
	stmt.setString(2,(String)payload.get("empid"));
	stmt.executeUpdate();
}
catch (SQLException e) {
	
	e.printStackTrace();
	System.out.println(e.getMessage());

}
		}
		else {
			s="Please login";
		}
		//System.out.println("here");
		return s;
	}
	
	
	@GetMapping("/pi/emp/enter/admin/login/employee_exist")//tested
	public  Map<String,Boolean> Employee_exists(@RequestBody Map<String, Object> payload) throws SQLException{
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		String sql ="SELECT \"Login\"FROM public.credentials where \"Login\" = '"+payload.get("Employee_ID")+"';";
		Statement st = adb.connect().createStatement();
		ResultSet rs = st.executeQuery(sql);
		rs.next();
			if(rs.getString("Login").equals((String)payload.get("Employee_ID"))) {
				map.put("Employee_ID", true);	
		   		return map;
			}
		map.put("Employee_ID", false);
		return map;
	}
	


	
	
	
	@PostMapping("/pi/emp/enter/admin/login/details")   //for data filling by admin //tested
	public String data(@RequestBody Map<String, Object> payload) throws Exception{
	if(admin_log) {
	String log = (String) payload.get("Employee_ID");
	Education E= new Education();
	String s = E.entry(payload,log);
	Officeinfo oi = new Officeinfo();
	String s3 = oi.entry(payload, log);
//	Others o = new Others();
//	String s2 = o.entry(payload, log);
	
	return s;
	}
	else {
		return "Please sign in";
	}
	
}
	
	
	
	
	@PostMapping("/pi/emp/enter/admin/login/details/publications/national_journal")   //national international journal publications functions 
	public String national_journal(@RequestBody Map<String, Object> payload) throws Exception{
	if(admin_log) {

		int num=(int)payload.get("number");
		String log = (String) payload.get("Employee_ID");
		
		while(num>0)
		{
			String author = (String) payload.get("author"+num+"");
			String title = (String) payload.get("title"+num+"");
			String name = (String) payload.get("name"+num+"");
			String ISSN = (String) payload.get("ISSN"+num+"");

			int vol_no = (int) payload.get("vol_no"+num+"");		
			int issue_no = (int) payload.get("issue_no"+num+"");
			int pages = (int) payload.get("pages"+num+"");
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date1 = sdf1.parse((String)payload.get("date"+num+""));
			java.sql.Date date = new java.sql.Date(date1.getTime());
			
			String primarykey = log +num+"";
			num--;


			String sql = "INSERT INTO public.nationaljournal(author,title,name,\"ISSN\",vol_no,issue_no,pages,date,prikey,\"Employee_ID\")VALUES (?, ?,?,?,?,?,?,?,?,?);";
			
			try {
				PreparedStatement stmt = db.connect().prepareStatement(sql);
				stmt.setString(1, author);
				stmt.setString(2, title);
				stmt.setString(3, name);
				stmt.setString(4, ISSN);
				stmt.setInt(5, vol_no);
				stmt.setInt(6, issue_no);
				stmt.setInt(7, pages);
			    stmt.setDate(8,date);
				
				stmt.setString(9,primarykey);
				stmt.setString(10, log);
		
				System.out.println("LOGIN ID IS10"+log);				
				stmt.executeUpdate();
				System.out.println("done");
			} catch (SQLException e) {
				
				e.printStackTrace();
				System.out.println(e.getMessage());

			}
			}
		
		return "Done";
	}
	else {
		return "Please sign in";
	}
	
}
	
	
	
	@PostMapping("/pi/emp/enter/admin/login/details/publications/international_journal")   //national international journal publications functions 
	public String international_journal(@RequestBody Map<String, Object> payload) throws Exception{
	if(admin_log) {

		int num=(int)payload.get("number");
		String log = (String) payload.get("Employee_ID");
		
		while(num>0)
		{
			String author = (String) payload.get("author"+num+"");
			String title = (String) payload.get("title"+num+"");
			String name = (String) payload.get("name"+num+"");
			String ISSN = (String) payload.get("ISSN"+num+"");

			int vol_no = (int) payload.get("vol_no"+num+"");		
			int issue_no = (int) payload.get("issue_no"+num+"");
			int pages = (int) payload.get("pages"+num+"");
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date1 = sdf1.parse((String)payload.get("date"+num+""));
			java.sql.Date date = new java.sql.Date(date1.getTime());
			
			String primarykey = log +num+"";
			num--;


			String sql = "INSERT INTO public.inter_natjournal(author,title,name,\"ISSN\",vol_no,issue_no,pages,date,prikey,\"Employee_ID\")VALUES (?, ?,?,?,?,?,?,?,?,?);";
			
			try {
				PreparedStatement stmt = db.connect().prepareStatement(sql);
				stmt.setString(1, author);
				stmt.setString(2, title);
				stmt.setString(3, name);
				stmt.setString(4, ISSN);
				stmt.setInt(5, vol_no);
				stmt.setInt(6, issue_no);
				stmt.setInt(7, pages);
			    stmt.setDate(8,date);
				
				stmt.setString(9,primarykey);
				stmt.setString(10, log);
		
				System.out.println("LOGIN ID IS10"+log);				
				stmt.executeUpdate();
				System.out.println("done");
			} catch (SQLException e) {
				
				e.printStackTrace();
				System.out.println(e.getMessage());

			}
			}
		
		return "Done";
	}
	else {
		return "Please sign in";
	}
	
}


	@PostMapping("/pi/emp/enter/admin/login/details/publications/national_conf")   //national international journal publications functions 
	public String national_conf(@RequestBody Map<String, Object> payload) throws Exception{
	if(admin_log) {

		int num=(int)payload.get("number");
		String log = (String) payload.get("Employee_ID");
		
		while(num>0)
		{
			String author = (String) payload.get("author"+num+"");
			String title = (String) payload.get("title"+num+"");
			String name = (String) payload.get("name"+num+"");
			String ISSN = (String) payload.get("ISSN"+num+"");

			int vol_no = (int) payload.get("vol_no"+num+"");		
			int issue_no = (int) payload.get("issue_no"+num+"");
			int pages = (int) payload.get("pages"+num+"");
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date1 = sdf1.parse((String)payload.get("date"+num+""));
			java.sql.Date date = new java.sql.Date(date1.getTime());
			
			String primarykey = log +num+"";
			num--;


			String sql = "INSERT INTO public.nationalconf(author,title,name,\"ISSN\",vol_no,issue_no,pages,date,prikey,\"Employee_ID\")VALUES (?, ?,?,?,?,?,?,?,?,?);";
			
			try {
				PreparedStatement stmt = db.connect().prepareStatement(sql);
				stmt.setString(1, author);
				stmt.setString(2, title);
				stmt.setString(3, name);
				stmt.setString(4, ISSN);
				stmt.setInt(5, vol_no);
				stmt.setInt(6, issue_no);
				stmt.setInt(7, pages);
			    stmt.setDate(8,date);
				
				stmt.setString(9,primarykey);
				stmt.setString(10, log);
		
				System.out.println("LOGIN ID IS10"+log);				
				stmt.executeUpdate();
				System.out.println("done");
			} catch (SQLException e) {
				
				e.printStackTrace();
				System.out.println(e.getMessage());

			}
			}
		
		return "Done";
	}
	else {
		return "Please sign in";
	}
	
}


	@PostMapping("/pi/emp/enter/admin/login/details/publications/international_conf")   //national international journal publications functions 
	public String international_conf(@RequestBody Map<String, Object> payload) throws Exception{
	if(admin_log) {

		int num=(int)payload.get("number");
		String log = (String) payload.get("Employee_ID");
		
		while(num>0)
		{
			String author = (String) payload.get("author"+num+"");
			String title = (String) payload.get("title"+num+"");
			String name = (String) payload.get("name"+num+"");
			String ISSN = (String) payload.get("ISSN"+num+"");

			int vol_no = (int) payload.get("vol_no"+num+"");		
			int issue_no = (int) payload.get("issue_no"+num+"");
			int pages = (int) payload.get("pages"+num+"");
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date1 = sdf1.parse((String)payload.get("date"+num+""));
			java.sql.Date date = new java.sql.Date(date1.getTime());
			
			String primarykey = log +num+"";
			num--;


			String sql = "INSERT INTO public.inter_natconf(author,title,name,\"ISSN\",vol_no,issue_no,pages,date,prikey,\"Employee_ID\")VALUES (?, ?,?,?,?,?,?,?,?,?);";
			
			try {
				PreparedStatement stmt = db.connect().prepareStatement(sql);
				stmt.setString(1, author);
				stmt.setString(2, title);
				stmt.setString(3, name);
				stmt.setString(4, ISSN);
				stmt.setInt(5, vol_no);
				stmt.setInt(6, issue_no);
				stmt.setInt(7, pages);
			    stmt.setDate(8,date);
				
				stmt.setString(9,primarykey);
				stmt.setString(10, log);
		
				System.out.println("LOGIN ID IS10"+log);				
				stmt.executeUpdate();
				System.out.println("done");
			} catch (SQLException e) {
				
				e.printStackTrace();
				System.out.println(e.getMessage());

			}
			}
		
		return "Done";
	}
	else {
		return "Please sign in";
	}
	
}
	
	
	@PostMapping("/pi/emp/enter/admin/login/details/publications/book")   //Books
	public String books(@RequestBody Map<String, Object> payload) throws Exception{
	if(admin_log) {

		int num=(int)payload.get("number");
		String log = (String) payload.get("Employee_ID");
		
		while(num>0)
		{
			String author = (String) payload.get("author"+num+"");
			String title = (String) payload.get("title"+num+"");
			
  		int pages = (int) payload.get("pages"+num+"");
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date1 = sdf1.parse((String)payload.get("date"+num+""));
			java.sql.Date date = new java.sql.Date(date1.getTime());
			
			String primarykey = log +num+"";
			num--;


			String sql = "INSERT INTO public.book(author,title,pages,date,prikey,\"Employee_ID\")VALUES (?, ?,?,?,?,?);";
			
			try {
				PreparedStatement stmt = db.connect().prepareStatement(sql);
				stmt.setString(1, author);
				stmt.setString(2, title);
				stmt.setInt(3, pages);
				stmt.setDate(4,date); 
				
				stmt.setString(5,primarykey);
				stmt.setString(6, log);
		
				System.out.println("LOGIN ID IS10"+log);				
				stmt.executeUpdate();
				System.out.println("done");
			} catch (SQLException e) {
				
				e.printStackTrace();
				System.out.println(e.getMessage());

			}
			}
		
		return "Done";
	}
	else {
		return "Please sign in";
	}
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	@PostMapping("/pi/emp/admin/login")
	public boolean adminlog(@RequestBody Map<String, Object> payload) throws Exception{
		 final String adminid = (String)payload.get("Admin_ID");
		 final String pass = (String)payload.get("Password");
		 String sql2 ="SELECT password FROM public.\"Admin\" where login = '"+ adminid +"';";
			try{
				Statement st = adb.connect().createStatement();
				ResultSet rs = st.executeQuery(sql2);
				rs.next();
				if(pass.equals(rs.getString("password"))) {
					admin_log=true;
					System.out.println(rs.getString("password"));
					return true;    
			}
			}
			catch (SQLException e) {
				
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		 return false;	
	}
	
	
	
	@GetMapping("/pi/emp/admin/login/approval_list")
	public Map<Integer,String> adminapproval_list() throws Exception{
		if(admin_log) {
		String sql2="select employee_data from public.temp;";
			Statement st = db.connect().createStatement();
			ResultSet rs = st.executeQuery(sql2);
			Map<Integer, String> approve = new HashMap<Integer, String>();
			 int j=0;
			while (rs.next())
			{ approve.put(j,rs.getString("employee_data"));
				    j++;
			}
			rs.close();
			st.close();
			return approve;
		}
		else {
			Map<Integer, String> approve = new HashMap<Integer, String>();
			approve.put(1,"admin not logged in");
			return approve;
					
		}
				}
	
	
	@GetMapping("/pi/emp/admin/login/emp_temp_data")
	public Map<String,String> emp_temp_data(@RequestBody Map<String, Object> payload) throws Exception{
		if(admin_log) {
		Map<String, String> emp_temp = new HashMap<String, String>();
		String sql2="SELECT * From public.otherinfo1";
			Statement st = db.connect().createStatement();
			ResultSet rs = st.executeQuery(sql2);
			
			while (rs.next())
			{ 
			
				if(payload.get("employee_id").equals(rs.getString(10))) {
					emp_temp.put("nop",rs.getString(1));
					emp_temp.put("nop_int",rs.getString(2));
					emp_temp.put("nop_conf",rs.getString(3));
					emp_temp.put("nop_intconf",rs.getString(4));
					emp_temp.put("nob",rs.getString(5));
					emp_temp.put("nopatents",rs.getString(6));
					emp_temp.put("pggrant",rs.getString(7));
					emp_temp.put("awarddets",rs.getString(8));
					emp_temp.put("grantr",rs.getString(9));
					emp_temp.put("employee_id",rs.getString(10));
					break;


			}
			}
					   
			rs.close();
			st.close();
			return emp_temp;
		}
		else {
			Map<String, String> emp_temp = new HashMap<String, String>();
			emp_temp.put("Error","Please Login");
			return emp_temp;
			}
			
}
	
	
	
	
	
	
	@PostMapping("/pi/emp/admin/login/check_approve")//tested
	public Map<String,String> admin_approve(@RequestBody Map<String, Object> payload) throws Exception{
		if(admin_log) {
			PastInfo p = new PastInfo();
			Map<String, String> s = p.validateteaching(payload);
					
		Map<String, String> emp_temp = new HashMap<String, String>();
		emp_temp.put("Query","Query Excecuted");
		return s;
		}
		else {
			Map<String, String> emp_temp = new HashMap<String, String>();
			emp_temp.put("Error","Query not Excecuted");
			return emp_temp;
			
		}
				
						
		 
	}
	
	
	@PostMapping("/pi/emp/admin/login/approve")//tested
	public String approved(@RequestBody Map<String, Object> payload) throws Exception{
		if(admin_log) {
			PastInfo p = new PastInfo();
			String s = p.approve(payload);
			
		}
		return "approved";
	}
	
	
	
	
	
	
	@PostMapping("/pi/emp/enter/login")//employee login//tested
	public boolean loginmethod(@RequestBody Map<String, Object> login) throws Exception{
		 log = (String)login.get("ID");
		String pass =(String)login.get("Password");
		
		System.out.println("here1");
		String sql2 ="SELECT password  FROM public.credentials where \"Login\" = '"+log+"';";
		
		Statement st = adb.connect().createStatement();
		//System.out.println("......");
		ResultSet rs = st.executeQuery(sql2);
		rs.next();
			System.out.print("Column 1 returned ");
		    if(rs.getString("Password").equals(pass)) {
		    	emp_log=true;
		    return true;
		}
		System.out.println("incorrect id or password");
		rs.close();
		st.close();
		
		return false;
	}
	
	
	
	
	@PostMapping("/pi/emp/enter/login/info")
	public String tempdata(@RequestBody Map<String, Object> payload) throws Exception{
		if(!emp_log) {
			return "You aren't logged in";
		}
	PastInfo p = new PastInfo();
	String s = p.entry(payload, log);

	String sql ="Select \"Employee_ID\" from public.approve where \"Employee_ID\" = '"+log+"';";
	Statement st = db.connect().createStatement();
	ResultSet rs = st.executeQuery(sql);
	rs.next();
	if(rs.getString("Employee_ID").equals(log)) {
		return"Done";
	}
	
		String sql3 ="INSERT INTO public.approve(\r\n" + 
				"	\"Employee_ID\", approve)\r\n" + 
				"	VALUES (?,?);";

PreparedStatement stmt1 = db.connect().prepareStatement(sql3);
stmt1.setString(1,log);
stmt1.setBoolean(2,false);
stmt1.executeUpdate();
System.out.println("Approval pending try block");
System.out.println("Approval pending");
		

				return "Done";
	}
	
	
	@PostMapping("/pi/emp/faculty")
	public Map<String,Integer> facultyinfo(@RequestBody Map<String, Object> payload) throws SQLException {
		Map<String, Integer> faculty = new HashMap<String, Integer>();
		facultyreport f = new facultyreport();
		return f.facultyinfo(payload,adb);
	
	}
	
	
	
	
	@PostMapping("/pi/emp/salary_certificate")
	public Map<String,String> salary_request(@RequestBody Map<String, Object> payload) throws Exception {
		Map<String, String> salary = new HashMap<String, String>();
		Salary_certificate s = new Salary_certificate();
		s.req(payload);
	    salary.put("Status","Request Pending");
		return salary;
		
	}
	
	//
	@GetMapping("/pi/emp/changedesignation")
	public Map<String,String> changed(@RequestBody Map<String, Object> payload) throws Exception {
		
		Map<String, String> salary = new HashMap<String, String>();
		String newdesign=(String)payload.get("newdesign");//STAFF ENTERS NEW EMPLOYEE
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date tempdate1 = sdf1.parse((String)payload.get("datestart"));
		java.util.Date tempdate2= sdf1.parse((String)payload.get("oldend")); //END DATE FOR PREVIOUS DESIGNATION
	    Date datestart = new java.sql.Date(tempdate1.getTime()); //DATE OF NEW POST
	    Date endold = new java.sql.Date(tempdate2.getTime()); //END DATE OF OLD POST
	    
//	    String sql="UPDATE public.pastteaching SET  "
	    

	    
	    
	    
	    
	    
		
		
				
		
		Salary_certificate s = new Salary_certificate();
		s.req(payload);
	    salary.put("Status","Request Pending");
		return salary;
		
	}
	
	
	

	@PostMapping("/pi/emp/logout")
	public String logouteveryone() {
		 admin_log=false;
		 emp_log = false;
		 return "done";
	}
}








	
	