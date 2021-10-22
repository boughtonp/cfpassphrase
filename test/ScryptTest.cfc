<cfcomponent extends="TestBase">
<cfscript>

	function init( IncludeSlowTests )
	{
		super.init( argumentcollection=arguments );

		this.Name = 'scrypt';

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
		var info3 = PassphraseInfo('$s0$501ff$5JQALASFiKGKdfY9Z0GYMA==$8c4aMGktdRlLIeY+erIA62fNtgb2OxJrjyhw+XeWHk4=');
		assertEqual(info3.Algorithm,'SCrypt');
		assertEqual(info3.Status,'Supported');
		assertEqual(info3.Version,'0');
		assertEqual(info3.CpuCost,32);
		assertEqual(info3.MemoryCost,1);
		assertEqual(info3.Parallelization,255);
		assertEqual(info3.Salt,'5JQALASFiKGKdfY9Z0GYMA==');
		assertEqual(info3.Hash,'8c4aMGktdRlLIeY+erIA62fNtgb2OxJrjyhw+XeWHk4=');
		assertEqual(StructCount(info3),8);
	}


	function test_basic()
	{
		var Passwd = "secret";
		var N = 16384;
		var r = 8;
		var p = 1;

		var hashed = PassphraseHash(Passwd,'scrypt',{CpuCost:N,MemoryCost:r,Parallelization:p});

		var parts = hashed.split('\$');

		assertEqual(len(parts),5);
		assertEqual(parts[1],"");
		assertEqual(parts[2],"s0");
		assertEqual(len(BinaryDecode(parts[4],'base64')),16);
		assertEqual(len(BinaryDecode(parts[5],'base64')),32);

		var params = InputBaseN(parts[3],16);
		assertEqual( N , 2^( BitAnd(BitShRn(params,16),65535)) );
		assertEqual( r , BitAnd(BitShRn(params,8),255) );
		assertEqual( p , BitAnd(BitShRn(params,0),255) );

		assertTrue(PassphraseCheck(passwd,hashed));
		assertFalse(PassphraseCheck("s3cr3t",hashed));
	}


</cfscript>
</cfcomponent>