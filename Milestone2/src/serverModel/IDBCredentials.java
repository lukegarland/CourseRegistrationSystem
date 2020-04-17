package serverModel;
/**
 * Provides data fields used to gain access to SQL database.
 * @author Guillaume Raymond-Fauteux
 * @version 0.0
 * @since April 16 2020
 *
 */
public interface IDBCredentials {
	
	// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/mydb";

	   //  Database credentials
	   static final String USERNAME = "root";
	   static final String PASSWORD = "password";

}
