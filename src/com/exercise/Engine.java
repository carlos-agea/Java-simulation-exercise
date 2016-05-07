package com.exercise;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

import com.exercise.model.DataInput;
import com.exercise.model.DataVector;
import com.exercise.model.Equation;
import com.exercise.model.FinishCondition;
import com.exercise.model.InputField;
import com.exercise.model.Logic;
import com.exercise.model.TestSet;
import com.exercise.model.Variables;

/**
 * <p>
 * This class represents the engine of the simulation system.
 * </p>
 *
 * @author Carlos Agea
 */
public class Engine {

	private TestSet testSet = null;
	private Logic logic = null;
	
	public Engine(Logic logic, TestSet testSet) {
		this.logic = logic;
		this.testSet = testSet;
	}
	
	/**
	 * Apply the equation to the test vector indicated by inputNum.
	 * The Variables object is provided to get the type of fields involved.
	 * 
	 * @param testVector the data to be simulated.
	 * @param equation the equation to apply.
	 * @param variables the fields and types to use.
	 * @param inputNum the result vector number to utilize.
	 */
	private void applyEquation(DataVector testVector, Equation equation,  Variables variables, int inputNum) {
		ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("JavaScript");
		HashMap<String, Object> dataMap = testVector.getResultDataMapList().get(inputNum);
		String resultStrValue = null;
		Double resultDoubleValue;
		Boolean resultBooleanValue;
		
		try {
			if(variables.getDataFieldsNameMap().get(equation.getResultField()).getType().equals("String")) {
				resultStrValue = (String) scriptEngine.eval(equation.getFormula(), new SimpleBindings(dataMap));
				dataMap.put(equation.getResultField(), resultStrValue);
			} else if(variables.getDataFieldsNameMap().get(equation.getResultField()).getType().equals("Double")) {
				resultDoubleValue = (Double) scriptEngine.eval(equation.getFormula(), new SimpleBindings(dataMap));
				dataMap.put(equation.getResultField(), resultDoubleValue);
			} else if(variables.getDataFieldsNameMap().get(equation.getResultField()).getType().equals("Boolean")) {
				resultBooleanValue = (Boolean) scriptEngine.eval(equation.getFormula(), new SimpleBindings(dataMap));
				dataMap.put(equation.getResultField(), resultBooleanValue);
			}
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Check if the finish condition is completed by checking the conditions in finishCondition object.
	 * 
	 * @param testVector with data to check against.
	 * @param variables the fields and types to use.
	 * @param finishCondition the conditions to finish the simulation.
	 * @param inputNum the result vector number to utilize.
	 * @return
	 */
	private boolean isFinishCondition(DataVector testVector, Variables variables, FinishCondition finishCondition, int inputNum) {
		Iterator it = finishCondition.getDataFieldsConditionMap().entrySet().iterator();
		String field = "";
		String finishValue = "";
		String fieldType = "";
		Double epsilon = 0.001;
		
		while (it.hasNext()) {  // Iterates all the finish conditions.
			Map.Entry pair = (Map.Entry)it.next();
			field = (String) pair.getKey();
			finishValue = (String) pair.getValue();
			fieldType = variables.getDataFieldsNameMap().get(field).getType();
			
			if(fieldType.equals("String")) {
				if(testVector.getResultDataMapList().get(inputNum).get(field).equals(pair.getValue()))
					return true;
			} else if(fieldType.equals("Double")) {
				if(Math.abs((Double)testVector.getResultDataMapList().get(inputNum).get(field) - Double.valueOf(finishValue)) < epsilon)
					return true;  // A condition to finish has being found.
			}
	    }
		
		return false;  // No finish condition has being found.
	}
	
	/**
	 * Simulate the system with one provided data vector, data input and finish conditions.
	 * 
	 * @param testVector the data to be simulated.
	 * @param variables he fields and types to use.
	 * @param finishCondition the conditions to finish the simulation.
	 * @param dataInput the data to use to input the system.
	 */
	public void simulateTestVector(DataVector testVector, Variables variables, FinishCondition finishCondition, DataInput dataInput) {
		List<InputField> inputList = dataInput.getInputList();
		InputField inputField = null;
		
		for(int i=0; i < inputList.size(); i++) {  // loop the input list for data input the system.
			HashMap<String, Object> resultVector = new HashMap<String, Object>(testVector.getOriginalDataMap());
			inputField = inputList.get(i);
			resultVector.put(inputField.getField(), inputField.getValue());
			resultVector.put("Year", 0);
			resultVector.put("Change_Breed", Boolean.FALSE);
			resultVector.put("Number_changes", Double.valueOf(0));
			
			testVector.getResultDataMapList().add(i, resultVector);	
			do {  
				for(int n=0;n<logic.getEquationList().size();n++) {  // Apply the list of equations in order of definition.
					Equation equation = logic.getEquationList().get(n);
					applyEquation(testVector, equation, variables, i);
				}	
			} while (!isFinishCondition(testVector, variables, finishCondition, i));
			// Repetad the simulation until the finish condition is true.
		}
	}
	
}
