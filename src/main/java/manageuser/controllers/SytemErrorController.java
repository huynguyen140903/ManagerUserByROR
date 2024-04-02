/**
 * luvina softwware JSC, 2022
 * SystemErrorController.java,HuyNQ

 */ 
package manageuser.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.utils.Constant;
import manageuser.utils.MessageErrorProperties;

/**
 * Class dung de xu li loi de in thong bao ra man hinh
 * @author HuyNQ
 *
 */
@WebServlet ({"/SystemError.do"})
public class SytemErrorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mess = req.getParameter("mess");
		req.setAttribute("mess", MessageErrorProperties.getValueByKey(mess));
		RequestDispatcher dispatcher = req.getRequestDispatcher(Constant.SYSTEM_ERROR_PATH);
		dispatcher.forward(req, resp);
	}
}
