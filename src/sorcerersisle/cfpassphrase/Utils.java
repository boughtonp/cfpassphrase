// cfPassphrase v0.1-rc | (c) Peter Boughton | License: LGPLv3 | Website: sorcerersisle.com/projects:cfpassphrase.html
package sorcerersisle.cfpassphrase;

import java.util.Map;

public final class Utils
{

	public static enum Algorithm
	{ bcrypt , pbkdf2 , scrypt
	// Unimplemented algorithms:
	, unix_crypt_md5    // $1$
	, unix_crypt_nthash // $3$
	, unix_crypt_sha256 // $5$
	, unix_crypt_sha512 // $6$
	, sun_crypt_md5     // $md5
	//
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
		if ( Hash.matches("^\\$2[axy]?\\$\\d+\\$[0-9A-Za-z./]+$") )
			return Algorithm.bcrypt;

		else if ( Hash.matches("^\\d+:[0-9a-f]+:[0-9a-f]+$") )
			return Algorithm.pbkdf2;

		else if ( Hash.matches("^\\$s0\\$[0-9a-z]+(?:\\$[0-9A-Za-z+=/]+){2}$") )
			return Algorithm.scrypt;

		else if ( Hash.matches("^\\$1\\$[0-9A-Za-z./]{8}\\$[0-9A-Za-z./]{22}$") )
			return Algorithm.unix_crypt_md5;

		else if ( Hash.matches("^\\$3\\$\\$[0-9A-Fa-f]{32}$") )
			return Algorithm.unix_crypt_nthash;

		else if ( Hash.matches("^\\$5\\$(?:rounds=\\d{1,9}\\$)?[0-9A-Za-z./]{16}\\$[0-9A-Za-z./]{43}$") )
			return Algorithm.unix_crypt_sha256;

		else if ( Hash.matches("^\\$6\\$(?:rounds=\\d{1,9}\\$)?[0-9A-Za-z./]{16}\\$[0-9A-Za-z./]{86}$") )
			return Algorithm.unix_crypt_sha512;

		else if ( Hash.matches("^\\$md5(?:[$,]rounds=\\d+)?\\$[./0-9A-Za-z]+\\$[./0-9A-Za-z]+$") )
			return Algorithm.sun_crypt_md5;

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