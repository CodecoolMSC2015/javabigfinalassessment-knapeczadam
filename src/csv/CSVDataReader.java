package csv;

import java.util.List;
import java.util.Set;

import hr.Person;

/**
 * @author Knapecz Adam
 *
 */
public class CSVDataReader extends DataReader
{
	private String			csvFilePath;
	private List<Person>	persons;

	public CSVDataReader(String csvFilePath)
	{
		this.csvFilePath = csvFilePath;

	}

	public List<Person> getPersons()
	{
		return persons;
	}

	@Override
	public Set<Person> getPerson(String searchCriteria, SearchType searchType)
	{
		return null;
	}

	@Override
	public void setSearchCriteria(String searchCriteria)
	{
		this.searchCriteria = searchCriteria;
	}

	@Override
	public void setSearchType(SearchType searchType)
	{
		this.searchType = searchType;
	}

}
