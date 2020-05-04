<cfsetting showdebugoutput=false enablecfoutputonly=true />
<cfcontent type="text/html"><cfoutput><!doctype html>
	<meta charset=utf-8 />
	<style>html{font-family:monospace;}</style>
	<title>cfPassphrase Tests</title>
	<b>Testing...</b>

	<cfset IncludeSlowTests = StructKeyExists(Url,'RunAll') />

	<cfset s = getTickCount() />
	<li>#createObject('UsageTest').init( IncludeSlowTests )#
	<li>#createObject('BcryptTest').init( IncludeSlowTests )#
	<li>#createObject('Pbkdf2Test').init( IncludeSlowTests )#
	<li>#createObject('ScryptTest').init( IncludeSlowTests )#
	<p><b>Total Time: ~#round((getTickCount()-s)/1000)# seconds</b>

</html></cfoutput>