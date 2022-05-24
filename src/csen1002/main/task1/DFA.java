package csen1002.main.task1;
import java.util.ArrayList;

/**
 * Write your info here
 * 
 * @name Youssef Shaarawy
 * @id 43-3973
 * @labNumber 10
 */
public class DFA {
	ArrayList<Integer> states;
	ArrayList<Integer> alphabet;
	ArrayList<ArrayList<Integer>> transitions;
	int startState = 0;
	ArrayList<Integer> acceptStates;
	
	/**
	 * DFA constructor
	 * 
	 * @param description is the string describing a DFA
	 */
	public DFA(String description) {
		// TODO Write Your Code Here
		states = new ArrayList<Integer>();
		alphabet = new ArrayList<Integer>();
		transitions = new ArrayList<ArrayList<Integer>>();
		acceptStates = new ArrayList<Integer>();
		
		String transitionFunction = description.split("#")[0];
		String accept = description.split("#")[1];
		
		String[] functions = transitionFunction.split(";");
		for(int i =0;i<functions.length;i++) {
			String function = functions[i];
			String[] functionStates = function.split(",");
			ArrayList<Integer> transition = new ArrayList<Integer>();
			transition.add(Integer.parseInt(functionStates[0]));
			transition.add(Integer.parseInt(functionStates[1]));
			transition.add(Integer.parseInt(functionStates[2]));
			transitions.add(transition);
			
			if (!states.contains(Integer.parseInt(functionStates[0])))
				states.add(Integer.parseInt(functionStates[0]));
			if (!states.contains(Integer.parseInt(functionStates[1])))
				states.add(Integer.parseInt(functionStates[1]));
			if (!states.contains(Integer.parseInt(functionStates[2])))
				states.add(Integer.parseInt(functionStates[2]));
		}
		
		alphabet.add(0);
		alphabet.add(1);
		
		String[] accepted = accept.split(",");
		for (int i =0;i<accepted.length;i++) {
			acceptStates.add(Integer.parseInt(accepted[i]));
		}
				
		
	}

	/**
	 * Returns true if the string is accepted by the DFA and false otherwise.
	 * 
	 * @param input is the string to check by the DFA.
	 * @return if the string is accepted or not.
	 */
	public boolean run(String input) {
		// TODO Write Your Code Here
		int currentState = startState;
		
		for(int i =0;i<input.length();i++) {
			for(int j =0;j<transitions.size();j++) {
				if(transitions.get(j).get(0)==currentState){
					if(input.charAt(i)=='0')
						currentState = transitions.get(j).get(1);
					else
						currentState = transitions.get(j).get(2);
					break;
				}
			}
		}
		if (acceptStates.contains(currentState))
			return true;
		return false;
	}
	
	public String toString() {
		String print = "States: " + states + "\n";
		print += "Alphabet: " + alphabet + "\n";
		print+= "Transitions: " + transitions + "\n";
		print+= "Accept States: "+ acceptStates + "\n";
		return print;
		
	}
}
