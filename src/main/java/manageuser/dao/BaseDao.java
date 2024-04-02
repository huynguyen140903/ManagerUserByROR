/**
 * luvina softwware JSC, 2022
 * BaseDao.java,HuyNQ

 */
package manageuser.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class này dùng để khai báo các phương thức dùng chung
 * 
 * @author HuyNQ
 *
 */
public interface BaseDao {
	/**
	 * Phương thức mở kết nối
	 * 
	 * @return Connection
	 * @throws SQLException
	 */
	public Connection openConnect() throws SQLException;

	/**
	 * Phương thức đóng kết nối
	 * 
	 * @param connection
	 * @return true khi đóng kết nối thành công, false khi không đóng được kết nối
	 */
	public boolean closeConnect();

}
