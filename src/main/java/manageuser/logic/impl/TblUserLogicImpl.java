/**
 * luvina softwware JSC, 2022
 * TblUserLogicImpl.java,HuyNQ

 */
package manageuser.logic.impl;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import manageuser.dao.TblUserDao;
import manageuser.dao.impl.TblDetailUserJapanDaoImpl;
import manageuser.dao.impl.TblUserDaoImpl;
import manageuser.entities.TblDetailUserJapanEntity;
import manageuser.entities.TblUserEntity;
import manageuser.entities.UserInforEntity;
import manageuser.logic.TblUserLogic;
import manageuser.utils.Common;
import manageuser.utils.Constant;
import manageuser.utils.MessageErrorProperties;

/**
 * Class này dùng để định nghĩa lại các phương thức dùng chung của lớp InterFace
 * 
 * @author HuyNQ
 *
 */
public class TblUserLogicImpl implements TblUserLogic {

	private Connection connection;

	@Override
	public boolean checkLogin(String loginName, String pass) {
		// Bắt ngoại lệ
		try {
			// khai báo khởi tạo đối tượng tblUserDaoImpl
			TblUserDao tblUserDaoImpl = new TblUserDaoImpl();
			// Khai báo khơi tạo đối tượng user có kiểu TblUserEntity, và gọi phương thức
			// getTblUserByLoginName
			TblUserEntity user = tblUserDaoImpl.getTblUserByLoginName(loginName);
			// Nếu đối tượng user có giá trị null
			if (user == null) {
				// trả về false,tên tài khoản sai đăng nhập thất bại
				return false;
				// nếu lấy được đối tượng tương ứng trong db
			} else {
				// Khai báo đối tượng passSHA truyền và gọi hàm mã hóa truyền vào salt của tài
				// khoản+ pass mới nhập
				String passSHA = Common.encryptPass(user.getSalt(), pass);

				// nếu pass trong DB != pass nhập đã mã hóa
				if (!passSHA.equals(user.getPassword())) {
					// trả về false. mật khẩu sai đăng nhập thất bại
					return false;
					// nếu pass trong DB = pass nhập đã mã hóa
				} else {
					// trả về true. mật khẩu đúng đăng nhập thành công
					return true;
					// đóng else
				}
				// đóng else
			}
			// xử lí ngoại lệ
		} catch (NoSuchAlgorithmException | SQLException e) {
			// in ra màn hình console
			System.out.println("Lớp TblUserLogicImpl phương thức checkLogin " + e.getMessage());

			// Đóng catch

		}
		// trả về false. đăng nhập thất bại
		return false;
		// đóng phương thức
	}

	@Override
	public int getTotalUsers(int groupId, String fullName) throws SQLException {
		TblUserDao tblUserDaoImpl = new TblUserDaoImpl();
		if (!"".equals(fullName) || fullName == null) {
			fullName = fullName.replace("%", "\\%");
			fullName = fullName.replace("_", "\\_");
		}
		return tblUserDaoImpl.getTotalUsers(groupId, fullName);
	}

	@Override
	public List<UserInforEntity> getListUsers(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate) throws SQLException {
		TblUserDao tblUserDaoImpl = new TblUserDaoImpl();
		if (!"".equals(fullName) || fullName == null) {
			fullName = fullName.replace("%", "\\%");
			fullName = fullName.replace("_", "\\_");
		}

		return tblUserDaoImpl.getListUsers(offset, limit, groupId, fullName, sortType, sortByFullName, sortByCodeLevel,
				sortByEndDate);
	}

	@Override
	public boolean createUser(UserInforEntity user) throws SQLException, NoSuchAlgorithmException {
		// mở kết nối và set kết nối đó không tự động comit
		TblUserDaoImpl tblUserDaoImpl = new TblUserDaoImpl();
		Connection connection = null;
		try {
			connection = tblUserDaoImpl.openConnect();
			if (connection != null)
				connection.setAutoCommit(false);
			TblUserEntity tblUserEntity = new TblUserEntity();
			String salt = Common.createKey();
			// set giá trị cho TblUserEntity để insert vào DB
			tblUserEntity.setGroupId(Common.convertInt(user.getGroup(), 0));
			tblUserEntity.setLoginName(user.getLoginName());
			tblUserEntity.setPassword(Common.encryptPass(salt, user.getPass()));
			tblUserEntity.setFullName(user.getFullName());
			tblUserEntity.setFullNameKana(user.getFullNameKana());
			tblUserEntity.setEmail(user.getEmail());
			tblUserEntity.setTel(user.getTel());
			tblUserEntity.setBirthday(user.getBirthday());
			tblUserEntity.setRule(Constant.DEFAULT_RULE);
			tblUserEntity.setSalt(salt);
			int userId = tblUserDaoImpl.insertUser(tblUserEntity, connection);
			if (!"0".equals(user.getJapanLevel())) {
				// set giá trị cho TblDetailUserJapanEntity để insert vào DB
				TblDetailUserJapanDaoImpl tblDetailUserJapanDaoImpl = new TblDetailUserJapanDaoImpl();
				TblDetailUserJapanEntity tblDetailUserJapanEntity = new TblDetailUserJapanEntity();
				tblDetailUserJapanEntity.setUserId(userId);
				tblDetailUserJapanEntity.setCodeLevel(user.getJapanLevel());
				tblDetailUserJapanEntity.setStartDate(user.getStartDate());
				tblDetailUserJapanEntity.setEndDate(user.getEndDate());
				tblDetailUserJapanEntity.setTotal(user.getTotal());
				tblDetailUserJapanDaoImpl.insertDetailUserJapan(tblDetailUserJapanEntity);
			}

			connection.commit();

		} catch (SQLException | NoSuchAlgorithmException e) {
			// Trường hợp xảy ra lỗi sẽ rollback lại trạng thái ban đầu của DB
			connection.rollback();

			System.out.println("Lớp TblUserLogicImpl - phương thức creatUser - lỗi :" + e.getMessage());
			throw e;
		} finally {
			connection.close();
		}
		return true;
	}

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
	@Override
	public boolean checkExistUserId(int userId) throws SQLException {
		boolean check = false;
		try {
			TblUserDao tblUserDao = new TblUserDaoImpl();
			TblUserEntity tblUser = new TblUserEntity();
			tblUser = tblUserDao.getUserByUserId(userId);
			if (tblUser != null) {
				check = true;
			}
		} catch (SQLException e) {
			System.out.println("TblUserLogicImpl checkExitsUserId: " + e.getMessage());
			throw e;
		}
		return check;
	}

	/**
	 * lấy thông tin của UserInfoEntity thao userId tương ứng
	 * 
	 * @param userId id của user tương ứng
	 * @return thông tin của userInforEntity
	 * @throws SQLException lỗi SQL
	 */
	@Override
	public UserInforEntity getUserInforByUserId(int userId) throws SQLException {
		TblUserDao tblUserDao = new TblUserDaoImpl();
		UserInforEntity userInforEntity = tblUserDao.getUserInforByUserId(userId);
		return userInforEntity;
	}

	/**
	 * Xóa user có userId tương ứng muốn xóa
	 * 
	 * @param userId : id muốn xóa
	 * @return boolean true nếu xóa thành công/ false nếu xóa không thành công
	 * @throws SQLException Lỗi SQL
	 * 
	 */
	@Override
	public boolean deleteUserByUserId(int userId) throws SQLException {
		TblUserDaoImpl tblUserDao = new TblUserDaoImpl();
		TblDetailUserJapanDaoImpl detailUserJapanDaoImpl = new TblDetailUserJapanDaoImpl();
		Connection connection = detailUserJapanDaoImpl.openConnect();
		connection.setAutoCommit(false);
		boolean check = false;
		try {
			detailUserJapanDaoImpl.deleteDetailUserJapanById(userId);

			tblUserDao.deleteUserById(userId);
			connection.commit();

			check = true;
		} catch (SQLException e) {
			connection.rollback();
			System.out.println("Lớp TblUserLogicImpl - phương thức delUserInforByUserId - lỗi :" + e.getMessage());
			throw e;
		} finally {
			connection.close();
		}
		return check;
	}

	@Override
	public String getRuleByUserId(int userId) throws SQLException {
		try {
			String error = "";
			TblUserDao tblUserDao = new TblUserDaoImpl();
			UserInforEntity userInforEntity = tblUserDao.getUserInforByUserId(userId);
			if (userInforEntity == null) {
				error = MessageErrorProperties.getValueByKey("ER013");
			} else if (userInforEntity.getRule() == 0) {
				error = MessageErrorProperties.getValueByKey("ER020");
			}
			return error;
		} catch (SQLException e) {
			System.out.println("Lớp TblUserLogicImpl - phương thức getErrorByUserId - lỗi :" + e.getMessage());
			throw e;
		}

	}

	/**
	 * Thực hiện update user ở 2 bảng tbl_user và tbl_detail_user_japan
	 * 
	 * @param userInfor              Thông tin của user cần update
	 * @param isExistDetailUserJapan Biến cho biết user có trình độ tiếng Nhật hay
	 *                               không
	 * @return Trả về true nếu update thành công, false nếu update thất bại
	 * @throws SQLException Trả ra Exception khi lỗi kết nối đến database
	 */
	@Override
	public boolean updateUser(UserInforEntity userInforEntity) throws SQLException {
		boolean check = false;
		try {
			TblUserDaoImpl tblUserDao = new TblUserDaoImpl();
			TblUserEntity tblUserEntity = new TblUserEntity();
			TblDetailUserJapanEntity tblDetailUserJapanEntity = new TblDetailUserJapanEntity();
			TblDetailUserJapanDaoImpl detailUserJapanDaoImpl = new TblDetailUserJapanDaoImpl();
			connection = tblUserDao.openConnect();
			tblUserDao.setConnection(connection);
			detailUserJapanDaoImpl.setConnection(connection);
			connection.setAutoCommit(false);

			// set giá trị cho tblUserEntity để update vào bảng user
			tblUserEntity.setGroupId(userInforEntity.getGroupId());
			tblUserEntity.setFullName(userInforEntity.getFullName());
			tblUserEntity.setFullNameKana(userInforEntity.getFullNameKana());
			tblUserEntity.setEmail(userInforEntity.getEmail());
			tblUserEntity.setTel(userInforEntity.getTel());
			tblUserEntity.setBirthday(userInforEntity.getBirthday());
			tblUserEntity.setUserId(userInforEntity.getUserId());

			// set giá trị cho tblDetailUserJapanEntity để thao tác với bảng detail
			tblDetailUserJapanEntity.setUserId(userInforEntity.getUserId());
			tblDetailUserJapanEntity.setCodeLevel(userInforEntity.getCodeLevel());
			tblDetailUserJapanEntity.setStartDate(userInforEntity.getStartDate());
			tblDetailUserJapanEntity.setEndDate(userInforEntity.getEndDate());
			tblDetailUserJapanEntity.setTotal(userInforEntity.getTotal());

			// thực hiện update vào bảng user
			tblUserDao.updateUser(tblUserEntity);
			// xét trường hợp thao tác với bảng detail
			if (!Common.checkExistCodeLevelByUserId(userInforEntity.getUserId())) {
				if (!"0".equals(userInforEntity.getCodeLevel())) {
					// insert
					detailUserJapanDaoImpl.insertDetailUserJapan(tblDetailUserJapanEntity);
				}
			} else {
				if (!"0".equals(userInforEntity.getCodeLevel())) {
					// update
					detailUserJapanDaoImpl.updateDetailUser(tblDetailUserJapanEntity);
				} else {
					// delete
					detailUserJapanDaoImpl.deleteDetailUserJapanById(userInforEntity.getUserId());
				}
			}
			connection.commit();
			check = true;
		} catch (SQLException e) {
			connection.rollback();
			System.out.println("Lớp TblUserLogicImpl - phương thức delUserInforByUserId - lỗi :" + e.getMessage());
			throw e;
		} finally {
			connection.close();
		}
		return check;
	}
}
