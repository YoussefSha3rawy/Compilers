grammar Example;


s: TYPE ID EOF;

fragment LETTER_ : [A-Za-z] | '_';

fragment DIGIT : [0-9];

TYPE: 'int' | 'float';

ID: LETTER_ (LETTER_ | DIGIT)*;

SPACE: ' ' -> skip;