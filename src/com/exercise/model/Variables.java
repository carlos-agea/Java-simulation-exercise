package com.exercise.model;

import java.util.HashMap;

/*
 * Represents the data fields to utilize in the equations.
 * They are grouped by:
 * Fields position: to get the fields data from data.txt in the correct order.
 * Fields name: to match with the fields in the equations.
 */
public class Variables {
	private HashMap<String, DataField> dataFieldsPositionMap = new HashMap<String, DataField>();
	private HashMap<String, DataField> dataFieldsNameMap = new HashMap<String, DataField>();
	
	public HashMap<String, DataField> getDataFieldsNameMap() {
		return dataFieldsNameMap;
	}
	public void setDataFieldsNameMap(HashMap<String, DataField> dataFieldsNameMap) {
		this.dataFieldsNameMap = dataFieldsNameMap;
	}
	public HashMap<String, DataField> getDataFieldsPositionMap() {
		return dataFieldsPositionMap;
	}
	public void setDataFieldsPositionMap(HashMap<String, DataField> dataFieldsPositionMap) {
		this.dataFieldsPositionMap = dataFieldsPositionMap;
	}
	
}
