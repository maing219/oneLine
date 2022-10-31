package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CommentDAO;
import model.CommentDTO;

/**
 * Servlet implementation class CommentController
 */
@WebServlet("/CommentController")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CommentDAO dao = CommentDAO.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommentController() {
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
		
		if (command.equals("/board/CommentWriteAction.comment")) {
			CommentWriteAction(request);
			int num = Integer.parseInt(request.getParameter("num"));
			int pageNum = Integer.parseInt(request.getParameter("pageNum"));
			RequestDispatcher rd = request.getRequestDispatcher("BoardViewAction.do?num=" + num + "&pageNum=" + pageNum);
			rd.forward(request, response);
		} else if (command.equals("/board/CommentDeleteAction.comment")) {
			CommentDeleteAction(request);
			int num = Integer.parseInt(request.getParameter("num"));
			int pageNum = Integer.parseInt(request.getParameter("pageNum"));
			RequestDispatcher rd = request.getRequestDispatcher("BoardViewAction.do?num=" + num + "&pageNum=" + pageNum);
			rd.forward(request, response);
		}
	}
	
	private void CommentWriteAction(HttpServletRequest request) {
		CommentDTO comment = new CommentDTO();
		int comment_board = Integer.parseInt(request.getParameter("comment_board"));
		comment.setComment_num(dao.getSeq());
		comment.setComment_board(comment_board);
		comment.setComment_id(request.getParameter("comment_id"));
		comment.setComment_content(request.getParameter("comment_content"));
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy/MM/dd(HH:mm:ss)");
		String regist_day = formatter.format(new java.util.Date());
		comment.setComment_date(regist_day);
		dao.insertComment(comment);
	}
	
	private void CommentDeleteAction(HttpServletRequest request) {
		int comment_num = Integer.parseInt(request.getParameter("comment_num"));
		dao.deleteComment(comment_num);
	}

}
