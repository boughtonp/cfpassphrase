// cfPassphrase v0.1 | (c) Peter Boughton | License: LGPLv3 | Website: https://www.sorcerersisle.com/software/cfpassphrase
package sorcerersisle.cfpassphrase.lucee;

import sorcerersisle.cfpassphrase.*;
import lucee.runtime.ext.function.Function;
import lucee.runtime.PageContext;
import lucee.runtime.exp.PageException;
import lucee.loader.engine.CFMLEngineFactory;


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