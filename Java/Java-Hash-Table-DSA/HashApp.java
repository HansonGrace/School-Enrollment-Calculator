import java.util.InputMismatchException;
import java.util.Scanner;

public class HashApp {

	public static void main (String [] args)
	{
		int length = 0;
		boolean lengthCheck = true;

		System.out.println("HashTable Application\n\n"
				+ "Enter a length: ");

		int lengthZero = 0;
		//initial input for length
		while(lengthCheck)
		{		
			Scanner input = new Scanner (System.in);
			//prevents looping infinite times
			try
			{
				length = input.nextInt();
				lengthZero = length;

				if(lengthZero > 0)
				{
					lengthCheck = false;
				}
				else
				{
					System.out.println("Length must be larger than 0."
							+ "\n\nEnter: ");
				}
			}
			catch(InputMismatchException e)
			{
				System.out.println("Either you must use a number for length, or length is too large."
						+ "\n\nEnter a length: ");
			}

		}//end function

		HashTable table = new HashTable(length);

		Menu(); Enter();
		boolean sentinel = true;

		while(sentinel)
		{
			int selection = -1;
			Scanner loopInput = new Scanner (System.in);

			try{
				selection = loopInput.nextInt();}
			catch(InputMismatchException e)
			{}


			switch (selection)
			{
			case 0: Menu(); Enter(); break;
			//case, assuming not full
			case 1:
				if(table.isFull()){System.out.println("HashTable is full. Can not insert."); 
				Enter(); break;}
				System.out.println("Enter key to insert: ");
				String key = "";
				try
				{
					key = loopInput.next();
					if (duplicate(key, table))
					{
						System.out.println("Duplicate key found. Re-enter:");
						Enter();
						break;
					}

				}
				catch(InputMismatchException e){System.out.println("Key must be a number(s) or letter(s) only."
						+ "\nOr it is too big for this application. \nRe-enter.");
				try
				{
					int keyInt = Integer.valueOf(key);
					if(keyInt < 0)
					{
						System.out.println("Key can not be negative."); Enter();
						break;
					}
				}
				catch(Exception b){}

				Enter(); break;}

				String keyString = key + "";

				table.insert(new Object(keyString));
				System.out.println("Key inserted.");

				Enter(); break;

				//search case
			case 2: 
				if(table.isEmpty()){System.out.println("Nothing to search. HashTable is empty."); 
				Enter(); break;}
				System.out.println("Enter key to search: ");
				String key2;
				try
				{
					key2 = loopInput.next();
				}
				catch(InputMismatchException e){System.out.println("Key must be a number.");
				Enter(); break;}

				try
				{
					int key2Integer = Integer.valueOf(key2);
					if(key2Integer < 0)
					{
						System.out.println("Key can not be negative."); Enter();
						break;
					}
				}
				catch(Exception e){} //Assuming specifying not needed

				try{
					System.out.println("The key is in index: " + table.Search(key2));
					System.out.println("If the result is -1, it was not found.");

				}
				catch(InputMismatchException e){
					System.out.println("Key must be a number.");
				}

				Enter(); break;

				//delete case
			case 3: 
				if(table.isEmpty()){System.out.println("Nothing to delete. HashTable is empty."); 
				Enter(); break;}
				System.out.println("Enter key to delete: ");
				String key3 = loopInput.next();

				try
				{
					int key3int = Integer.valueOf(key3);
					if(key3int < 0)
					{
						System.out.println("Key can not be negative."); Enter(); break;
					}
				}
				catch(Exception e){}

				try{
					if (table.delete(key3) == false)
					{
						System.out.println("No need to delete. Key not found in HashTable.");
					}
					else
					{
						System.out.println("Key successfully deleted.");	
					}
				}
				catch(InputMismatchException e){System.out.println("Key must be a number."); 
				Enter(); break;}


				Enter(); break;

			case 4: displayTable(table);
			System.out.println(); Enter(); break;

			//successful quit case
			case 5: System.out.println("Quit successful."); 
			sentinel = false; break;

			default: System.out.println("Enter only a number for menu options."); 
			Enter(); break;

			}//end case switch and end loop
		}
	} 
	public static void Menu()
	{
		System.out.println("\nMenu"
				+ "\n0)Display Menu \n1)Insert \n2)Search "
				+ "\n3)Delete \n4)Display \n5)Quit");
	}

	public static void Enter()
	{
		System.out.println("-----------------\nEnter: ");
	}

	//checks for letter
	public static boolean ValidNumber(String length)
	{
		if (length.matches(".*[a-zA-Z].*")){ 
			System.out.println("Name must contain only numbers"
					+ "\n\nEnter: "); 
			return false;
		} 
		return true;
	}

	//duplicate ID checker
	public static boolean duplicate(String key, HashTable table)
	{	

		for(int x = 0; x < table.getPrimeSize(); x++)
		{
			try
			{
				if (table.getHashArrayKey(x).compareTo(key)==0)
				{ 
					return true;
				}
			}
			catch(Exception e){}

		}
		return false;
	}
	public static void displayTable(HashTable table)
	{
		System.out.println("Table: ");

		for(int x = 0; x < table.getPrimeSize(); x++)
		{
			if(!table.NullChecker(x))
			{
				if(!table.getHashArrayKey(x).equals("-1"))
				{
					System.out.println("index["+x + "]: "+ table.getHashArrayKey(x) + "");
				}
				else{System.out.println("index["+x + "]: **");}
			}
			else
			{
				System.out.println("index["+x + "]: **");
			}
		}//end loop
		System.out.println("\nNOTE: ** means either empty or deleted.");

	}//end table
}
