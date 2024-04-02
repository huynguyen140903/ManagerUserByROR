/**
 * luvina softwware JSC, 2022
 * TblUserLogicImpl.java,HuyNQ

 */
package manageuser.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import manageuser.dao.TblUserDao;
import manageuser.entities.TblUserEntity;
import manageuser.entities.UserInforEntity;
import manageuser.utils.Constant;

/**
 * Implement TblUserDao để Thao tác với DB của các chức năng login + list/search
 * user
 * 
 * @author HuyNQ
 *
 */
public class TblUserDaoImpl extends BaseDaoImpl implements TblUserDao {
	// Khai báo 1 TblUserEntity
	private TblUserEntity tblUserEntity = new TblUserEntity();
	private int totalUsers = 0;
	private int generatedKey = 0;
	private List<UserInforEntity> listUserInforEntity = new ArrayList<UserInforEntity>();
	/**
	 * Phương thức lấy ra password, rule và salt trong bảng tbl_user dựa vào
	 * loginName
	 * 
	 * @param loginName loginName của user cần lấy thông tin
	 * @return Trả về entity của bảng tbl_user chứa 3 thông tin password, salt và
	 *         rule, nếu không tồn tại loginName tương ứng trong db thì trả về null
	 * @throws SQLException Trả ra SQLException khi kết nối với database bị lỗi
	 */
	@Override
	public TblUserEntity getTblUserByLoginName(String loginName) throws SQLException {
		// Bắt ngoại lệ
		try {
			// Mở kết nối
			openConnect();
			// Nếu không có kết nối
			if (connection == null) {
				// thì trả về 1 đối tượng có giá trị null
				return tblUserEntity = null;
				// Đóng if
			}
			// Tạp 1 câu lệnh SQL
			StringBuilder sql = new StringBuilder(
					"SELECT tbl_user.password,tbl_user.salt, tbl_user.rule FROM tbl_user  Where login_name = ?;");
			// Tạo 1 prepareStatement
			PreparedStatement pstmt = connection.prepareStatement(sql.toString());
			// thay thế ? bằng giá trị tham số truyền vào
			pstmt.setString(1, loginName);
			// Thực thi câu lệnh SQL và gán giá trị vào rs
			ResultSet rs = pstmt.executeQuery();

			// vòng lặp đọc giá trị của rs
			while (rs.next()) {
				// biến đếm count
				int count = 1;
				// set giá trị đầu tiên từ kết quả database cho pass của user
				tblUserEntity.setPassword(rs.getString(count++));
				// set giá trị tiếp theo từ kết quả database cho salt của user
				tblUserEntity.setSalt(rs.getString(count++));
				tblUserEntity.setRule(rs.getInt(count));
				// đóng while
			}
			// set giá trị tham số loginName cho loginName của user
			tblUserEntity.setLoginName(loginName);
			if (tblUserEntity.getPassword() == null) {
				return null;
			}
			// xử lí ngoại lệ
		} catch (SQLException e) {
			// in ra màn hình console nếu có lỗi xảy ra
			System.out.println("Lớp TblUserDaoImpl - phương thức getTblUserByLoginName - lỗi :" + e.getMessage());
			throw e;
			// xử lí ngoại lệ xong thực hiện finally
		} finally {
			// Đóng kết nối
			closeConnect();
			// đóng finally
		}
		// trả về 1 user có kiểu TblUserEntity
		return tblUserEntity;
		// đóng phương thức
	}
	/**
	 * Lấy ra đối tượng TblUserEntity dựa theo email
	 * 
	 * @param email giá trị email truyền vào
	 * @return Trả về null nếu không có user nào có email giống với tham số truyền
	 *         vào, trả về đối tượng TblUserEntity nếu có
	 * @throws SQLException Trả ra Exception khi lỗi kết nối đến database
	 */
	@Override
	public TblUserEntity getTblUserByEmail(String email) throws SQLException {
		// Bắt ngoại lệ
		try {
			// Mở kết nối
			openConnect();
			// Nếu không có kết nối
			if (connection == null) {
				// thì trả về 1 đối tượng có giá trị null
				return tblUserEntity = null;
				// Đóng if
			}
			// Tạp 1 câu lệnh SQL
			StringBuilder sql = new StringBuilder("SELECT tbl_user.email FROM tbl_user  Where email = ?;");
			// Tạo 1 prepareStatement
			PreparedStatement pstmt = connection.prepareStatement(sql.toString());
			// thay thế ? bằng giá trị tham số truyền vào
			pstmt.setString(1, email);
			// Thực thi câu lệnh SQL và gán giá trị vào rs
			ResultSet rs = pstmt.executeQuery();
			// vòng lặp đọc giá trị của rs
			while (rs.next()) {
				tblUserEntity.setEmail(rs.getString(1));
			}

			if (tblUserEntity.getEmail() == null) {
				tblUserEntity = null;
			}
			// xử lí ngoại lệ
		} catch (SQLException e) {
			// in ra màn hình console nếu có lỗi xảy ra
			System.out.println("Lớp TblUserDaoImpl - phương thức getTblUserByLoginName - lỗi :" + e.getMessage());
			throw e;
			// xử lí ngoại lệ xong thực hiện finally
		} finally {
			// Đóng kết nối
			closeConnect();
			// đóng finally
		}
		// trả về 1 user có kiểu TblUserEntity
		return tblUserEntity;
		// đóng phương thức
	}

	/**
	 * Lấy ra đối tượng TblUserEntity dựa theo email và userId
	 * 
	 * @param email  giá trị email truyền vào
	 * @param userId giá trị id truyền vào
	 * @return Trả về null nếu không có user nào có email giống với tham số truyền
	 *         vào, trả về đối tượng TblUserEntity nếu có
	 * @throws SQLException Trả ra Exception khi lỗi kết nối đến database
	 */
	@Override
	public TblUserEntity getTblUserByEmailAndUserId(String email, int userId) throws SQLException {
		try {
			// Mở kết nối
			openConnect();
			// Nếu không có kết nối
			if (connection == null) {
				// thì trả về 1 đối tượng có giá trị null
				return tblUserEntity = null;
				// Đóng if
			}
			// Tạp 1 câu lệnh SQL
			StringBuilder sql = new StringBuilder(
					"SELECT tbl_user.email FROM tbl_user  Where email = ?  and user_id != ?;");
			// Tạo 1 prepareStatement
			PreparedStatement pstmt = connection.prepareStatement(sql.toString());
			// thay thế ? bằng giá trị tham số truyền vào
			int count = 1;
			pstmt.setString(count++, email);
			pstmt.setInt(count, userId);
			// Thực thi câu lệnh SQL và gán giá trị vào rs
			ResultSet rs = pstmt.executeQuery();
			// vòng lặp đọc giá trị của rs
			while (rs.next()) {
				tblUserEntity.setEmail(rs.getString(1));
			}

			if (tblUserEntity.getEmail() == null) {
				tblUserEntity = null;
			}
			// xử lí ngoại lệ
		} catch (SQLException e) {
			// in ra màn hình console nếu có lỗi xảy ra
			System.out.println("Lớp TblUserDaoImpl - phương thức getTblUserByEmailAndUserId - lỗi :" + e.getMessage());
			throw e;
			// xử lí ngoại lệ xong thực hiện finally
		} finally {
			// Đóng kết nối
			closeConnect();
			// đóng finally
		}
		// trả về 1 user có kiểu TblUserEntity
		return tblUserEntity;
	}
	/**
	 * Lấy tổng user trong DB
	 * 
	 * @param groupId  id nhóm
	 * @param fullName Tên đăng nhập
	 * @return int tổng số người dùng lấy ra được từ DB
	 * @throws SQLException
	 */
	@Override
	public int getTotalUsers(int groupId, String fullName) throws SQLException {
		// Bắt ngoại lệ
		try {
			totalUsers = 0;
			// Mở kết nối
			openConnect();
			// Nếu không có kết nối
			if (connection == null) {
				// thì trả về 1 đối tượng có giá trị null
				return totalUsers = 0;
				// Đóng if
			}
			// Tạp 1 câu lệnh SQL
			StringBuilder sql = new StringBuilder();
			sql.append(
					"SELECT u.user_id,u.full_name, u.birthday, g.group_name,u.email ,u.tel, m.name_level,d.end_date,d.total ");
			sql.append("FROM tbl_user u inner join mst_group g on u.group_id = g.group_id ");
			sql.append(
					"left join tbl_detail_user_japan d on u.user_id = d.user_id left join mst_japan m on d.code_level = m.code_level where u.rule = 1 ");
			PreparedStatement pstmt = null;

			if (groupId == 0) {
				sql.append("and u.full_name like ? ");

			} else if (groupId != 0) {
				sql.append("and u.group_id like ? and u.full_name like ? ");
			}
			if (groupId == 0) {
				pstmt = connection.prepareStatement(sql.toString());
				pstmt.setString(1, "%" + fullName + "%");
			} else if (groupId != 0) {
				pstmt = connection.prepareStatement(sql.toString());
				pstmt.setInt(1, groupId);
				pstmt.setString(2, "%" + fullName + "%");
			}
			// Thực thi câu lệnh SQL và gán giá trị vào rs
			ResultSet rs = pstmt.executeQuery();
			// vòng lặp đọc giá trị của rs
			while (rs.next()) {
				totalUsers++;
				// đóng while
			}
			// xử lí ngoại lệ
		} catch (SQLException e) {
			// in ra màn hình console nếu có lỗi xảy ra
			System.out.println("Lớp TblUserDaoImpl - phương thức getTotalUsers - lỗi :" + e.getMessage());
			throw e;
			// xử lí ngoại lệ xong thực hiện finally
		} finally {
			// Đóng kết nối
			closeConnect();
			// đóng finally
		}
		return totalUsers;
	}

	/**
	 * Lấy danh sách user
	 * 
	 * @param offset          Vị trí data cần lấy vào
	 * @param limit           Số lượng lấy
	 * @param groupId         Mã nhóm tìm kiếm
	 * @param fullName        Tên tìm kiếm
	 * @param sortType        Nhận biết xem cột nào được ưu tiên sắp xếp (full_name
	 *                        hoặc code_level hoặc end_date)
	 * @param sortByFullName  Giá trị sắp xếp của cột Tên (ASC hoặc DESC)
	 * @param sortByCodeLevel Giá trị sắp xếp của cột Trình độ tiếng Nhật (ASC hoặc
	 *                        DESC)
	 * @param sortByEndDate   Giá trị sắp xếp của cột Ngày hết hạn (ASC hoặc DESC)
	 * @return Danh sách User
	 * @throws SQLException Trả ra Exception khi lỗi kết nối đến database
	 */
	@Override
	public List<UserInforEntity> getListUsers(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate) throws SQLException {
		try {
			// Mở kết nối
			openConnect();
			if (connection == null) {
				return listUserInforEntity;
			}
			// tạo câu lệnh SQL
			StringBuilder sqlOrder = new StringBuilder();
			StringBuilder sql = new StringBuilder();
			sql.append(
					"SELECT u.user_id,u.full_name, u.birthday, g.group_name,u.email ,u.tel, m.name_level,d.end_date,d.total ");
			sql.append("FROM tbl_user u inner join mst_group g on u.group_id = g.group_id ");
			sql.append("left join tbl_detail_user_japan d on u.user_id = d.user_id ");
			sql.append(" left join mst_japan m on d.code_level = m.code_level ");
			sql.append(" where u.rule = 1 ");
			if ("default".equals(sortType)) {
				sqlOrder.append("order by u.full_name asc,m.name_level desc, d.end_date desc limit ");
			} else if ("FullName".equals(sortType)) {
				sqlOrder.append("order by u.full_name ");
				sqlOrder.append(sortByFullName);
				sqlOrder.append(" ,d.code_level desc, m.name_level desc limit ");
			} else if ("CodeLevel".equals(sortType)) {
				sqlOrder.append("order by d.code_level ");
				sqlOrder.append(sortByCodeLevel);
				sqlOrder.append(" ,u.full_name asc, m.name_level desc limit ");
			} else if ("EndDate".equals(sortType)) {
				sqlOrder.append("order by  d.end_date ");
				sqlOrder.append(sortByEndDate);
				sqlOrder.append(" ,u.full_name asc,m.name_level desc limit ");
			} else {
				sqlOrder.append("order by u.full_name asc,m.name_level desc, d.end_date desc limit ");
			}
			sqlOrder.append(limit + "");
			sqlOrder.append(" OFFSET ");
			sqlOrder.append(offset + "");

			PreparedStatement pstmt = null;
			if (groupId == 0) {
				sql.append("and u.full_name like ? ");
				sql.append(sqlOrder);

			} else if (groupId != 0) {
				sql.append("and u.group_id like ? and u.full_name like ? ");
				sql.append(sqlOrder);
			}

			if (groupId == 0) {
				pstmt = connection.prepareStatement(sql.toString());
				pstmt.setString(1, "%" + fullName + "%");
			} else if (groupId != 0) {
				pstmt = connection.prepareStatement(sql.toString());
				pstmt.setInt(1, groupId);
				pstmt.setString(2, "%" + fullName + "%");
			}
			// thực hiện câu lệnh SQL và lấy giá trị trả ra
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				UserInforEntity userInforEntity = new UserInforEntity();
				int count = 1;
				userInforEntity.setUserId(rs.getInt(count++));
				userInforEntity.setFullName(rs.getString(count++));
				userInforEntity.setBirthday(rs.getDate(count++));
				userInforEntity.setGroup(rs.getString(count++));
				userInforEntity.setEmail(rs.getString(count++));
				userInforEntity.setTel(rs.getString(count++));
				userInforEntity.setJapanLevel(rs.getString(count++));
				userInforEntity.setEndDate(rs.getDate(count++));
				userInforEntity.setTotal(rs.getInt(count++));
				listUserInforEntity.add(userInforEntity);

			}

		} catch (SQLException e) {
			System.out.println("Lớp TblUserDaoImpl - phương thức getListUsers - lỗi :" + e.getMessage());
			throw e;
		} finally {
			closeConnect();
		}

		return listUserInforEntity;
	}

	/**
	 * Thực hiện thêm mới 1 user vào database
	 * 
	 * @param tblUser Đối tượng chứa thông tin của user
	 * @return id của user vừa thêm mới
	 * @throws SQLException Trả ra Exception khi insert không thành công
	 */
	@Override
	public Integer insertUser(TblUserEntity tblUserEntity, Connection connection) throws SQLException {
		try {

			// kiểm tra kết nối
			if (connection != null) {
				// tạo câu lệnh SQL
				StringBuilder sql = new StringBuilder();
				sql.append("INSERT INTO tbl_user (group_id, login_name, password, ");
				sql.append("full_name, full_name_kana, email, tel, birthday, rule, salt) ");
				sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
				PreparedStatement pstmt = null;
				pstmt = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				int count = 1;
				pstmt.setInt(count++, tblUserEntity.getGroupId());
				pstmt.setString(count++, tblUserEntity.getLoginName());
				pstmt.setString(count++, tblUserEntity.getPassword());
				pstmt.setString(count++, tblUserEntity.getFullName());
				pstmt.setString(count++, tblUserEntity.getFullNameKana());
				pstmt.setString(count++, tblUserEntity.getEmail());
				pstmt.setString(count++, tblUserEntity.getTel());
				pstmt.setDate(count++, tblUserEntity.getBirthday());
				pstmt.setInt(count++, tblUserEntity.getRule());
				pstmt.setString(count++, tblUserEntity.getSalt());
				// thực thi câu lệnh SQL
				pstmt.execute();
				// lấy ra giá trị id sau khi đã hoàn thành câu lệnh SQL
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					generatedKey = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			System.out.println("Lớp TblUserDaoImpl - phương thức insertUser - lỗi :" + e.getMessage());
			throw e;
		}

		return generatedKey;
	}

	/**
	 * Phương thức lấy tblUserEntity theo userId
	 * 
	 * @param userId: id của user cần lấy
	 * @return tblUser: thông tin chính của 1 user trong bảng tbl_user
	 * @throws ClassNotFoundException: lỗi không tìm thấy lớp
	 * @throws SQLException            : lỗi SQL
	 */
	@Override
	public TblUserEntity getUserByUserId(int userId) throws SQLException {
		TblUserEntity tblUserEntity = new TblUserEntity();
		try {
			// mở kết nối
			openConnect();
			// kiểm tra nếu kết nối thành công
			if (connection != null) {
				// Tạo câu truy vấn
				StringBuilder select = new StringBuilder();
				select.append("SELECT * FROM tbl_user ");
				select.append("WHERE user_id = ? ");
				select.append("AND rule = " + Constant.DEFAULT_RULE);
				// khai báo preparedStatement
				PreparedStatement preparedStatement = connection.prepareStatement(select.toString());
				// truyền dữ liệu cho câu truy vấn
				int index = 1;
				preparedStatement.setInt(index++, userId);
				// Thực hiện truy vấn và lấy về kết quả
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					tblUserEntity.setUserId(resultSet.getInt("user_id"));
					tblUserEntity.setLoginName(resultSet.getString("login_name"));
					tblUserEntity.setGroupId(resultSet.getInt("group_id"));
					tblUserEntity.setFullName(resultSet.getString("full_name"));
					tblUserEntity.setFullNameKana(resultSet.getString("full_name_kana"));
					tblUserEntity.setBirthday(resultSet.getDate("birthday"));
					tblUserEntity.setEmail(resultSet.getString("email"));
					tblUserEntity.setTel(resultSet.getString("tel"));
				}
			}
		} catch (SQLException e) {
			System.out.println("TblUserDaoImpl: getTblUserByUserId" + e.getMessage());
			throw e;
		} finally {
			// đóng kết nối
			closeConnect();
		}
		return tblUserEntity;
	}

	/**
	 * Phương thức lấy ra UserInfor theo userId để hiển thị cho màn hình ADM005
	 * 
	 * @param userId: id của user
	 * @return UserInfor: user có thông tin chi tiết
	 * @throws ClassNotFoundException Lỗi không tìm thấy lớp
	 * @throws SQLException           Lỗi SQL
	 */
	@Override
	public UserInforEntity getUserInforByUserId(int userId) throws SQLException {
		UserInforEntity userInforEntity = new UserInforEntity();
		try {
			openConnect();
			// Nếu không có kết nối
			if (connection == null) {
				// thì trả về 1 đối tượng có giá trị null
				return userInforEntity = null;

			}
			// Tạp 1 câu lệnh SQL
			StringBuilder sql = new StringBuilder();
			sql.append(
					"SELECT u.login_name, u.rule, g.group_name , g.group_id, u.full_name, u.full_name_kana, u.birthday, u.email, u.tel,m.name_level,m.code_level, d.start_date, d.end_date, d.total ");
			sql.append("FROM tbl_user u ");
			sql.append("inner join mst_group g on u.group_id = g.group_id ");
			sql.append("left join tbl_detail_user_japan d on u.user_id = d.user_id ");
			sql.append(" left join mst_japan m on d.code_level = m.code_level ");
			sql.append(" where u.user_id = ?;");
			PreparedStatement pstmt = connection.prepareStatement(sql.toString());
			// thay thế ? bằng giá trị tham số truyền vào
			pstmt.setInt(1, userId);
			// Thực thi câu lệnh SQL và gán giá trị vào rs
			ResultSet rs = pstmt.executeQuery();
			int temp = 0;
			// vòng lặp đọc giá trị của rs
			while (rs.next()) {

				userInforEntity.setLoginName(rs.getString("login_name"));
				userInforEntity.setRule(rs.getInt("rule"));
				userInforEntity.setGroup(rs.getString("group_name"));
				userInforEntity.setGroupId(rs.getInt("group_id"));
				userInforEntity.setFullName(rs.getString("full_name"));
				userInforEntity.setFullNameKana(rs.getString("full_name_kana"));
				userInforEntity.setBirthday(rs.getDate("birthday"));
				userInforEntity.setEmail(rs.getString("email"));
				userInforEntity.setTel(rs.getString("tel"));
				userInforEntity.setJapanLevel(rs.getString("name_level"));
				userInforEntity.setCodeLevel(rs.getString("code_level"));
				userInforEntity.setStartDate(rs.getDate("start_date"));
				userInforEntity.setEndDate(rs.getDate("end_date"));
				userInforEntity.setTotal(rs.getInt("total"));
			}

		} catch (SQLException e) {
			// in ra màn hình console nếu có lỗi xảy ra
			System.out.println("Lớp TblUserDaoImpl - phương thức getUserInforByUserId - lỗi :" + e.getMessage());
			throw e;
			// xử lí ngoại lệ xong thực hiện finally
		} finally {
			closeConnect();
		}
		// trả về 1 user có kiểu UserInforEntity
		return userInforEntity;

	}

	/**
	 * Xóa user khổi bảng tbl_user dựa vào userId tương ứng
	 * 
	 * @param userId : id của user cần xóa
	 * @throws SQLException Lỗi SQL
	 */
	@Override
	public void deleteUserById(int userId) throws SQLException {
		try {
			openConnect();
			// Kiểm tra kết nối
			if (connection != null) {
				// Câu truy vấn
				String deleteUser = "DELETE FROM tbl_user WHERE user_id = ? AND rule = " + Constant.DEFAULT_RULE + " ;";
				// Khai báo biến PreparedStatement
				PreparedStatement preparedStatement = connection.prepareStatement(deleteUser);

				// Truyền tham số vào
				int indexParam = 1;
				preparedStatement.setInt(indexParam++, userId);
				// Thực thi truy vấn và gán kết quả vào biến
				preparedStatement.executeUpdate();
			}

		} catch (SQLException e) {
			System.out.println("TblUserDaoImpl deleteUserById: " + e.getMessage());
			throw e;
		}
	}

	/**
	 * Thực hiện update 1 user trong database
	 * 
	 * @param tblUser thông tin của user muốn update
	 * @throws SQLException Trả ra Exception khi update không thành công
	 */
	@Override
	public void updateUser(TblUserEntity tblUserEntity) throws SQLException {
		try {
			openConnect();
			if (connection != null) {
				StringBuilder sql = new StringBuilder("UPDATE tbl_user SET ");
				sql.append(" tbl_user.group_id = ?, ");
				sql.append(" tbl_user.full_name = ?, ");
				sql.append(" tbl_user.full_name_kana = ?, ");
				sql.append(" tbl_user.email = ?, ");
				sql.append(" tbl_user.tel = ?, ");
				sql.append(" tbl_user.birthday = ? ");
				sql.append(" WHERE tbl_user.user_id = ? AND tbl_user.rule = ? ;");
				PreparedStatement pstmt = connection.prepareStatement(sql.toString());
				// thay thế ? bằng giá trị tham số truyền vào
				int indexParam = 1;
				pstmt.setInt(indexParam++, tblUserEntity.getGroupId());
				pstmt.setString(indexParam++, tblUserEntity.getFullName());
				pstmt.setString(indexParam++, tblUserEntity.getFullNameKana());
				pstmt.setString(indexParam++, tblUserEntity.getEmail());
				pstmt.setString(indexParam++, tblUserEntity.getTel());
				pstmt.setDate(indexParam++, tblUserEntity.getBirthday());
				pstmt.setInt(indexParam++, tblUserEntity.getUserId());
				pstmt.setInt(indexParam++, Constant.DEFAULT_RULE);
				// Thực thi câu lệnh SQL
				pstmt.executeUpdate();

			}
		} catch (SQLException e) {
			// in ra màn hình console nếu có lỗi xảy ra
			System.out.println("Lớp TblUserDaoImpl - phương thức updateUser - lỗi :" + e.getMessage());
			throw e;
		} finally {
			closeConnect();
		}
	}

}
