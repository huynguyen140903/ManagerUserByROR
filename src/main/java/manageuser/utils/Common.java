/**
 * luvina softwware JSC, 2022
 * Common.java,HuyNQ

 */
package manageuser.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import manageuser.dao.TblDetailUserJapanDao;
import manageuser.dao.TblUserDao;
import manageuser.dao.impl.MstGroupDaoImpl;
import manageuser.dao.impl.MstJapanDaoImpl;
import manageuser.dao.impl.TblDetailUserJapanDaoImpl;
import manageuser.dao.impl.TblUserDaoImpl;
import manageuser.entities.MstGroupEntity;
import manageuser.entities.MstJapanEntity;
import manageuser.entities.TblDetailUserJapanEntity;
import manageuser.logic.TblUserLogic;
import manageuser.logic.impl.TblUserLogicImpl;

/**
 * Class dùng để chứa các phương thức dùng chung của các đối tượng khác trong
 * bài toán
 * 
 * @author HuyNQ
 *
 */
public class Common {
	/**
	 * Phương thức dùng để mã hóa 1 String theo mã hóa SHA1
	 * 
	 * @param salt Đoạn kí tự thêm của một tài khoản
	 * @param pass mật khẩu muốn mã hóa
	 * @return trả về 1 String đã được mã hóa
	 * @throws NoSuchAlgorithmException
	 */
	public static String encryptPass(String salt, String pass) throws NoSuchAlgorithmException {
		
		String passSHA = new String();
		pass = pass + salt;
		// Gọi mã hóa SHA1
		MessageDigest mDigest = MessageDigest.getInstance("SHA1");
		// tạo 1 mảng byte
		byte[] result = mDigest.digest(pass.getBytes());
		// khai báo khởi tại 1 StringBuffer
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < result.length; i++) {
			// mã hóa
			sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		}
		// lấy 1 nửa số kí tự đã mã hóa
		for (int i = 0; i < result.length / 2; i++) {
			// gán giá trị vào passSHA
			passSHA += result[i];
		}
		
		return passSHA;
		// đóng phương thức
	}
	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(createKey());
		System.out.println(encryptPass(createKey(), "123456"));
	}
	/**
	 * Phương thức dùng để kiểm tra khi vào ADM002 đã qua đăng nhập chưa
	 * 
	 * @param loginName Tên đăng nhập
	 * @return Boolean
	 * @throws SQLException
	 */
	public static boolean checkLogin(HttpSession loginName) throws SQLException {

		if (loginName.getAttribute("loginName") != "" && loginName.getAttribute("loginName") != null) {
			TblUserDao tblUserDao = new TblUserDaoImpl();
			if (tblUserDao.getTblUserByLoginName((String) loginName.getAttribute("loginName")).getRule() == 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	/**
	 * Phương thức dùng để tính số Paging hiển thị
	 * 
	 * @param totalRecords Tổng số bản ghi
	 * @param limit        Số bản ghi giới hạn
	 * @param currentPage  Page hiện tại
	 * @return List<Integer>
	 */
	public List<Integer> getListPaging(int totalRecords, int limit, int currentPage) {
		List<Integer> listPaging = new ArrayList<Integer>();

		int totalPage = getTotalPage(totalRecords, limit);
		// lấy Trang đầu tiên của list
		int startOfPagingList;
		int pageLimit = Integer.parseInt(DatabaseProperties.getValueByKey("pageLimit"));
		if (currentPage % pageLimit == 0) {
			startOfPagingList = (currentPage / pageLimit - 1) * pageLimit + 1;
		} else {
			startOfPagingList = (int) (currentPage / pageLimit) * pageLimit + 1;
		}
		if (totalPage - startOfPagingList >= pageLimit) {
			for (int i = 0; i < pageLimit; i++) {
				listPaging.add(startOfPagingList + i);
			}

		} else {
			while (totalPage - startOfPagingList >= 0) {
				listPaging.add(startOfPagingList++);
			}
		}
		return listPaging;
	}

	/**
	 * Phuong thuc lay offset
	 * 
	 * @param limit       Số bản ghi giới hạn
	 * @param currentPage Trang hien tại
	 * @return int
	 */
	public static int getOffset(int limit, int currentPage) {
		int offset;
		offset = (currentPage - 1) * limit;
		return offset;
	}

	/**
	 * Phuong thuc lay limit trong file config
	 * 
	 * @return int
	 */
	public static int getLimit() {
		int limit;
		limit = Integer.parseInt(DatabaseProperties.getValueByKey("limit"));
		return limit;
	}

	/**
	 * Phuong thuc tinh tong so page
	 * 
	 * @param totalRecords tổng số bản ghi
	 * @param limit        Số bản ghi giới hạn trong 1 trang
	 * @return int
	 */
	public static int getTotalPage(int totalRecords, int limit) {
		int totalPage;
		if (totalRecords % limit != 0) {
			totalPage = totalRecords / limit + 1;
		} else {
			totalPage = totalRecords / limit;
		}
		return totalPage;
	}

	/**
	 * Phương dùng để khoảng trắng (Nhật việt) ở đầu và cuối chuỗi
	 * 
	 * @param fullName
	 * @return String
	 */
	public static String deleteSpace(String fullName) {
		return fullName.replace("　", " ").trim();
	}

	/**
	 * Lấy năm hiện tại
	 * 
	 * @return int
	 */
	public static int getYearNow() {
		LocalDate localDate = LocalDate.now();
		int year = localDate.getYear();
		return year;
	}

	/**
	 * Lấy tháng hiện tại
	 * 
	 * @return
	 */
	public static int getMonthNow() {
		LocalDate localDate = LocalDate.now();
		int month = localDate.getMonthValue();
		return month;
	}

	/**
	 * Lấy Ngày hiện tại
	 * 
	 * @return
	 */
	public static int getDayNow() {
		LocalDate localDate = LocalDate.now();
		int day = localDate.getDayOfMonth();
		return day;
	}

	/**
	 * Lấy danh sách các năm từ năm 1900 -> năm hiện tại
	 * 
	 * @param fromYear
	 * @param toYear
	 * @return
	 */
	public static List<Integer> getListYear(int fromYear, int toYear) {
		List<Integer> listYear = new ArrayList<Integer>();
		for (int i = fromYear; i <= toYear; i++) {
			listYear.add(i);
		}

		return listYear;

	}

	/**
	 * Lấy danh sách các tháng từ 1->12
	 * 
	 * @return
	 */
	public static List<Integer> getListMonth() {
		List<Integer> listMonth = new ArrayList<Integer>();
		for (int i = 1; i <= 12; i++) {
			listMonth.add(i);
		}
		return listMonth;
	}

	/**
	 * Lấy danh sách các ngày từ 1->31
	 * 
	 * @return
	 */
	public static List<Integer> getListDay() {
		List<Integer> listDay = new ArrayList<Integer>();
		for (int i = 1; i <= 31; i++) {
			listDay.add(i);
		}
		return listDay;

	}

	/**
	 * Check độ dài ER006
	 * 
	 * @param chuoiCanCheck
	 * @param doDaiToiDa
	 * @param doDaiToiThieu
	 * @return
	 */
	public static boolean checkLength(String chuoiCanCheck, int maxLength, int minLength) {
		if (chuoiCanCheck.length() <= maxLength && chuoiCanCheck.length() >= minLength) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Check độ dài ER006
	 * 
	 * @param chuoiCanCheck
	 * @param doDaiToiDa
	 * @return
	 */
	public static boolean checkLength(String chuoiCanCheck, int maxLength) {
		if (chuoiCanCheck.length() <= maxLength && chuoiCanCheck.length() >= 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Check format ER005
	 * 
	 * @param chuoiCanCheck
	 * @param regex
	 * @return
	 */
	public static boolean checkFormat(String chuoiCanCheck, String regex) {
		if (Pattern.matches(regex, chuoiCanCheck)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Check tồn tại ER003
	 * 
	 * @param chuoiCanCheck
	 * @return neu co trong db thi tra ve true
	 * @throws SQLException
	 */
	public static boolean checkExistEmailAndLoginName(String chuoiCanCheck) throws SQLException {
		TblUserDao tblUserDao = new TblUserDaoImpl();
		if (chuoiCanCheck.contains("@")) {
			if (tblUserDao.getTblUserByEmail(chuoiCanCheck) == null) {
				return false;
			} else {
				return true;
			}
		} else {
			if (tblUserDao.getTblUserByLoginName(chuoiCanCheck) == null) {
				return false;
			} else {
				return true;
			}
		}
	}
	/**
	 * Check tồn tại ER003
	 * 
	 * @param chuoiCanCheck
	 * @return neu co trong db thi tra ve true
	 * @throws SQLException
	 */
	public static boolean checkExitEmail(String chuoiCanCheck, int userId) throws SQLException {
		TblUserDao tblUserDao = new TblUserDaoImpl();

		if (tblUserDao.getTblUserByEmailAndUserId(chuoiCanCheck, userId) == null) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * Check khong ton tồn tại ER004
	 * 
	 * @param chuoiCanCheck
	 * @return Trong db khong co se tra ve True
	 */
	public static boolean checkNotExistGroup(String chuoiCanCheck) {
		List<MstGroupEntity> ListGroupEntities = new ArrayList<MstGroupEntity>();
		MstGroupDaoImpl mstGroupDaoImpl = new MstGroupDaoImpl();
		ListGroupEntities = mstGroupDaoImpl.getAllMstGroup();
		for (int i = 0; i < ListGroupEntities.size(); i++) {
			if (ListGroupEntities.get(i).getGroupId() == Integer.parseInt(chuoiCanCheck)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Check khong ton tồn tại ER004
	 * 
	 * @param chuoiCanCheck
	 * @return trong db khong co se tra ve true
	 */
	public static boolean checkNotExistJapan(String nameLevel) {
		if("0".equals(nameLevel) || nameLevel == null) {
			return false;
		}
		List<MstJapanEntity> mstJapanEntities = new ArrayList<MstJapanEntity>();
		MstJapanDaoImpl mstGroupDaoImpl = new MstJapanDaoImpl();
		mstJapanEntities = mstGroupDaoImpl.getAllMstJapan();
		for (int i = 0; i < mstJapanEntities.size(); i++) {
			if (nameLevel.equals(mstJapanEntities.get(i).getCodeLevel())) {
				return false;
			}
		}
		return true;
	}
	/**
	 * Phương thức kiểm tra một user với userId có trình độ tiếng Nhật hay không
	 * 
	 * @param userId id của user cần kiểm tra
	 * @return true nếu user có trình độ tiếng Nhật, false nếu user không có trình
	 *         độ tiếng Nhật
	 * @throws SQLException           Trả ra SQLException khi kết nối với database
	 *                                bị lỗi
	 * @throws ClassNotFoundException Trả ra ClassNotFoundException khi tên Driver
	 *                                bị sai hoặc Driver không tồn tại
	 */
	
	public static boolean checkExistCodeLevelByUserId(int userID) throws SQLException {
		try {
			TblUserLogic tblUserLogic = new TblUserLogicImpl();
			if (tblUserLogic.getUserInforByUserId(userID).getCodeLevel()!= null) {
				return true;
			}
		} catch (SQLException e) {
			System.out
					.println("Lớp TblUserLogicImpl - phương thức checkExitCodeLevelByUserId - lỗi :" + e.getMessage());
			e.printStackTrace();
			throw e;
		}

		return false;
	}
	/**
	 * Kiểm tra ngày có hợp lệ hay không
	 * @param ngay
	 * @param thang
	 * @param nam
	 * @return boolean
	 */
	public static boolean checkValidDay(int day, int month, int year) {
		boolean check = false;
		if (year % 4 == 0) {
			if (month == 2 && day <= 29) {
				check = true;
			} else if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10
					|| month == 12) && day <= 31) {
				check = true;
			} else if ((month == 4 || month == 6 || month == 9 || month == 11) && day <= 30) {
				check = true;
			}
		}
		if (year % 4 != 0) {
			if (month == 2 && day <= 28) {
				check = true;
			} else if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10
					|| month == 12) && day <= 31) {
				check = true;
			} else if ((month == 4 || month == 6 || month == 9 || month == 11) && day <= 30) {
				check = true;
			}
		}
		return check;
	}

	/**
	 * Tạo tên cho 1 session id
	 * 
	 * @return
	 */
	public static String createKey() {
		Date date = new Date();
		// This method returns the time in millis
		long timeMilli = date.getTime();
		return timeMilli + "";
	}

	/**
	 * convertInt: chuyển đổi String sang int
	 * 
	 * @param str          chuỗi cần chuyển đổi
	 * @param valueDefault giá trị mặc định cho trường hợp chuỗi chuyển đổi có chứa
	 *                     ký tự không phải là số hoặc null
	 * @return int: giá trị trả về sau khi chuyển đổi
	 */
	public static int convertInt(String str, int valueDefault) {
		int valueConvert;

		// trường hợp str là null
		if (str == null || str.length() > 10 || "".equals(str)) {
			valueConvert = valueDefault;

			// trường hợp str chỉ chứa ký tự số có độ dài là 10
		} else if (str.matches("[0-9]{1,10}")) {
			// có thể xảy ra lỗi NumberFormatException với trường hợp giá trị >
			// 2,147,483,647
			try {
				valueConvert = Integer.parseInt(str);
			} catch (NumberFormatException e) {
				System.out.println("Common convertInt: " + e.getMessage());
				valueConvert = valueDefault;
			}
		} else {
			valueConvert = valueDefault;
		}

		return valueConvert;
	}

	
}
