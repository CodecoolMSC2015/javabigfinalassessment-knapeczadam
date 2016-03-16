package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import csv.CSVDataReader;
import csv.DataReader;
import csv.SearchType;

public class PersonStoreServerSocket
{
	DataReader store;

	public PersonStoreServerSocket()
	{
		store = new CSVDataReader("path");
		try
		{
			ServerSocket server = new ServerSocket(1992);
			System.out.println("Waiting for client...");
			Socket serverSocket = server.accept();
			ObjectInputStream ois = new ObjectInputStream(serverSocket.getInputStream());
			ObjectOutputStream oos = new ObjectOutputStream(serverSocket.getOutputStream());
			while (true)
			{
				if (ois.read() != 0)
				{
					String searchCriteria = ois.readUTF();
					store.setSearchCriteria(searchCriteria);
					Object object = ois.readObject();
					if (object instanceof SearchType)
					{
						SearchType searchType = (SearchType) object;
						store.setSearchType(searchType);
					}

					oos.writeObject(store.getPerson());
				}
				ois.close();
				oos.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void start()
	{
		PersonStoreServerSocket personStoreServerSocket = new PersonStoreServerSocket();
	}

	public static void main(String[] args)
	{
		PersonStoreServerSocket.start();
	}
}
