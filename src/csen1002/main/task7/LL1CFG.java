package csen1002.main.task7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * Write your info here
 * 
 * @name Youssef Shaarawy
 * @id 43-3973
 * @labNumber 10
 */
public class LL1CFG {
	/**
	 * LL1 CFG constructor
	 * 
	 * @param description is the string describing an LL(1) CFG, first, and follow as represented in the task description.
	 */
	HashMap rules;
	HashMap firsts;
	HashMap follows;
	HashMap<String, HashMap> table;
	public LL1CFG(String description) {
		String[] descriptionParts = description.split("#");
		String[] ruleStrings = descriptionParts[0].split(";");
		String[] firstStrings = descriptionParts[1].split(";");
		String[] followStrings = descriptionParts[2].split(";");

		rules = new HashMap();
		firsts = new HashMap<>();
		follows = new HashMap<>();

		for(int i =0;i< ruleStrings.length;i++){
			String[] ruleString = ruleStrings[i].split(",");
			String[] firstString = firstStrings[i].split(",");

			ArrayList<String> varRules = new ArrayList<>();
			ArrayList<String> ruleFirsts = new ArrayList<>();
			for(int j=1;j<ruleString.length;j++){
				varRules.add(ruleString[j]);
				ruleFirsts.add(firstString[j]);
			}
			rules.put(ruleString[0],varRules);
			firsts.put(firstString[0],ruleFirsts);
		}

		for(int i=0;i< followStrings.length;i++){
			String[] followString = followStrings[i].split(",");
			ArrayList<String> follow = new ArrayList<>();
			for(int j=0;j<followString[1].length();j++){
				follow.add(followString[1].charAt(j)+"");
			}
			follows.put(followString[0],follow);
		}

		System.out.println(rules.entrySet());
		System.out.println(firsts.entrySet());
		System.out.println(follows.entrySet());

		table = new HashMap<>();
		Object[] vars =  rules.keySet().toArray();
		for(int i=0;i< vars.length;i++){
			HashMap<String,String> tableRow = new HashMap<>();

			ArrayList<String> varFirsts = (ArrayList<String>) firsts.get(vars[i]);
			ArrayList<String> varRules = (ArrayList<String>) rules.get(vars[i]);

			for(int j=0;j<varFirsts.size();j++){
				String ruleFirsts = varFirsts.get(j);
				for(int k=0;k<ruleFirsts.length();k++){
					if(ruleFirsts.charAt(k) !='e'){
						tableRow.put(ruleFirsts.charAt(k)+"",varRules.get(j));
					}
					else{
						ArrayList<String> varFollows = (ArrayList<String>) follows.get(vars[i]);
						for(int l=0;l<varFollows.size();l++){
							tableRow.put(varFollows.get(l),varRules.get(j));
						}
					}
				}

			}



			table.put((String) vars[i],tableRow);
		}
		System.out.println(table.entrySet());
	}
	/**
	 * Returns A string encoding a derivation is a comma-separated sequence of sentential forms each representing a step in the derivation..
	 * 
	 * @param input is the string to be parsed by the LL(1) CFG.
	 * @return returns a string encoding a left-most derivation.
	 */
	public String parse(String input) {
		Stack<String> pda = new Stack<String>();
		pda.push("$");
		pda.push("S");
		ArrayList<String> result = new ArrayList<>();
		result.add("S");
		input+="$";
		for(int i=0;i<input.length();i++){
			String top = pda.pop();
			if (top.charAt(0) > 64 && top.charAt(0)<91){
				String rule;
				try{
					rule = (String) table.get(top).get(input.charAt(i)+"");
					result.add(result.get(result.size()-1).replaceFirst(top,rule).replace("e",""));
					for(int j=rule.length()-1;j>=0;j--){
						if(rule.charAt(j)!='e'){
							pda.push(rule.charAt(j)+"");
						}
					}
					i--;
				}
				catch (NullPointerException e){
					result.add("ERROR");
					break;
				}

			}
			else{
				if (!top.equals(input.charAt(i)+"")){
					result.add("ERROR");
					break;
				}
			}
		}
		String resultString = "";
		for(int i=0;i< result.size();i++){
			resultString+= "," + result.get(i);
		}
		return resultString.substring(1);
	}

}
