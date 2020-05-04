<cfcomponent>
<cfscript>

	function init()
	{
		this.Results = [];
		this.Messages = [];

		variables.Status =
			{ Pass : "."
			, Fail : "!"
			, Skip : "-"
			};
	}


	function start()
	{
		this.StartTime = getTickCount();
	}


	function end()
	{
		return '<br/>#this.Name#: #ArrayToList(this.Results,'')#'
			& '<br/>time: #getTickCount()-this.StartTime#ms'
			& '<pre>#ArrayToList(this.Messages,'<br/>')#</pre>'
			;
	}


	function assertEqual(a,b,Msg='')
	{
		if ( len(Arguments.Msg) )
			return assertTrue( a eq b , Arguments.Msg );

		return assertTrue( a eq b , 'Expected [#a#] to be same as [#b#]' );
	}


	function assertNotEqual(a,b,Msg='')
	{
		if ( len(Arguments.Msg) )
			return assertFalse( a eq b , Arguments.Msg );

		return assertFalse( a eq b , 'Expected [#a#] to differ from [#b#]' );
	}


	function assertTrue(a,Msg='')
	{
		if ( not isBoolean(a) )
			return fail('Expected boolean, received [#a#]');

		if ( not a )
		{
			if ( len(Arguments.Msg) )
				return fail(Arguments.Msg);

			return fail('Expected True received [#a#]');
		}

		return pass();
	}


	function assertFalse(a,Msg='')
	{
		if ( not isBoolean(a) )
			return fail('Expected boolean, received [#a#]');

		if ( a )
		{
			if ( len(Arguments.Msg) )
				return fail(Arguments.Msg);

			return fail('Expected False received [#a#]');
		}

		return pass();
	}


	function pass()
	{
		ArrayAppend(this.Results,Status.Pass);
		return true;
	}


	function fail(Msg)
	{
		if ( len(Arguments.Msg) )
			logMessage(Msg);

		ArrayAppend(this.Results,Status.Fail);
		return false;
	}


	function skip(n=1)
	{
		ArrayAppend(this.Results,RepeatString(Status.Skip,n));
		logMessage('Skipped #n# assertions.');
	}


	function logMessage(Msg)
	{
		ArrayAppend(this.Messages,Msg);
	}


</cfscript>
</cfcomponent>