package csen1002.main.task5;

import java.util.ArrayList;

/**
 * Write your info here
 * 
 * @name Youssef Shaarawy
 * @id 43-3973
 * @labNumber 10
 */
public class CFG {
	ArrayList<String> variables;
	ArrayList<ArrayList<String>> rhs;
	/**
	 * CFG constructor
	 * 
	 * @param description is the string describing a CFG
	 */
	public CFG(String description) {
		// TODO Write Your Code Here
		String[] rules = description.split(";");
		variables = new ArrayList<String>();
		rhs = new ArrayList<ArrayList<String>>();

		for(int i=0;i< rules.length;i++){
			String[] varRules = rules[i].split(",");
			variables.add(varRules[0]);
			ArrayList<String> rhsRules = new ArrayList<String>();
			for(int j=1;j<varRules.length;j++){
				rhsRules.add(varRules[j]);
			}
			rhs.add(rhsRules);
		}
	}

	/**
	 * Returns a string of elimnated left recursion.
	 * 
	 //* @param input is the string to simulate by the CFG.
	 * @return string of elimnated left recursion.
	 */
	public String lre() {
		// TODO Write Your Code Here
		ArrayList<String> finalVariables = new ArrayList<String>();
		for (int i=0;i< variables.size();i++){
			finalVariables.add(variables.get(i));
			int index = finalVariables.size()-1;
			ArrayList<String> rules = rhs.get(index);
			ArrayList<String> finalRules = new ArrayList<String>();

			for(int j=0;j<i;j++){
				String var = variables.get(j);
				for(int k=0;k<rhs.get(index).size();k++){
					String rule = rhs.get(index).get(k);
					if(rule.charAt(0)==(var.charAt(0))){
						rhs.get(index).remove(k);
						int HPindex = finalVariables.indexOf(var);
						ArrayList<String> newRules = rhs.get(HPindex);
						for(int l=0;l<newRules.size();l++){
							String newRule = newRules.get(l);
							if (rule.length()>1)
								newRule += rule.substring(1);
							rhs.get(index).add(k+l,newRule);
						}
					}
				}
			}

//			System.out.println(finalR);
			boolean ilr= false;
			for(int j=0;j< rhs.get(index).size();j++){
				if(rhs.get(index).get(j).charAt(0)==(variables.get(i).charAt(0))){
					ilr=true;
				}
			}
			if (ilr){
				finalVariables.add(variables.get(i)+"'");
				ArrayList<String> dashRules = new ArrayList<String>();
				for(int j=0;j<rhs.get(index).size();j++){
					if(rhs.get(index).get(j).charAt(0)==(variables.get(i).charAt(0))){
						String rule = rhs.get(index).remove(j);
						j--;
						dashRules.add(rule.substring(1)+variables.get(i)+"'");
					}
					else{
						String newRule = rhs.get(index).get(j)+variables.get(i)+"'";
						rhs.get(index).set(j, newRule);
					}
				}
				dashRules.add("e");
				rhs.add(index+1,dashRules);
			}
		}
		System.out.println(finalVariables);
		System.out.println(rhs);
		String output = "";
		for(int i=0;i< finalVariables.size();i++){
			output+= finalVariables.get(i);
			for (int j=0;j<rhs.get(i).size();j++){
				output+=","+rhs.get(i).get(j);
			}
			output+=";";
		}
		return output.substring(0,output.length()-1);
	}
}
