package hr;

/**
 * @author Knapecz Adam
 *
 */
public class Employee extends Person
{
	private int		salary;
	private String	jobTitle;

	public Employee(String name, String email, int salary, String jobTitle)
	{
		super(name, email);
		this.salary = salary;
		this.jobTitle = jobTitle;
	}

	public int getSalary()
	{
		return salary;
	}

	public void setSalary(int salary)
	{
		this.salary = salary;
	}

	public String getJobTitle()
	{
		return jobTitle;
	}

	public void setJobTitle(String jobTitle)
	{
		this.jobTitle = jobTitle;
	}

	@Override
	public String toString()
	{
		return "Employee [salary=" + salary + ", jobTitle=" + jobTitle + ", toString()=" + super.toString() + "]";
	}

}
