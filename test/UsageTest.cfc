<cfcomponent extends="TestBase">
<cfscript>

	function init()
	{
		super.init( argumentcollection=arguments );

		this.Name = 'usage';

		super.start();

		this.test_info();
		this.test_hash();
		this.test_check();

		return super.end();
	}


	function test_info()
	{
		try
		{
			var info = PassphraseInfo('$8$unknown-hash');
			assertFalse(1,"Expected exception to be thrown");
		}
		catch( any e )
		{
			assertEqual(e.Message,"Unknown Algorithm Signature");
		}

		var info = PassphraseInfo('$2a$10$9zXu55aPNya8ek17DwCXZ.X6kExa5cK5bpGmyTBrqD1dg76rkWz4y');
		assertEqual(info.Algorithm,'BCrypt');
		assertEqual(info.Status,'Supported');

		var info = PassphraseInfo('180:5fabd8b160f5f9225ec5569ce2f02d5a2e29a29e0b280614:d11a602ecc7830280b25cdd29b539e9e0f8438a0a43e6637');
		assertEqual(info.Algorithm,'PBKDF2');
		assertEqual(info.Status,'Supported');

		var info = PassphraseInfo('$s0$501ff$5JQALASFiKGKdfY9Z0GYMA==$8c4aMGktdRlLIeY+erIA62fNtgb2OxJrjyhw+XeWHk4=');
		assertEqual(info.Algorithm,'SCrypt');
		assertEqual(info.Status,'Supported');

		var info = PassphraseInfo('$1$etNnh7FA$OlM7eljE/B7F1J4XYNnk81');
		assertEqual(info.Algorithm,'md5crypt');
		assertEqual(info.Status,'Obsolete');
		assertEqual(info.Salt,'etNnh7FA');
		assertEqual(info.Hash,'OlM7eljE/B7F1J4XYNnk81');

		var info = PassphraseInfo('$3$$8846f7eaee8fb117ad06bdd830b7586c');
		assertEqual(info.Algorithm,'NT-Hash');
		assertEqual(info.Status,'Obsolete');
		assertEqual(info.Hash,'8846f7eaee8fb117ad06bdd830b7586c');

		var info = PassphraseInfo('$5$9ks3nNEqv31FX.F$gdEoLFsCRsn/WRN3wxUnzfeZLoooVlzeF4WjLomTRFD');
		assertEqual(info.Algorithm,'SHA-2');
		assertEqual(info.Version,'256');
		assertEqual(info.Status,'Unsupported');
		assertEqual(info.Rounds,'5000');
		assertEqual(info.Salt,'9ks3nNEqv31FX.F');
		assertEqual(info.Hash,'gdEoLFsCRsn/WRN3wxUnzfeZLoooVlzeF4WjLomTRFD');

		var info = PassphraseInfo('$6$qoE2letU$wWPRl.PVczjzeMVgjiA8LLy2nOyZbf7Amj3qLIL978o18gbMySdKZ7uepq9tmMQXxyTIrS12Pln.2Q/6Xscao0');
		assertEqual(info.Algorithm,'SHA-2');
		assertEqual(info.Version,'512');
		assertEqual(info.Status,'Unsupported');
		assertEqual(info.Rounds,'5000');
		assertEqual(info.Salt,'qoE2letU');
		assertEqual(info.Hash,'wWPRl.PVczjzeMVgjiA8LLy2nOyZbf7Amj3qLIL978o18gbMySdKZ7uepq9tmMQXxyTIrS12Pln.2Q/6Xscao0');

		var info = PassphraseInfo('$md5,rounds=5000$GUBv0xjJ$mSwgIswdjlTY0YxV7HBVm0');
		assertEqual(info.Algorithm,'SunMD5');
		assertEqual(info.Status,'Obsolete');
		assertEqual(info.Rounds,'5000');
		assertEqual(info.Salt,'GUBv0xjJ');
		assertEqual(info.Hash,'mSwgIswdjlTY0YxV7HBVm0');

	}


	function test_hash()
	{
		var r = RandRange(4,6);
		var hash = PassphraseHash('x','bcrypt',{rounds:r});
		var info = PassphraseInfo(hash);
		assertEqual(info.Algorithm,'BCrypt');
		assertEqual(info.Rounds,r);
		assertNotEqual(hash,PassphraseHash('x','bcrypt',{rounds:r}));

		var i = RandRange(1000,2000);
		var hash = PassphraseHash('x','pbkdf2',{iterations:i});
		var info = PassphraseInfo(hash);
		assertEqual(info.Algorithm,'PBKDF2');
		assertEqual(info.Iterations,i);
		assertNotEqual(hash,PassphraseHash('x','pbkdf2',{iterations:i}));

		var c = 2^RandRange(1,3);
		var m = RandRange(1,3);
		var hash = PassphraseHash('x','scrypt',{CpuCost:c,MemoryCost:m});
		var info = PassphraseInfo(hash);
		assertEqual(info.Algorithm,'SCrypt');
		assertEqual(info.CpuCost,c);
		assertEqual(info.MemoryCost,m);
		assertNotEqual(hash,PassphraseHash('x','scrypt',{CpuCost:c,MemoryCost:m}));
	}


	function test_check()
	{
		var hash = PassphraseHash('x','bcrypt',{rounds:4});
		assertTrue(PassphraseCheck('x',hash));
		assertFalse(PassphraseCheck('x ',hash));

		var hash = PassphraseHash('x','pbkdf2',{iterations:1000});
		assertTrue(PassphraseCheck('x',hash));
		assertFalse(PassphraseCheck('x ',hash));

		var hash = PassphraseHash('x','scrypt',{CpuCost:2,MemoryCost:2});
		assertTrue(PassphraseCheck('x',hash));
		assertFalse(PassphraseCheck('x ',hash));

		try
		{
			PassphraseCheck(hash,'');
			assertFalse(1,"Expected exception to be thrown");
		}
		catch( any e )
		{
			assertEqual(e.Message,"Unknown Algorithm Signature");
		}
	}


</cfscript>
</cfcomponent>