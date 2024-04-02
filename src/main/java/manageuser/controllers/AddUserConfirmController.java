/**
 * luvina softwware JSC, 2022
 * AddUserConfirmController.java,HuyNQ
 */
package manageuser.controllers;

import java.io.IOException;

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

/**
 * Controller xử lý các logic của màn hình ADM004
 * 
 * @author HuyNQ
 *
 */
@WebServlet({ "/AddUserConfirm.do", "/AddUserOK.do" })
public class AddUserConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;

	/**
	 * Phương thức xử lí GET Request tới URL /AddUserConfirm.do
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

			MstGroupLogic mstGroupLogic = new MstGroupLogicImpl();
			MstJapanLogic mstJapanLogic = new MstJapanLogicImpl();
			// lấy về tên của 1 đối tương UserInforEntity
			String key = req.getParameter("key");
			// lấy đối tượng UserInforEntity từ SS có tên key
			UserInforEntity userInforEntity = (UserInforEntity) session.getAttribute(key);
			// Đẩy các giá trị lên Req
			req.setAttribute("user", userInforEntity);
			req.setAttribute("group",
					mstGroupLogic.getGroupNameByGroupId(Integer.parseInt(userInforEntity.getGroup())));
			req.setAttribute("codeLevel", mstJapanLogic.getNameLevelByCodeLevel(userInforEntity.getJapanLevel()));
			req.setAttribute("key", key);

			// Đẩy ra view
			RequestDispatcher requestDispatcher;
			requestDispatcher = req.getRequestDispatcher(Constant.ADM004_PATH);
			requestDispatcher.forward(req, resp);

		} catch (Exception e) {
			System.out.println("class AddUserConfirmController Phuong thức doPost lỗi: " + e.getMessage());
			RequestDispatcher requestDispatcher;
			requestDispatcher = req.getRequestDispatcher(Constant.SYSTEM_ERROR);
			requestDispatcher.forward(req, resp);
		}

	}
	/**
	 * Phương thức xử lí POST Request tới URL /AddUserOK.do
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

			TblUserLogic tblUserLogic = new TblUserLogicImpl();
			session = req.getSession();
			String key = req.getParameter("key");
			UserInforEntity userInfor = (UserInforEntity) session.getAttribute(key);
			session.removeAttribute(key);

			boolean checkNotExistGroup = !Common.checkNotExistGroup(userInfor.getGroup());
			boolean checkNotExistJapan;
			if ("0".equals(userInfor.getJapanLevel())) {
				checkNotExistJapan = true;
			} else {
				checkNotExistJapan = !Common.checkNotExistJapan(userInfor.getJapanLevel());
			}
			boolean checkExistLoginName = !Common.checkExistEmailAndLoginName(userInfor.getLoginName());
			boolean checkExistEmail = !Common.checkExistEmailAndLoginName(userInfor.getEmail());

			if (!checkNotExistGroup || !checkNotExistJapan || checkExistLoginName || checkExistEmail) {
				if (tblUserLogic.createUser(userInfor)) {
					// Đẩy dữ liệu ra view ADM006
					resp.sendRedirect(Constant.SUCCESS_DO + "?mess=MSG001");
				}
			} else {
				resp.sendRedirect(Constant.SYSTEM_ERROR + "?mess=ER015");
			}

		} catch (Exception e) {
			System.out.println("class AddUserConfirmController Phuong thức doPost lỗi: " + e.getMessage());
			resp.sendRedirect(Constant.SYSTEM_ERROR + "?mess=ER015");
		}
	}

}
