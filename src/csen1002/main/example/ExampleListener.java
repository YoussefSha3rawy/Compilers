// Generated from D:/Documents/eclipse-workspace/Compilers/grammars\Example.g4 by ANTLR 4.10.1
package csen1002.main.example;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExampleParser}.
 */
public interface ExampleListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExampleParser#s}.
	 * @param ctx the parse tree
	 */
	void enterS(ExampleParser.SContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExampleParser#s}.
	 * @param ctx the parse tree
	 */
	void exitS(ExampleParser.SContext ctx);
}