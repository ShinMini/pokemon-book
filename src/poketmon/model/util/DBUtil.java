/* ???
 * 1. JDBC URL - mysql용이어야 합니다
 */
package poketmon.model.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil { 
	static Properties p = new Properties();
	
	static {
		try {  
			p.load(new FileInputStream("db.properties"));

			// Class.forName(p.getProperty("com.mysql.cj.jdbc.Driver"));
			Class.forName(p.getProperty("driver"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();   
		}
	}  

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(p.getProperty("url"), 
											p.getProperty("id"),
											p.getProperty("pw"));
	}

	public static void close(Connection con, Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}if (con != null) {
				con.close();
				con = null;
			}
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	// close 순서는 rset -> stmt -> con 순으로 닫아야함. 
	public static void close(Connection con, Statement stmt, ResultSet rset) {
		try {
			if (rset != null) {
				rset.close();
				rset = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}
}
