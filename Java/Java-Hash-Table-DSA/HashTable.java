
public class HashTable {

	private Object[] HashArray;
	private int Size;
	private Object deleted;

	HashTable(int length)
	{
		Size = length * 2;
		try
		{
			HashArray = new Object[getPrime(Size)];
		}
		catch(NegativeArraySizeException e)
		{
			System.out.println("Too large of a number for memory storage/application.");
		}
		deleted = new Object("-1");
	}
	public void insert(Object object)
	{
		int index = Hash(object.getKeyString());

		//searches for an empty space or a deleted space for an input
		while(HashArray[index] != null)
		{
			//If its equal to -1, then replace the -1
			try{
				if(HashArray[index].getKeyString().compareTo("-1") == 0)
				{
					break;
				}
			}
			catch(NullPointerException e){}

			//after comparison so 1st index that needs checked gets checked
			if(index == HashArray.length-1)
			{
				index = -1;
			}
			index++;

		}

		HashArray[index] = new Object(object.getKeyString());
	}

	public int Search(String keySearch)
	{
		int key = Hash(keySearch);
		int keyStatic = key;
		int count = 0; 
		try{
			if(getHashArrayKey(getPrime(Size)-1).equals(keySearch))
			{
				return getPrime(Size)-1;
			}

			if(getHashArrayKey(0).equals(keySearch))
			{
				return 0;
			}
		}
		catch(NullPointerException e)
		{

		}

		try{
			while(!getHashArrayKey(key).equals(keySearch))
			{				

				if(count == getPrime(Size))
				{
					return -1;
				}
				if(HashArray[key] == null)
				{
					return -1;
				}
				if(key == HashArray.length-1)
				{
					key = -1;
				}
				count++;
				key++;
			}
		}
		catch(NullPointerException e)
		{
			return -1;
		}
		return key;
	}
	public boolean delete(String key)
	{	
		//does not have to search multiple times since it is an int
		int SearchResult = Search(key);

		if (SearchResult == -1)
		{
			return false;
		}
		else
		{
			HashArray[SearchResult] = deleted;
			return true;
		}
	}

	//hash function
	public int Hash(String key)
	{
		String keyString = key + "";
		int Code = keyString.hashCode();
		String CodeString = Code + "";
		String SizeString = getPrime(Size) + "";


		int Folds = CountZeroes(SizeString);

		//tracks when loop goes around amount of times to get information from the string
		int countFold = 0;
		int KeyAdder = 0;
		String Numbers = "";

		int y = 0;
		boolean forloop = true;
		while (y < CodeString.length() && forloop)
		{
			for(int x = 0; x < CodeString.length(); x++, y++)
			{
				countFold++;
				Numbers += CodeString.substring(x);

				if (countFold == Folds)
				{
					try
					{
						KeyAdder += Integer.valueOf(Numbers);
					}
					catch(NumberFormatException e)
					{
						forloop = false;
					}

					countFold = 0;
					Numbers = "";
				}
			}
		}
		try
		{
			if (Numbers != "")
			{
				KeyAdder += Integer.valueOf(Numbers); 
			}
		}
		catch(NumberFormatException e)
		{
		}
		int index = KeyAdder%getPrime(Size);
		return index;
	}//hash end


	public int CountZeroes(String Size)
	{
		int counter = 0;

		for (int x = 0; x < Size.length(); x++)
		{
			counter++;
		}

		return counter-1;
	}

	private int getPrime(int min)
	{
		for(int j = min+1; true; j++)
		{
			if (isPrime(j))
			{
				return j;
			}
		}
	}

	private boolean isPrime(int n)
	{
		for(int j = 2; (j*j <= n); j++)
		{
			if(n%j == 0)
			{
				return false;
			}
		}
		return true;
	}
	public boolean isFull()
	{
		for(int x = 0; x < HashArray.length; x++)
		{
			if (HashArray[x] == null)
			{
				return false;
			}
			if(getHashArrayKey(x).equals("-1"))
			{
				return false;
			}
		}
		return true;
	}
	public boolean isEmpty()
	{
		for(int x = 0; x < HashArray.length; x++)
		{
			if (HashArray[x] != null)
			{ 
				if (!getHashArrayKey(x).equals("-1"))
				{
					return false;
				}
			}
		}
		return true;
	}
	public int getPrimeSize()
	{
		return getPrime(Size);
	}
	public boolean NullChecker(int index){
		try
		{
			if(getHashArrayKey(index) == null)
			{
				return true;
			}
		}
		catch(NullPointerException e){return true;}
		
		return false;
	}

	public String getHashArrayKey(int index) {
		return HashArray[index].getKeyString();
	}

}//end hashtable
