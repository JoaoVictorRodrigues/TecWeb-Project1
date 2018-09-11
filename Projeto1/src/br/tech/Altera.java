package br.tech;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/altera")
public class Altera extends HttpServlet {
	protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		
	}
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		DAO dao = new DAO();
		Mensagem msg = new Mensagem();
		String msgs = request.getParameter("msgEdit");
		String tags = request.getParameter("tagEdit");
		String ids = request.getParameter("idAlterar");
		List<String> list = new ArrayList<String>(Arrays.asList(tags.split(" ")));
		msg.setId(Integer.parseInt(ids));
		msg.setTag(list);
		msg.setMens(msgs);
		dao.altera(msg);
		dao.close();
		response.sendRedirect("Testejsp.jsp");
	}
}
