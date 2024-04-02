/**
 * luvina softwware JSC, 2022
 * TblUserDao.java,HuyNQ

 */
package manageuser.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import manageuser.entities.TblUserEntity;
import manageuser.entities.UserInforEntity;

/**
 * Interface khai báo các phương thức tương tác với bảng tbl_user trong database
 * 
 * @author HuyNQ
 */
public interface TblUserDao extends BaseDao {
	/**
	 * Phương thức lấy ra password, rule và salt trong bảng tbl_user dựa vào
	 * loginName
	 * 
	 * @param loginName loginName của user cần lấy thông tin
	 * @return Trả về entity của bảng tbl_user chứa 3 thông tin password, salt và
	 *         rule, nếu không tồn tại loginName tương ứng trong db thì trả về null
	 * @throws SQLException           Trả ra SQLException khi kết nối với database
	 *                                bị lỗi
	 */
	public TblUserEntity getTblUserByLoginName(String loginName) throws SQLException;

	/**
	 * Phương thức lấy dữ liệu trong DB
	 * 
	 * @param loginName
	 * @return TblUserEntity
	 * @throws SQLException
	 */
	public TblUserEntity getTblUserByEmail(String email) throws SQLException;
	/**
	 * 
	 * @param email
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public TblUserEntity getTblUserByEmailAndUserId(String email, int userId) throws SQLException;

	/**
	 * Lấy tổng user trong DB
	 * 
	 * @param groupId
	 * @param fullName
	 * @return int
	 * @throws SQLException
	 */
	public int getTotalUsers(int groupId, String fullName) throws SQLException;

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
	 * @throws SQLException           Trả ra Exception khi lỗi kết nối đến database
	 */
	public List<UserInforEntity> getListUsers(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate) throws SQLException;

	/**
	 * Phuong thuc them moi trong table user
	 * 
	 * @param tblUserEntity
	 * @return
	 * @throws SQLException
	 */
	public Integer insertUser(TblUserEntity tblUserEntity, Connection connection) throws SQLException;
	
	/**
	 * Phương thức lấy tblUserEntity theo userId
	 * @param userId: id của user cần lấy
	 * @return tblUser: thông tin chính của 1 user trong bảng tbl_user
	 * @throws ClassNotFoundException: lỗi không tìm thấy lớp
	 * @throws SQLException            : lỗi SQL
	 */
	public TblUserEntity getUserByUserId(int userId) throws SQLException;

	/**
	 * Phương thức lấy ra UserInfor theo userId để hiển thị cho màn hình ADM005
	 * @param userId: id của user
	 * @return UserInfor: user có thông tin chi tiết 
	 * @throws ClassNotFoundException Lỗi không tìm thấy lớp
	 * @throws SQLException           Lỗi SQL
	 */
	public UserInforEntity getUserInforByUserId(int userId) throws SQLException;
	/**
	 * Xóa user khỏi bảng tbl_user dựa vào userId tương ứng
	 * @param userId : id của user cần xóa
	 * @throws SQLException Lỗi SQL
	 */
	public void deleteUserById(int userId) throws SQLException;
	/**
	 * Thực hiện update 1 user trong database
	 * 
	 * @param tblUser thông tin của user muốn update
	 * @throws SQLException Trả ra Exception khi update không thành công
	 */
	public void updateUser(TblUserEntity tblUserEntity) throws SQLException;
}
