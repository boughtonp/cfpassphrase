// cfPassphrase v0.1 | (c) Peter Boughton | License: LGPLv3 | Website: https://www.sorcerersisle.com/software/cfpassphrase
package sorcerersisle.cfpassphrase.openbd;

import sorcerersisle.cfpassphrase.*;
import com.naryx.tagfusion.expression.function.functionBase;
import com.naryx.tagfusion.cfm.engine.cfArgStructData;
import com.naryx.tagfusion.cfm.engine.cfData;
import com.naryx.tagfusion.cfm.engine.cfSession;
import com.naryx.tagfusion.cfm.engine.cfStructData;
import com.naryx.tagfusion.cfm.engine.cfmRunTimeException;


@SuppressWarnings("serial")
public final class PassphraseInfo
	extends functionBase
{

	public PassphraseInfo()
	{
		min = 1;
		max = 2;
		setNamedParams( new String[] { "Hash" , "Algorithm" } );
	}


	public cfData execute
		( cfSession _session
		, cfArgStructData ArgStruct
		)
	throws cfmRunTimeException
	{
		try
		{
			String Hash       = getNamedStringParam( ArgStruct , "Hash"       , null );
			String Algorithm  = getNamedStringParam( ArgStruct , "Algorithm"  , null );

			cfStructData Result = new cfStructData();
			Result.putAll( Impl.info(Hash,Algorithm) );

			return Result;
		}
		catch(Exception Ex)
		{
			throw new cfmRunTimeException(_session,Ex);
		}
	}

}