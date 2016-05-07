package com.exercise.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a data input to apply to the system.
 * 
 * @author Carlos Agea
 *
 */
public class DataInput {
	private List<InputField> inputList = new ArrayList<InputField>();

	public List<InputField> getInputList() {
		return inputList;
	}

	public void setInputList(List<InputField> inputList) {
		this.inputList = inputList;
	}

}
