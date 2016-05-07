package com.exercise.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the model -list of equations- to simulate the system.
 * 
 * @author Carlos Agea
 *
 */
public class Logic {
	private List<Equation> equationList = new ArrayList<Equation>();

	public List<Equation> getEquationList() {
		return equationList;
	}

	public void setEquationList(List<Equation> equationList) {
		this.equationList = equationList;
	}
	 
}
