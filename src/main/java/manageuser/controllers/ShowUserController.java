/**
 * luvina softwware JSC, 2022
 * ShowUserController.java,HuyNQ

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
import manageuser.logic.TblUserLogic;
import manageuser.logic.impl.TblUserLogicImpl;
import manageuser.utils.Constant;

/**
 * @author HuyNQ
 *
 */
@WebServlet ({"/ShowUser.do"})
public class ShowUserController extends HttpServlet {
	/**
	 * Phương thức xử lí GET Request tới URL /ShowUser.do
	 * 
	 * @param request  Request client gửi lên server
	 * @param response Response servler gửi lại client
	 * @throws ServletException Trả ra ServletException khi servlet có lỗi
	 * @throws IOException      Trả ra IOException khi đọc ghi file bị lỗi
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			session = request.getSession();
			// Lấy userId từ request khi click link ID của màn hình ADM002
			int userId = Integer.parseInt(request.getParameter("userId"));
			
			TblUserLogic tblUserLogic = new TblUserLogicImpl();
			// Kiểm tra tồn tại của userId
			if (userId > 0 && tblUserLogic.checkExistUserId(userId)) {
				// Nếu tồn tại, Lấy ra UserInfor theo userId
				UserInforEntity userInforEntity = tblUserLogic.getUserInforByUserId(userId);
				userInforEntity.setUserId(userId);
				request.setAttribute("userInforEntity", userInforEntity);
				// đẩy ra view

				RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.ADM005_PATH);
				dispatcher.forward(request, response);
			} else {
				// Nếu không tồn tại userId chuyển đến màn hình error
				response.sendRedirect(Constant.SYSTEM_ERROR + "?mess=ER013");
			}
		} catch (Exception e) {
			System.out.println("AddUserConfirmController doPost" + e.getMessage());
			response.sendRedirect(Constant.SYSTEM_ERROR + "?mess=ER015");
		}
	}
}
