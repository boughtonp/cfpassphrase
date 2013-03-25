package sorcerersisle.cfpassphrase.coldfusion;

import sorcerersisle.cfpassphrase.*;
import coldfusion.runtime.Struct;


public final class PassphraseInfo
{


	public static Struct call
		( String Passphrase )
	throws Throwable
	{ return call(Passphrase,null); }


	public static Struct call
		( String Hash
		, String Algorithm
		)
	throws Throwable
	{
		Struct Result = new Struct();

		Result.putAll(Impl.info(Hash,Algorithm));

		return Result;
	}

}