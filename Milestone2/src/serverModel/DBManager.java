package serverModel;
import java.sql.*;
import java.util.ArrayList;

/**
 * Provides methods to query a mySQL database to gather student and course information.
 * @author Luke Garland & Guillaume Raymond-Fauteux
 * @version 0.1
 * @since April 16 2020
 *
 */
public class DBManager implements IDBCredentials{
	
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
	 * Reads all courses from SQL database and stores in courseList.
	 */
	public void loadCourseList() {
		initializeConnection();
		ArrayList<Course> courseList = new ArrayList<Course>();
		courseList = new ArrayList<Course>();
		
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
	
	public void createStudentTable() {
		String sql = "CREATE TABLE STUDENT " + "(id INTEGER not NULL, " + " name VARCHAR(255), "
				+ " PRIMARY KEY ( id ))";

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
	
	public void populateStudentTable() {
		insertUser(1, "Guillaume");
		insertUser(2, "Aidan");
		insertUser(3, "Michele");
		insertUser(4, "Dylan");
		insertUser(5, "Tyler");
		insertUser(6, "Hailey");
		insertUser(7, "Sadie");
		insertUser(8, "Luke");
		insertUser(9, "Cam");
		insertUser(17, "Taylor");
		insertUser(42, "Mike");
	}
	
	public void insertUser(int id, String name) {
		try {
			String query = "INSERT INTO STUDENT (ID,name) values(?,?)";
			PreparedStatement pStat = conn.prepareStatement(query);
			pStat.setInt(1, id);
			pStat.setString(2, name);
			pStat.executeUpdate();
			pStat.close();
		} catch (SQLException e) {
			System.out.println("Problem inserting user");
			e.printStackTrace();
		}
	}
	
	public void createCourseTable() {
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
	
	public void populateCourseTable() {
		insertCourse(1,"ENGG", 233, 0, 25, 1, 20 );
		insertCourse(2,"ENGG", 200, 0, 40, 1, 45 );
		insertCourse(3,"ENGG", 201, 0, 35, 1, 50 );
		insertCourse(4,"ENGG", 202, 0, 100, 1, 50 );
		insertCourse(5,"ENGG", 225, 0, 70, 1, 70 );
		insertCourse(6,"ENCM", 511, 0, 80, 1, 80 );
		insertCourse(7,"ENSF", 409, 0, 50, 1, 50 );
		insertCourse(8,"PHYS", 259, 0, 66, 1, 42 );
	}
	
	public void insertCourse(int id, String name, int num, int off1, int off1cap, int off2, int off2cap) {
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

//Run main to init and populate DB on local system
	public static void main(String[] args0) {
		DBManager init = new DBManager();
		init.initializeConnection();
		init.createStudentTable();
		init.createCourseTable();
		init.populateStudentTable();
		init.populateCourseTable();
		init.close();
	}

}
