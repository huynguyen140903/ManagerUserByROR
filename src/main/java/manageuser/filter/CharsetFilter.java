/**
 * Luvina Software JSC, 2022
 * CharsetFilter.java, HuyNQ
 */
package manageuser.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Lớp thực hiện set Encoding cho các request gửi dưới dạng POST
 * 
 * @author HuyNQ
 */
@WebFilter(urlPatterns = { "/login", "/AddUserValidate.do", "/EditUserValidate.do" })
public class CharsetFilter implements Filter {

	private String encoding;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/**
	 * Phương thức xử lí set Encoding
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
		// Đưa 2 tham số request và response về kiểu dữ liệu HttpServletRequest và
		// HttpServletResponse
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// Set encoding cho các request
		req.setCharacterEncoding(encoding);
		// Cho phép vượt qua filter
		chain.doFilter(req, res);
	}

	/**
	 * Phương thức lấy giá trị mặc định cho biến encoding
	 * 
	 * @param config Đối tượng chứa cấu hình của filter
	 * @throws ServletException Trả ra ServletException khi servlet có lỗi
	 */
	@Override
	public void init(FilterConfig config) throws ServletException {
		// Lấy ra giá trị biến requestEncoding từ config web.xml
		encoding = config.getInitParameter("requestEncoding");
		// Nếu không tồn tại giá trị encoding thì gán = utf-8
		if (encoding == null) {
			encoding = "UTF-8";
		}
	}

}
