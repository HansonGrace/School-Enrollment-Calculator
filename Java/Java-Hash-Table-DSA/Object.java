public class Object {
	private String key;

	public Object()
	{
		//null by default
		key = null;
	}
	
	public Object(String keyNum)
	{
		key = keyNum + "";
	}
	
	public String getKeyString()
	{
		return key;
	}	
}
