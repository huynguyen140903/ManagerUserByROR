/**
 * luvina softwware JSC, 2022
 * LoginController.java,HuyNQ
 */
package manageuser.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.utils.Constant;
import manageuser.validates.ValidateUser;

/**
 * 
 * Controller để xử lý cho màn hình ADM001
 * 
 * @author HuyNQ
 *
 */

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * Phương thức xử lí POST Request tới URL /login
	 * 
	 * @param request  Request client gửi lên server
	 * @param response Response servler gửi lại client
	 * @throws ServletException Trả ra ServletException khi servlet có lỗi
	 * @throws IOException      Trả ra IOException khi đọc ghi file bị lỗi
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// Khai báo 1 biến loginName và gán bằng giá trị nhập từ màn hình
			String loginName = req.getParameter("loginId");
			// Khai báo 1 biến pass và gán bằng giá trị nhập từ màn hình
			String pass = req.getParameter("password");
			// Khai báo 1 RequestDispatcher
			RequestDispatcher requestDispatcher;
			// Khai báo khởi tạo 1 list lỗi
			ArrayList<String> listMessageErr = new ArrayList<String>();
			// Khai báo đối tượng validateUser
			ValidateUser validateUser = new ValidateUser();
			// Lấy giá trị của phuong thức xử lí tên đăng nhập và pass gán cho list lỗi
			listMessageErr = validateUser.validateLogin(loginName, pass);
			// Nếu list lỗi rỗng, đăng nhập thành công
			if (listMessageErr.size() == 0) {
				// thiết lập giá trị gửi đi lưu trên session
				req.getSession().setAttribute("loginName", loginName);
				resp.sendRedirect(Constant.LISTUSER_DO);
				// Nếu list lỗi không rỗng,đăng nhập không thành công
			} else {
				// gửi yêu cầu tới ADM001 thông qua getRequestDispatcher
				requestDispatcher = req.getRequestDispatcher(Constant.ADM001_PATH);
				// thiết lập giá trị gửi đi
				req.setAttribute("listMessageErr", listMessageErr);
				// đóng else kiểm tra thất bại
				req.setAttribute("loginName", loginName);
				// chuyển tiếp yêu cầu sang trang khác
				requestDispatcher.forward(req, resp);
				// đóng else kiểm tra thất bại
			}
			// đóng phương thức
		} catch (Exception e) {

			System.out.println("class LoginController Phuong thức doPost lỗi: " + e.getMessage());
			RequestDispatcher requestDispatcher;
			requestDispatcher = req.getRequestDispatcher(Constant.SYSTEM_ERROR);
			requestDispatcher.forward(req, resp);
		}

	}

	/**
	 * Phương thức xử lí GET Request tới URL /login
	 * 
	 * @param HttpServletRequest  Request client gửi lên server
	 * @param HttpServletResponse Response servler gửi lại client
	 * @throws ServletException Trả ra ServletException khi servlet có lỗi
	 * @throws IOException      Trả ra IOException khi đọc ghi file bị lỗi
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(Constant.ADM001_PATH);
		requestDispatcher.forward(request, response);
	}
}
