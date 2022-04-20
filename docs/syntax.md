Syntax of Yui
=============

Lexical Structure
-----------------

```ebnf
whitespace = intraline whitespace | line ending | comment ;
intraline whitespace = ' ' | '\t' ;
line ending = '\r' | '\n' ;
comment = ';' ? any character except line ending ? ;

token = identifier | literal | '(' | ')' | '.' ;

identifier = identifier start, identifier body * ;
identifier start = letter | '!' | '$' | '%' | '^' | '&' | '*' | '-' | '_'
                 | '+' | '=' | ':' | '~' | '<' | '>' | '.' | '?' | '/' ;
identifier body = identifier start | digit ;

literal = symbol literal
        | character literal
        | string literal
        | integer literal
        | float literal ;

symbol literal = '\'', identifier ;
character literal = '#\', ? TODO ? ;
string literal = '"', ( ? TODO ? ) *, '"' ;
integer literal = [ sign ], digit, { digit * } ;
float literal = [ sign ], digit +, '.', digit +, [ exponent ] ;
exponent = 'e', sign, digit + ;

sign = '+' | '-' ;
letter = 'a' | 'b' | ... | 'z' | 'A' | 'B' | ... | 'Z' ;
digit = '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' ;
```
