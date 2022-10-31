package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDAO;
import model.BoardDTO;
import model.CommentDAO;
import model.CommentDTO;

/**
 * Servlet implementation class BoardController
 */
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final int LIST_COUNT = 5;
	BoardDAO dao = BoardDAO.getInstance();
	CommentDAO commnetDAO = CommentDAO.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardController() {
		super();
		// TODO Auto-generated constructor stub
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

		if (command.equals("/board/BoardListAction.do")) {
			requestBoardList(request);
			RequestDispatcher rd = request.getRequestDispatcher("./list.jsp");
			rd.forward(request, response);
		} else if (command.equals("/board/BoardWriterForm.do")) {
			requestLoginName(request);
			RequestDispatcher rd = request.getRequestDispatcher("./addBoard.jsp");
			rd.forward(request, response);
		} else if (command.equals("/board/BoardWriteAction.do")) {
			requestBoardWrite(request);
			RequestDispatcher rd = request.getRequestDispatcher("BoardListAction.do");
			rd.forward(request, response);
		} else if (command.equals("/board/BoardViewAction.do")) {
			requestBoardView(request);
			RequestDispatcher rd = request.getRequestDispatcher("./view.jsp");
			rd.forward(request, response);
		} else if (command.equals("/board/BoardUpdateAction.do")) {
			requestBoardUpdate(request);
			RequestDispatcher rd = request.getRequestDispatcher("BoardListAction.do");
			rd.forward(request, response);
		} else if (command.equals("/board/BoardDeleteAction.do")) {
			requestBoardDelete(request);
			RequestDispatcher rd = request.getRequestDispatcher("BoardListAction.do");
			rd.forward(request, response);
		}
	}

	private void requestBoardDelete(HttpServletRequest request) {
		int num = Integer.parseInt(request.getParameter("num"));
		dao.deleteBoard(num);
	}

	private void requestBoardUpdate(HttpServletRequest request) {
		int num = Integer.parseInt(request.getParameter("num"));
		BoardDTO board = new BoardDTO();
		board.setNum(num);
		board.setName(request.getParameter("name"));
		board.setSubject(request.getParameter("subject"));
		board.setContent(request.getParameter("content"));

		dao.updateBoard(board);
	}

	private void requestBoardView(HttpServletRequest request) {
		int num = Integer.parseInt(request.getParameter("num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		BoardDTO board = new BoardDTO();
		board = dao.getBoardByNum(num);
		
		// ´ñ±Û ¸ñ·Ï
		ArrayList<CommentDTO> commentList = commnetDAO.getCommentList(num);
		if(commentList.size() > 0) {
			request.setAttribute("commentList", commentList);
		}

		request.setAttribute("num", num);
		request.setAttribute("page", pageNum);
		request.setAttribute("board", board);
	}

	private void requestBoardWrite(HttpServletRequest request) {
		BoardDTO board = new BoardDTO();
		board.setId(request.getParameter("id"));
		board.setName(request.getParameter("name"));
		board.setSubject(request.getParameter("subject"));
		board.setContent(request.getParameter("content"));
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy/MM/dd(HH:mm:ss)");
		String regist_day = formatter.format(new java.util.Date());
		board.setHit(0);
		board.setRegist_day(regist_day);
		board.setIp(request.getRemoteAddr());
		dao.insertBoard(board);
	}

	private void requestLoginName(HttpServletRequest request) {
		String id = request.getParameter("id");
		String name = dao.getLoginNameById(id);
		request.setAttribute("name", name);
	}

	private void requestBoardList(HttpServletRequest request) {
		List<BoardDTO> boardlist = new ArrayList<BoardDTO>();
		int pageNum = 1;
		int limit = LIST_COUNT;
		if (request.getParameter("pageNum") != null)
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		String items = request.getParameter("items");
		String text = request.getParameter("text");
		int total_record = dao.getListCount(items, text);
		boardlist = dao.getBoardList(pageNum, limit, items, text);
		int total_page;
		if (total_record % limit == 0) {
			total_page = total_record / limit;
		} else {
			total_page = total_record / limit + 1;
		}
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("total_page", total_page);
		request.setAttribute("total_record", total_record);
		request.setAttribute("boardlist", boardlist);
	}
}
