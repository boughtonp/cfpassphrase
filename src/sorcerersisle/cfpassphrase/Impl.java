package sorcerersisle.cfpassphrase;

import mindrot.jbcrypt.BCrypt;
import crackstation.PBKDF2.PasswordHash;
import com.lambdaworks.crypto.SCryptUtil;
import java.util.Map;
import java.util.HashMap;


public final class Impl
{


	public static String hash
		( String             Passphrase
		, String             Algorithm
		, Map<String,Object> AlgorithmParams
		)
	throws Exception
	{

		switch ( Utils.Algorithm.fromString(Algorithm) )
		{

			case bcrypt:
				Integer Rounds = Utils.StructGetInt(AlgorithmParams,"Rounds",16);

				String Salt = ( Rounds == null )
					? BCrypt.gensalt()
					: BCrypt.gensalt(Rounds)
					;

				return BCrypt.hashpw
					( Passphrase
					, Salt
					);

			case pbkdf2:
				return PasswordHash.createHash
					( Passphrase
					, Utils.StructGetInt(AlgorithmParams,"Iterations",86000)
					, Utils.StructGetInt(AlgorithmParams,"SaltBytes",24)
					, Utils.StructGetInt(AlgorithmParams,"HashBytes",24)
					);

			case scrypt:
				return SCryptUtil.scrypt
					( Passphrase
					, Utils.StructGetInt(AlgorithmParams,"CpuCost",2^16)
					, Utils.StructGetInt(AlgorithmParams,"MemoryCost",8)
					, Utils.StructGetInt(AlgorithmParams,"Parallelization",1)
					);

			default:
				throw new Exception("Unsupported Algorithm");

		}
	}


	public static Boolean check
		( String Passphrase
		, String Hash
		, String Algorithm
		)
	throws Exception
	{
		switch
			( Algorithm == null
			? Utils.identifyAlgorithm(Hash)
			: Utils.Algorithm.fromString(Algorithm)
			)
		{

			case bcrypt:
				return BCrypt.checkpw
					( Passphrase
					, Hash
					);

			case pbkdf2:
				return PasswordHash.validatePassword
					( Passphrase
					, Hash
					);

			case scrypt:
				return SCryptUtil.check
					( Passphrase
					, Hash
					);

			default:
				throw new Exception("Unsupported Algorithm");

		}
	}


	public static Map<String,String> info
		( String Hash
		, String Algorithm
		)
	throws Exception
	{
		String[]           Parts;
		Map<String,String> Info = new HashMap<String,String>();

		switch
			( Algorithm == null
			? Utils.identifyAlgorithm(Hash)
			: Utils.Algorithm.fromString(Algorithm)
			)
		{

			case bcrypt:
				Parts = Hash.substring(1).split("\\$");

				Info.put("Algorithm"  , "BCrypt" );
				Info.put("Version"    , Parts[0] );

				if ( Parts[0].equals("2a") )
					Info.put("Status" , "Supported" );
				else if ( Parts[0].equals("2") )
					Info.put("Status" , "Obsolete" );
				else
					Info.put("Status" , "Unsupported" );

				Info.put("Rounds"     , Parts[1] );
				Info.put("Salt"       , Parts[2].substring(0,16) );
				Info.put("Hash"       , Parts[2].substring(16)   );

				return Info;

			case pbkdf2:
				Parts = Hash.split(":");

				Info.put("Algorithm"  , "PBKDF2" );
				Info.put("Status"     , "Supported" );

				Info.put("Iterations" , Parts[0] );
				Info.put("Salt"       , Parts[1] );
				Info.put("Hash"       , Parts[2] );

				return Info;

			case scrypt:
				Parts = Hash.substring(2).split("\\$");
				long Params = Long.parseLong(Parts[1],16);

				Info.put("Algorithm"       , "SCrypt" );
				Info.put("Version"         , Parts[0] );
				Info.put("Status"          , "Supported" );

				Info.put("CpuCost"         , String.valueOf(Math.round((Math.pow(2, Params >> 16 & 0xffff)))) );
				Info.put("MemoryCost"      , String.valueOf(Params >> 8 & 0xff) );
				Info.put("Parallelization" , String.valueOf(Params      & 0xff) );
				Info.put("Salt"            , Parts[2] );
				Info.put("Hash"            , Parts[3] );

				return Info;

			default:
				throw new Exception("Unsupported Algorithm");

		}
	}


}