package com.exercise.model;

import java.util.HashMap;

/**
 * Represents the finish conditions to check
 * to the simulation to finish with the current data vector.
 * 
 * @author Carlos Agea
 *
 */
public class FinishCondition {
	private HashMap<String, String> dataFieldsConditionMap = new HashMap<String, String>();

	public HashMap<String, String> getDataFieldsConditionMap() {
		return dataFieldsConditionMap;
	}

	public void setDataFieldsConditionMap(HashMap<String, String> dataFieldsConditionMap) {
		this.dataFieldsConditionMap = dataFieldsConditionMap;
	}


}
