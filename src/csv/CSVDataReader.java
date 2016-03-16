package csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import hr.Employee;
import hr.Person;
import hr.Skill;

/**
 * @author Knapecz Adam
 *
 */
public class CSVDataReader extends DataReader
{
	private String csvFilePath;

	public CSVDataReader(String csvFilePath)
	{
		this.csvFilePath = csvFilePath;
	}

	@Override
	public Set<Person> getPerson()
	{
		Set<Person> persons = new HashSet<Person>();
		Map<String, Person> personsMap = new HashMap<String, Person>();

		String[] searchCriteria = this.searchCriteria.split(";");
		Set<String> searchCriteriaSet = new HashSet<String>();
		for (String searchCriteriaElement : searchCriteria)
		{
			searchCriteriaSet.add(searchCriteriaElement);
		}

		try
		{
			Scanner scan = new Scanner(new File(csvFilePath));
			while (scan.hasNextLine())
			{
				String line = scan.nextLine();
				String[] lineArray = line.split(",");

				for (String criterium : searchCriteria)
				{
					if (lineArray[2].toUpperCase().equals(criterium))
					{
						if (lineArray[5] != null)
						{
							Person employee = new Employee(lineArray[0], lineArray[1], Integer.valueOf(lineArray[5]),
									null);
							Skill skill = new Skill(lineArray[2], null, Double.valueOf(lineArray[4]));
							for (Map.Entry<String, Person> entry : personsMap.entrySet())
							{
								if (entry.getValue().equals(employee))
								{
									Person existingEmployee = entry.getValue();
									existingEmployee.addSkill(skill);

								}
								else
								{
									employee.addSkill(skill);
									personsMap.put(employee.getName(), employee);

								}
							}
						}
						else
						{
							Person person = new Person(lineArray[0], lineArray[1]);
							Skill skill = new Skill(lineArray[2], null, Double.valueOf(lineArray[4]));
							for (Map.Entry<String, Person> entry : personsMap.entrySet())
							{
								if (entry.getValue().equals(person))
								{
									Person existingPerson = entry.getValue();
									existingPerson.addSkill(skill);

								}
								else
								{
									person.addSkill(skill);
									personsMap.put(person.getName(), person);

								}
							}
						}

					}
				}

			}
			for (Map.Entry<String, Person> entry : personsMap.entrySet())
			{

				Set<String> personSkillSet = new HashSet<String>();

				List<Skill> skillList = new ArrayList<Skill>();
				skillList.add((Skill) entry.getValue().getSkillset());
				for (Skill skill : skillList)
				{
					personSkillSet.add(skill.getName());
				}
				if (searchType instanceof SearchType && searchType == SearchType.Mandatory)
				{
					if (personSkillSet.equals(searchCriteriaSet))
					{
						persons.add(entry.getValue());
					}

				}
				if (searchType instanceof SearchType && searchType == SearchType.Optional)
				{
					for (String searchedSkill : searchCriteriaSet)
					{
						if (personSkillSet.contains(searchedSkill))
						{
							persons.add(entry.getValue());
							break;
						}
					}
				}
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		return persons;
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
