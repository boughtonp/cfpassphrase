// cfPassphrase v0.2-rc | (c) Peter Boughton | License: LGPLv3 | Website: https://www.sorcerersisle.com/software/cfpassphrase
package sorcerersisle.cfpassphrase.railo;

import sorcerersisle.cfpassphrase.*;
import railo.runtime.ext.function.Function;
import railo.runtime.PageContext;
import railo.runtime.exp.PageException;
import railo.runtime.type.Struct;
import railo.runtime.util.Cast;
import railo.loader.engine.CFMLEngineFactory;


@SuppressWarnings("serial")
public final class PassphraseInfo
	implements Function
{


	public static Struct call
		( PageContext pc , String Passphrase )
	throws PageException
	{ return call(pc,Passphrase,null); }


	public static Struct call
		( PageContext pc
		, String      Hash
		, String      Algorithm
		)
	throws PageException
	{
		Cast Caster = CFMLEngineFactory.getInstance().getCastUtil();

		try
		{
			return Caster.toStruct( Impl.info(Hash,Algorithm) );
		}
		catch(Exception Ex)
		{
			throw Caster.toPageException( Ex );
		}
	}

}