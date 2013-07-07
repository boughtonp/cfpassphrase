// cfPassphrase v0.1-rc | (c) Peter Boughton | License: LGPLv3 | Website: sorcerersisle.com/projects:cfpassphrase.html
package sorcerersisle.cfpassphrase.coldfusion;

import sorcerersisle.cfpassphrase.*;


public final class PassphraseCheck
{


	public static Boolean call
		( String Passphrase , String Hash )
	throws Exception
	{ return call(Passphrase,Hash,null); }


	public static Boolean call
		( String      Passphrase
		, String      Hash
		, String      Algorithm
		)
	throws Exception
	{
		return Impl.check(Passphrase,Hash,Algorithm);
	}

}