/**
 * luvina softwware JSC, 2022
 * ListUserController.java,HuyNQ

 */
package manageuser.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.logic.MstGroupLogic;
import manageuser.logic.TblUserLogic;
import manageuser.logic.impl.MstGroupLogicImpl;
import manageuser.logic.impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.Constant;
import manageuser.utils.DatabaseProperties;
import manageuser.utils.MessageProperties;

/**
 * 
 * Controller để xử lý cho màn hình ADM002
 * 
 * @author HuyNQ
 *
 */
@WebServlet("/listUser.do")
public class ListUserController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	/**
	 * Phương thức xử lí GET Request tới URL /listUser.do
	 * 
	 * @param request  Request client gửi lên server
	 * @param response Response servler gửi lại client
	 * @throws ServletException Trả ra ServletException khi servlet có lỗi
	 * @throws IOException      Trả ra IOException khi đọc ghi file bị lỗi
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// Lấy dữ liệu
			Common common = new Common();
			HttpSession session = req.getSession();
			String actionType = req.getParameter("actionType");

			// khai báo thuộc tính và gán giá trị default
			int offset = Constant.DEFAULT_OFFSET;
			int limit = Common.getLimit();
			int groupId = Constant.DEFAULT_GROUPID;
			String fullName = Constant.DEFAULT_FULLNAME;
			String sortType = Constant.DEFAULT_SORT_TYPE;
			String sortByFullName = Constant.DEFAULT_SORTBYFULLNAME;
			String sortByCodeLevel = Constant.DEFAULT_SORTBYCODELEVEL;
			String sortByEndDate = Constant.DEFAULT_SORTBYENDDATE;
			int currentPage = Constant.DEFAULT_CURRENTPAGE;

			if ("search".equals(actionType) || "pasing".equals(actionType) || "sort".equals(actionType)) {
				// lấy dữ liệu
				if (req.getParameter("currentPage") == null) {
					currentPage = 1;
				} else {
					currentPage = Integer.parseInt(req.getParameter("currentPage"));
				}
				offset = Common.getOffset(limit, currentPage);
				groupId = Integer.parseInt(req.getParameter("group_id"));
				fullName = req.getParameter("name");

				// Xử lí
				sortType = req.getParameter("sortType");
				if ("FullName".equals(sortType)) {
					sortByFullName = req.getParameter("sortByFullName");
					sortByCodeLevel = Constant.DEFAULT_SORTBYCODELEVEL;
					sortByEndDate = Constant.DEFAULT_SORTBYENDDATE;
				} else if ("CodeLevel".equals(sortType)) {
					sortByCodeLevel = req.getParameter("sortByCodeLevel");
					sortByFullName = Constant.DEFAULT_SORTBYFULLNAME;
					sortByEndDate = Constant.DEFAULT_SORTBYENDDATE;
				} else if ("EndDate".equals(sortType)) {
					sortByEndDate = req.getParameter("sortByEndDate");
					sortByFullName = Constant.DEFAULT_SORTBYFULLNAME;
					sortByCodeLevel = Constant.DEFAULT_SORTBYCODELEVEL;
				} else {
					sortType = Constant.DEFAULT_SORT_TYPE;
					sortByFullName = Constant.DEFAULT_SORTBYFULLNAME;
					sortByCodeLevel = Constant.DEFAULT_SORTBYCODELEVEL;
					sortByEndDate = Constant.DEFAULT_SORTBYENDDATE;
				}
				String sortAsc = "asc";
				String sortDesc = "desc";
				if (!sortAsc.equals(sortByFullName) && !sortDesc.equals(sortByFullName)) {
					sortByFullName = Constant.DEFAULT_SORTBYFULLNAME;
				}
				if (!sortAsc.equals(sortByCodeLevel) && !sortDesc.equals(sortByCodeLevel)) {
					sortByCodeLevel = Constant.DEFAULT_SORTBYCODELEVEL;
				}
				if (!sortAsc.equals(sortByEndDate) && !sortDesc.equals(sortByEndDate)) {
					sortByEndDate = Constant.DEFAULT_SORTBYENDDATE;
				}
				// truong hop back
			} else if ("back".equals(actionType)) {
				// Lấy dữ liệu
				sortType = (String) session.getAttribute("sortType");
				groupId = (int) session.getAttribute("groupId");
				fullName = (String) session.getAttribute("fullName");
				currentPage = (int) session.getAttribute("currentPage");
				groupId = (int) session.getAttribute("listGroup");
				sortByFullName = (String) session.getAttribute("sortByFullName");
				sortByCodeLevel = (String) session.getAttribute("sortByCodeLevel");
				sortByEndDate = (String) session.getAttribute("sortByEndDate");
			}
			fullName = Common.deleteSpace(fullName);

			TblUserLogic tblUserLogicImpl = new TblUserLogicImpl();
			MstGroupLogic groupLogicImpl = new MstGroupLogicImpl();
			int totalUsers = tblUserLogicImpl.getTotalUsers(groupId, fullName);
			req.setAttribute("totalUsers", totalUsers);
			req.setAttribute("listGroup", groupLogicImpl.getAllMstGroup());
			req.setAttribute("fullName", fullName);
			req.setAttribute("groupId", groupId);
			if (totalUsers == 0) {
				req.setAttribute("MSG005", MessageProperties.getValueByKey("MSG005"));
			} else if (totalUsers > 0) {
				List<Integer> listPaging = common.getListPaging(totalUsers, limit, currentPage);
				// Đẩy dữ liệu lên req
				req.setAttribute("listPaging", listPaging);
				req.setAttribute("sortByFullName", sortByFullName);
				req.setAttribute("sortByCodeLevel", sortByCodeLevel);
				req.setAttribute("sortByEndDate", sortByEndDate);
				req.setAttribute("sortType", sortType);
				req.setAttribute("listUserInfors", tblUserLogicImpl.getListUsers(offset, limit, groupId, fullName,
						sortType, sortByFullName, sortByCodeLevel, sortByEndDate));
				req.setAttribute("totalPage", Common.getTotalPage(totalUsers, limit));
				req.setAttribute("currentPage", currentPage);
				req.setAttribute("pageLimit", Integer.parseInt(DatabaseProperties.getValueByKey("pageLimit")));
			}

			// đẩy dữ liệu lên SS
			session.setAttribute("sortType", sortType);
			session.setAttribute("groupId", groupId);
			session.setAttribute("fullName", fullName);
			session.setAttribute("currentPage", currentPage);
			session.setAttribute("listGroup", groupId);
			session.setAttribute("sortByFullName", sortByFullName);
			session.setAttribute("sortByCodeLevel", sortByCodeLevel);
			session.setAttribute("sortByEndDate", sortByEndDate);
			// Đẩy dữ liệu ra view ADM002
			RequestDispatcher requestDispatcher;
			requestDispatcher = req.getRequestDispatcher(Constant.ADM002_PATH);
			requestDispatcher.forward(req, resp);

		} catch (Exception e) {
			System.out.println("class ListUserController Phuong thức doGet lỗi: " + e.getMessage());
			RequestDispatcher requestDispatcher;
			requestDispatcher = req.getRequestDispatcher(Constant.SYSTEM_ERROR);
			requestDispatcher.forward(req, resp);

		}
	}
}
