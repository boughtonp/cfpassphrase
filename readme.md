cfPassphrase

* Version:       0.2
* License:       LGPLv3
* Homepage:      https://www.sorcerersisle.com/software/cfpassphrase
* Documentation: https://docs.sorcerersisle.com/cfpassphrase
* Repository:    https://code.sorcerersisle.com/cfpassphrase.git
* Issues:        https://github.com/boughtonp/cfpassphrase/issues


Description
-----------

cfPassphrase is a library for securely hashing and checking passwords and 
passphrases using proven industry standard algorithms. It aims to provide 
a simple and common implementation that can be used in any CFML engine.

For further details of what is provided see docs.pdf for documentation.


Requirements
------------

cfPassphrase supports any modern CFML engine, specifically:

* ColdFusion 9 and above.
* Lucee 5.3 and above.
* Railo / Lucee 4.x
* OpenBD 3.x

It requires a minimum JRE/JDK version of 8.

If you are interested in running it on a different environment,
or have issues getting it to work, please raise an issue.


Licensing & Credits
-------------------

This project is available under the terms of the LGPLv3 license.
See license.txt to understand your rights and obligations.

cfPassphrase was created by Peter Boughton, and gratefully makes 
use of the third-party software detailed below, each available 
individually under their respective licenses.

jBCrypt v0.4 (mindrot.org/projects/jBCrypt)
* Source: http://mindrot.org/files/jBCrypt/
* License: ISC/BSD (http://mindrot.org/files/jBCrypt/LICENSE)
* Files: src/org/mindrot/jbcrypt/BCrypt.java

Java PBKDF2 (crackstation.net/hashing-security.htm)
* Source: http://crackstation.net/hashing-security.htm
* License: Public Domain
* Files: src/crackstation/PBKDF2/PasswordHash.java

Java SCrypt (github.com/wg/scrypt)
* Source: https://github.com/wg/scrypt
* License: Apache v2.0 (http://www.apache.org/licenses/LICENSE-2.0) 
* Files: src/com/lambdaworks/*


Contributing
------------

This project was created with the aim of making secure hashing
easy to implement for all CFML applications that need it.

Bug fixes, backwards-compatible improvements and additions are welcome,
but please discuss first (either raise an issue or send an email).

Changes are accepted as either pull requests or patch files.


/eof