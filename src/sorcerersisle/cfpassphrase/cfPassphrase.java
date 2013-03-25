package sorcerersisle.cfpassphrase;

import java.io.IOException;
import javax.swing.JOptionPane;


public final class cfPassphrase
{

	public static void main
		( String[] Args )
	{
		String Msg =
			  "cfPassphrase v0.0.000\n"
			+ "\n"
			+ "Project Info: http://sorcerersisle.com/projects:cfpassphrase.html\n"
			+ "Install Docs: https://github.com/boughtonp/cfpassphrase/wiki/Installation\n"
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