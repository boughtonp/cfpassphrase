package sorcerersisle.cfpassphrase;

import java.util.Map;

public final class Utils
{

	public static enum Algorithm
	{ bcrypt , pbkdf2 , scrypt
	; public static Algorithm fromString(String Str)
		{
			if ( Str == null ) return DefaultAlgorithm;
			try { return valueOf(Str.toLowerCase());}
			catch (Exception ex){return null;}
		}
	}

	static Algorithm DefaultAlgorithm = Algorithm.bcrypt;


	static Algorithm identifyAlgorithm
		( String Hash )
	throws Exception
	{
		if ( Hash.matches("^\\$2a?\\$\\d+\\$[0-9A-Za-z./]+$") )
			return Algorithm.bcrypt;

		else if ( Hash.matches("^\\d+:[0-9a-f]+:[0-9a-f]+$") )
			return Algorithm.pbkdf2;

		else if ( Hash.matches("^\\$s0\\$[0-9a-z]+(?:\\$[0-9A-Za-z+=/]+){2}$") )
			return Algorithm.scrypt;

		else
			throw new Exception("Unknown Algorithm Signature");
	}


	static Integer StructGetInt
		( Map<String,Object> Struct
		, String             KeyName
		, Integer            Default
		)
	throws Exception
	{
		if ( Struct == null || ! Struct.containsKey(KeyName) )
			return Default;

		Object Value = Struct.get(KeyName);

		if ( Value instanceof Integer )
			return (Integer) Value;
		else if ( Value instanceof Double )
			return (int) Math.round( (Double)Value );
		else
			return Integer.valueOf( (String)Value );
	}


}