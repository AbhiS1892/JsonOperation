package com.abhi.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTester {

	public static void main(String[] args) throws IOException {

		byte[] jsonData1 = Files.readAllBytes(Paths.get("name_dob_height.json"));
		byte[] jsonData2 = Files.readAllBytes(Paths.get("name_dob_salary.json"));
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setPropertyNamingStrategy(new EmployeeNameStrategy());

		// Employee object containing First_Name, Last_Name, Date_Of_Birth
		// and Height
		Employee[] employee1 = objectMapper.readValue(jsonData1, Employee[].class);

		// Employee object containing First_Name, Last_Name, Date_Of_Birth
		// and Salary
		Employee[] employee2 = objectMapper.readValue(jsonData2, Employee[].class);
		
		Employee[] employeeMergedDetails = null;

		for(int i=0; i<employee1.length; i++){
			if(employee1[i].First_name.equals(employee2[i].First_name)
					&& employee1[i].Last_name.equals(employee2[i].Last_name)){

				if(employee1[i].Date_of_birth.equals(employee2[i].Date_of_birth)){
					employeeMergedDetails = employee1;
					employeeMergedDetails[i].setSalary(employee2[i].Salary);
				}
			}
		}

		List<Employee> list = new ArrayList<>();
		list.addAll(Arrays.asList(employeeMergedDetails));


		// Sorting the output
		Collections.sort(list);
		Iterator<Employee> itr = list.iterator();

		// Storing the output
		try{
			PrintWriter writer = new PrintWriter("output.csv", "UTF-8");
			writer.println("First_Name,Last_Name,Date_of_birth,Height,Salary");
			while(itr.hasNext()){
				writer.println(itr.next());
			}
			writer.close();
		} catch (IOException e11) {
			e11.printStackTrace();
		}		
	}
}
