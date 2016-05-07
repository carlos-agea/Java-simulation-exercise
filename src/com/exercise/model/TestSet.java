package com.exercise.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a test set with the data vector to simulate the system with,
 * as well as the input data vector to aplly
 * 
 * @author Carlos Agea
 *
 */
public class TestSet {
	private Set<DataVector> testVector = new HashSet<DataVector>();
	private DataInput inputVector = new DataInput();
	
	public Set<DataVector> getTestVector() {
		return testVector;
	}

	public void setTestVector(Set<DataVector> testVector) {
		this.testVector = testVector;
	}

	public DataInput getInputVector() {
		return inputVector;
	}

	public void setInputVector(DataInput inputVector) {
		this.inputVector = inputVector;
	}

}
