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