// cfPassphrase v0.2-rc | (c) Peter Boughton | License: LGPLv3 | Website: https://www.sorcerersisle.com/software/cfpassphrase
package sorcerersisle.cfpassphrase.railo;

import sorcerersisle.cfpassphrase.*;
import railo.runtime.ext.function.Function;
import railo.runtime.PageContext;
import railo.runtime.exp.PageException;
import railo.loader.engine.CFMLEngineFactory;


@SuppressWarnings("serial")
public final class PassphraseCheck
	implements Function
{


	public static Boolean call
		( PageContext pc , String Passphrase , String Hash )
	throws PageException
	{ return call(pc,Passphrase,Hash,null); }


	public static Boolean call
		( PageContext pc
		, String      Passphrase
		, String      Hash
		, String      Algorithm
		)
	throws PageException
	{
		try
		{
			return Impl.check( Passphrase , Hash , Algorithm );
		}
		catch(Exception Ex)
		{
			throw CFMLEngineFactory.getInstance().getCastUtil().toPageException( Ex );
		}
	}

}