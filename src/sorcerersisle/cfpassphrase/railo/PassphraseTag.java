// cfPassphrase v0.2 | (c) Peter Boughton | License: LGPLv3 | Website: https://www.sorcerersisle.com/software/cfpassphrase
package sorcerersisle.cfpassphrase.railo;

import railo.runtime.ext.tag.TagSupport;
import railo.runtime.ext.tag.DynamicAttributes;
import railo.runtime.type.Collection.Key;
import railo.runtime.type.Struct;
import railo.runtime.exp.PageException;
import railo.loader.engine.CFMLEngineFactory;

@SuppressWarnings("deprecation")
public final class PassphraseTag
	extends TagSupport
	implements DynamicAttributes
{


	private Struct Attributes = CFMLEngineFactory.getInstance().getCreationUtil().createStruct();


	public int doStartTag()
	throws PageException
	{
		String Action          = (String)Attributes.get("action",null);
		String Variable        = (String)Attributes.get("variable",null);
		String Passphrase      = (String)Attributes.get("passphrase",null);
		String Algorithm       = (String)Attributes.get("algorithm",null);
		Object Result;

		if (Action.equalsIgnoreCase("hash"))
		{
			Struct AlgorithmParams = Attributes.containsKey("algorithmparams") ? (Struct)Attributes.get("algorithmparams") : null;

			Result = PassphraseHash.call
				( pageContext
				, Passphrase
				, Algorithm
				, AlgorithmParams
				);
		}
		else if (Action.equalsIgnoreCase("check"))
		{
			String Hash = (String)Attributes.get("hash",null);

			Result = PassphraseCheck.call
				( pageContext
				, Passphrase
				, Hash
				, Algorithm
				);
		}
		else if (Action.equalsIgnoreCase("info"))
		{
			String Hash = (String)Attributes.get("hash",null);

			Result = PassphraseCheck.call
				( pageContext
				, Hash
				, Algorithm
				);
		}
		else
		{
			throw CFMLEngineFactory.getInstance().getCastUtil().toPageException
				( new Exception("Invalid value for [Action] attribute. Accepted values are 'hash','check', or [info].")
				);
		}

		pageContext.setVariable( Variable , Result );


		return SKIP_BODY;
	}


	public void setDynamicAttribute(String uri, String localName, Object value)
	{
		Attributes.setEL(localName,value);
	}

	public void setDynamicAttribute(String uri, Key localName, Object value)
	{
		Attributes.setEL(localName,value);
	}


	public void release()
	{
		super.release();
		Attributes.clear();
	}

}