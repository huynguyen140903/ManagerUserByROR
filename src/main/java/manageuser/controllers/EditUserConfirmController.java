/**
 * luvina softwware JSC, 2022
 * EditUserConfirmController.java,HuyNQ
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
 * @author HuyNQ
 *
 */
@WebServlet({ "/EditUserConfirm.do", "/EditUserOK.do" })
public class EditUserConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;

	/**
	 * Phương thức xử lí GET Request tới URL /EditUserConfirm.do
	 * 
	 * @param request  Request client gửi lên server
	 * @param response Response servler gửi lại client
	 * @throws ServletException Trả ra ServletException khi servlet có lỗi
	 * @throws IOException      Trả ra IOException khi đọc ghi file bị lỗi
	 */

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
			req.setAttribute("group", mstGroupLogic.getGroupNameByGroupId(userInforEntity.getGroupId()));
			req.setAttribute("codeLevel", mstJapanLogic.getNameLevelByCodeLevel(userInforEntity.getCodeLevel()));
			req.setAttribute("key", key);

			// Đẩy ra view
			RequestDispatcher requestDispatcher;
			requestDispatcher = req.getRequestDispatcher(Constant.ADM004_PATH);
			requestDispatcher.forward(req, resp);

		} catch (Exception e) {
			System.out.println("class EditUserConfirmController Phuong thức doGet lỗi: " + e.getMessage());
			resp.sendRedirect(Constant.SYSTEM_ERROR_PATH + "?mess=ER015");
		}
	}

	/**
	 * Phương thức xử lí update dữ liệu vào DB khi click vào OK tại ADM004 edit
	 * 
	 * @param req  thông tin được gửi từ client lên server
	 * @param resp kết quả server trả về cho client
	 * @throws ServletException Trả ra ServletException khi servlet có lỗi
	 * @throws IOException      Trả ra IOException khi đọc ghi file bị lỗi
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			session = req.getSession();
			TblUserLogic tblUserLogic = new TblUserLogicImpl();
			String key = req.getParameter("key");
			UserInforEntity userInforEntity = (UserInforEntity) session.getAttribute(key);

			session.removeAttribute(key);
			if (userInforEntity.getUserId() > 0 && tblUserLogic.checkExistUserId(userInforEntity.getUserId())
					&& !Common.checkNotExistGroup(userInforEntity.getGroupId() + "")
					&& !Common.checkNotExistJapan(userInforEntity.getCodeLevel())
					&& !Common.checkExitEmail(userInforEntity.getEmail(), userInforEntity.getUserId())) {
				// thực hiện update thông tin user đã edit vào DB
				if (tblUserLogic.updateUser(userInforEntity)) {
					// Đẩy dữ liệu ra view ADM006
					resp.sendRedirect(Constant.SUCCESS_DO + "?mess=MSG002");
				}
			} else {
				resp.sendRedirect(Constant.SYSTEM_ERROR + "?mess=ER013");
			}

		} catch (Exception e) {
			System.out.println("class EditUserConfirmController Phuong thức doPost lỗi: " + e.getMessage());
			resp.sendRedirect(Constant.SYSTEM_ERROR + "?mess=ER015");
		}
	}
}
