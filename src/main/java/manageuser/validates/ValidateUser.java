/**
 * luvina softwware JSC, 2022
 * ValidateUser.java,HuyNQ

 */
package manageuser.validates;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import manageuser.entities.TblUserEntity;
import manageuser.entities.UserInforEntity;
import manageuser.logic.TblUserLogic;
import manageuser.logic.impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.Constant;
import manageuser.utils.MessageErrorProperties;

/**
 * xử lí thông tin nhập vào
 * 
 * @author HuyNQ
 *
 */
public class ValidateUser {
	/**
	 * Phương thức xử lý tên đăng nhập và pass đã được nhập hay chưa
	 * 
	 * @param loginName tên đang nhập
	 * @param pass      mật khẩu
	 * @return danh sách lỗi
	 */
	public ArrayList<String> validateLogin(String loginName, String pass) {
		ArrayList<String> listMessageErr = new ArrayList<String>();
		if (loginName == "") {
			listMessageErr.add(MessageErrorProperties.getValueByKey("ER001_loginName"));
		}
		if (pass == "") {
			listMessageErr.add(MessageErrorProperties.getValueByKey("ER001_password"));
		}
		// Khai báo khỏi tạo đối tượng tblUserLogicImpl
		TblUserLogicImpl tblUserLogicImpl = new TblUserLogicImpl();
		// giá trị biến result được gán bằng giá trị trả về của phương thức checkLogin
		boolean result = tblUserLogicImpl.checkLogin(loginName, pass);
		if (listMessageErr.size() == 0 && !result) {
			listMessageErr.add(MessageErrorProperties.getValueByKey("ER016"));
		}
		return listMessageErr;
	}

	/**
	 * Thực hiện check dữ liệu nhập vào
	 * 
	 * @param user
	 * @return list lỗi
	 * @throws SQLException
	 */

	/**
	 * Check dữ liệu nhập vào của loginName
	 * 
	 * @param loginName tên tài khoản
	 * @return nội dung của mã lỗi kiểu String
	 * @throws SQLException Lỗi thực thi với DB
	 */
	public String validateLoginName(String loginName) throws SQLException {
		String error = "";
		// validate LoginName
		if (!"".equals(loginName)) {
			if (Common.checkLength(loginName, Constant.MAXLENGHT_LOGIN_NAME, Constant.MINLENGHT_LOGIN_NAME)) {
				if (Common.checkFormat(loginName, Constant.REGEX_LOGIN_NAME)) {
					if (!Common.checkExistEmailAndLoginName(loginName)) {

					} else {
						error = MessageErrorProperties.getValueByKey("ER003_loginName");
					}
				} else {
					error = MessageErrorProperties.getValueByKey("ER019");
				}
			} else {
				error = MessageErrorProperties.getValueByKey("ER007_loginName");
			}
		} else {
			error = MessageErrorProperties.getValueByKey("ER001_loginName");
		}
		return error;

	}

	/**
	 * Thực hiện check dữ liệu nhập vào của nhóm
	 * 
	 * @param group tên nhóm
	 * @return
	 */
	public String validateGroup(String group) {
		String error = "";
		// validate group
		if (!"0".equals(group)) {
			if (!Common.checkNotExistGroup(group)) {

			} else {
				error = MessageErrorProperties.getValueByKey("ER004_group");
			}
		} else {
			error = MessageErrorProperties.getValueByKey("ER002_group");
		}

		return error;
	}

	/**
	 * Thực hiện check dữ liệu nhập vào của tên
	 * 
	 * @param fullName tên đầy đủ
	 * @return
	 */
	public String validateFullName(String fullName) {
		String error = "";
		// validate fullName
		if (!"".equals(fullName)) {
			if (Common.checkLength(fullName, Constant.MAXLENGHT_FULL_NAME)) {

			} else {
				error = MessageErrorProperties.getValueByKey("ER006_fullName");
			}
		} else {
			error = MessageErrorProperties.getValueByKey("ER001_fullName");
		}
		return error;
	}

	/**
	 * Thực hiện check dữ liệu nhập vào của tên kana
	 * 
	 * @param fullNameKana tên kana
	 * @return
	 */
	public String validateFullNameKana(String fullNameKana) {
		String error = "";
		// validate fullNameKana
		if (!"".equals(fullNameKana)) {
			if (Common.checkFormat(fullNameKana, Constant.REGEX_FULL_NAME_KANA)) {
				if (Common.checkLength(fullNameKana, Constant.MAXLENGHT_FULL_NAME_KANA)) {

				} else {
					error = MessageErrorProperties.getValueByKey("ER006_fullNameKana");
				}
			} else {
				error = MessageErrorProperties.getValueByKey("ER009_fullNameKana");
			}
		}
		return error;
	}

	/**
	 * Thực hiện check dữ liệu nhập vào ngày sinh
	 * 
	 * @param day             ngày sinh
	 * @param month           tháng sinh
	 * @param year            năm sinh
	 * @param UserInforEntity
	 * @return
	 */
	public String validateBirthDay(int day, int month, int year, UserInforEntity UserInforEntity) {
		String error = "";
		// validate birthday
		if (Common.checkValidDay(day, month, year)) {
			UserInforEntity.setBirthday(Date.valueOf(LocalDate.of(year, month, day)));
		} else {
			error = MessageErrorProperties.getValueByKey("ER011_birthDay");
		}
		return error;
	}

	/**
	 * Thực hiện check dữ liệu nhập vào của email
	 * 
	 * @param email địa chỉ email
	 * @return
	 * @throws SQLException Lỗi thực thi với DB
	 */
	public String validateEmail(String email) throws SQLException {
		String error = "";
		// validate email
		if (!"".equals(email)) {
			if (Common.checkLength(email, Constant.MAXLENGHT_EMAIL)) {
				if (Common.checkFormat(email, Constant.REGEX_EMAIL)) {
					if (!Common.checkExistEmailAndLoginName(email)) {

					} else {
						error = MessageErrorProperties.getValueByKey("ER003_email");
					}
				} else {
					error = MessageErrorProperties.getValueByKey("ER005_email");
				}
			} else {
				error = MessageErrorProperties.getValueByKey("ER006_email");
			}
		} else {
			error = MessageErrorProperties.getValueByKey("ER001_email");
		}
		return error;
	}

	/**
	 * Thực hiện check dữ liệu nhập vào của email
	 * 
	 * @param email địa chỉ email
	 * @return
	 * @throws SQLException Lỗi thực thi với DB
	 */
	public String validateEmail(String email, int userId) throws SQLException {
		String error = "";
		// validate email
		if (!"".equals(email)) {
			if (Common.checkLength(email, Constant.MAXLENGHT_EMAIL)) {
				if (Common.checkFormat(email, Constant.REGEX_EMAIL)) {
					if (!Common.checkExitEmail(email, userId)) {

					} else {
						error = MessageErrorProperties.getValueByKey("ER003_email");
					}
				} else {
					error = MessageErrorProperties.getValueByKey("ER005_email");
				}
			} else {
				error = MessageErrorProperties.getValueByKey("ER006_email");
			}
		} else {
			error = MessageErrorProperties.getValueByKey("ER001_email");
		}
		return error;
	}

	/**
	 * Thực hiện check dữ liệu nhập vào của số điện thoại
	 * 
	 * @param tel số điện thoại
	 * @return
	 */
	public String validateTel(String tel) {
		String error = "";
		// validate tel
		if (!"".equals(tel)) {
			if (Common.checkLength(tel, Constant.MAXLENGHT_TEL)) {
				if (Common.checkFormat(tel, Constant.REGEX_TEL)) {

				} else {
					error = MessageErrorProperties.getValueByKey("ER005_tel");
				}
			} else {
				error = MessageErrorProperties.getValueByKey("ER006_tel");
			}
		} else {
			error = MessageErrorProperties.getValueByKey("ER001_tel");
		}
		return error;
	}

	/**
	 * Thực hiện check dữ liệu nhập vào của mật khẩu và mật khẩu xác nhận
	 * 
	 * @param pass   mật khẩu
	 * @param passCF mật khẩu xác nhận
	 * @return
	 */
	public String validatePassAndPassConfirm(String pass, String passConfirm) {
		String error = "";
		// validate pass va passCF
		if (!"".equals(pass)) {
			if (Common.checkFormat(pass, Constant.REGEX_PASS)) {
				if (Common.checkLength(pass, Constant.MAXLENGHT_PASS, Constant.MINLENGHT_PASS)) {
					if (pass.equals(passConfirm)) {

					} else {
						error = MessageErrorProperties.getValueByKey("ER017");
					}
				} else {
					 error = MessageErrorProperties.getValueByKey("ER007_password");
				}
			} else {
				error = MessageErrorProperties.getValueByKey("ER008_password");
			}
		} else {
			error = MessageErrorProperties.getValueByKey("ER001_password");
		}
		return error;
	}

	/**
	 * Thực hiện check dữ liệu nhập vào của trình độ tiếng nhật
	 * 
	 * @param japanLevel trình độ tiếng nhật
	 * @return
	 */
	public String validateNameLevel(String nameLevel) {
		String error = "";
		// validate japanLevel
		if (!"0".equals(nameLevel)) {
			if (Common.checkNotExistJapan(nameLevel)) {
				error = MessageErrorProperties.getValueByKey("ER004_japanLevel");
			}
		}
		return error;
	}

	/**
	 * Thực hiện check dữ liệu nhập vào của ngày bắt đầu và hết hạn của trình độ
	 * tiếng nhật
	 * 
	 * @param dayStart
	 * @param monthStart
	 * @param yearStart
	 * @param dayEnd
	 * @param monthEnd
	 * @param yearEnd
	 * @param userInforEntity
	 * @return
	 */
	public String validateDayOfMstJapan(int dayStart, int monthStart, int yearStart, int dayEnd, int monthEnd,
			int yearEnd, UserInforEntity userInforEntity) {
		String error = "";
		// validate startDate va EndDate

		if (!"0".equals(userInforEntity.getCodeLevel())) {
			if (Common.checkValidDay(dayStart, monthStart, yearStart)) {
				userInforEntity.setStartDate((Date.valueOf(LocalDate.of(yearStart, monthStart, dayStart))));
				;
			} else {
				error = MessageErrorProperties.getValueByKey("ER011_startDate");
			}
			if (Common.checkValidDay(dayEnd, monthEnd, yearEnd)) {
				userInforEntity.setEndDate(Date.valueOf(LocalDate.of(yearEnd, monthEnd, dayEnd)));

			} else {
				error = MessageErrorProperties.getValueByKey("ER011_endDate");
			}

		}
		return error;
	}

	/**
	 * Thực hiện check dữ liệu nhập vào của total
	 * 
	 * @param total
	 * @param userInforEntity
	 * @return
	 */
	public String validateTotal(int total, UserInforEntity userInforEntity) {
		String error = "";
		// validate total

		if (!"0".equals(userInforEntity.getCodeLevel())) {
			if (total > 0) {
				if (Common.checkFormat(total + "", Constant.REGEX_TOTAL)) {
					if (Common.checkLength(total + "", Constant.MAXLENGHT_TOTAL)) {
						if (total >= 90 && total <= 180) {

						} else {
							error = MessageErrorProperties.getValueByKey("ER021_total");
						}
					} else {
						error = MessageErrorProperties.getValueByKey("ER006_total");
					}
				} else {
					error = MessageErrorProperties.getValueByKey("ER018_total");
				}
			} else {
				error = MessageErrorProperties.getValueByKey("ER001_total");
			}
		}
		return error;
	}

	/**
	 * Thực hiện check dữ liệu nhập vào
	 * 
	 * @param user thông tin người dùng
	 * @return list lỗi
	 * @throws SQLException Lỗi thực thi với DB
	 */
	public List<String> validateUserInfor(UserInforEntity user) throws SQLException {
		List<String> listError = new ArrayList<String>();
		if (user.getUserId() == 0) {
			listError.add(validateLoginName(user.getLoginName()));
			listError.add(validateGroup(user.getGroup()));
			listError.add(validateFullName(user.getFullName()));
			listError.add(validateFullNameKana(user.getFullNameKana()));
			listError.add(validateBirthDay(user.getDayBirth(), user.getMonthBirth(), user.getYearBirth(), user));
			listError.add(validateEmail(user.getEmail()));
			listError.add(validateTel(user.getTel()));
			listError.add(validatePassAndPassConfirm(user.getPass(), user.getPassConfirm()));
			// kiem tra xem co TDTN hay ko
			if (!"0".equals(user.getJapanLevel())) {
				listError.add(validateNameLevel(user.getCodeLevel()));
				listError.add(validateDayOfMstJapan(user.getDayStart(), user.getMonthStart(), user.getYearStart(),
						user.getDayEnd(), user.getMonthEnd(), user.getYearEnd(), user));
				listError.add(validateTotal(user.getTotal(), user));
			}
		} else {
			
			listError.add(validateGroup(user.getGroupId() + ""));
			listError.add(validateFullName(user.getFullName()));
			listError.add(validateFullNameKana(user.getFullNameKana()));
			listError.add(validateBirthDay(user.getDayBirth(), user.getMonthBirth(), user.getYearBirth(), user));
			listError.add(validateEmail(user.getEmail(), user.getUserId()));
			listError.add(validateTel(user.getTel()));
			listError.add(validateNameLevel(user.getCodeLevel()));
			listError.add(validateDayOfMstJapan(user.getDayStart(), user.getMonthStart(), user.getYearStart(),
					user.getDayEnd(), user.getMonthEnd(), user.getYearEnd(), user));
			listError.add(validateTotal(user.getTotal(), user));

		}
		while (listError.remove(""));
		return listError;
	}
}
