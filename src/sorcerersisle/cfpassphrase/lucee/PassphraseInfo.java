// cfPassphrase v0.1 | (c) Peter Boughton | License: LGPLv3 | Website: https://www.sorcerersisle.com/software/cfpassphrase
package sorcerersisle.cfpassphrase.lucee;

import sorcerersisle.cfpassphrase.*;
import lucee.runtime.ext.function.Function;
import lucee.runtime.PageContext;
import lucee.runtime.exp.PageException;
import lucee.runtime.type.Struct;
import lucee.runtime.util.Cast;
import lucee.loader.engine.CFMLEngineFactory;


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