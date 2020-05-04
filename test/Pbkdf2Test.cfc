<cfcomponent extends="TestBase">
<cfscript>

	function init( IncludeSlowTests )
	{
		super.init( argumentcollection=arguments );

		this.Name = 'pbkdf2';

		super.start();

		this.test_info();

		if ( IncludeSlowTests )
			this.test_basic();
		else
			skip( 30 );

		return super.end();
	}


	function test_info( TestData )
	{
		var info2 = PassphraseInfo('180:5fabd8b160f5f9225ec5569ce2f02d5a2e29a29e0b280614:d11a602ecc7830280b25cdd29b539e9e0f8438a0a43e6637');
		assertEqual(info2.Algorithm,'PBKDF2');
		assertEqual(info2.Status,'Supported');
		assertEqual(info2.Iterations,180);
		assertEqual(info2.Salt,'5fabd8b160f5f9225ec5569ce2f02d5a2e29a29e0b280614');
		assertEqual(info2.Hash,'d11a602ecc7830280b25cdd29b539e9e0f8438a0a43e6637');
		assertEqual(StructCount(info2),5);
	}


	function test_basic()
	{
		for ( var i=0 ; i<10 ; ++i )
		{
			var password = ""&i;
			var hash = PassphraseHash(password,'pbkdf2');
			var secondHash = PassphraseHash(password,'pbkdf2');

			assertNotEqual( hash , secondHash , "Two hashes are equal." );

			var wrongPassword = ""&(i+1);
			assertFalse( PassphraseCheck(wrongPassword,hash) , "Wrong password accepted." );

			assertTrue( PassphraseCheck(password,hash) , "Good password not accepted." );
		}
	}


</cfscript>
</cfcomponent>