<cfif NOT StructKeyExists(Attributes,'Action')>
	<cfthrow
		type    = "cfPassphrase.ColdFusion.Tag.MissingAttribute.Action"
		message = "You must specify the [Action] attribute. Accepted values are 'hash', 'check', or 'info'."
	/>
</cfif>
<cfif NOT StructKeyExists(Attributes,'Variable')>
	<cfthrow
		type    = "cfPassphrase.ColdFusion.Tag.MissingAttribute.Variable"
		message = "You must specify the [Variable] attribute."
	/>
</cfif>
<cfif NOT StructKeyExists(Attributes,'Passphrase')>
	<cfthrow
		type    = "cfPassphrase.ColdFusion.Tag.MissingAttribute.Passphrase"
		message = "You must specify the [Passphrase] attribute."
	/>
</cfif>

<cfswitch expression=#Attributes.Action# >

	<cfcase value="hash">
		<cfset JavaObj = createObject( 'java' , "sorcerersisle.cfpassphrase.coldfusion.PassphraseHash" ) />

		<cfif StructKeyExists(Attributes,'AlgorithmParams')>
			<cfif NOT StructKeyExists(Attributes,'Algorithm')>
				<cfthrow
					type    = "cfPassphrase.ColdFusion.Tag.MissingAttribute.Algorithm"
					message = "You must specify the [Algorithm] attribute when [AlgorithmParams] is specified."
				/>
			</cfif>
			<cfset Caller[Attributes.Variable] = JavaObj.call
				( Attributes.Passphrase
				, Attributes.Algorithm
				, Attributes.AlgorithmParams
				)/>
		<cfelseif StructKeyExists(Attributes,'Algorithm')>
			<cfset Caller[Attributes.Variable] = JavaObj.call
				( Attributes.Passphrase
				, Attributes.Algorithm
				)/>
		<cfelse>
			<cfset Caller[Attributes.Variable] = JavaObj.call
				( Attributes.Passphrase
				)/>
		</cfif>
	</cfcase>

	<cfcase value="check">
		<cfif NOT StructKeyExists(Attributes,'Hash')>
			<cfthrow
				type    = "cfPassphrase.ColdFusion.Tag.MissingAttribute.Hash"
				message = "You must specify the [Hash] attribute."
			/>
		</cfif>

		<cfset JavaObj = createObject( 'java' , "sorcerersisle.cfpassphrase.coldfusion.PassphraseCheck" ) />

		<cfif StructKeyExists(Attributes,'Algorithm')>
			<cfset Caller[Attributes.Variable] = JavaObj.call
				( Attributes.Passphrase
				, Attributes.Hash
				, Attributes.Algorithm
				)/>
		<cfelse>
			<cfset Caller[Attributes.Variable] = JavaObj.call
				( Attributes.Passphrase
				, Attributes.Hash
				)/>
		</cfif>

	</cfcase>

	<cfcase value="info">
		<cfif NOT StructKeyExists(Attributes,'Hash')>
			<cfthrow
				type    = "cfPassphrase.ColdFusion.Tag.MissingAttribute.Hash"
				message = "You must specify the [Hash] attribute."
			/>
		</cfif>

		<cfset JavaObj = createObject( 'java' , "sorcerersisle.cfpassphrase.coldfusion.PassphraseInfo" ) />

		<cfif StructKeyExists(Attributes,'Algorithm')>
			<cfset Caller[Attributes.Variable] = JavaObj.call
				( Attributes.Hash
				, Attributes.Algorithm
				)/>
		<cfelse>
			<cfset Caller[Attributes.Variable] = JavaObj.call
				( Attributes.Hash
				)/>
		</cfif>

	</cfcase>

	<cfdefaultcase>
		<cfthrow
			type    = "cfPassphrase.ColdFusion.Tag.InvalidAttribute.Action"
			message = "Invalid value for [Action] attribute. Accepted values are 'hash', 'check', or 'info'."
		/>
	</cfdefaultcase>

</cfswitch>

<cfexit method="exitTag" />