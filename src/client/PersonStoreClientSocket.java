package client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Set;

import csv.SearchType;
import hr.Person;

public class PersonStoreClientSocket
{
	private Set<Person> goodPersons;

	PersonStoreClientSocket(String searchCriteria, SearchType searchType)
	{
		try
		{
			Socket socket = new Socket("localhost", 1992);
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			oos.write(0);
			oos.writeUTF(searchCriteria);
			oos.writeObject(searchType);
			goodPersons = (Set<Person>) ois.readObject();
			oos.close();
			ois.close();

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public Set<Person> getGoodPersons()
	{
		return goodPersons;
	}

}
