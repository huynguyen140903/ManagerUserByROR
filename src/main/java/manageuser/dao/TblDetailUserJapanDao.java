/**
 * luvina softwware JSC, 2022
 * TblDetailUserJapanDao.java,HuyNQ

 */
package manageuser.dao;

import java.sql.Connection;
import java.sql.SQLException;

import manageuser.entities.TblDetailUserJapanEntity;

/**
 * Class này dùng để khai báo các phương thức dùng chung
 * 
 * @author HuyNQ
 *
 */
public interface TblDetailUserJapanDao {
	/**
	 * Thêm đối tượng TblDetailUserJapanEntity vào DB
	 * 
	 * @param tblDetailUserJapanEntity 
	 * @param connection
	 * @throws SQLException
	 */
	public void insertDetailUserJapan(TblDetailUserJapanEntity tblDetailUserJapanEntity)
			throws SQLException;
	/**
	 * Phương thức xóa dữ liệu tại bảng tbl_detail_user_japan theo userId tương ứng
	 *@param userId : id của user muốn xóa
	 * @throws SQLException Lỗi SQL
	 */
	public void deleteDetailUserJapanById(int userId) throws SQLException;
	/**
	 * update thông tin trình độ tiếng Nhật của user vào bảng tbl_detail_user_japan
	 * 
	 * @param tblDetailUserJapanEntity Thông tin tiếng Nhật của user cần update
	 * @throws SQLException Trả ra Exception khi insert không thành công
	 */
	public void updateDetailUser(TblDetailUserJapanEntity tblDetailUserJapanEntity)
			throws SQLException;
	/**
	 * Phương thức lấy ra đối tượng TblDetailUserJapanEntity theo userId truyền vào
	 * 
	 * @param userId id của user cần lấy
	 * @return null nếu user không có trình độ tiếng Nhật, đối tượng
	 *         TblDetailUserJapanEntity nếu user có trình độ tiếng Nhật
	 * @throws ClassNotFoundException Trả ra Exception khi không tìm được Driver
	 * @throws SQLException           Trả ra Exception khi lỗi kết nối đến database
	 */
	public TblDetailUserJapanEntity getDetailUserJapanById(int userId) throws SQLException, ClassNotFoundException;
}
