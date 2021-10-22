// cfPassphrase v0.2 | (c) Peter Boughton | License: LGPLv3 | Website: https://www.sorcerersisle.com/software/cfpassphrase
package sorcerersisle.cfpassphrase.openbd;

import sorcerersisle.cfpassphrase.*;
import com.naryx.tagfusion.expression.function.functionBase;
import com.naryx.tagfusion.cfm.engine.cfSession;
import com.naryx.tagfusion.cfm.engine.cfArgStructData;
import com.naryx.tagfusion.cfm.engine.cfStringData;
import com.naryx.tagfusion.cfm.engine.cfStructData;
import com.naryx.tagfusion.cfm.engine.cfmRunTimeException;

@SuppressWarnings("serial")
public final class PassphraseHash
	extends functionBase
{

	public PassphraseHash()
	{
		min = 1;
		max = 3;
		setNamedParams( new String[] { "Passphrase" , "Algorithm" , "AlgorithmParams" } );
	}


	@SuppressWarnings("unchecked")
	public cfStringData execute
		( cfSession _session
		, cfArgStructData ArgStruct
		)
	throws cfmRunTimeException
	{
		try
		{
			String Passphrase = getNamedStringParam( ArgStruct , "Passphrase" , null );
			String Algorithm  = getNamedStringParam( ArgStruct , "Algorithm"  , null );
			cfStructData AlgorithmParams = (cfStructData) getNamedParam( ArgStruct , "AlgorithmParams" , null );

			return new cfStringData
				( Impl.hash
					( Passphrase
					, Algorithm
					, AlgorithmParams
					)
				);

		}
		catch(Exception Ex)
		{
			throw new cfmRunTimeException(_session,Ex);
		}
	}

}