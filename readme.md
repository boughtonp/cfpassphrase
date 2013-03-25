cfPassphrase v0.0.000


DESCRIPTION
-----------

cfPassphrase is a library for securely hashing and checking passphrases using
proven industry standard algorithms. It aims to provide a simple and common 
implentation that can be used in any CFML engine.

Further details of what it provides can be found on the wiki:
https://github.com/boughtonp/cfpassphrase/wiki


STATUS
------

This is a pre-release version of cfPassphrase, and is not yet ready for general use.

Version: 0.0.000
Released: n/a


REQUIREMENTS
------------

cfPassphrase is intended to run on any CFML engine.


LICENSING
---------

cfPassphrase is a project created and maintained by Peter Boughton,
licensed under the LGPLv3 (read license.txt for details).

The project gratefully makes use of the third-party software detailed below,
each available individually under their respective licenses.

jBCrypt v0.3 (mindrot.org/projects/jBCrypt)
* Source: http://mindrot.org/files/jBCrypt/
* License: ISC/BSD (http://mindrot.org/files/jBCrypt/LICENSE)
* Files: src/mindrot/jbcrypt/BCrypt.java

Java PBKDF2 (crackstation.net/hashing-security.htm)
* Source: http://crackstation.net/hashing-security.htm
* License: Public Domain
* Files: src/crackstation/PBKDF2/PasswordHash.java

Java scrypt (github.com/wg/scrypt)
* Source: https://github.com/wg/scrypt
* License: Apache v2.0 (http://www.apache.org/licenses/LICENSE-2.0) 
* Files: src/com/lambdaworks/*


/eof