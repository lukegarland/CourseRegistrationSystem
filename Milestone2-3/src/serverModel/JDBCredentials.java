package serverModel;
/**
 * Provides data fields used to gain access to SQL database.
 * 
 * @author C. Faith, L. Garland, G. Raymond-Fauteux
 * @since April 19 2020
 * @version 1.1
 *
 */
public interface JDBCredentials {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/mydb";

	//  Database credentials
	static final String USERNAME = "root";
	static final String PASSWORD = "password";

}
