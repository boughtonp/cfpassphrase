// cfPassphrase v0.2-rc | (c) Peter Boughton | License: LGPLv3 | Website: https://www.sorcerersisle.com/software/cfpassphrase
package sorcerersisle.cfpassphrase.railo;

import sorcerersisle.cfpassphrase.*;
import railo.runtime.ext.function.Function;
import railo.runtime.PageContext;
import railo.runtime.exp.PageException;
import railo.loader.engine.CFMLEngineFactory;
import railo.runtime.type.Struct;
import railo.runtime.util.Cast;


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