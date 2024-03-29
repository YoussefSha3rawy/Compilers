/**
 * Write your info here
 *
 * @name Youssef Shaarawy
 * @id 43-3973
 * @labNumber 10
 */

grammar Task9;

@members {
	/**
	 * Compares two integer numbers
	 *
	 * @param x the first number to compare
	 * @param y the second number to compare
	 * @return 1 if x is equal to y, and 0 otherwise
	 */
	public static int equals(int x, int y) {
	    return x == y ? 1 : 0;
	}
}

s returns [int check]:
 // Write the definition of parser rule "s" here
 a c b { $check = equals($a.n,$b.n) *equals($a.n,$c.n); };

a returns [int n]:
 A A2=a { $n= $A2.n + 1; }
 | { $n = 0; };

b returns [int n]:
 B B2=b {$n= $B2.n + 1;}
 | { $n = 0; };

c returns [int n]:
 C C2=c {$n= $C2.n + 1;}
 | { $n = 0; };

// Write additional lexer and parser rules here


A:'a';
B:'b';
C:'c';