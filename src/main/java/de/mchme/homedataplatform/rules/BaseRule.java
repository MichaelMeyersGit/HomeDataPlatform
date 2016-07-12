package de.mchme.homedataplatform.rules;

import org.easyrules.annotation.Priority;

public abstract class BaseRule {
	
	private int myNumber ;
	private int[] ruleList ;
	
	public BaseRule(int mynumber, int[] rulelist) {
		this.myNumber = mynumber;
		this.ruleList = rulelist ;
	}
	
	@Priority
	public int getPriority() {
		int prio = -1 ;
		
		for(int i=0; i<this.ruleList.length; i++) {
			if(ruleList[i] == this.myNumber) {
				prio = i ;
				break ;
			}
		}
		
		return prio ;
	}

}
