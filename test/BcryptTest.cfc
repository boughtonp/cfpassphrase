<cfcomponent extends="TestBase">
<cfscript>

	function init( IncludeSlowTests )
	{
		super.init( argumentcollection=arguments );

		this.Name = 'bcrypt';

		var TestData =
			[ [ "", "$2a$06$DCq7YPn5Rq63x1Lad4cll.TV4S6ytwfsfvkgY8jIucDrjc8deX1s." ]
			, [ "", "$2a$08$HqWuK6/Ng6sg9gQzbLrgb.Tl.ZHfXLhvt/SgVyWhQqgqcZ7ZuUtye" ]
			, [ "", "$2a$10$k1wbIrmNyFAPwPVPSVa/zecw2BCEnBwVS2GbrmgzxFUOqW9dk4TCW" ]
			, [ "", "$2a$12$k42ZFHFWqBp3vWli.nIn8uYyIkbvYRvodzbfbK18SSsY.CsIQPlxO" ]
			, [ "a", "$2a$06$m0CrhHm10qJ3lXRY.5zDGO3rS2KdeeWLuGmsfGlMfOxih58VYVfxe" ]
			, [ "a", "$2a$08$cfcvVd2aQ8CMvoMpP2EBfeodLEkkFJ9umNEfPD18.hUF62qqlC/V." ]
			, [ "a", "$2a$10$k87L/MF28Q673VKh8/cPi.SUl7MU/rWuSiIDDFayrKk/1tBsSQu4u" ]
			, [ "a", "$2a$12$8NJH3LsPrANStV6XtBakCez0cKHXVxmvxIlcz785vxAIZrihHZpeS" ]
			, [ "abc", "$2a$06$If6bvum7DFjUnE9p2uDeDu0YHzrHM6tf.iqN8.yx.jNN1ILEf7h0i" ]
			, [ "abc", "$2a$08$Ro0CUfOqk6cXEKf3dyaM7OhSCvnwM9s4wIX9JeLapehKK5YdLxKcm" ]
			, [ "abc", "$2a$10$WvvTPHKwdBJ3uk0Z37EMR.hLA2W6N9AEBhEgrAOljy2Ae5MtaSIUi" ]
			, [ "abc", "$2a$12$EXRkfkdmXn2gzds2SSitu.MW9.gAVqa9eLS1//RYtYCmB1eLHg.9q" ]
			, [ "abcdefghijklmnopqrstuvwxyz", "$2a$06$.rCVZVOThsIa97pEDOxvGuRRgzG64bvtJ0938xuqzv18d3ZpQhstC" ]
			, [ "abcdefghijklmnopqrstuvwxyz", "$2a$08$aTsUwsyowQuzRrDqFflhgekJ8d9/7Z3GV3UcgvzQW3J5zMyrTvlz." ]
			, [ "abcdefghijklmnopqrstuvwxyz", "$2a$10$fVH8e28OQRj9tqiDXs1e1uxpsjN0c7II7YPKXua2NAKYvM6iQk7dq" ]
			, [ "abcdefghijklmnopqrstuvwxyz", "$2a$12$D4G5f18o7aMMfwasBL7GpuQWuP3pkrZrOAnqP.bmezbMng.QwJ/pG" ]
			, [ "~!@##$%^&*()      ~!@##$%^&*()PNBFRD", "$2a$06$fPIsBO8qRqkjj273rfaOI.HtSV9jLDpTbZn782DC6/t7qT67P6FfO" ]
			, [ "~!@##$%^&*()      ~!@##$%^&*()PNBFRD", "$2a$08$Eq2r4G/76Wv39MzSX262huzPz612MZiYHVUJe/OcOql2jo4.9UxTW" ]
			, [ "~!@##$%^&*()      ~!@##$%^&*()PNBFRD", "$2a$10$LgfYWkbzEvQ4JakH7rOvHe0y8pHKF9OaFgwUZ2q7W2FFZmZzJYlfS" ]
			, [ "~!@##$%^&*()      ~!@##$%^&*()PNBFRD", "$2a$12$WApznUOJfkEGSmYRfnkrPOr466oFDCaj4b6HY3EXGvfxm43seyhgC" ]
			];

		super.start();

		this.test_info( TestData );

		if ( IncludeSlowTests )
			this.test_basic( TestData );
		else
			skip( ArrayLen(TestData)*2 );

		this.test_nonascii();

		return super.end();
	}


	function test_info( TestData )
	{
		var info1 = PassphraseInfo('$2a$10$9zXu55aPNya8ek17DwCXZ.X6kExa5cK5bpGmyTBrqD1dg76rkWz4y');
		assertEqual(info1.Algorithm,'BCrypt');
		assertEqual(info1.Status,'Supported');
		assertEqual(info1.Version,'2a');
		assertEqual(info1.Rounds,10);
		assertEqual(info1.Salt,'9zXu55aPNya8ek17');
		assertEqual(info1.Hash,'DwCXZ.X6kExa5cK5bpGmyTBrqD1dg76rkWz4y');
		assertEqual(StructCount(info1),6);

		var info = PassphraseInfo(Arguments.TestData[1][2]);
		assertEqual(info.Algorithm,'BCrypt');
		assertEqual(info.Status,'Supported');
		assertEqual(info.Version,'2a');
		assertEqual(info.Rounds,6);
		assertEqual(info.Salt,'DCq7YPn5Rq63x1La');
		assertEqual(info.Hash,'d4cll.TV4S6ytwfsfvkgY8jIucDrjc8deX1s.');

		info = PassphraseInfo(Arguments.TestData[20][2]);
		assertEqual(info.Algorithm,'BCrypt');
		assertEqual(info.Status,'Supported');
		assertEqual(info.Version,'2a');
		assertEqual(info.Rounds,12);
		assertEqual(info.Salt,'WApznUOJfkEGSmYR');
		assertEqual(info.Hash,'fnkrPOr466oFDCaj4b6HY3EXGvfxm43seyhgC');
	}


	function test_basic( TestData )
	{
		for ( var i=1 ; i<=ArrayLen(Arguments.TestData) ; ++i )
		{
			assertTrue( PassphraseCheck(Arguments.TestData[i][1],Arguments.TestData[i][2]) );
			assertFalse( PassphraseCheck(Arguments.TestData[i][1],Arguments.TestData[((i + 4) % ArrayLen(Arguments.TestData))+1][2]) );
		}
	}


	function test_nonascii()
	{
		var TestData =
			[ RepeatString(chr(2605),8)
			, "????????"
			];

		var h1 = PassphraseHash(TestData[1],'bcrypt',{rounds:6});
		var h2 = PassphraseHash(TestData[2],'bcrypt',{rounds:6});
		assertTrue(PassphraseCheck(TestData[1],h1));
		assertFalse(PassphraseCheck(TestData[1],h2));
		assertTrue(PassphraseCheck(TestData[2],h2));
		assertFalse(PassphraseCheck(TestData[2],h1));
	}


</cfscript>
</cfcomponent>