/**
 * luvina softwware JSC, 2022
 * MstJapanDaoImpl.java,HuyNQ

 */
package manageuser.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import manageuser.dao.MstJapanDao;
import manageuser.entities.MstJapanEntity;

/**
 * 
 * Định nghĩa Phương thức dùng chung
 * 
 * @author HuyNQ
 *
 */
public class MstJapanDaoImpl extends BaseDaoImpl implements MstJapanDao {
	// Khai báo 1 kết nối
	private List<MstJapanEntity> ListJapanEntities = new ArrayList<MstJapanEntity>();
	/**
	 * Lấy danh sách trình độ tiếng Nhật
	 * 
	 * @return Danh sách trình độ tiếng Nhật
	 * @throws ClassNotFoundException Trả ra Exception khi không tìm được Driver
	 * @throws SQLException           Trả ra Exception khi lỗi kết nối đến database
	 */
	@Override
	public List<MstJapanEntity> getAllMstJapan() {
		// Bắt ngoại lệ
		try {
			// Mở kết nối
			openConnect();
			// Nếu không có kết nối
			if (connection == null) {
				// thì trả về 1 đối tượng có giá trị null
				return ListJapanEntities = null;
				// Đóng if
			}
			// Tạp 1 câu lệnh SQL
			String sql = "SELECT * FROM mst_japan";
			// Tạo 1 prepareStatement
			PreparedStatement pstmt = connection.prepareStatement(sql);
			// Thực thi câu lệnh SQL và gán giá trị vào rs
			ResultSet rs = pstmt.executeQuery();

			// vòng lặp đọc giá trị của rs
			while (rs.next()) {
				// biến đếm count
				int count = 1;
				MstJapanEntity japanEntity = new MstJapanEntity();
				japanEntity.setCodeLevel(rs.getString(count++));
				japanEntity.setNameLevel(rs.getString(count++));
				ListJapanEntities.add(japanEntity);
				// đóng while

			}
			// xử lí ngoại lệ
		} catch (SQLException | NullPointerException e) {
			// in ra màn hình console nếu có lỗi xảy ra
			System.out.println("Lớp MstJapanDaoImpl - phương thức getAllMstJapan - lỗi :" + e.getMessage());
			// xử lí ngoại lệ xong thực hiện finally
		} finally {
			// Đóng kết nối
			closeConnect();
			// đóng finally
		}
		return ListJapanEntities;
	}

}
