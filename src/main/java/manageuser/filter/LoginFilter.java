/**
 * luvina softwware JSC, 2022
 * LoginFilter.java,HuyNQ
 */
package manageuser.filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.utils.Common;

/**
 * Lớp kiểm tra tình trạng login của người dùng với tất cả URL có đuôi .do trừ systemError
 * 
 * @author HuyNQ
 */
@WebFilter({ "/AddUserConfirm.do", "/AddUserOk.do", "/AddUserInput.do", "/DelUser.do", "/EditUserConfirm.do",
		"/EditUserOk.do", "/EditUserInput.do", "/listUser.do", "/ViewUser.do" })
public class LoginFilter implements Filter {

	/**
	 * Phương thức xử lí kiểm tra login
	 * 
	 * @param request  Request client gửi lên server
	 * @param response Response servler gửi lại client
	 * @param chain    Đối tượng điều hướng của filter
	 * @throws ServletException Trả ra ServletException khi servlet có lỗi
	 * @throws IOException      Trả ra IOException khi đọc ghi file bị lỗi
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			String loginURI = req.getContextPath() + "/";

			if (Common.checkLogin(req.getSession(false)) || loginURI.equals(req.getRequestURI())) {
				chain.doFilter(req, res);
			} else {
				res.sendRedirect(loginURI);
			}
		} catch (SQLException | IOException | ServletException e) {
			System.out.println("class LoginFilter Phuong thức doFilter lỗi: " + e.getMessage());
		}
	}
	@Override
	public void destroy() {
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
