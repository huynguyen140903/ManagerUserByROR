/**
 * luvina softwware JSC, 2022
 * BaseDaoImpl.java,HuyNQ

 */
package manageuser.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import manageuser.dao.BaseDao;
import manageuser.utils.DatabaseProperties;

/**
 * Lớp định nghĩa lại các phương thức dùng chung trong BaseDao
 * 
 * @author HuyNQ
 *
 */
public class BaseDaoImpl implements BaseDao {
	// Khai báo 1 Connection
	protected Connection connection;
	
	/**
	 * Phương thức mở kết nối dữ liệu đến database
	 * 
	 * @throws SQLException: Ném ra SQLException khi kết nối đến database bị lỗi
	 */
	@Override
	public Connection openConnect() throws SQLException {
		// Bắt Ngoại lệ
		try {
			// đăng kí driver với DriverManager
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			// Lấy giá trị url trong file DatabaseProperties
			String url = DatabaseProperties.getValueByKey("url");
			// Lấy giá trị user trong file DatabaseProperties
			String user = DatabaseProperties.getValueByKey("user");
			// Lấy giá trị password trong file DatabaseProperties
			String password = DatabaseProperties.getValueByKey("password");
			// Truyền url, user, pass cho Connection
			connection = DriverManager.getConnection(url, user, password);
			// xử lý ngoại lệ
		} catch (SQLException e) {
			// in ra màn hình console
			System.out.println("Lớp BaseDaoImpl phương thức openConnect " + e.getMessage());
			// Đóng catch
		}
		// trả về connection
		return connection;
		// Đóng phương thức
	}
	
	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * @param connection the connection to set
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Phương thức đóng kết nối dữ liệu đến database
	 * 
	 * @throws SQLException Ném ra SQLException khi đóng kết nối đến database bị lỗi
	 */
	@Override
	public boolean closeConnect() {
		// Bắt ngoại lệ
		try {
			// nếu có kết nối
			if (connection != null) {
				// sẽ đóng kết nối đó
				connection.close();
				// Đóng if
			}
			// xử lý ngoại lệ
		} catch (SQLException e) {
			// in ra màn hình console
			System.out.println("Lớp BaseDaoImpl phương thức closeConnect " + e.getMessage());
			// trả về false
			return false;
			// đóng catch
		}
		// trả về true
		return true;
		// đóng phương thức
	}

}
