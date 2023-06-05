package com.safetyNet.DTO;


import java.util.ArrayList;
import java.util.List;


import com.safetyNet.model.PersonsModel;



public class ChildDTO {
	
	
	public String firstName ;
	
	public String lastName;
	
	public int age ; 
	
	public List<PersonsModel> familles = new ArrayList<>(); 
	

	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public void setFamilles(List<PersonsModel> familles) {
		this.familles = familles;
	}
	
}
