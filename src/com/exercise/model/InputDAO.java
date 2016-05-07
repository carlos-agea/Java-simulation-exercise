package com.exercise.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


/**
 * Represents the DB for the input data.
 * 
 * @author Carlos Agea
 *
 */
public class InputDAO {

	public InputDAO(DataInput dataInput) {
		 List<InputField> inputList = dataInput.getInputList();	
		String[] array = null;
		int fieldNumber = 0;
		
		try {
            BufferedReader in = new BufferedReader(new FileReader("input.txt"));
            String line = "";
            while ((line = in.readLine()) != null) {
            	array=line.split(",");
            	
            	InputField inputField = new InputField();
            	inputField.setField(array[0]);
            	inputField.setValue(array[1]);
            	inputList.add(fieldNumber, inputField);	
            	fieldNumber++;
            }
            in.close();
        } catch (IOException e) {
            System.out.println("File Read Error");
        }
	}
	
}
