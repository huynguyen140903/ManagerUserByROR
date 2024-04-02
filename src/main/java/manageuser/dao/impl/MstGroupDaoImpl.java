/**
 * luvina softwware JSC, 2022
 * MstGroupDaoImpl.java,HuyNQ

 */
package manageuser.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import manageuser.dao.MstGroupDao;
import manageuser.entities.MstGroupEntity;

/**
 * 
 * Lớp triển khai các phương thức tương tác với bảng mst_group trong database
 * 
 * @author HuyNQ
 *
 */

public class MstGroupDaoImpl extends BaseDaoImpl implements MstGroupDao {
	// Khai báo 1 kết nối
	private List<MstGroupEntity> ListGroupEntities = new ArrayList<MstGroupEntity>();

	/**
	 * Lấy danh sách group
	 * 
	 * @return Danh sách group
	 * @throws ClassNotFoundException Trả ra Exception khi không tìm được Driver
	 * @throws SQLException           Trả ra Exception khi lỗi kết nối đến database
	 */
	@Override
	public List<MstGroupEntity> getAllMstGroup() {
		// Bắt ngoại lệ
		try {
			// Mở kết nối
			openConnect();
			// Nếu không có kết nối
			if (connection == null) {
				// thì trả về 1 đối tượng có giá trị null
				return ListGroupEntities = null;
				// Đóng if
			}
			// Tạp 1 câu lệnh SQL
			String sql = "SELECT * FROM mst_group";
			// Tạo 1 prepareStatement
			PreparedStatement pstmt = connection.prepareStatement(sql);
			// Thực thi câu lệnh SQL và gán giá trị vào rs
			ResultSet rs = pstmt.executeQuery();

			// vòng lặp đọc giá trị của rs
			while (rs.next()) {
				// biến đếm count
				int count = 1;
				MstGroupEntity groupEntity = new MstGroupEntity();
				groupEntity.setGroupId(rs.getInt(count++));
				groupEntity.setGroupName(rs.getString(count++));
				ListGroupEntities.add(groupEntity);
				// đóng while

			}

		} catch (SQLException | NullPointerException e) {

			System.out.println("Lớp TblUserDaoImpl - phương thức getDataTblUser - lỗi :" + e.getMessage());

		} finally {

			closeConnect();

		}
		return ListGroupEntities;
	}

}
