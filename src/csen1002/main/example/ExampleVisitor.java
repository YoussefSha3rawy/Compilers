// Generated from D:/Documents/eclipse-workspace/Compilers/grammars\Example.g4 by ANTLR 4.10.1
package csen1002.main.example;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ExampleParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ExampleVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ExampleParser#s}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitS(ExampleParser.SContext ctx);
}