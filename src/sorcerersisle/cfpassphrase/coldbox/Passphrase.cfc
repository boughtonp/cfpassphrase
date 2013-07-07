<!--- cfPassphrase v0.1-rc | (c) Peter Boughton | License: LGPLv3 | Website: sorcerersisle.com/projects:cfpassphrase.html --->
<cfcomponent output=false singleton >

	<cffunction name="init" returntype="any" output=false access="public">
		<cfargument name="Controller" type="coldbox.system.web.Controller" />

		<cfset var Javaloader = Controller.getPlugin('javaloader') />

		<cftry>
			<cfset Variables.PassphraseObj = Javaloader.create('sorcerersisle.cfpassphrase.Impl') />

			<!--- Handle errors due to misconfiguration with more specific messages. --->
			<cfcatch type="javaloader.ClassNotFoundException">

				<cfif NOT Controller.SettingExists('javaloader_libpath') >
					<cfthrow
						type    = "cfPassphrase.ColdboxPlugin.Init.Javaloader.InvalidConfig"
						message = "Setting [javaloader_libpath] not found in config file."
						detail  = "You must point javaloader to a directory containing the jar file."
					/>
				</cfif>

				<cfset var isJarLoaded = false />
				<cfloop index="local.CurJar" array=#javaloader.getLoadedURLs()#>
					<cfif ListLast(CurJar,'/\').startsWith('cfpassphrase-')>
						<cfset isJarLoaded = true />
						<cfbreak />
					</cfif>
				</cfloop>

				<cfif isJarLoaded >
					<cfthrow
						type    = "cfPassphrase.ColdboxPlugin.Init.Javaloader.UnknownError"
						message = "Unknown error; jar [#CurJar#] loaded but class not found."
						detail  = "Original error: #cfcatch.message# / #cfcatch.detail#"
					/>
				<cfelse>
					<cfthrow
						type    = "cfPassphrase.ColdboxPlugin.Init.Javaloader.MissingJar"
						message = "File [cfpassphrase-{version}.jar] not loaded from [#Controller.getSetting('javaloader_libpath')#]."
						detail  = "Jars loaded: #serializeJson(javaloader.getLoadedURLs())#"
					/>
				</cfif>

				<cfrethrow />
			</cfcatch>
		</cftry>

		<cfreturn this />
	</cffunction>


	<cffunction name="Hash" returntype="String" output=false access="public">
		<cfargument name="Passphrase"      type="String" required_ />
		<cfargument name="Algorithm"       type="String" optional  />
		<cfargument name="AlgorithmParams" type="Struct" optional  />

		<cfreturn PassphraseObj.hash
			( Arguments.Passphrase
			, StructKeyExists(Arguments,'Algorithm')       ? Arguments.Algorithm       : JavaCast('null','')
			, StructKeyExists(Arguments,'AlgorithmParams') ? Arguments.AlgorithmParams : JavaCast('null','')
			)/>
	</cffunction>


	<cffunction name="Check" returntype="String" output=false access="public">
		<cfargument name="Passphrase" type="String" required_ />
		<cfargument name="Hash"       type="String" required_ />
		<cfargument name="Algorithm"  type="String" optional  />

		<cfreturn PassphraseObj.check
			( Arguments.Passphrase
			, Arguments.Hash
			, StructKeyExists(Arguments,'Algorithm') ? Arguments.Algorithm : JavaCast('null','')
			)/>
	</cffunction>


	<cffunction name="Info" returntype="Struct" output=false access="public">
		<cfargument name="Hash"       type="String" required_ />
		<cfargument name="Algorithm"  type="String" optional  />

		<cfreturn PassphraseObj.info
			( Arguments.Hash
			, StructKeyExists(Arguments,'Algorithm') ? Arguments.Algorithm : JavaCast('null','')
			)/>
	</cffunction>


</cfcomponent>