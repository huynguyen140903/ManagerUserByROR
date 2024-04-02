/**
 * luvina softwware JSC, 2022
 * LogoutController.java,HuyNQ

 */
package manageuser.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller để xử lý logout
 * 
 * @author HuyNQ
 *
 */
//Cấu hình Servlet theo annotation
@WebServlet("/logout")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * Phương thức xử lí GET Request tới URL /logout
	 * 
	 * @param request  Request client gửi lên server
	 * @param response Response servler gửi lại client
	 * @throws ServletException Trả ra ServletException khi servlet có lỗi
	 * @throws IOException      Trả ra IOException khi đọc ghi file bị lỗi
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Khai báo 1 RequestDispatcher
		RequestDispatcher requestDispatcher = null;
		
		req.getSession().removeAttribute("loginName");
		
		requestDispatcher = req.getRequestDispatcher("/jsp/ADM001.jsp");
		
		requestDispatcher.forward(req, resp);
	}
}
