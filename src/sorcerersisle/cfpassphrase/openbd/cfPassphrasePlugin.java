// cfPassphrase v0.1 | (c) Peter Boughton | License: LGPLv3 | Website: https://www.sorcerersisle.com/software/cfpassphrase
package sorcerersisle.cfpassphrase.openbd;

import com.bluedragon.plugin.Plugin;
import com.bluedragon.plugin.PluginManagerInterface;
import com.naryx.tagfusion.xmlConfig.xmlCFML;

public final class cfPassphrasePlugin implements Plugin
{

	public cfPassphrasePlugin()
	{

	}

	public String getPluginName()
	{
		return "cfPassphrase";
	}

	public String getPluginVersion()
	{
		return "0.0.000";
	}

	public String getPluginDescription()
	{
		return "Provides functions PassphraseHash, PassphraseCheck and PassphraseInfo to OpenBD.";
	}

	public void pluginStart( PluginManagerInterface Manager, xmlCFML SystemParameters )
	{
		Manager.registerFunction("PassphraseHash" ,"sorcerersisle.cfpassphrase.openbd.PassphraseHash");
		Manager.registerFunction("PassphraseCheck","sorcerersisle.cfpassphrase.openbd.PassphraseCheck");
		Manager.registerFunction("PassphraseInfo","sorcerersisle.cfpassphrase.openbd.PassphraseInfo");
		Manager.registerTag("cfpassphrase","sorcerersisle.cfpassphrase.openbd.PassphraseTag");
	}

	public void pluginStop( PluginManagerInterface Manager )
	{
	}

}