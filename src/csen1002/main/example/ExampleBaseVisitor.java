// Generated from D:/Documents/eclipse-workspace/Compilers/grammars\Example.g4 by ANTLR 4.10.1
package csen1002.main.example;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link ExampleVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public class ExampleBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements ExampleVisitor<T> {
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitS(ExampleParser.SContext ctx) { return visitChildren(ctx); }
}