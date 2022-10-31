package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDAO;
import model.MemberDTO;

/**
 * Servlet implementation class MemberController
 */
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberDAO dao = MemberDAO.getInstance();
	String error = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		if (command.equals("/member/MemberAddAction.member")) {
			requestMemberAdd(request);
			RequestDispatcher rd = request.getRequestDispatcher("./resultMember.jsp?msg=1");
			rd.forward(request, response);
		} else if (command.equals("/member/MemberLoginAction.member")) {
			requestMemberLogin(request);
			if (error == null) {
				RequestDispatcher rd = request.getRequestDispatcher("./resultMember.jsp?msg=2");
				rd.forward(request, response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("./loginMember.jsp?error=1");
				rd.forward(request, response);
			}
		} else if (command.equals("/member/MemberLogoutAction.member")) {
			requestMemberLogout(request);
			RequestDispatcher rd = request.getRequestDispatcher("./loginMember.jsp");
			rd.forward(request, response);
		} else if (command.equals("/member/MemberUpdateForm.member")) {
			requestMemberUpdateForm(request);
			RequestDispatcher rd = request.getRequestDispatcher("./updateMember.jsp");
			rd.forward(request, response);
		} else if (command.equals("/member/MemberUpdateAction.member")) {
			requestMemberUpdate(request);
			RequestDispatcher rd = request.getRequestDispatcher("./resultMember.jsp?msg=0");
			rd.forward(request, response);
		} else if (command.equals("/member/MemberDeleteAction.member")) {
			requestMemberDelete(request);
			requestMemberLogout(request);
			RequestDispatcher rd = request.getRequestDispatcher("./resultMember.jsp");
			rd.forward(request, response);
		}

	}

	private void requestMemberAdd(HttpServletRequest request) {
		MemberDTO member = new MemberDTO();
		member.setId(request.getParameter("id"));
		member.setPassword(request.getParameter("password"));
		member.setName(request.getParameter("name"));
		dao.addMember(member);
	}

	private void requestMemberLogin(HttpServletRequest request) {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		MemberDTO loginMember = dao.loginMember(id, password);
		if (loginMember != null) {
			HttpSession session = request.getSession();
			session.setAttribute("sessionId", id);
			error = null;
		} else {
			error = "1";
		}
	}

	private void requestMemberLogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
	}

	private void requestMemberUpdate(HttpServletRequest request) {
		MemberDTO member = new MemberDTO();
		member.setId(request.getParameter("id"));
		member.setPassword(request.getParameter("password"));
		member.setName(request.getParameter("name"));
		dao.updateMember(member);
	}

	private void requestMemberUpdateForm(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String sessionId = (String) session.getAttribute("sessionId");
		MemberDTO member = new MemberDTO();
		member = dao.updateFormMember(sessionId);
		request.setAttribute("member", member);
	}

	private void requestMemberDelete(HttpServletRequest request) {
		String id = request.getParameter("id");
		dao.deleteMember(id);
	}
}
