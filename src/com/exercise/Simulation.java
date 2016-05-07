package com.exercise;

import java.util.List;

import com.exercise.model.DataDAO;
import com.exercise.model.DataFieldsDAO;
import com.exercise.model.DataVector;
import com.exercise.model.EquationsDAO;
import com.exercise.model.FinishCondition;
import com.exercise.model.FinishConditionDAO;
import com.exercise.model.InputDAO;
import com.exercise.model.InputField;
import com.exercise.model.Logic;
import com.exercise.model.DataInput;
import com.exercise.model.TestSet;
import com.exercise.model.Variables;

/**
 * <p>
 * This class allows to simulate a system.
 * The model has to be implemented in form of Js equations -equations.txt- that will be executed in order.
 * In datafields.txt the variables used during the calculation, followed by the type.
 * In input.txt the input variables and values to simulated the model with.
 * In finishCondition.txt the variable/value that will set the simulation to finish.
 * In data.txt are stored the test data vectors.
 * 
 * </p>
 *
 * @author Carlos Agea
 */
public class Simulation {
	DataFieldsDAO dataFieldsDAO = null;
	EquationsDAO equationsDAO = null;
	DataDAO dataDAO = null;
	FinishConditionDAO finishConditionDAO = null;
	InputDAO inputDAO = null;	
	Engine engine = null;
	TestSet testSet = null;
	Logic logic = null;
	FinishCondition finishCondition = null;
	Variables variables = null;
	FinishCondition finishConfition = null;
	DataInput dataInput = null;
	
	public Simulation() {
		variables = new Variables();
		dataFieldsDAO = new DataFieldsDAO(variables);
		testSet = new TestSet();
		dataDAO = new DataDAO(testSet, variables);
		logic = new Logic();
		equationsDAO = new EquationsDAO(logic);
		finishCondition = new FinishCondition();
		finishConditionDAO = new FinishConditionDAO(finishCondition);
		engine = new Engine(logic, testSet);
		dataInput = new DataInput();
		inputDAO = new InputDAO(dataInput);	
	}
	
	 /**
     * <p>
     * Prints the initial values and results <br/>
     * </p>
     */
	private void printResults() {
		long initialBreedCNumber = 0;
		long initialBreedNCNumber = 0;
		long finalBreedCNumber = 0;
		long finalBreedNCNumber = 0;
		long regainedNumber = 0;
		
		System.out.print("Number of vectors to test: " + testSet.getTestVector().size());
		for (DataVector vector : testSet.getTestVector()) {
			if(vector.getOriginalDataMap().get("Agent_Breed").equals("Breed_C")) {
				initialBreedCNumber++;
			}
			else if(vector.getOriginalDataMap().get("Agent_Breed").equals("Breed_NC")) {
				initialBreedNCNumber++;
			}
	    }
		System.out.print("\nInitial -> 'Breed_C' : " + initialBreedCNumber);
		System.out.print(",l 'Breed_NC' : " + initialBreedNCNumber);
		
		List<InputField> inputList = dataInput.getInputList();
		for(int i=0; i<inputList.size(); i++) {
			InputField inputField = inputList.get(i);
			System.out.print("\n" + inputField.getField() + " = " + inputField.getValue() );
			
			finalBreedCNumber = 0;
			finalBreedNCNumber = 0;
			regainedNumber = 0;
			for (DataVector vector : testSet.getTestVector()) {
				if(vector.getResultDataMapList().get(i).get("Agent_Breed").equals("Breed_C")) {
					finalBreedCNumber++;
				}
				else if(vector.getResultDataMapList().get(i).get("Agent_Breed").equals("Breed_NC")) {
					finalBreedNCNumber++;
				}
				
				if(((Double) vector.getResultDataMapList().get(i).get("Number_changes")) > 1.0) {
					regainedNumber++;
				}
				
			}
			System.out.print(" -> 'Breed_C' : " + finalBreedCNumber);
			System.out.print(", 'Breed_NC' : " + finalBreedNCNumber);
			System.out.print(", 'Breed_C' regained: " + regainedNumber);
	    }
	}
	
	 /**
     * <p>
     * Run the simulation for each vector in the vectors set<br/>
     * </p>
     */
	private void simulate() {
		for (DataVector vector : testSet.getTestVector()) {
			this.engine.simulateTestVector(vector, variables, finishCondition, dataInput);
	    }
		printResults();
	}
	
	public static void main(String[] args) {
		Simulation sim = new Simulation();
		sim.simulate();
	}
	
}
