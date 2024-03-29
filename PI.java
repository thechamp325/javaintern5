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
	Emp_temp et = new Emp_temp();
	
	


	
	@GetMapping("/pi/emp/t")
	public Map<String, String> personalInfo() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("value", "hello");
		return map;
	}
	
	
	@PostMapping("/pi/emp/enter")
	public String NewUser(@RequestBody Map<String, Object> payload) throws Exception{
		String s;
		if(admin_log) {
		System.out.println(payload);
		Personal p = new Personal();
		 s = p.entry(payload);

String sql="INSERT INTO public.credentials(\r\n" + 
		"	\"Login\", password)\r\n" + 
		"	VALUES (?, ?);";

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
	
	
	@GetMapping("/pi/emp/enter/admin/login/employee_exist")
	public  Map<String,Boolean> Employee_exists(@RequestBody Map<String, Object> payload) throws SQLException{
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		String sql ="SELECT \"Login\"FROM public.credentials;";
		Statement st = adb.connect().createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next())
		{ 
			if(rs.getString("Login").equals((String)payload.get("Employee_ID"))) {
				map.put("Employee_ID", true);	
		   		return map;
			}
		}
		map.put("Employee_ID", false);
		return map;
	}
	


	
	
	
	@PostMapping("/pi/emp/enter/admin/login/details")   //for data filling by admin 
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
	if(true) {

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
		 String sql2 ="SELECT * FROM public.\"Admin\"";
			Statement st = adb.connect().createStatement();
			ResultSet rs = st.executeQuery(sql2);
			while (rs.next())
			{ 
				if((adminid.equals(rs.getString(1))&&pass.equals(rs.getString(2)))) {
					admin_log=true;
					return true;
				}
			    
			}
			rs.close();
			st.close();
			
		 
	return false;	
	}
	
	
	
	@GetMapping("/pi/emp/admin/login/approval_list")
	public Map<Integer,String> adminapproval_list() throws Exception{
		if(admin_log) {
		String sql2="select employee_data from public.temp;";
			Statement st = et.connect().createStatement();
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
			Statement st = et.connect().createStatement();
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
	
	
	
	
	
	
	@PostMapping("/pi/emp/admin/login/approve")
	public Map<String,String> admin_approve(@RequestBody Map<String, Object> payload) throws Exception{
		if(admin_log) {
			System.out.println(payload.get("employee_id"));

			String sql1="Select * from public.otherinfo1 ;";
			Statement st1 = et.connect().createStatement();
			ResultSet rs1= st1.executeQuery(sql1);
			while(rs1.next()) {
				if(rs1.getString(10).equals(payload.get("employee_id"))) {
					break;
				}
			}

			System.out.println("after resultset rs1 ="+rs1.getString(10)+"");
			
					
					String sql2 ="INSERT INTO public.otherinfo1(\r\n" + 
							"	nop, nop_int, nop_conf, nop_intconf, nob, nopatents, pgrant, awarddets, grantr, \"Employee_ID\")\r\n" + 
							"	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
					System.out.println("after sql2 ");

		
					try {
						PreparedStatement stmt = db.connect().prepareStatement(sql2);
						stmt.setInt(1, rs1.getInt(1));
						stmt.setInt(2,rs1.getInt(2));
						stmt.setInt(3, rs1.getInt(3));
						stmt.setInt(4, rs1.getInt(4));
						stmt.setInt(5, rs1.getInt(5));
						stmt.setInt(6, rs1.getInt(6));
						stmt.setInt(7, rs1.getInt(7));
					    stmt.setString(8,rs1.getString(8));
						stmt.setString(9, rs1.getString(9));
						stmt.setString(10,rs1.getString(10));
					


						
						stmt.executeUpdate();
						System.out.println("done");
					} catch (SQLException e) {
						
						e.printStackTrace();
						System.out.println(e.getMessage());

					}
					rs1.close();
					st1.close();
					
					
					
					String sql3="Select * from public.temp ;";
					Statement st2 = et.connect().createStatement();
					ResultSet rs2= st2.executeQuery(sql3);
					System.out.println("after sql3");
					while(rs2.next()) {
						if(rs2.getString(10).equals(payload.get("employee_id"))) {
							break;
						}
					}
					
//				String sql4 = "UPDATE public.eduqualification SET past_teaching = "+rs2.getArray("past_teaching")+", pt_startdate = "+rs2.getArray("pt_startdate")+", pt_enddate = "+rs2.getArray("pt_enddate")+", past_industry = "+rs2.getArray("past_industry")+", pi_startdate = "+rs2.getArray("pi_startdate")+", pi_enddate = "+rs2.getArray("pi_enddate")+", past_research = "+rs2.getArray("past_research")+", pr_startdate = "+rs2.getArray("pr_startdate")+", pr_enddate = "+rs2.getArray("pr_enddate")+" WHERE \"Employee_ID\" = "+rs2.getString(10)+";";
					String sql4 = "UPDATE public.eduqualification\r\n" + 
							"	SET  past_teaching=?, pt_startdate=?, pt_enddate=?, past_industry=?, pi_startdate=?, pi_enddate=?, past_research=?, pr_startdate=?, pr_enddate=?\r\n" + 
							"	WHERE \"Employee_ID\" = ?;";
					System.out.println("sql4");
					try {
						PreparedStatement stmt = db.connect().prepareStatement(sql4);
						stmt.setArray(1, rs2.getArray(1));
						stmt.setArray(2, rs2.getArray(2));
						stmt.setArray(3, rs2.getArray(3));

						stmt.setArray(4, rs2.getArray(4));

						stmt.setArray(5, rs2.getArray(5));
						stmt.setArray(6, rs2.getArray(6));

						stmt.setArray(7, rs2.getArray(7));
						stmt.setArray(8, rs2.getArray(8));
						stmt.setArray(9, rs2.getArray(9));
						stmt.setString(10,(String) payload.get("employee_id"));



						stmt.executeUpdate();
						System.out.println("sql4 executed");
					
						} 
					catch (SQLException e) {
						
						e.printStackTrace();
						System.out.println(e.getMessage());

					}
					
					
		Map<String, String> emp_temp = new HashMap<String, String>();
		emp_temp.put("Query","Query Excecuted");
		return emp_temp;
		}
		else {
			Map<String, String> emp_temp = new HashMap<String, String>();
			emp_temp.put("Error","Query not Excecuted");
			return emp_temp;
			
		}
				
						
		 
	}
	
	
	
	
	
	
	@PostMapping("/pi/emp/enter/login")//employee login
	public boolean loginmethod(@RequestBody Map<String, Object> login) throws Exception{
		 log = (String)login.get("ID");
		String pass =(String)login.get("Password");
		
		System.out.println("here1");
		String sql2 ="SELECT * FROM public.credentials";
		
		Statement st = adb.connect().createStatement();
		//System.out.println("......");
		ResultSet rs = st.executeQuery(sql2);
		//rs.next();
		//String name = rs.getString("First_Name");
		//System.out.println(name);
		//String s = null;
		while (rs.next())
		{
		    System.out.print("Column 1 returned ");
		    if(rs.getString("Login").equals(log)&&rs.getString("Password").equals(pass)) {
		    	emp_log=true;
		    return true;
		  
		    }
		    
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
		System.out.println("in the function");
//		String sql1 ="INSERT INTO public.otherinfo1(\r\n" + 
//				"	nop, nop_int, nop_conf, nop_intconf, nob, nopatents, pggrant, awarddets, grantr, employee_id)\r\n" + 
//				"	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		
	
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

		
		
		
		
		
		
		String sql1 ="INSERT INTO public.otherinfo1(\r\n" + 
				"	nop, nop_int, nop_conf, nop_intconf, nob, nopatents, pggrant, awarddets, grantr, employee_id)\r\n" + 
				"	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try {
			PreparedStatement stmt = et.connect().prepareStatement(sql1);
			
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


//				int i=0;
//				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
//
//	
//				int no_of_institutes =  (int) payload.get("no_of_institutes");
//				Date date[] = new Date[no_of_institutes];
//				String pastteaching[] = new String[no_of_institutes];
//				while(i<no_of_institutes) {
//				 pastteaching[i] =  (String) payload.get("pastteaching");
//				 java.util.Date date[i] = sdf1.parse((String)payload.get(""));
//				 java.sql.Date dob = new java.sql.Date(date.getTime()); 
//				 
//				i++;
//				}
//				
//				
//				i=0;
//				int no_of_industries =  (int) payload.get("no_of_industries");
//				String pastindustry[] = new String[no_of_industries];
//				
//				while(i<no_of_industries) {
//					 pastindustry[i] =  (String)payload.get("pastindustry");
//					 i++;
//				
//				}
//				
//				
//				i=0;
//				int no_of_research =  (int) payload.get("no_of_research");
//				String pastresearch[] = new String[no_of_research];
//
//				while(i<no_of_industries) {
//					 pastresearch[i]=  (String)payload.get("pastresearch");
//				}
//				
SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		
		int no_of_institutes  = (int) payload.get("no_of_institutes");
		String[] past_teaching = new String[no_of_institutes];
		Date[] pt_startdate = new Date[no_of_institutes];
		Date[] pt_enddate = new Date[no_of_institutes];
		
		int i = 0;
		while(i < no_of_institutes) {
			past_teaching[i]= (String) payload.get("inst"+i+"");
			java.util.Date tempdate = sdf1.parse((String)payload.get("instsdate"+i+""));
			pt_startdate[i] = new java.sql.Date(tempdate.getTime());
			
			java.util.Date tempdate1 = sdf1.parse((String)payload.get("instedate"+i+""));
			pt_enddate[i] = new java.sql.Date(tempdate1.getTime());
			i++;
		}
		
		Array past_teaching1=et.connect().createArrayOf("text",past_teaching);
		Array pt_startdate1=et.connect().createArrayOf("date",pt_startdate);
		Array pt_enddate1=et.connect().createArrayOf("date",pt_enddate);	
		
		
		
		
		i=0;
		int no_of_industries = (int) payload.get("no_of_industries");
		String[] past_industries = new String[no_of_industries];
		Date[] pi_startdate = new Date[no_of_industries];
		Date[] pi_enddate = new Date[no_of_industries];
		while(i < no_of_industries) {
			past_industries[i]= (String) payload.get("ind"+i+"");
			java.util.Date tempdate = sdf1.parse((String)payload.get("indsdate"+i+""));
			pi_startdate[i] = new java.sql.Date(tempdate.getTime());
			
			java.util.Date tempdate1 = sdf1.parse((String)payload.get("indedate"+i+""));
			pi_enddate[i] = new java.sql.Date(tempdate1.getTime());
			i++;
		}
		
//		 
		Array past_industries1=et.connect().createArrayOf("text",past_industries);
		Array pi_startdate1=et.connect().createArrayOf("date",pi_startdate);
		Array pi_enddate1=et.connect().createArrayOf("date",pi_enddate);
		
		i=0;
		int no_of_researches = (int) payload.get("no_of_researches");
		String[] past_researches = new String[no_of_researches];
		Date[] pr_startdate = new Date[no_of_researches];
		Date[] pr_enddate = new Date[no_of_researches];
		while(i < no_of_researches) {
			past_researches[i]= (String) payload.get("res"+i+"");
			java.util.Date tempdate = sdf1.parse((String)payload.get("ressdate"+i+""));
			pr_startdate[i] = new java.sql.Date(tempdate.getTime());
			
			java.util.Date tempdate1 = sdf1.parse((String)payload.get("resedate"+i+""));
			pr_enddate[i] = new java.sql.Date(tempdate1.getTime());
			i++;
		}
		
		 
		Array past_researches1=et.connect().createArrayOf("text",past_researches);
		Array pr_startdate1=et.connect().createArrayOf("date",pr_startdate);
		Array pr_enddate1=et.connect().createArrayOf("date",pr_enddate);
		System.out.println("whiles completed");
		String sql ="INSERT INTO public.temp(\r\n" + 
				"	past_teaching, pt_startdate, pt_enddate, past_industry, pi_startdate, pi_enddate, past_research, pr_startdate, pr_enddate, employee_data)\r\n" + 
				"	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		
		try {
			PreparedStatement stmt = et.connect().prepareStatement(sql);
			stmt.setArray(1,past_teaching1);
			stmt.setArray(2, pt_startdate1);
			stmt.setArray(3, pt_enddate1);
			
			
			stmt.setArray(4,past_industries1);
			stmt.setArray(5, pi_startdate1);
			stmt.setArray(6, pi_enddate1);
			
			stmt.setArray(7,past_researches1);
			stmt.setArray(8, pr_startdate1);
			stmt.setArray(9, pr_enddate1);
			stmt.setString(10,log);
			stmt.executeUpdate();
			

			
		

			
			
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());

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
		

//				System.out.println("LOGIN ID IS"+log);
//				int nop = (int) payload.get("nop");		
//				System.out.println("LOGIN ID IS1"+log);
//
//				int nop_int = (int) payload.get("nop_int");
//				System.out.println("LOGIN ID IS2"+log);
//
//				int nop_conf = (int) payload.get("nop_conf");
//				System.out.println("LOGIN ID IS3"+log);
//
//				int nop_intconf = (int) payload.get("nop_intconf");	
//				System.out.println("LOGIN ID IS4"+log);
//
//				int nob = (int) payload.get("nob");		
//				System.out.println("LOGIN ID IS5"+log);
//
//				int nopatents = (int) payload.get("nopatents");	
//				System.out.println("LOGIN ID IS6"+log);
//
//				int pggrant = (int) payload.get("pggrant");
//				System.out.println("LOGIN ID IS7"+log);
//
//				String awarddets = (String) payload.get("awarddets");
//				System.out.println("LOGIN ID IS8"+log);
//
//				String grantr = (String) payload.get("grantr");
//				System.out.println("LOGIN ID IS9"+log);
//
//				//int empid = (int) payload.get("empid");
//				System.out.println("nop is"+nop);
//
//				
//				
//				
//				
//				
//				
//				String sql ="INSERT INTO public.otherinfo1(\r\n" + 
//						"	nop, nop_int, nop_conf, nop_intconf, nob, nopatents, pgrant, awarddets, grantr, \"Employee_ID\")\r\n" + 
//						"	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
//				try {
//					PreparedStatement stmt = db.connect().prepareStatement(sql);
//					
//					stmt.setInt(1, nop);
//					stmt.setInt(2, nop_int);
//					stmt.setInt(3, nop_conf);
//					stmt.setInt(4, nop_intconf);
//					stmt.setInt(5, nob);
//					stmt.setInt(6, nopatents);
//					stmt.setInt(7, pggrant);
//				    stmt.setString(8,awarddets);
//					stmt.setString(9, grantr);
////					stmt.setString(10,log);
//					stmt.setString(10,pastteaching);
//					stmt.setString(11, pastindustry);
//					stmt.setString(12, pastresearch);
//				
//
//					System.out.println("LOGIN ID IS10"+log);
//
//					
//					stmt.executeUpdate();
//					System.out.println("done");
//				} catch (SQLException e) {
//					
//					e.printStackTrace();
//					System.out.println(e.getMessage());
//
//				}
				//Officeinfo oi = new Officeinfo();
				//oi.entry(payload,log);
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
	public void changed(@RequestBody Map<String, Object> payload) throws Exception {
		
		String empid=(String)payload.get("empid");
		
		String newdesign=(String)payload.get("newdesign");//STAFF ENTERS NEW EMPLOYEE
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date tempdate1 = sdf1.parse((String)payload.get("datestart"));
		java.util.Date tempdate2= sdf1.parse((String)payload.get("oldend")); //END DATE FOR PREVIOUS DESIGNATION
	    
		Date datestart = new java.sql.Date(tempdate1.getTime()); //DATE OF NEW POST
	    
		Date endold = new java.sql.Date(tempdate2.getTime()); //END DATE OF OLD POST
	    
	    
		String sql ="SELECT * FROM public.pastteaching";
		String sql1 = "UPDATE public.pastteaching\r\n" + 
				"	SET  pt_enddate=?\r\n" + 
				"	WHERE \"Employee_ID\" = ?;";
		String sql2 ="INSERT INTO public.pastteaching(\r\n" + 
				"	past_teaching, pt_startdate, pt_enddate, pt_design,employee_data)\r\n" + 
				"	VALUES (?, ?, ?, ?, ?);";//check syntax and add salaryid
		
		Statement stmt = db.connect().createStatement();
		
		
		try
		{
			ResultSet rs=stmt.executeQuery(sql);
			//x=THE COLUMN NUMBER OF pr_enddate in pastteaching table
		
			if(rs.getDate(x).equals(null))//empty
			{
				//ADD END DATE TO THE PREVIOUS DESIGNATION
				stmt.executeUpdate(sql1);
				//ADD NEW POST AND DESIGNATION
				PreparedStatement stmt1 = db.connect().prepareStatement(sql);
				
				stmt1.setString(1, newdesign);
				stmt1.setDate(2,datestart);
				stmt1.setString(5, empid);
				//ADD EMPLOYEEID
				
				stmt1.executeUpdate(sql2);
				
				
				
				
			}
			
			
			
		}
		catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println(e.getMessage());
	}
		
		
		
		
	    
	    
	    
	    
		
		
				
		
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








	
	