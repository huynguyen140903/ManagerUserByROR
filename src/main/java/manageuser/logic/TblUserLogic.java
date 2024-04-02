/**
 * luvina softwware JSC, 2022
 * TblUserLogic.java,HuyNQ

 */
package manageuser.logic;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import manageuser.entities.UserInforEntity;

/**
 * Class này dùng để khai báo các phương thức dùng chung
 * 
 * @author HuyNQ
 *
 */
public interface TblUserLogic {
	/**
	 * Phương thức checkLogin dùng để kiểm tra tài khoản và mật khẩu đăng nhập có
	 * đúng với dữ liệu trong DB không
	 * 
	 * @param loginName tên đăng nhập
	 * @param pass      Mật khẩu
	 * @return true: khi trùng cả loginName và pass. False khi sai LoginName hoặc
	 *         pass
	 * @throws NoSuchAlgorithmException
	 * @throws SQLException
	 */
	public boolean checkLogin(String loginName, String pass) throws NoSuchAlgorithmException, SQLException;

	/**
	 * Phương thức tính tổng sô User thỏa mãn điều kiện
	 * 
	 * @param groupId  Nhóm tìm kiếm
	 * @param fullName tên tìm kiếm
	 * @return int
	 * @throws SQLException
	 */
	public int getTotalUsers(int groupId, String fullName) throws SQLException;

	/**
	 * Phương thức trả về danh sách user thỏa mãn điều kiện
	 * 
	 * @param offset          vi tri data cần lấy
	 * @param limit           số bản ghi giới hạn
	 * @param groupId         Nhóm tìm kiếm
	 * @param fullName        tên tìm kiếm
	 * @param sortType        sắp xếp theo loại gì
	 * @param sortByFullName  sắp xếp tăng hay giảm
	 * @param sortByCodeLevel sắp xếp tăng hay giảm
	 * @param sortByEndDate   sắp xếp tăng hay giảm
	 * @return List<UserInforEntity>
	 * @throws SQLException
	 */
	public List<UserInforEntity> getListUsers(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate) throws SQLException;

	/**
	 * Phuong thuc them moi user trong db
	 * @param user UserInforEntity
	 * @return Boolean
	 * @throws SQLException 
	 * @throws NoSuchAlgorithmException 
	 */
	public boolean createUser(UserInforEntity user) throws SQLException, NoSuchAlgorithmException;
	/**
	 * Phương thức kiểm tra có user nào tồn tại trong bảng tbl_user với id cho trước
	 * hay không
	 * 
	 * @param userId user_id cần kiểm tra
	 * @return true nếu có tồn tại user tương ứng với user_id, false nếu không tồn
	 *         tại
	 * @throws ClassNotFoundException Trả ra Exception khi không tìm được Driver
	 * @throws SQLException           Trả ra Exception khi lỗi kết nối đến database
	 */
	
	public boolean checkExistUserId(int userId) throws SQLException;
	
	/**
	* lấy thông tin của UserInfoEntity thao userId tương ứng
	* @param userId id của user tương ứng
	* @return
	* @throws SQLException lỗi SQL
	*/
	public UserInforEntity getUserInforByUserId(int userId) throws SQLException;
	/**
	 * Phương thức xóa thông tin của user theo userId
	 * @param userId id của user
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteUserByUserId(int userId) throws SQLException;
	/**
	 * Phương thức lấy ra lỗi nếu user không tồn tại hoặc user là Admin
	 * @param userId id của user
	 * @return
	 * @throws SQLException
	 */
	public String getRuleByUserId(int userId) throws SQLException;
	/**
	 * Thực hiện update user ở 2 bảng tbl_user và tbl_detail_user_japan
	 * 
	 * @param userInfor              Thông tin của user cần update
	 * @param isExistDetailUserJapan Biến cho biết user có trình độ tiếng Nhật hay
	 *                               không
	 * @return Trả về true nếu update thành công, false nếu update thất bại
	 *
	 * @throws SQLException             Trả ra Exception khi lỗi kết nối đến
	 *                                  database
	 */
	
	public boolean updateUser(UserInforEntity userInforEntity)
			throws SQLException;
}
