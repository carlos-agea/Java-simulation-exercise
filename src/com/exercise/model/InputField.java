package com.exercise.model;

/**
 * Represents the input fields to utilize in the equations.
 * 
 * @author Carlos Agea
 * 
 *
 */
public class InputField {
	private String field = "";
	private String value = "";
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	
}
