package com.exercise.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a data vector to initiate the simulation with,
 * as well as the data vector result of the simulation for
 * each of the possible input values.
 * 
 * @author Carlos Agea
 *
 */
public class DataVector {
	private HashMap<String, Object> originalDataMap = new HashMap<String, Object>();
	private List<HashMap<String, Object>> resultDataMapList = new ArrayList<HashMap<String, Object>>();

	public HashMap<String, Object> getOriginalDataMap() {
		return originalDataMap;
	}

	public void setOriginalDataMap(HashMap<String, Object> originalDataMap) {
		this.originalDataMap = originalDataMap;
	}

	public List<HashMap<String, Object>> getResultDataMapList() {
		return resultDataMapList;
	}

	public void setResultDataMapList(List<HashMap<String, Object>> resultDataMapList) {
		this.resultDataMapList = resultDataMapList;
	}
	
}
