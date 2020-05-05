cfPassphrase v0.2-rc


DESCRIPTION
-----------

cfPassphrase is a library for securely hashing and checking passphrases using
proven industry standard algorithms. It aims to provide a simple and common 
implementation that can be used in any CFML engine.

Further details of what it provides can be found on the wiki:
https://docs.sorcerersisle.com/cfpassphrase


STATUS
------

Version: 0.2-rc
Released: 2019-05-05

To check latest release, visit https://www.sorcerersisle.com/software/cfpassphrase


REQUIREMENTS
------------

cfPassphrase is intended to run on any CFML engine, but specifically:

* ColdFusion 9 and above.
* Lucee 5.3 and above.
* Railo / Lucee 4.x
* OpenBD 3.x

If you have issues getting it to work, please log them at:

  https://github.com/boughtonp/cfpassphrase/issues


LICENSING
---------

cfPassphrase is a project created and maintained by Peter Boughton,
licensed under the LGPLv3 (read license.txt for details).

The project gratefully makes use of the third-party software detailed below,
each available individually under their respective licenses.

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


/eof