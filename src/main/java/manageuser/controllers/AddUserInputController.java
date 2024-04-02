/**
 * luvina softwware JSC, 2022
 * AddUserInputController.java,HuyNQ
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
import manageuser.logic.impl.MstGroupLogicImpl;
import manageuser.logic.impl.MstJapanLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.Constant;
import manageuser.validates.ValidateUser;

/**
 * Controller xử lý các logic của màn hình ADM003
 * 
 * @author HuyNQ
 *
 */
@WebServlet({ "/AddUserInput.do", "/AddUserValidate.do" })
public class AddUserInputController extends HttpServlet {
	private HttpSession session;

	private static final long serialVersionUID = 1L;
	private UserInforEntity userInfor = new UserInforEntity();
	/**
	 * Phương thức xử lí GET Request tới URL /AddUserInput.do
	 * 
	 * @param request  Request client gửi lên server
	 * @param response Response servler gửi lại client
	 * @throws ServletException Trả ra ServletException khi servlet có lỗi
	 * @throws IOException      Trả ra IOException khi đọc ghi file bị lỗi
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			session = req.getSession();

			// set giá trị DF lên req
			setDataLogic(req, resp);
			req.setAttribute("user", getDefaultValue(req, resp));
			// Đẩy dữ liệu ra view ADM003
			RequestDispatcher requestDispatcher;
			requestDispatcher = req.getRequestDispatcher(Constant.ADM003_PATH);
			requestDispatcher.forward(req, resp);

		} catch (Exception e) {
			System.out.println("class AddUserInputController Phuong thức doGet lỗi: " + e.getMessage());
			e.printStackTrace();
			RequestDispatcher requestDispatcher;
			requestDispatcher = req.getRequestDispatcher(Constant.SYSTEM_ERROR);
			requestDispatcher.forward(req, resp);
		}

	}

	/**
	 * Phương thức thực hiện set giá trị cho các hạng mục SelectBox ở màn hình
	 * ADM003
	 * 
	 * @param request Request client gửi lên server
	 * @throws ServletException
	 * @throws SQLException           Trả ra khi có lỗi SQL
	 */
	private void setDataLogic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			MstGroupLogic groupLogicImpl = new MstGroupLogicImpl();
			MstJapanLogic mstJapanLogic = new MstJapanLogicImpl();
			// Đẩy list giá trị của các selecbox lên Req
			req.setAttribute("listGroup", groupLogicImpl.getAllMstGroup());
			req.setAttribute("listJapan", mstJapanLogic.getAllMstJapan());
			req.setAttribute("day", Common.getListDay());
			req.setAttribute("month", Common.getListMonth());
			req.setAttribute("year", Common.getListYear(1900, Common.getYearNow()));
			req.setAttribute("yearEndDate", Common.getListYear(1900, Common.getYearNow() + 1));
		} catch (Exception e) {
			System.out.println("class AddUserInputController Phuong thức doGet lỗi: " + e.getMessage());
			RequestDispatcher requestDispatcher;
			requestDispatcher = req.getRequestDispatcher(Constant.SYSTEM_ERROR);
			requestDispatcher.forward(req, resp);
		}

	}

	/**
	 * Get giá trị default cho màn hình ADM003
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	private UserInforEntity getDefaultValue(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		userInfor = new UserInforEntity();
		try {
			// Khai báo giá trị các biến mặc định
			String action = req.getParameter("action");
			String loginName = "";
			int groupId = 0;
			String fullName = "";
			String fullNameKana = "";
			int dayBirth = Common.getDayNow();
			int monthBirth = Common.getMonthNow();
			int yearBirth = Common.getYearNow();
			String email = "";
			String tel = "";
			String codeLevel = "";
			String password = "";
			String passwordConfirm = "";
			int dayStart = Common.getDayNow();
			int monthStart = Common.getMonthNow();
			int yearStart = Common.getYearNow();
			int dayEnd = Common.getDayNow();
			int monthEnd = Common.getMonthNow();
			int yearEnd = Common.getYearNow() + 1;
			int total = 0;

			// trường hợp click vào button add trong màn hình ADM003
			if ("validate".equals(action)) {
				// lấy dữ liệu từ màn hình
				loginName = req.getParameter("loginName");
				groupId = Integer.parseInt(req.getParameter("group_id"));
				fullName = req.getParameter("name");
				fullNameKana = req.getParameter("nameKana");
				yearBirth = Integer.parseInt(req.getParameter("yearBirth"));
				monthBirth = Integer.parseInt(req.getParameter("monthBirth"));
				dayBirth = Integer.parseInt(req.getParameter("dayBirth"));
				email = req.getParameter("email");
				tel = req.getParameter("tel");
				password = req.getParameter("pass");
				passwordConfirm = req.getParameter("passConfirm");
				codeLevel = req.getParameter("level");
				dayStart = Integer.parseInt(req.getParameter("dayStart"));
				monthStart = Integer.parseInt(req.getParameter("monthStart"));
				yearStart = Integer.parseInt(req.getParameter("yearStart"));
				dayStart = Integer.parseInt(req.getParameter("dayEnd"));
				monthEnd = Integer.parseInt(req.getParameter("monthEnd"));
				yearEnd = Integer.parseInt(req.getParameter("yearEnd"));
				total = Common.convertInt(req.getParameter("total"), 0);
			}
			if ("back".equals(action)) {
				String key = req.getParameter("key");
				session = req.getSession();
				UserInforEntity userInforEntity = (UserInforEntity) session.getAttribute(key);
				loginName = userInforEntity.getLoginName();
				groupId = Integer.parseInt(userInforEntity.getGroup());
				fullName = userInforEntity.getFullName();
				fullNameKana = userInforEntity.getFullNameKana();
				yearBirth = userInforEntity.getYearBirth();
				monthBirth = userInforEntity.getMonthBirth();
				dayBirth = userInforEntity.getDayBirth();
				email = userInforEntity.getEmail();
				tel = userInforEntity.getTel();
				password = userInforEntity.getPass();
				passwordConfirm = userInforEntity.getPassConfirm();
				codeLevel = userInforEntity.getJapanLevel();
				dayStart = userInforEntity.getDayStart();
				monthStart = userInforEntity.getMonthStart();
				yearStart = userInforEntity.getYearStart();
				dayEnd = userInforEntity.getDayEnd();
				monthEnd = userInforEntity.getMonthEnd();
				yearEnd = userInforEntity.getYearEnd();
				total = userInforEntity.getTotal();
			}

			// set giá trị các thuộc tính của đối tượng UserInfor
			userInfor.setLoginName(loginName);
			userInfor.setGroup(groupId + "");
			userInfor.setFullName(fullName);
			userInfor.setFullNameKana(fullNameKana);
			userInfor.setDayBirth(dayBirth);
			userInfor.setMonthBirth(monthBirth);
			userInfor.setYearBirth(yearBirth);
			userInfor.setEmail(email);
			userInfor.setTel(tel);
			userInfor.setJapanLevel(codeLevel);
			userInfor.setDayStart(dayStart);
			userInfor.setMonthStart(monthStart);
			userInfor.setYearStart(yearStart);
			userInfor.setDayEnd(dayEnd);
			userInfor.setMonthEnd(monthEnd);
			userInfor.setYearEnd(yearEnd);
			userInfor.setTotal(total);
			userInfor.setPass(password);
			userInfor.setPassConfirm(passwordConfirm);

		} catch (Exception e) {
			System.out.println(Thread.currentThread().getStackTrace()[1].getClassName() + " "
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + " " + e.getMessage());
			throw e;
		}

		return userInfor;
	}
	/**
	 * Phương thức xử lí POST Request tới URL /AddUserValidate.do
	 * 
	 * @param request  Request client gửi lên server
	 * @param response Response servler gửi lại client
	 * @throws ServletException Trả ra ServletException khi servlet có lỗi
	 * @throws IOException      Trả ra IOException khi đọc ghi file bị lỗi
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			session = req.getSession();

			UserInforEntity userInforEntity = getDefaultValue(req, resp);
			ValidateUser validateUser = new ValidateUser();
			// kiểm tra validate của đối tượng
			List<String> listError = validateUser.validateUserInfor(userInforEntity);
			// nếu đối tượng nhập đúng
			if (listError.size() == 0) {
				String key = Common.createKey();
				session.setAttribute(key, userInforEntity);
				// gọi URL của Controller 004 và truyền thêm tên của đối tượng
				resp.sendRedirect(Constant.ADDUSERCONFIRM_DO + "?key=" + key);
				// Đối tượng nhập sai thì ở lại màn 003 và đẩy lỗi ra view
			} else {
				RequestDispatcher requestDispatcher;
				requestDispatcher = req.getRequestDispatcher(Constant.ADM003_PATH);
				setDataLogic(req, resp);

				req.setAttribute("user", userInforEntity);
				req.setAttribute("listError", listError);
				requestDispatcher.forward(req, resp);
			}

		} catch (Exception e) {
			System.out.println("class AddUserInputController Phuong thức doPost lỗi: " + e.getMessage());
			RequestDispatcher requestDispatcher;
			requestDispatcher = req.getRequestDispatcher(Constant.SYSTEM_ERROR);
			requestDispatcher.forward(req, resp);
		}

	}
}
