package com.exercise.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Represents the equations DAO.
 * 
 * @author CArlos Agea
 *
 */
public class EquationsDAO {
	
	public EquationsDAO(Logic logic) {
		String[] array = null;	
		int equationNumber = 0;
		
		try {
            BufferedReader in = new BufferedReader(new FileReader("equations.txt"));
            String line = "";
            while ((line = in.readLine()) != null) {
            	array=line.split(",");
            	
            	Equation equation = new Equation();
            	equation.setResultField(array[0]);
            	equation.setFormula(array[1]);
            	logic.getEquationList().add(equationNumber,equation);
            	equationNumber++;
            	
            }
            in.close();
        } catch (IOException e) {
            System.out.println("File Read Error");
        }
	}
	
}
