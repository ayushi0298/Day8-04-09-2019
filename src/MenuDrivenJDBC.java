import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Scanner;

public class MenuDrivenJDBC {
	int enumber;
	String ename;
	int salary;
	String designation;
	String department;

	Scanner sc = new Scanner(System.in);

	public void create(Connection conn) throws Exception {
		Statement st = conn.createStatement();
		String s = "create table Menu ( enumber int(20) primary key, ename  varchar(50),salary varchar(10), designatiin varchar(10),department varchar(10))";
		st.executeUpdate(s);
		System.out.println("TABLE CREATION COMPLETED");
	}

	public void addEmpl(Connection conn) throws Exception {

		System.out.println("Enter Employee Number: ");
		int no = sc.nextInt();
		System.out.println("Enter Employee Name: ");
		String ename = sc.next();

		System.out.println("Enter Employee salary: ");
		int salary = sc.nextInt();

		System.out.println("Enter Employee designation: ");
		String designation = sc.next();

		System.out.println("Enter Employee department: ");
		String department = sc.next();

		CallableStatement ca = conn.prepareCall("{call Employee(?,?,?,?,?)}");
		ca.setInt(1, no);
		ca.setString(2, ename);
		ca.setInt(3, salary);
		ca.setString(4, designation);
		ca.setString(5, department);
		ca.executeUpdate();
		System.out.println("Employee added");

	}
	public void viewAllEmp(Connection conn) throws Exception {
		
		String sql = "select * from Menu ";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			int n1 = rs.getInt(1);
			String s1 = rs.getString(2);
			int n2 = rs.getInt(3);
			String s2 = rs.getString(4);
			String s3 = rs.getString(5);
			System.out.println("Employee number: " + n1 + " Employee name: " + s1 + " Employee salary: " + n2
					+ " Enmployee Department: " + s2 + " Employee designation: " + s3);
		}
	}
	public void deleteEmp(Connection conn) throws Exception {
		System.out.println("Enter the Employee number: ");
		int number = sc.nextInt();
		String sql = "delete from Menu where enumber=" + number;
		PreparedStatement p = conn.prepareStatement(sql);
		p.executeUpdate();

		System.out.println("Deletion done");
	}

	public void clearData(Connection conn) throws Exception {
		String sql = "truncate table Menu ";
		PreparedStatement p = conn.prepareStatement(sql);
		p.executeUpdate();

		System.out.println("Data Cleared");
	}

	public void changeSalary(Connection conn) throws Exception {
		System.out.println("Enter the employee number ");
		int n = sc.nextInt();
		System.out.println("Enter the updated salary:");
		int sal = sc.nextInt();
		String sql = "update Menu set salary=" + sal + " where enumber=" + n;
		PreparedStatement p = conn.prepareStatement(sql);
		p.executeUpdate();

		System.out.println("Updation done");
	}

	public void searchEmp(Connection conn) throws Exception {
		System.out.println("Enter the employee number:");
		int number = sc.nextInt();
		String sql = "select * from Menu where enumber=" + number;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			int n1 = rs.getInt(1);
			String s1 = rs.getString(2);
			int n2 = rs.getInt(3);
			String s2 = rs.getString(4);
			String s3 = rs.getString(5);
			System.out.println("Employee number: " + n1 + " Employee name: " + s1 + " Employee salary: " + n2
					+ " Enmployee Department: " + s2 + " Employee designation: " + s3);
		}
	}

	public void viewDeptWise(Connection conn) throws Exception {
		System.out.println("Enter department: ");
		String dep = sc.next();
		String sql = "select ename,department from Menu where department='" + dep + "' group by ename,department";
		Statement st1 = conn.createStatement();
		ResultSet rs1 = st1.executeQuery(sql);
		while (rs1.next()) {
			String s1 = rs1.getString(1);
			String s2 = rs1.getString(2);
			System.out.println("Employee name: " + s1 + " Employee depatrment: " + s2);
		}
	}

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/test";
		Connection conn = DriverManager.getConnection(url, "root", "root");
		Statement st = conn.createStatement();
		// create(conn);

		outer: while (true) {
			System.out.println(
					"1. Add Emp \n 2.View All Emp \n 3.Remove Emp \n 4. Clear Data \n 5. Change Sal \n 6.Search Emp \n 7. View dept Wise \n 8. Sort employees \n 9: Exit");

			Scanner sc = new Scanner(System.in);
			int choices = sc.nextInt();
			switch (choices) {
			case 1:
				MenuDrivenJDBC emp = new MenuDrivenJDBC();
				emp.addEmpl(conn);
				break;
			case 2:
				MenuDrivenJDBC em = new MenuDrivenJDBC();
				em.viewAllEmp(conn);
				break;

			case 3:
				MenuDrivenJDBC e = new MenuDrivenJDBC();
				e.deleteEmp(conn);
				break;
			case 4:
				MenuDrivenJDBC e1 = new MenuDrivenJDBC();
				e1.clearData(conn);
				break;
			case 5:
				MenuDrivenJDBC e2 = new MenuDrivenJDBC();
				e2.changeSalary(conn);
				break;
			case 6:
				MenuDrivenJDBC e3 = new MenuDrivenJDBC();
				e3.searchEmp(conn);
				break;

			case 7:
				MenuDrivenJDBC e4 = new MenuDrivenJDBC();
				e4.viewDeptWise(conn);
				break;

			}
		}
	}
}
