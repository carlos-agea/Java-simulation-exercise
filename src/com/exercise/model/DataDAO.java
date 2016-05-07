package com.exercise.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * Class that represents the DB for the data.
 * 
 * @author Carlos Agea
 */
public class DataDAO {

	private void parseField(int fieldNumber, String fieldValue, DataVector testVector, Variables variables) throws IOException {
		String fieldName = "";
		DataField dataField = variables.getDataFieldsPositionMap().get(String.valueOf(fieldNumber));
		fieldName = dataField.getName();
		
		try {
			if(dataField.getType().equals("String")) {
				testVector.getOriginalDataMap().put(fieldName, fieldValue);
			}
			else if(dataField.getType().equals("Integer")) {
				testVector.getOriginalDataMap().put(fieldName, Integer.valueOf(fieldValue));
			}
			else if(dataField.getType().equals("Double")) {
				testVector.getOriginalDataMap().put(fieldName, Double.valueOf(fieldValue));
			}
			else if(dataField.getType().equals("Boolean")) {
				testVector.getOriginalDataMap().put(fieldName, Boolean.valueOf(fieldValue));
			}
			
		} catch(NumberFormatException e) {
			throw new IOException();
		}
	}
	
	private void parseLine(String line, TestSet testSet, Variables variables) {
		String[] array = null;
		DataVector testVector = new DataVector();
		
		array=line.split(",");
		for(int i=0; i<array.length; i++) {
			try {
				parseField(i, array[i], testVector, variables);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		testSet.getTestVector().add(testVector);
	}
	
	public DataDAO(TestSet testSample, Variables variables) {
		try {
            BufferedReader in = new BufferedReader(new FileReader("data.txt"));
            String str = "";
         
            while ((str = in.readLine()) != null) {
            	parseLine(str, testSample, variables);
            }
            in.close();
        } catch (IOException e) {
            System.out.println("File Read Error");
        }
	}
	
	
}
