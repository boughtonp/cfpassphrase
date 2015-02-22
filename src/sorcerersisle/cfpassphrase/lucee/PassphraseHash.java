// cfPassphrase v0.1-rc | (c) Peter Boughton | License: LGPLv3 | Website: sorcerersisle.com/projects:cfpassphrase.html
package sorcerersisle.cfpassphrase.lucee;

import sorcerersisle.cfpassphrase.*;
import lucee.runtime.ext.function.Function;
import lucee.runtime.PageContext;
import lucee.runtime.exp.PageException;
import lucee.loader.engine.CFMLEngineFactory;
import lucee.runtime.type.Struct;
import lucee.runtime.util.Cast;


@SuppressWarnings("serial")
public final class PassphraseHash
	implements Function
{


	public static String call
		( PageContext pc , String Passphrase )
	throws PageException
	{ return call(pc,Passphrase,null,null); }

	public static String call
		( PageContext pc , String Passphrase , String Algorithm )
	throws PageException
	{ return call(pc,Passphrase,Algorithm,null); }


	@SuppressWarnings("unchecked")
	public static String call
		( PageContext pc
		, String      Passphrase
		, String      Algorithm
		, Struct      AlgorithmParams
		)
	throws PageException
	{
		Cast Caster = CFMLEngineFactory.getInstance().getCastUtil();

		try
		{
			return Impl.hash
				( Passphrase
				, Algorithm
				, Caster.toMap(AlgorithmParams,null)
				);
		}
		catch(Exception Ex)
		{
			throw Caster.toPageException( Ex );
		}

	}


}