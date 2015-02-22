<!--- cfPassphrase v0.0-dev | (c) Peter Boughton | License: LGPLv3 | Website: sorcerersisle.com/projects:cfpassphrase.html --->
<cfcomponent output=false >


	<cffunction name="Hash" returntype="String" output=false access="public">
		<cfargument name="Passphrase"      type="String" required />
		<cfargument name="Algorithm"       type="String" optional />
		<cfargument name="AlgorithmParams" type="Struct" optional />

		<cfset var JavaObj = createObject( 'java' , "sorcerersisle.cfpassphrase.coldfusion.PassphraseHash" ) />

		<cfif StructKeyExists(Arguments,'AlgorithmParams')>
			<cfif NOT StructKeyExists(Arguments,'Algorithm')>
				<cfthrow
					type    = "cfPassphrase.ColdFusion.Cfc.MissingArgument.Algorithm"
					message = "You must specify the [Algorithm] argument when [AlgorithmParams] is specified."
				/>
			</cfif>
			<cfreturn JavaObj.call
				( Arguments.Passphrase
				, Arguments.Algorithm
				, Arguments.AlgorithmParams
				)/>
		<cfelseif StructKeyExists(Arguments,'Algorithm')>
			<cfreturn JavaObj.call
				( Arguments.Passphrase
				, Arguments.Algorithm
				)/>
		<cfelse>
			<cfreturn JavaObj.call
				( Arguments.Passphrase
				)/>
		</cfif>
	</cffunction>


	<cffunction name="Check" returntype="String" output=false access="public">
		<cfargument name="Passphrase" type="String" required />
		<cfargument name="Hash"       type="String" required />
		<cfargument name="Algorithm"  type="String" optional />

		<cfset var JavaObj = createObject( 'java' , "sorcerersisle.cfpassphrase.coldfusion.PassphraseCheck" ) />

		<cfif StructKeyExists(Arguments,'Algorithm')>
			<cfreturn JavaObj.call
				( Arguments.Passphrase
				, Arguments.Hash
				, Arguments.Algorithm
				)/>
		<cfelse>
			<cfreturn JavaObj.call
				( Arguments.Passphrase
				, Arguments.Hash
				)/>
		</cfif>
	</cffunction>


	<cffunction name="Info" returntype="Struct" output=false access="public">
		<cfargument name="Hash"       type="String" required />
		<cfargument name="Algorithm"  type="String" optional />

		<cfset var JavaObj = createObject( 'java' , "sorcerersisle.cfpassphrase.coldfusion.PassphraseInfo" ) />

		<cfif StructKeyExists(Arguments,'Algorithm')>
			<cfreturn JavaObj.call
				( Arguments.Hash
				, Arguments.Algorithm
				)/>
		<cfelse>
			<cfreturn JavaObj.call
				( Arguments.Hash
				)/>
		</cfif>
	</cffunction>


</cfcomponent>