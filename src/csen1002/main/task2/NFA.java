package csen1002.main.task2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Write your info here
 * 
 * @name Youssef Shaarawy
 * @id 43-3973
 * @labNumber 10
 */
public class NFA{
	DFA dfa;
	/**
	 * NFA constructor
	 * 
	 * @param description is the string describing a NFA
	 */
	public NFA(String description) {
		// TODO Write Your Code Here
		String[] descriptionParts = description.split("#");
		String[] zeroTransitions = descriptionParts[0].split(";");
		String[] oneTransitions = descriptionParts[1].split(";");
		String[] epsilonTransitions = descriptionParts[2].split(";");

		int maxState = 0;
		for (int i=0;i< zeroTransitions.length;i++){
			int stateOne = Integer.parseInt(zeroTransitions[i].split(",")[0]);
			int stateTwo = Integer.parseInt(zeroTransitions[i].split(",")[1]);
			if (stateOne>maxState)
				maxState = stateOne;
			if (stateTwo > maxState)
				maxState = stateTwo;
		}
		for (int i=0;i< oneTransitions.length;i++){
			int stateOne = Integer.parseInt(oneTransitions[i].split(",")[0]);
			int stateTwo = Integer.parseInt(oneTransitions[i].split(",")[1]);
			if (stateOne>maxState)
				maxState = stateOne;
			if (stateTwo > maxState)
				maxState = stateTwo;
		}
		for (int i=0;i< epsilonTransitions.length;i++){
			int stateOne = Integer.parseInt(epsilonTransitions[i].split(",")[0]);
			int stateTwo = Integer.parseInt(epsilonTransitions[i].split(",")[1]);
			if (stateOne>maxState)
				maxState = stateOne;
			if (stateTwo > maxState)
				maxState = stateTwo;
		}

		ArrayList<Integer>[] states = new ArrayList[maxState+1];

		for (int i =0;i<states.length;i++){
			states[i] = new ArrayList<Integer>();
			states[i].add(i);
		}
		for (int i=0;i< epsilonTransitions.length;i++){
			int stateOne = Integer.parseInt(epsilonTransitions[i].split(",")[0]);
			int stateTwo = Integer.parseInt(epsilonTransitions[i].split(",")[1]);
			states[stateOne].add(stateTwo);
		}

		boolean modified = true;

		while (modified==true){
			modified=false;
			for(int i =0;i<states.length;i++){
				ArrayList<Integer> epsilonTransStates =  states[i];
				for (int j=0;j< epsilonTransStates.size();j++){
					ArrayList<Integer> reachableStates =  states[epsilonTransStates.get(j)];
					for (int k=0;k< reachableStates.size();k++){
						if (!epsilonTransStates.contains(reachableStates.get(k))){
							modified=true;
							epsilonTransStates.add(reachableStates.get(k));
						}
					}
				}
			}
		}

		ArrayList<ArrayList<Integer>> dfaStates = new ArrayList<ArrayList<Integer>>();
		dfaStates.add(states[0]);
		String dfa = "";
		for(int i=0;i< dfaStates.size();i++){
			ArrayList<Integer> dfaState = dfaStates.get(i);
			ArrayList<Integer> newState = new ArrayList<>();
			for(int j=0;j< dfaState.size();j++){
				int singleState = dfaState.get(j);
				for(int k=0;k< zeroTransitions.length;k++){
					if (Integer.parseInt(zeroTransitions[k].split(",")[0]) == singleState){
						int resultState = Integer.parseInt(zeroTransitions[k].split(",")[1]);
						newState.addAll(states[resultState]);
					}
				}
			}
			newState = (ArrayList<Integer>) newState.stream().distinct().sorted().collect(Collectors.toList());


			if(!dfaStates.contains(newState)){
				dfa += i + ","+ dfaStates.size();
				dfaStates.add(newState);
			}
			else{
				dfa += i + "," + dfaStates.indexOf(newState);
			}

			newState = new ArrayList<>();
			for(int j=0;j< dfaState.size();j++){
				int singleState = dfaState.get(j);
				for(int k=0;k< oneTransitions.length;k++){
					if (Integer.parseInt(oneTransitions[k].split(",")[0]) == singleState){
						int resultState = Integer.parseInt(oneTransitions[k].split(",")[1]);
						newState.addAll(states[resultState]);
					}
				}
			}
			newState = (ArrayList<Integer>) newState.stream().distinct().sorted().collect(Collectors.toList());

			if(!dfaStates.contains(newState)){
				dfa += ","+ dfaStates.size() + ";";
				dfaStates.add(newState);
			}
			else{
				dfa += "," + dfaStates.indexOf(newState) + ";";
			}
		}
		System.out.println(dfaStates);
		dfa = dfa.substring(0,dfa.length()-1) + "#";
		String[] nfaAcceptStates = descriptionParts[3].split(",");
		ArrayList<Integer> dfaAcceptStates = new ArrayList<Integer>();
		for(int j=0;j< dfaStates.size();j++){
			ArrayList<Integer> dfaState = dfaStates.get(j);
			for (int k=0;k< nfaAcceptStates.length;k++){
				if (dfaState.contains(Integer.parseInt(nfaAcceptStates[k]))){
					dfaAcceptStates.add(dfaStates.indexOf(dfaState));
				}
			}
		}
		dfaAcceptStates = (ArrayList<Integer>) dfaAcceptStates.stream().distinct().sorted().collect(Collectors.toList());
		for(int i =0;i<dfaAcceptStates.size();i++){
			dfa+=dfaAcceptStates.get(i) + ",";
		}
		dfa = dfa.substring(0,dfa.length()-1);
		System.out.println(dfa);
		this.dfa = new DFA(dfa);
	}

	/**
	 * Returns true if the string is accepted by the NFA and false otherwise.
	 * 
	 * @param input is the string to check by the NFA.
	 * @return if the string is accepted or not.
	 */
	public boolean run(String input) {
		// TODO Write Your Code Here
		System.out.println(this.dfa);
		return dfa.run(input);
	}

	class DFA {
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

	public static void main(String[] args){
		NFA nfa = new NFA("0,0;1,2;3,3#0,0;0,1;2,3;3,3#1,2#3");
	}
}
