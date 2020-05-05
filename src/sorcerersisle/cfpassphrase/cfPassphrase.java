// cfPassphrase v0.2-rc | (c) Peter Boughton | License: LGPLv3 | Website: https://www.sorcerersisle.com/software/cfpassphrase
package sorcerersisle.cfpassphrase;

import java.io.IOException;
import javax.swing.JOptionPane;


public final class cfPassphrase
{

	public static void main
		( String[] Args )
	{
		String Msg =
			  "cfPassphrase v0.2-rc\n"
			+ "\n"
			+ "Project Info: https://www.sorcerersisle.com/software/cfpassphrase\n"
			+ "Install Docs: https://docs.sorcerersisle.com/cfpassphrase/Installation\n"
			;


		if (isJavaw() )
			JOptionPane.showMessageDialog(null,Msg);
		else
			System.out.println(Msg);
	}


	private static boolean isJavaw()
	{
		try
		{
			System.in.available();
			return false;
		}
		catch (IOException e)
		{
			return true;
		}
	}

}