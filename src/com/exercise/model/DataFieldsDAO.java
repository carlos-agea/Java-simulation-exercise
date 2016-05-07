package com.exercise.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


/**
 * Class that represents the DB for the data fields.
 */
public class DataFieldsDAO {

	public DataFieldsDAO(Variables variables) {
		HashMap<String, DataField> dataFieldsPositionMap = variables.getDataFieldsPositionMap();
		HashMap<String, DataField> dataFieldsNameMap = variables.getDataFieldsNameMap();
		
		String[] array = null;
		int fieldNumber = 0;
		
		try {
            BufferedReader in = new BufferedReader(new FileReader("dataFields.txt"));
            String line = "";
            while ((line = in.readLine()) != null) {
            	array=line.split(",");
            	
            	DataField dataField = new DataField();
            	dataField.setName(array[0]);
            	dataField.setType(array[1]);
            	dataFieldsPositionMap.put(String.valueOf(fieldNumber),dataField);
            	dataFieldsNameMap.put(dataField.getName(), dataField);
            	fieldNumber++;
            }
            in.close();
        } catch (IOException e) {
            System.out.println("File Read Error");
        }
	}
	
}
