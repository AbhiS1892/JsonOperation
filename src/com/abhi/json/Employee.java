package com.abhi.json;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee implements Comparable<Employee>{

	String First_name;
	String Last_name;
	String Date_of_birth;
	String Height;
	int Salary;

	public String getFirst_name() {
		return First_name;
	}

	public void setFirst_name(String first_name) {
		First_name = first_name;
	}

	public String getLast_name() {
		return Last_name;
	}

	public void setLast_name(String last_name) {
		Last_name = last_name;
	}

	public String getDate_of_birth() {
		return Date_of_birth;
	}

	public void setDate_of_birth(String date_of_birth) {
		Date_of_birth = date_of_birth;
	}

	public String getHeight() {
		return Height;
	}

	public void setHeight(String height) {
		Height = height;
	}

	public long getSalary() {
		return Salary;
	}

	public void setSalary(int salary) {
		Salary = salary;
	}

	@Override
	public String toString() {
		return First_name + "," + Last_name + "," + Date_of_birth
				+ "," + Height + "," + Salary;
	}

	@Override
	public int compareTo(Employee o) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date dob1 = null;
		Date dob2 = null;

		try{
			dob1 = df.parse(this.Date_of_birth);
			dob2 = df.parse(o.Date_of_birth);
		}catch (Exception e) {
			e.printStackTrace();
		}

		if(dob1.equals(dob2)){
			if(this.Height.compareTo(o.Height) == 0){
				if(this.Salary == o.Salary){
					return 0;
				}else{
					return this.Salary - o.Salary;
				}
			}else {
				return this.Height.compareTo(o.Height);
			}
		}else if (dob1.after(dob2)) {
			return 1;
		}else
			return -1;
	}



}
