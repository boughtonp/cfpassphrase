// cfPassphrase v0.1 | (c) Peter Boughton | License: LGPLv3 | Website: sorcerersisle.com/projects:cfpassphrase.html
package sorcerersisle.cfpassphrase.openbd;

import sorcerersisle.cfpassphrase.*;
import com.naryx.tagfusion.expression.function.functionBase;
import com.naryx.tagfusion.cfm.engine.cfArgStructData;
import com.naryx.tagfusion.cfm.engine.cfBooleanData;
import com.naryx.tagfusion.cfm.engine.cfSession;
import com.naryx.tagfusion.cfm.engine.cfmRunTimeException;

@SuppressWarnings("serial")
public final class PassphraseCheck
	extends functionBase
{

	public PassphraseCheck()
	{
		min = 2;
		max = 3;
		setNamedParams( new String[] { "Passphrase" , "Hash" , "Algorithm" } );
	}


	public cfBooleanData execute
		( cfSession _session
		, cfArgStructData ArgStruct
		)
	throws cfmRunTimeException
	{
		try
		{
			String Passphrase = getNamedStringParam( ArgStruct , "Passphrase" , null );
			String Hash       = getNamedStringParam( ArgStruct , "Hash"       , null );
			String Algorithm  = getNamedStringParam( ArgStruct , "Algorithm"  , null );

			return cfBooleanData.getcfBooleanData
				( Impl.check
					( Passphrase , Hash , Algorithm )
				);
		}
		catch(Exception Ex)
		{
			throw new cfmRunTimeException(_session,Ex);
		}
	}

}