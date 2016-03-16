package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import csv.SearchType;

/**
 * Servlet implementation class SearchServlet
 */
/**
 * @author Knapecz Adam
 *
 */
public class SearchServlet extends HttpServlet
{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String searchCriteria = request.getParameter("searchCriteria");
		SearchType searchType = SearchType.valueOf(request.getParameter("searchType"));
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			Map<String, SearchType> goodPersons = (Map<String, SearchType>) session.getAttribute("goodPersons");
			goodPersons.put(searchCriteria, searchType);
			session.setAttribute("goodPersons", goodPersons);
		}
		else
		{
			session = request.getSession();
			Map<String, SearchType> goodPersons = new HashMap<String, SearchType>();
			session.setAttribute("goodPersons", goodPersons);
		}

	}

}
