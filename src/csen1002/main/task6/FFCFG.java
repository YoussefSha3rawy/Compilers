package csen1002.main.task6;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Write your info here
 * 
 * @name Youssef Shaarawy
 * @id 43-3973
 * @labNumber 10
 */

public class FFCFG {

	HashMap rules;
	ArrayList<String> variables;

	/**
	 * Constructs a CFG for which the First and Follow are to be computed
	 * 
	 * @param description A string representation of a CFG as specified in the task
	 *                    description
	 */
	public FFCFG(String description) {
		// TODO Auto-generated constructor stub
		rules = new HashMap();
		variables = new ArrayList<String>();
		description = description.replaceAll(" ","");
		String[] rows = description.split(";");
		for (int i =0;i< rows.length;i++){
			String[] varRow = rows[i].split(",");
			String var = varRow[0];
			ArrayList<String> varRules = new ArrayList<String>();
			for(int j=1;j<varRow.length;j++){
				varRules.add(varRow[j]);
			}
			variables.add(var);
			rules.put(var,varRules);

		}
		System.out.println(rules.entrySet());
	}

	/**
	 * Calculates the First of each variable in the CFG.
	 * 
	 * @return A string representation of the First of each variable in the CFG,
	 *         formatted as specified in the task description.
	 */
	public String first() {
		// TODO Auto-generated method stub
		HashMap first = firstHashMap();
		System.out.println(first.entrySet());
		String output = "";
		for(String k:variables){
			output+=";" + k + ",";
			ArrayList<String> firsts = (ArrayList<String>) first.get(k);
			for (String l : firsts){
				output+=l;
			}
		}
		output = output.substring(1);
		System.out.println(output);
		return output;
	}
	public HashMap firstHashMap(){
		HashMap first = new HashMap<>();
		Set keyset = rules.keySet();
		for (Object k: keyset) {
			String key = (String) k;
			first.put(key, new ArrayList<String>());
		}
		boolean change = true;
		while (change){
			change = false;
			for (Object k: first.keySet()) {
				ArrayList<String> varFirsts = (ArrayList<String>) first.get(k);
				String key = (String) k;
				ArrayList<String> varRules = (ArrayList<String>) rules.get(key);
				for (String rule : varRules) {
					boolean allE = true;
					for (int i = 0; i < rule.length(); i++) {
						if(rule.charAt(i) >96 && rule.charAt(i)<123 && rule.charAt(i)!=101){
							allE = false;
							break;
						}
						if(rule.charAt(i)>64 && rule.charAt(i)<91){
							ArrayList<String> newVarFirsts = (ArrayList<String>) first.get(rule.charAt(i)+"");
							if (!newVarFirsts.contains("e")){
								allE=false;
								break;
							}
						}
					}
					if (allE && !varFirsts.contains("e")){
						varFirsts.add("e");
						change = true;
					}
				}
				first.put(key,varFirsts);

				for (String rule : varRules) {
					for (int i = 0; i < rule.length(); i++) {
						if(rule.charAt(i) >96 && rule.charAt(i)<123){
							if (!varFirsts.contains(rule.charAt(i)+"")){
								varFirsts.add(rule.charAt(i)+"");
								change = true;
							}
							break;
						}
						if(rule.charAt(i)>64 && rule.charAt(i)<91){
							ArrayList<String> newVarFirsts = (ArrayList<String>) ((ArrayList<String>) first.get(rule.charAt(i)+"")).clone();
							if (newVarFirsts.contains("e")){
								newVarFirsts.remove("e");
								if(!varFirsts.containsAll(newVarFirsts)){
									varFirsts.addAll(newVarFirsts);
									change = true;
								}
							}
							else{
								if(!varFirsts.containsAll(newVarFirsts)){
									varFirsts.addAll(newVarFirsts);
									change = true;
								}
								break;
							}
						}
					}
				}
				varFirsts = (ArrayList<String>) varFirsts.stream().distinct().sorted().collect(Collectors.toList());
				first.put(key,varFirsts);
			}
		}
		return first;
	}

	/**
	 * Calculates the Follow of each variable in the CFG.
	 * 
	 * @return A string representation of the Follow of each variable in the CFG,
	 *         formatted as specified in the task description.
	 */
	public String follow() {
		// TODO Auto-generated method stub
		HashMap follow = new HashMap<>();
		HashMap first = this.firstHashMap();

		Set keyset = rules.keySet();
		for (Object k: keyset) {
			String key = (String) k;
			follow.put(key, new ArrayList<String>());
		}
		((ArrayList<String>) follow.get("S")).add("$");
		boolean change = true;
		while (change) {
			change = false;
			for (Object k: follow.keySet()) {
				String key = (String) k;
				ArrayList<String> varRules = (ArrayList<String>) rules.get(key);
				for (String rule : varRules) {
					for (int i = 0; i < rule.length(); i++) {
						if(rule.charAt(i)>64 && rule.charAt(i)<91) {
							boolean allE = true;
							ArrayList<String> varFollow = (ArrayList<String>) follow.get(rule.charAt(i)+"");
							for(int j=i+1;j<rule.length();j++){
								if(rule.charAt(j)>64 && rule.charAt(j)<91){
									ArrayList<String> nextFirst =
											(ArrayList<String>) ((ArrayList<String>) first.get(rule.charAt(j)+"")).clone();
									if(nextFirst.contains("e")){
										nextFirst.remove("e");
										if(!varFollow.containsAll(nextFirst)){
											varFollow.addAll(nextFirst);
											change=true;
										}
									}
									else{
										allE = false;
										if(!varFollow.containsAll(nextFirst)){
											varFollow.addAll(nextFirst);
											change=true;
										}
										break;
									}

								}
								if (rule.charAt(j)>96 && rule.charAt(j)<123){
									allE=false;
									varFollow.add(rule.charAt(j)+"");
									break;
								}
							}
							if(allE && !varFollow.containsAll((ArrayList<String>)(follow.get(key)))){
								varFollow.addAll((ArrayList<String>)(follow.get(key)));
								change=true;
							}
						}
					}
				}

			}

			for(Object key: follow.keySet()){
				ArrayList<String> follows = (ArrayList<String>) follow.get(key);
				follows = (ArrayList<String>) follows.stream().distinct().sorted().collect(Collectors.toList());
				follow.put(key,follows);
			}
		}
		System.out.println(follow.entrySet());
		String output = "";
		for(String key: variables){
			output+=";"+(String) key + ",";
			ArrayList<String> values = (ArrayList<String>) follow.get(key);
			if(values.get(0).equals("$"))
				values.add(values.remove(0));
			for (String v: values){
				output+=v;
			}
		}
		System.out.println(output.substring(1));
		return output.substring(1);
	}
}
