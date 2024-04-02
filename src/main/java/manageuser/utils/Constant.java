/**
 * luvina softwware JSC, 2022
 * Constant.java,HuyNQ

 */
package manageuser.utils;

/**
 * hàm định nghĩa các biến hằng
 * 
 * @author HuyNQ
 *
 */
public class Constant {
	// biến chứa địa chỉ trỏ tới file config_database.properties
	public static final String PROPERTIES_DATABASE_PATH = "./manageuser/properties/config.properties";
	// biến chứa địa chỉ trỏ tới file message_error_ja.properties
	public static final String PROPERTIES_MESSAGE_ERROR_PATH = "./manageuser/properties/message_error_ja.properties";
	// biến chứa địa chỉ trỏ tới file message_ja.properties
	public static final String PROPERTIES_MESSAGE_PATH = "./manageuser/properties/message_ja.properties";
	public static final String ADM006_PATH = "/jsp/ADM006.jsp";
	public static final String ADM005_PATH = "/jsp/ADM005.jsp";
	public static final String ADM004_PATH = "/jsp/ADM004.jsp";
	public static final String ADM003_PATH = "/jsp/ADM003.jsp";
	public static final String ADM002_PATH = "/jsp/ADM002.jsp";
	public static final String ADM001_PATH = "/jsp/ADM001.jsp";
	public static final String SYSTEM_ERROR_PATH = "./jsp/System_error.jsp";
	public static final String LISTUSER_DO = "listUser.do";
	public static final String ADDUSERCONFIRM_DO = "AddUserConfirm.do";
	public static final String EDIT_USER_CONFIRM_DO = "EditUserConfirm.do";
	public static final String SYSTEM_ERROR = "SystemError.do";
	public static final String SUCCESS_DO = "Success.do";
	public static final String LOGIN = "/17_NguyenQuangHuy_manager_user/";
	public static final int DEFAULT_OFFSET = 0;
	public static final String DEFAULT_SORT_TYPE = "default";
	public static final String DEFAULT_SORTBYFULLNAME = "asc";
	public static final String DEFAULT_SORTBYCODELEVEL = "desc";
	public static final String DEFAULT_SORTBYENDDATE = "desc";
	public static final int DEFAULT_CURRENTPAGE = 1;
	public static final int DEFAULT_GROUPID = 0;
	public static final String DEFAULT_FULLNAME = "";
	public static final int  MAXLENGHT_LOGIN_NAME = 15;
	public static final int  MINLENGHT_LOGIN_NAME = 4;
	public static final int  MAXLENGHT_FULL_NAME = 255;
	public static final int  MAXLENGHT_FULL_NAME_KANA = 255;
	public static final int  MAXLENGHT_EMAIL = 100;
	public static final int  MAXLENGHT_TEL = 15;
	public static final int  MAXLENGHT_PASS = 15;
	public static final int  MINLENGHT_PASS = 5;
	public static final int  MAXLENGHT_TOTAL =180;
	public static final String REGEX_LOGIN_NAME ="^[a-zA-Z_][a-zA-Z0-9_]{3,14}$";
	public static final String REGEX_FULL_NAME_KANA ="^[ア-ン]+$";
	public static final String REGEX_EMAIL ="^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-]+$";
	public static final String REGEX_TEL ="^[0-9]{4}-[0-9]{4}-[0-9]{1,4}$";
	public static final String REGEX_PASS ="^[a-zA-Z0-9.!@#$%&’*+/=?^_`{|}~-]+$";
	public static final String REGEX_TOTAL ="^[0-9]+$";
	public static final int DEFAULT_RULE = 1;
}
