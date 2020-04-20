package serverModel;
import java.sql.*;
import java.util.ArrayList;

/**
 * Database of the course registration system.
 *
 * Provides methods to query a mySQL database to gather student and course information.
 * @author C. Faith, L. Garland, G. Raymond-Fauteux
 * @version 1.1
 * @since April 16 2020
 *
 */
public class DBManager implements JDBCredentials{

	/**
	 * Serves as the connection to SQL database.
	 */
	private Connection conn;

	/**
	 * List of the various courses.
	 */
	private volatile CourseCatalogue courseList;
	/**
	 * List of all students.
	 */
	private volatile ArrayList<Student> studentList;
	/**
	 * List of all student's login information.
	 */
	private volatile ArrayList<String[]> studentLoginList;
	/**
	 * List of all admin's login information.
	 */
	private volatile ArrayList<String[]> adminLoginList;

	public synchronized ArrayList<String[]> getAdminLoginList() {
		return adminLoginList;
	}

	public synchronized ArrayList<String[]> getStudentLoginList() {
		return studentLoginList;
	}

	public synchronized CourseCatalogue getCatalogue() {
		return courseList;
	}

	public synchronized ArrayList<Student> getStudentList() {
		return studentList;
	}

	/**
	 * Opens the connection to the SQL database.
	 */
	public void initializeConnection() {
		try {
			// Register JDBC driver
			Driver driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.err.println("Problem opening connection to mySQL DB.");
			e.printStackTrace();
		}
	}

	/**
	 * Closes the connection to SQL database.
	 */
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads all students from SQL database and stores in studentList.
	 */
	public void loadStudentList(){
		initializeConnection();
		studentList = new ArrayList<Student>();

		try {
			String query = "select id, name from mydb.student";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				studentList.add(new Student(name, id));
			}
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Problem selecting student");
			e.printStackTrace();
		}
		close();
	}
	/**
	 * Reads all students user names and passwords from SQL database
	 * and stores in studentLoginList.
	 */
	public void loadStudentLoginList(){
		initializeConnection();
		studentLoginList = new ArrayList<String[]>();

		try {
			String query = "select username, password, id from mydb.student";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String[] loginInfo = new String[3];
				loginInfo[0] = rs.getString("username");
				loginInfo[1] = rs.getString("password");
				loginInfo[2] = Integer.toString(rs.getInt("id"));
				studentLoginList.add(loginInfo);
			}
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Problem selecting student");
			e.printStackTrace();
		}
		close();
	}

	/**
	 * Reads all admin's user names and passwords from SQL database
	 * and stores in adminLoginList.
	 */
	public void loadAdminLoginList(){
		initializeConnection();
		adminLoginList = new ArrayList<String[]>();

		try {
			String query = "select username, password from mydb.admin";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String[] loginInfo = new String[2];
				loginInfo[0] = rs.getString("username");
				loginInfo[1] = rs.getString("password");
				adminLoginList.add(loginInfo);
			}
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Problem selecting admin");
			e.printStackTrace();
		}
		close();
	}

	/**
	 * Reads all courses from SQL database and stores in courseList.
	 */
	public void loadCourseList() {
		initializeConnection();
		ArrayList<Course> courseList = new ArrayList<Course>();

		try {
			String query = "select id, name, num, off1, off1cap, off2, off2cap from mydb.course";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			int i = 0;
			while(rs.next()) {
				String name = rs.getString("name");
				int num = rs.getInt("num");
				int off1 = rs.getInt("off1");
				int off1cap = rs.getInt("off1cap");
				int off2 = rs.getInt("off2");
				int off2cap = rs.getInt("off2cap");

				courseList.add( new Course(name, num) );
				courseList.get(i).addOffering( new CourseOffering(off1, off1cap) );
				courseList.get(i).addOffering( new CourseOffering(off2, off2cap) );
				i++;
			}
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Problem selecting course");
			e.printStackTrace();
		}

		this.courseList = new CourseCatalogue();
		this.courseList.setCourseList(courseList);
		close();
	}

	
	
	
	
	
	
	
//Below are methods to help populate the DB when first being run on different machines.

	private void createStudentTable() {
		String sql = "CREATE TABLE STUDENT " + "(id INTEGER not NULL, " + " name VARCHAR(255), "
				+ " username VARCHAR(255), " + " password VARCHAR(255), " + " PRIMARY KEY ( id ))";

		try {
			Statement stmt = conn.createStatement(); // construct a statement
			stmt.executeUpdate(sql); // execute my query (i.e. sql)
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Table can NOT be created!");
		}
		System.out.println("Created student table in given database...");
	}

	private void populateStudentTable() {
		insertUser(1, "Guillaume", "Guillaume1", "password1");
		insertUser(2, "Aidan", "Aidan2", "password2");
		insertUser(3, "Michele", "Michele3", "password3");
		insertUser(4, "Dylan", "Dylan4", "password4");
		insertUser(5, "Tyler", "Tyler5", "password5");
		insertUser(6, "Hailey", "Hailey6", "password6");
		insertUser(7, "Sadie", "Sadie7", "password7");
		insertUser(8, "Luke", "Luke8", "password8");
		insertUser(9, "Cam", "Cam9", "password9");
		insertUser(17, "Taylor", "Taylor17", "password17");
		insertUser(42, "Mike", "Mike42", "password42");
	}

	private void insertUser(int id, String name, String username, String password) {
		try {
			String query = "INSERT INTO STUDENT (ID,name,username,password) values(?,?,?,?)";
			PreparedStatement pStat = conn.prepareStatement(query);
			pStat.setInt(1, id);
			pStat.setString(2, name);
			pStat.setString(3, username);
			pStat.setString(4, password);
			pStat.executeUpdate();
			pStat.close();
		} catch (SQLException e) {
			System.out.println("Problem inserting user");
			e.printStackTrace();
		}
	}

	private void createCourseTable() {
		String sql = "CREATE TABLE COURSE " + "(id INTEGER not NULL, " + " name VARCHAR(255), " + "num INTEGER, "
				+ " off1 INTEGER," + " off1cap INTEGER, " + " off2 INTEGER, "
				+ " off2cap INTEGER, " + " PRIMARY KEY ( id ))";

		try {
			Statement stmt = conn.createStatement(); // construct a statement
			stmt.executeUpdate(sql); // execute my query (i.e. sql)
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Table can NOT be created!");
		}
		System.out.println("Created course table in given database...");
	}

	private void populateCourseTable() {
		insertCourse(1,"ENGG", 233, 0, 25, 1, 20 );
		insertCourse(2,"ENGG", 200, 0, 40, 1, 45 );
		insertCourse(3,"ENGG", 201, 0, 35, 1, 50 );
		insertCourse(4,"ENGG", 202, 0, 100, 1, 50 );
		insertCourse(5,"ENGG", 225, 0, 70, 1, 70 );
		insertCourse(6,"ENCM", 511, 0, 80, 1, 80 );
		insertCourse(7,"ENSF", 409, 0, 50, 1, 50 );
		insertCourse(8,"PHYS", 259, 0, 66, 1, 42 );
	}

	private void insertCourse(int id, String name, int num, int off1, int off1cap, int off2, int off2cap) {
		try {
			String query = "INSERT INTO COURSE (id,name,num,off1,off1cap,off2,off2cap) values(?,?,?,?,?,?,?)";
			PreparedStatement pStat = conn.prepareStatement(query);
			pStat.setInt(1, id);
			pStat.setString(2, name);
			pStat.setInt(3, num);
			pStat.setInt(4, off1);
			pStat.setInt(5, off1cap);
			pStat.setInt(6, off2);
			pStat.setInt(7, off2cap);
			pStat.executeUpdate();
			pStat.close();
		} catch (SQLException e) {
			System.out.println("Problem inserting course");
			e.printStackTrace();
		}
	}

	private void createAdminTable() {
		String sql = "CREATE TABLE ADMIN " +  "(id INTEGER not NULL, " +
				" username VARCHAR(255), " +
				" password VARCHAR(255), " + " PRIMARY KEY ( id ))";

		try {
			Statement stmt = conn.createStatement(); // construct a statement
			stmt.executeUpdate(sql); // execute my query (i.e. sql)
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Table can NOT be created!");
		}
		System.out.println("Created admin table in given database...");
	}

	private void populateAdminTable() {
		insertAdmin(1, "admin","12345");
		insertAdmin(2, "adminTest","SecurePassword");
	}

	private void insertAdmin(int id, String username, String password) {
		try {
			String query = "INSERT INTO ADMIN (id,username,password) values(?,?,?)";
			PreparedStatement pStat = conn.prepareStatement(query);
			pStat.setInt(1, id);
			pStat.setString(2, username);
			pStat.setString(3, password);
			pStat.executeUpdate();
			pStat.close();
		} catch (SQLException e) {
			System.out.println("Problem inserting course");
			e.printStackTrace();
		}
	}

//Run main to init and populate DB on local system
	public static void main(String[] args0) {
		DBManager init = new DBManager();
		init.initializeConnection();
		init.createStudentTable();
		init.createCourseTable();
		init.createAdminTable();
		init.populateStudentTable();
		init.populateCourseTable();
		init.populateAdminTable();
		init.close();
	}



}
