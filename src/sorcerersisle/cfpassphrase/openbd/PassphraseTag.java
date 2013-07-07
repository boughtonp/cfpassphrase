// cfPassphrase v0.1-rc | (c) Peter Boughton | License: LGPLv3 | Website: sorcerersisle.com/projects:cfpassphrase.html
package sorcerersisle.cfpassphrase.openbd;

import com.naryx.tagfusion.cfm.tag.cfTag;
import com.naryx.tagfusion.cfm.tag.cfTagReturnType;
import com.naryx.tagfusion.cfm.engine.cfArgStructData;
import com.naryx.tagfusion.cfm.engine.cfSession;
import com.naryx.tagfusion.cfm.engine.cfData;
import com.naryx.tagfusion.cfm.engine.cfStructData;
import com.naryx.tagfusion.cfm.engine.cfmBadFileException;
import com.naryx.tagfusion.cfm.engine.cfmRunTimeException;

@SuppressWarnings("serial")
public final class PassphraseTag
	extends cfTag
{


	protected void defaultParameters
		( String _tag )
	throws cfmBadFileException
	{
		parseTagHeader( _tag );

		if ( ! containsAttribute("action") )
			throw newBadFileException( "You must specify the [Action] attribute. Accepted values are 'hash', 'check', 'info'." , "" );

		if ( ! containsAttribute("variable") )
			throw newBadFileException( "You must specify the [Variable] attribute." , "" );

		if ( ! containsAttribute("passphrase") )
			throw newBadFileException( "You must specify the [Passphrase] attribute." , "" );
	}


	public cfTagReturnType render
		( cfSession _session )
	throws cfmRunTimeException
	{
		String Action        = getDynamic(_session,"action").getString();
		String Variable      = getDynamic(_session, "variable").getString();
		cfArgStructData Args = new cfArgStructData();
		cfData Result;

		if (Action.equalsIgnoreCase("hash"))
		{
			Args.setData("passphrase",  getDynamic(_session, "passphrase").getString());
			if (containsAttribute("algorithm")) Args.setData("algorithm",  getDynamic(_session, "algorithm").getString());
			if (containsAttribute("algorithmparams")) Args.setData("algorithmparams",  (cfStructData)getDynamic(_session, "algorithmparams"));

			Result = new PassphraseHash().execute(_session,Args);
		}
		else if (Action.equalsIgnoreCase("check"))
		{
			Args.setData("passphrase" , getDynamic(_session, "passphrase").getString());
			Args.setData("hash"       , getDynamic(_session, "hash").getString());
			if (containsAttribute("algorithm")) Args.setData("algorithm",  getDynamic(_session, "algorithm").getString());

			Result = new PassphraseCheck().execute(_session,Args);
		}
		else if (Action.equalsIgnoreCase("info"))
		{
			Args.setData("hash"       , getDynamic(_session, "hash").getString());
			if (containsAttribute("algorithm")) Args.setData("algorithm",  getDynamic(_session, "algorithm").getString());

			Result = new PassphraseCheck().execute(_session,Args);
		}
		else
		{
			throw new cfmRunTimeException
				( _session
				, new Exception("Invalid value ["+Action+"] for [Action] attribute. Accepted values are 'hash', 'check', 'info'.")
				);
		}

		_session.setData( Variable , Result );

		return cfTagReturnType.NORMAL;
	}


}