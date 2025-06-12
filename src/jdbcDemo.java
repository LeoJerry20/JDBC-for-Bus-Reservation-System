import java.sql.*;
public class jdbcDemo {

	public static void main(String[] args) throws Exception {
		//readRecord();
		//insertRecord();
		//insertVar();
		//insertUsingpst();
		//delete();
		//update();
		//sp();
		//sp2();
		//sp3();
		//commitDemo();
		//autocommitDemo();
		batch();
	}
	// read table values in database
	public static void readRecord() throws Exception {
		String url="jdbc:mysql://localhost:3306/jdbcdemo";
		String userName="root";
		String passWord="2007";
		String query="select * from employee";
		Connection con= DriverManager.getConnection(url,userName,passWord);
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		while(rs.next()){
		System.out.println("Id is "+ rs.getInt(1));
		System.out.println("Name is "+ rs.getString(2));
		System.out.println("Salary is "+ rs.getInt(3));
		}
		con.close();
	}
	// insert values to table in datebase
	public static void insertRecord() throws Exception {
		String url="jdbc:mysql://localhost:3306/jdbcdemo";
		String userName="root";
		String passWord="2007";
		String query="Insert into employee values(4,'aravind',35000)";
		Connection con= DriverManager.getConnection(url,userName,passWord);
		Statement st=con.createStatement();
		int rs=st.executeUpdate(query);
		System.out.println("Number of rows affected "+ rs);
		con.close();
	}
	// insert with variables
	public static void insertVar() throws Exception {
		String url="jdbc:mysql://localhost:3306/jdbcdemo";
		String userName="root";
		String passWord="2007";
		int id=5;
		String name="leo";
		int salary=44000;
		String query="Insert into employee values("+ id + ", '"+ name +"',"+salary+");";
		Connection con= DriverManager.getConnection(url,userName,passWord);
		Statement st=con.createStatement();
		int rs=st.executeUpdate(query);
		System.out.println("Number of rows affected "+rs);
		con.close();
	}
	// insert values using prepared statement 
	public static void insertUsingpst() throws Exception {
		String url="jdbc:mysql://localhost:3306/jdbcdemo";
		String userName="root";
		String passWord="2007";
		int id=6;
		String name="moni";
		int salary=124000;
		String query="Insert into employee values(?,?,?);";
		Connection con= DriverManager.getConnection(url,userName,passWord);
		 PreparedStatement pst=con.prepareStatement(query);
		pst.setInt(1, id);
		pst.setString(2, name);
		pst.setInt(3, salary);
		int rs=pst.executeUpdate();
		System.out.println("Number of rows affected "+rs);
		con.close();
	}
	//delete
	public static void delete() throws Exception {
		String url="jdbc:mysql://localhost:3306/jdbcdemo";
		String userName="root";
		String passWord="2007";
		int id=5;
		String query="delete from employee where emp_id = "+id;
		Connection con= DriverManager.getConnection(url,userName,passWord);
		 Statement st=con.createStatement();
		int rs=st.executeUpdate(query);
		System.out.println("Number of rows affected "+rs);
		con.close();
	}
	// update
	public static void update() throws Exception {
		String url="jdbc:mysql://localhost:3306/jdbcdemo";
		String userName="root";
		String passWord="2007";
		int id=1;
		String query="update employee set salary=30000 where emp_id = "+id;
		Connection con= DriverManager.getConnection(url,userName,passWord);
		 Statement st=con.createStatement();
		int rs=st.executeUpdate(query);
		System.out.println("Number of rows affected "+rs);
		con.close();
	}
	// three types of statement
	// 1. normal statement
	// 2. prepared statement
	// 3. callable Statement
	
//calling simple store procedure
	public static void sp() throws Exception {
		String url="jdbc:mysql://localhost:3306/jdbcdemo";
		String userName="root";
		String passWord="2007";
		Connection con= DriverManager.getConnection(url,userName,passWord);
		 CallableStatement cs=con.prepareCall("{call getId()}");
		 ResultSet rs=cs.executeQuery();
		while(rs.next()) {
			System.out.println("id "+rs.getInt(1));
			System.out.println("name "+rs.getString(2));
			System.out.println("salary "+rs.getInt(3));
		}
		con.close();
	}
	//calling store procedure input parameter
		public static void sp2() throws Exception {
			String url="jdbc:mysql://localhost:3306/jdbcdemo";
			String userName="root";
			String passWord="2007";
			int id=3; 
			Connection con= DriverManager.getConnection(url,userName,passWord);
			 CallableStatement cs=con.prepareCall("{call getById(?)}");
			 cs.setInt(1,id);
			 ResultSet rs=cs.executeQuery();
			 while(rs.next()) {
				System.out.println("id "+rs.getInt(1));
				System.out.println("name "+rs.getString(2));
				System.out.println("salary "+rs.getInt(3));
			 }
			con.close();
		}
	// calling store procedure with input and output parameter	
		public static void sp3() throws Exception {
			String url="jdbc:mysql://localhost:3306/jdbcdemo";
			String userName="root";
			String passWord="2007";
			int id=3; 
			Connection con= DriverManager.getConnection(url,userName,passWord);
			 CallableStatement cs=con.prepareCall("{call getByIdOut(?,?)}");
			 cs.setInt(1,id);
			 cs.registerOutParameter(2,Types.VARCHAR);
			 cs.executeUpdate();
			 System.out.println("name "+cs.getString(2));
			con.close();
		}
	//commit and autocommit
		public static void commitDemo() throws Exception {
			String url="jdbc:mysql://localhost:3306/jdbcdemo";
			String userName="root";
			String passWord="2007";
			String query1="update employee set salary=20000 where emp_id=1"; 
			String query2="updat employee set salary=30000 where emp_id=2"; 
			Connection con= DriverManager.getConnection(url,userName,passWord);
		Statement st=con.createStatement();
		int rows1=st.executeUpdate(query1);
	   System.out.println("number of rows affected "+rows1);
	   int rows2=st.executeUpdate(query2);
	   System.out.println("number of rows affected "+rows2);

			con.close();
		}
		public static void autocommitDemo() throws Exception {
			String url="jdbc:mysql://localhost:3306/jdbcdemo";
			String userName="root";
			String passWord="2007";
			String query1="update employee set salary=20000 where emp_id=1"; 
			String query2="updat employee set salary=30000 where emp_id=2"; 
			Connection con= DriverManager.getConnection(url,userName,passWord);
		    con.setAutoCommit(false);
			Statement st=con.createStatement();
		int rows1=st.executeUpdate(query1);
	   System.out.println("number of rows affected "+rows1);
	   int rows2=st.executeUpdate(query2);
	   System.out.println("number of rows affected "+rows2);
	   if(rows1>0 && rows2>0) {
		   con.commit();
	   }
			con.close();
		}
		
		//batch processing 
		public static void batch() throws Exception {
			String url="jdbc:mysql://localhost:3306/jdbcdemo";
			String userName="root";
			String passWord="2007";
			String query1="update employee set salary=60000 where emp_id=1"; 
			String query2="update employee set salary=60000 where emp_id=2"; 
			String query3="update employee set salary=60000 where emp_id=3"; 
			String query4="update employee set salary=60000 where emp_id=4"; 
			//String query5="updat employee set salary=60000 where emp_id=4"; 
		Connection con= DriverManager.getConnection(url,userName,passWord);
		con.setAutoCommit(false);
		Statement st=con.createStatement();
		st.addBatch(query1);
		st.addBatch(query2);
		st.addBatch(query3);
		st.addBatch(query4);
		//st.addBatch(query5);
		int[] res=st.executeBatch();
		for(int i:res) {
			if(i>0)
				continue;
			else
				con.rollback();
		}
		con.commit();
		con.close();
		}
}
