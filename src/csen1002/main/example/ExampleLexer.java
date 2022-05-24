// Generated from D:/Documents/eclipse-workspace/Compilers/grammars\Example.g4 by ANTLR 4.10.1
package csen1002.main.example;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExampleLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		TYPE=1, ID=2, SPACE=3;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"LETTER_", "DIGIT", "TYPE", "ID", "SPACE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, "' '"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "TYPE", "ID", "SPACE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public ExampleLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Example.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0003&\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0001\u0000\u0003\u0000\r\b\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0003\u0002\u0019\b\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0005\u0003\u001e\b\u0003\n\u0003\f\u0003!\t\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0000\u0000\u0005\u0001\u0000"+
		"\u0003\u0000\u0005\u0001\u0007\u0002\t\u0003\u0001\u0000\u0002\u0003\u0000"+
		"AZ__az\u0001\u000009&\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007"+
		"\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0001\f\u0001"+
		"\u0000\u0000\u0000\u0003\u000e\u0001\u0000\u0000\u0000\u0005\u0018\u0001"+
		"\u0000\u0000\u0000\u0007\u001a\u0001\u0000\u0000\u0000\t\"\u0001\u0000"+
		"\u0000\u0000\u000b\r\u0007\u0000\u0000\u0000\f\u000b\u0001\u0000\u0000"+
		"\u0000\r\u0002\u0001\u0000\u0000\u0000\u000e\u000f\u0007\u0001\u0000\u0000"+
		"\u000f\u0004\u0001\u0000\u0000\u0000\u0010\u0011\u0005i\u0000\u0000\u0011"+
		"\u0012\u0005n\u0000\u0000\u0012\u0019\u0005t\u0000\u0000\u0013\u0014\u0005"+
		"f\u0000\u0000\u0014\u0015\u0005l\u0000\u0000\u0015\u0016\u0005o\u0000"+
		"\u0000\u0016\u0017\u0005a\u0000\u0000\u0017\u0019\u0005t\u0000\u0000\u0018"+
		"\u0010\u0001\u0000\u0000\u0000\u0018\u0013\u0001\u0000\u0000\u0000\u0019"+
		"\u0006\u0001\u0000\u0000\u0000\u001a\u001f\u0003\u0001\u0000\u0000\u001b"+
		"\u001e\u0003\u0001\u0000\u0000\u001c\u001e\u0003\u0003\u0001\u0000\u001d"+
		"\u001b\u0001\u0000\u0000\u0000\u001d\u001c\u0001\u0000\u0000\u0000\u001e"+
		"!\u0001\u0000\u0000\u0000\u001f\u001d\u0001\u0000\u0000\u0000\u001f \u0001"+
		"\u0000\u0000\u0000 \b\u0001\u0000\u0000\u0000!\u001f\u0001\u0000\u0000"+
		"\u0000\"#\u0005 \u0000\u0000#$\u0001\u0000\u0000\u0000$%\u0006\u0004\u0000"+
		"\u0000%\n\u0001\u0000\u0000\u0000\u0005\u0000\f\u0018\u001d\u001f\u0001"+
		"\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}