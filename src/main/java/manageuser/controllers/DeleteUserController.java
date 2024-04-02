/**
 * luvina softwware JSC, 2022
 * DeleteUserController.java,HuyNQ
 */
package manageuser.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.logic.TblUserLogic;
import manageuser.logic.impl.TblUserLogicImpl;
import manageuser.utils.Constant;

/**
 * Controller xử lý logic xóa của màn hình ADM005
 * @author HuyNQ
 */
@WebServlet ({"/DeleteUser.do"})
public class DeleteUserController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	/**
	 * Phương thức xử lí POST Request tới URL /DeleteUser.do
	 * 
	 * @param request  Request client gửi lên server
	 * @param response Response servler gửi lại client
	 * @throws ServletException Trả ra ServletException khi servlet có lỗi
	 * @throws IOException      Trả ra IOException khi đọc ghi file bị lỗi
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {		
			TblUserLogic tblUserLogic = new TblUserLogicImpl();
			int userId = Integer.parseInt(req.getParameter("userId"));
			String error = tblUserLogic.getRuleByUserId(userId);
			if ("".equals(error)) {
				if (tblUserLogic.deleteUserByUserId(userId)) {
					// Đẩy dữ liệu ra view ADM006
					
					resp.sendRedirect(Constant.SUCCESS_DO + "?mess=MSG003");
				} else {
					resp.sendRedirect(Constant.SYSTEM_ERROR + "?mess=ER013");
				}
			} else {
				resp.sendRedirect(Constant.SYSTEM_ERROR + "?mess=ER013" );
			}

		} catch (Exception e) {
			System.out.println("class DeleteUserController Phuong thức doGet lỗi: " + e.getMessage());
			resp.sendRedirect(Constant.SYSTEM_ERROR + "?mess=ER015");
		}

	}

}
