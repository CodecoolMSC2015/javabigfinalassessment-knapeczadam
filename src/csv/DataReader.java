package csv;

import java.util.Set;

import hr.Person;

/**
 * @author Knapecz Adam
 *
 */
public abstract class DataReader
{
	public String		searchCriteria;
	public SearchType	searchType;

	public abstract Set<Person> getPerson();

	public abstract void setSearchCriteria(String searchCriteria);

	public abstract void setSearchType(SearchType searchType);

}
