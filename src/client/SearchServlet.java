package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import csv.SearchType;
import hr.Person;

/**
 * Servlet implementation class SearchServlet
 */
/**
 * @author Knapecz Adam
 *
 */
public class SearchServlet extends HttpServlet
{
	private List<Person>	goodPersons;
	private String			searchCriteria;
	private SearchType		searchType;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String newSearchCriteria = request.getParameter("searchCriteria");
		SearchType newSearchType = SearchType.valueOf(request.getParameter("searchType"));

		HttpSession session = request.getSession(false);

		if (session != null)
		{
			if (searchCriteria.equals(newSearchCriteria) && searchType.equals(newSearchType))
			{
				List<Person> goodPersons = (List<Person>) session.getAttribute("goodPersons");
			}
			searchCriteria = newSearchCriteria;
			searchType = newSearchType;
		}
		else
		{
			PersonStoreClientSocket client = new PersonStoreClientSocket(searchCriteria, searchType);
			List<Person> goodPersons = (List<Person>) client.getGoodPersons();
			session.setAttribute("goodPersons", goodPersons);
		}

	}

}
