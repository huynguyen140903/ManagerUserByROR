/**
 * luvina softwware JSC, 2022
 * SuccessController.java,HuyNQ

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

import manageuser.utils.Constant;
import manageuser.utils.MessageProperties;

/**
 * @author HuyNQ
 *
 */
@WebServlet ({"/Success.do"})
public class SuccessController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			String mess = req.getParameter("mess");
			req.setAttribute("mess", MessageProperties.getValueByKey(mess));
			RequestDispatcher requestDispatcher;
			requestDispatcher = req.getRequestDispatcher(Constant.ADM006_PATH);
			requestDispatcher.forward(req, resp);
		} catch (Exception e) {

		}
	}
}
