/**
 * luvina softwware JSC, 2022
 * EditUserInputController.java,HuyNQ
 */
package manageuser.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.entities.UserInforEntity;
import manageuser.logic.MstGroupLogic;
import manageuser.logic.MstJapanLogic;
import manageuser.logic.TblUserLogic;
import manageuser.logic.impl.MstGroupLogicImpl;
import manageuser.logic.impl.MstJapanLogicImpl;
import manageuser.logic.impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.Constant;
import manageuser.validates.ValidateUser;

/**
 * @author HuyNQ
 *
 */
@WebServlet({ "/EditUserInput.do", "/EditUserValidate.do" })
public class EditUserInputController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;
	/**
	 * Phương thức xử lí GET Request tới URL /EditUserInput.do
	 * 
	 * @param request  Request client gửi lên server
	 * @param response Response servler gửi lại client
	 * @throws ServletException Trả ra ServletException khi servlet có lỗi
	 * @throws IOException      Trả ra IOException khi đọc ghi file bị lỗi
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			int userId = Integer.parseInt(req.getParameter("userId"));
			TblUserLogic tblUserLogic = new TblUserLogicImpl();
			if (userId > 0 && tblUserLogic.checkExistUserId(userId)) {
				setDataLogic(req);
				req.setAttribute("user", getDefaultValue(req));
				// Đẩy ra view
				RequestDispatcher requestDispatcher;
				requestDispatcher = req.getRequestDispatcher(Constant.ADM003_PATH);
				requestDispatcher.forward(req, resp);
			}
		} catch (Exception e) {
			System.out.println("class EditUserInputController Phuong thức doget lỗi: " + e.getMessage());
			resp.sendRedirect(Constant.SYSTEM_ERROR + "?mess=ER015");
		}
	}
	/**
	 * Phương thức xử lí POST Request tới URL /EditUserValidate.do
	 * 
	 * @param request  Request client gửi lên server
	 * @param response Response servler gửi lại client
	 * @throws ServletException Trả ra ServletException khi servlet có lỗi
	 * @throws IOException      Trả ra IOException khi đọc ghi file bị lỗi
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			int userId = Integer.parseInt(req.getParameter("userId"));
			session = req.getSession();

			TblUserLogic tblUserLogic = new TblUserLogicImpl();
			if (tblUserLogic.checkExistUserId(userId)) {

				UserInforEntity userInforEntity = getDefaultValue(req);
				ValidateUser validateUser = new ValidateUser();
				List<String> listError = validateUser.validateUserInfor(userInforEntity);

				if (listError.size() == 0) {
					// tạo cho đối tượng 1 cái tên
					String key = Common.createKey();
					// đẩy đối tượng có tên đã tạo lên SS
					session.setAttribute(key, userInforEntity);
					// gọi URL của Controller 004 và truyền thêm tên của đối tượng
					resp.sendRedirect(Constant.EDIT_USER_CONFIRM_DO + "?key=" + key);
					// Đối tượng nhập sai thì ở lại màn 003 và đẩy lỗi ra view
				} else {
					RequestDispatcher requestDispatcher;
					requestDispatcher = req.getRequestDispatcher(Constant.ADM003_PATH);
					setDataLogic(req);

					req.setAttribute("user", userInforEntity);
					req.setAttribute("listError", listError);
					requestDispatcher.forward(req, resp);
				}
			}
		} catch (Exception e) {

			System.out.println("class EditUserController Phuong thức doPost lỗi: " + e.getMessage());
			resp.sendRedirect(Constant.SYSTEM_ERROR + "?mess=ER015");
		}
	}
	/**
	 * Phương thức thực hiện set giá trị cho các hạng mục SelectBox ở màn hình
	 * ADM003
	 * 
	 * @param request Request client gửi lên server
	 * @throws ClassNotFoundException Trả ra khi không tìm được driver JDBC
	 * @throws SQLException           Trả ra khi có lỗi SQL
	 */
	private void setDataLogic(HttpServletRequest req) {
		try {
			// Khai báo
			MstGroupLogic mstGroupLogicImpl = new MstGroupLogicImpl();
			MstJapanLogic mstJapanLogicImpl = new MstJapanLogicImpl();

			// Đẩy list giá trị của các selecbox lên Req
			req.setAttribute("listGroup", mstGroupLogicImpl.getAllMstGroup());
			req.setAttribute("listJapan", mstJapanLogicImpl.getAllMstJapan());
			req.setAttribute("day", Common.getListDay());
			req.setAttribute("month", Common.getListMonth());
			req.setAttribute("year", Common.getListYear(1900, Common.getYearNow()));
			req.setAttribute("yearEndDate", Common.getListYear(1900, Common.getYearNow() + 1));

		} catch (Exception e) {
			System.out.println("AddUserInputController setDataLogic " + e.getMessage());
		}
	}
	/**
	 * Phương thức get giá trị default cho màn hình ADM003
	 * 
	 * @param request Request client gửi lên server
	 * @return Đối tượng UserInforEntity chứa các thông tin của màn hình ADM003
	 * @throws ClassNotFoundException Trả ra Exception khi không tìm được Driver
	 * @throws SQLException           Trả ra Exception khi lỗi kết nối đến database
	 */
	private UserInforEntity getDefaultValue(HttpServletRequest req) throws Exception {
		UserInforEntity userInfor = null;
		try {
			String action = req.getParameter("action");
			int userId = Integer.parseInt(req.getParameter("userId"));
			userInfor = new UserInforEntity();
			if ("validate".equals(action)) {

				userInfor.setUserId(userId);
				userInfor.setLoginName(req.getParameter("loginName"));
				userInfor.setGroupId(Integer.parseInt(req.getParameter("group_id")));
				userInfor.setFullName(req.getParameter("name"));
				userInfor.setFullNameKana(req.getParameter("nameKana"));
				userInfor.setDayBirth(Integer.parseInt(req.getParameter("dayBirth")));
				userInfor.setMonthBirth(Integer.parseInt(req.getParameter("monthBirth")));
				userInfor.setYearBirth(Integer.parseInt(req.getParameter("yearBirth")));
				userInfor.setEmail(req.getParameter("email"));
				userInfor.setTel(req.getParameter("tel"));
				userInfor.setCodeLevel(req.getParameter("level"));
				userInfor.setDayStart(Integer.parseInt(req.getParameter("dayStart")));
				userInfor.setMonthStart(Integer.parseInt(req.getParameter("monthStart")));
				userInfor.setYearStart(Integer.parseInt(req.getParameter("yearStart")));
				userInfor.setDayEnd(Integer.parseInt(req.getParameter("dayEnd")));
				userInfor.setMonthEnd(Integer.parseInt(req.getParameter("monthEnd")));
				userInfor.setYearEnd(Integer.parseInt(req.getParameter("yearEnd")));
				userInfor.setTotal(Common.convertInt(req.getParameter("total"), 0));
			} else if ("back".equals(action)) {
				// lấy tên của đối tượng UserInforEntity
				String key = req.getParameter("key");
				session = req.getSession();
				// Lấy UserInforEntity có tên key từ SS
				userInfor = (UserInforEntity) session.getAttribute(key);

			}

			else {

				TblUserLogic tblUserLogic = new TblUserLogicImpl();
				userInfor = tblUserLogic.getUserInforByUserId(userId);
				userInfor.setUserId(userId);
				userInfor.setDayBirth(Integer.parseInt(userInfor.getBirthday().toString().substring(8, 10)));
				userInfor.setMonthBirth(Integer.parseInt(userInfor.getBirthday().toString().substring(5, 7)));
				userInfor.setYearBirth(Integer.parseInt(userInfor.getBirthday().toString().substring(0, 4)));

				if (userInfor.getCodeLevel() == null) {
					userInfor.setDayStart(Common.getDayNow());
					userInfor.setMonthStart(Common.getMonthNow());
					userInfor.setYearStart(Common.getYearNow());
					userInfor.setDayEnd(Common.getDayNow());
					userInfor.setMonthEnd(Common.getMonthNow());
					userInfor.setYearEnd(Common.getYearNow() + 1);
					userInfor.setTotal(0);
				} else {
					userInfor.setDayStart(Integer.parseInt(userInfor.getStartDate().toString().substring(8, 10)));
					userInfor.setMonthStart(Integer.parseInt(userInfor.getStartDate().toString().substring(5, 7)));
					userInfor.setYearStart(Integer.parseInt(userInfor.getStartDate().toString().substring(0, 4)));
					userInfor.setDayEnd(Integer.parseInt(userInfor.getEndDate().toString().substring(8, 10)));
					userInfor.setMonthEnd(Integer.parseInt(userInfor.getEndDate().toString().substring(5, 7)));
					userInfor.setYearEnd(Integer.parseInt(userInfor.getEndDate().toString().substring(0, 4)));
				}
			}

		} catch (Exception e) {
			System.out.println("class EditUserController Phuong thức getDefaultValue lỗi: " + e.getMessage());
			throw e;
		}
		return userInfor;
	}
}
