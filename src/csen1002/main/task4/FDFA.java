package csen1002.main.task4;

import java.util.ArrayList;

/**
 * Write your info here
 * 
 * @name Youssef Shaarawy
 * @id 43-3973
 * @labNumber 10
 */
public class FDFA {
	ArrayList<Integer> states;
	ArrayList<Integer> alphabet;
	ArrayList<ArrayList<Integer>> transitions;
	int startState = 0;
	ArrayList<Integer> acceptStates;
	ArrayList<String> actions;
	/**
	 * FDFA constructor
	 * 
	 * @param description is the string describing a FDFA
	 */
	public FDFA(String description) {
		// TODO Write Your Code Here
		states = new ArrayList<Integer>();
		alphabet = new ArrayList<Integer>();
		transitions = new ArrayList<ArrayList<Integer>>();
		acceptStates = new ArrayList<Integer>();
		actions = new ArrayList<String>();

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
			actions.add(functionStates[3]);

			states.add(Integer.parseInt(functionStates[0]));
		}

		alphabet.add(0);
		alphabet.add(1);

		String[] accepted = accept.split(",");
		for (int i =0;i<accepted.length;i++) {
			acceptStates.add(Integer.parseInt(accepted[i]));
		}
	}

	/**
	 * Returns a string of actions.
	 * 
	 * @param input is the string to simulate by the FDFA.
	 * @return string of actions.
	 */
	public String run(String input) {
		// TODO Write Your Code Here
		String outputString="";
		while (input.length()!=0){
			boolean flag = false;
			for(int i=input.length();i>0;i--){
				if(checkString(input.substring(0,i))){
					outputString += input.substring(0,i)+"," +actions.get(getFinalState(input.substring(0,i)))+";";
					flag = true;
					input = input.substring(i);
					break;
				}
			}
			if (!flag){
				outputString+=input+","+actions.get(getFinalState(input))+";";
				break;
			}
		}
		return outputString;
	}

	public boolean checkString(String input){
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
		if (acceptStates.contains(currentState)){
			return true;
		}
		return false;
	}
	public int getFinalState(String input){
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
		return currentState;
	}
	public String toString() {
		String print = "States: " + states + "\n";
		print += "Alphabet: " + alphabet + "\n";
		print+= "Transitions: " + transitions + "\n";
		print+= "Accept States: "+ acceptStates + "\n";
		return print;

	}
	public static void main(String[] args){
		FDFA fdfa = new FDFA("0,0,1,A;1,2,1,B;2,0,3,C;3,3,3,N#0,1,2");
		System.out.println(fdfa.run("1011100"));
	}
}
