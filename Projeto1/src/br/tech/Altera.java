package br.tech;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/altera")
public class Altera extends HttpServlet {
	protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		DAO dao = new DAO();
		dao.exclui(Integer.valueOf(request.getParameter("id")));
		dao.close();
		response.sendRedirect("Testejsp.jsp");
	}
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		
	}
}
