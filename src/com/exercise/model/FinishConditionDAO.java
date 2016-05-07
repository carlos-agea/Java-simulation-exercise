package com.exercise.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


/**
 * Represents the DB for the finish conditions.
 * 
 * @author Carlos Agea
 *
 */
public class FinishConditionDAO {

	public FinishConditionDAO(FinishCondition finishCondition) {
		HashMap<String, String> dataFieldsConditionMap = finishCondition.getDataFieldsConditionMap();
		String[] array = null;
		
		try {
            BufferedReader in = new BufferedReader(new FileReader("finishCondition.txt"));
            String line = "";
            while ((line = in.readLine()) != null) {
            	array=line.split(",");
            	dataFieldsConditionMap.put(array[0],array[1]);	
            }
            in.close();
        } catch (IOException e) {
            System.out.println("File Read Error");
        }
	}

}
