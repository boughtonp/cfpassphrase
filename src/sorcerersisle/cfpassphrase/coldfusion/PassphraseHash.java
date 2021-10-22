// cfPassphrase v0.2 | (c) Peter Boughton | License: LGPLv3 | Website: https://www.sorcerersisle.com/software/cfpassphrase
package sorcerersisle.cfpassphrase.coldfusion;

import sorcerersisle.cfpassphrase.*;
import coldfusion.runtime.Struct;
import java.util.Map;


public final class PassphraseHash
{


	public static String call
		( String Passphrase )
	throws Exception
	{ return call(Passphrase,null,null); }

	public static String call
		( String Passphrase , String Algorithm )
	throws Exception
	{ return call(Passphrase,Algorithm,null); }


	@SuppressWarnings("unchecked")
	public static String call
		( String Passphrase
		, String Algorithm
		, Struct AlgorithmParams
		)
	throws Exception
	{
		return Impl.hash
			( Passphrase
			, Algorithm
			, (Map<String,Object>) AlgorithmParams
			);
	}


}