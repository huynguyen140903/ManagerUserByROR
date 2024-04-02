/**
 * luvina softwware JSC, 2022
 * TblDetailUserJapanDaoImpl.java,HuyNQ

 */
package manageuser.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import manageuser.dao.TblDetailUserJapanDao;
import manageuser.entities.TblDetailUserJapanEntity;

/**
 * Lớp triển khai các phương thức tương tác với bảng tbl_detail_user_japan trong
 * database
 * 
 * @author HuyNQ
 */
public class TblDetailUserJapanDaoImpl extends BaseDaoImpl implements TblDetailUserJapanDao {
	/**
	 * Phương thức thực hiện insert dữ liệu vào bảng tbl_detail_user_japan
	 * 
	 * @param TblDetailUserJapanEntity: đối tượng tblDetailUserJapan chứa dữ liệu để
	 *                                  insert
	 * @throws SQLException: Trả ra Exception khi insert không thành công
	 */
	@Override
	public void insertDetailUserJapan(TblDetailUserJapanEntity tblDetailUserJapanEntity)
			throws SQLException {
		try {
			if (connection != null) {
				// tạo câu lệnh sql thực thi thêm mới
				StringBuilder sql = new StringBuilder();
				// Tạo câu truy vấn
				sql.append("INSERT INTO tbl_detail_user_japan (user_id, code_level, start_date, end_date, total) ");
				sql.append("VALUES (?, ?, ?, ?, ?);");
				PreparedStatement pstmt = null;
				pstmt = connection.prepareStatement(sql.toString());
				int count = 1;
				pstmt.setInt(count++, tblDetailUserJapanEntity.getUserId());
				pstmt.setString(count++, tblDetailUserJapanEntity.getCodeLevel());
				pstmt.setDate(count++, tblDetailUserJapanEntity.getStartDate());
				pstmt.setDate(count++, tblDetailUserJapanEntity.getEndDate());
				pstmt.setInt(count++, tblDetailUserJapanEntity.getTotal());
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println(
					"Lớp TblDetailUserJapanDaoImpl - phương thức insertDetailUserJapan - lỗi :" + e.getMessage());
			throw e;
		}

	}
	/**
	 * Phương thức xóa dữ liệu tại bảng tbl_detail_user_japan theo userId tương ứng
	 *@param userId : id của user muốn xóa
	 * @throws SQLException Lỗi SQL
	 */
	@Override
	public void deleteDetailUserJapanById(int userId) throws SQLException {
		try {
			//Kiểm tra kết nối
			if(connection != null) {
				// Câu truy vấn
				String sqlDeleteUser = "DELETE FROM tbl_detail_user_japan WHERE user_id = ? ;";
				//Khai báo biến PreparedStatement
				PreparedStatement preparedStatement = connection.prepareStatement(sqlDeleteUser);
				// Truyền tham số vào 
				int indexParam = 1;
				preparedStatement.setInt(indexParam++,userId);
				//Thực thi truy vấn và gán kết quả vào biến
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("TblDetailUserImpl deleteDetailUserJapanById: " + e.getMessage());
			throw e;
		}
	}
	/**
	 * Phương thức lấy ra đối tượng TblDetailUserJapanEntity theo userId truyền vào
	 * 
	 * @param userId id của user cần lấy
	 * @return null nếu user không có trình độ tiếng Nhật, đối tượng
	 *         TblDetailUserJapanEntity nếu user có trình độ tiếng Nhật
	 * @throws ClassNotFoundException Trả ra Exception khi không tìm được Driver
	 * @throws SQLException           Trả ra Exception khi lỗi kết nối đến database
	 */
	@Override
	public TblDetailUserJapanEntity getDetailUserJapanById(int userId) throws SQLException, ClassNotFoundException {
		TblDetailUserJapanEntity tblDetailUserJapanEntity = null;
		try {
			// Mở kết nối đến database
			openConnect();
			if (connection != null) {
				// Khởi tạo câu truy vấn
				StringBuilder selectQuery = new StringBuilder();
				selectQuery.append("SELECT * FROM tbl_detail_user_japan ");
				selectQuery.append("WHERE user_id = ?;");

				// Gán giá trị cho câu truy vấn
				PreparedStatement preparedStatement = connection.prepareStatement(selectQuery.toString());
				int indexParam = 1;
				preparedStatement.setInt(indexParam++, userId);

				// Thực thi câu truy vấn
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					tblDetailUserJapanEntity = new TblDetailUserJapanEntity();
				}
			}
		} catch (SQLException e) {
			System.out.println(Thread.currentThread().getStackTrace()[1].getClassName() + " "
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + " " + e.getMessage());
			throw e;
		} finally {
			// Đóng kết nối đến database
			closeConnect();
		}
		return tblDetailUserJapanEntity;
	}
	/**
	 * update thông tin trình độ tiếng Nhật của user vào bảng tbl_detail_user_japan
	 * 
	 * @param tblDetailUserJapanEntity Thông tin tiếng Nhật của user cần update
	 * @throws SQLException Trả ra Exception khi insert không thành công
	 */
	@Override
	public void updateDetailUser(TblDetailUserJapanEntity tblDetailUserJapanEntity)
			throws SQLException {
		try {
			if (connection != null) {
				int index = 1;
				StringBuilder sql = new StringBuilder("UPDATE tbl_detail_user_japan SET ");
				sql.append("code_level = ?, ");
				sql.append("start_date = ?, ");
				sql.append("end_date = ?, ");
				sql.append("total = ? ");
				sql.append("WHERE tbl_detail_user_japan.user_id = ?;");

				PreparedStatement pstmt = connection.prepareStatement(sql.toString());

				pstmt.setString(index++, tblDetailUserJapanEntity.getCodeLevel());
				pstmt.setDate(index++, tblDetailUserJapanEntity.getStartDate());
				pstmt.setDate(index++, tblDetailUserJapanEntity.getEndDate());
				pstmt.setInt(index++, tblDetailUserJapanEntity.getTotal());
				pstmt.setInt(index++, tblDetailUserJapanEntity.getUserId());
				
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println(
					"Lớp TblDetailUserJapanDaoImpl - phương thức updateDetailUser - lỗi :" + e.getMessage());
			throw e;
		}
		
	}
}
