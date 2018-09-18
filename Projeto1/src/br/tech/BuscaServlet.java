package br.tech;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BuscaServlet
 */
@WebServlet("/BuscaServlet")
public class BuscaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DAO dao = new DAO();
		String tagBusca = request.getParameter("tagBusca");
		if(tagBusca.equals("")){
			response.sendRedirect("Testejsp.jsp");
			
		}
		else {
			List<Integer> listaId = dao.getBuscaId(tagBusca);
			List<Integer> listaZero = new ArrayList<>();
			if(listaId.equals(listaZero)) {
				response.sendRedirect("Testejsp.jsp");
			}
			else {
		
				request.setAttribute("result", listaId);
				request.getRequestDispatcher("buscaTag.jsp").forward(request, response);	
			}
		}
	}
}
