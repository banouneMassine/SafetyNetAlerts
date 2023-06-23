package com.safetyNet.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.safetyNet.DTO.PersonsDTO;
import com.safetyNet.exceptions.PersonIntrovableExeption;
import com.safetyNet.model.PersonsModel;
import com.safetyNet.service.PersonsService;

@ExtendWith(MockitoExtension.class)
class PersonsControllerTest {

	@InjectMocks
	private PersonsController personsController;

	@Mock
	private PersonsService personsService;

	@Test
	@DisplayName("Tester la récupération de la liste des personnes")
	void test_getPersonsALL()  {
		//GIVEN
		List<PersonsDTO> listPersons = new ArrayList<>();
		listPersons.add(new PersonsDTO());
		
		when(personsService.getPersons()).thenReturn(listPersons);
		//WHEN
		ResponseEntity<List<PersonsDTO>> result = personsController.getPersonsALL();	
		//THEN
		assertEquals(listPersons, result.getBody());
		verify(personsService, times(1)).getPersons();
		
	}
	
	@Test
	@DisplayName("Tester la récupération d'une personne a partir de son non/prenom")
	void getPerson_wheneEntringFirstNameAndLastName_ThenRuturnPerson() throws Exception {
		//GIVEN
		PersonsDTO person = new PersonsDTO();
		person.setFirstName("wayne");
		person.setLastName("ROONEY");
		
		when(personsService.getPerson("wayne","ROONEY")).thenReturn(person);
		//WHEN
		ResponseEntity<PersonsDTO> result = personsController.getPerson("wayne","ROONEY");	
		//THEN
		assertEquals(person, result.getBody());
		verify(personsService, times(1)).getPerson("wayne","ROONEY");
		
	}
	
	@Test
	@DisplayName("Tester la récupération d'une personne introuvable ")
	void getPerson_wheneEntringAnNotFoundPerson_ThenRuturnException() throws Exception {
		//GIVEN		
		when(personsService.getPerson("wayne","ROONEY")).thenThrow(new PersonIntrovableExeption("La personne wayne ROONEY est introuvable"));
		//WHEN
		
		PersonIntrovableExeption thrown =assertThrows(PersonIntrovableExeption.class, () -> personsController.getPerson("wayne","ROONEY"));

		//THEN
		assertEquals(thrown.getMessage(),"La personne wayne ROONEY est introuvable" );
		verify(personsService, times(1)).getPerson("wayne","ROONEY");
		
	}
	
	@Test
	@DisplayName("Tester l'ajout d'une personne")
	void postPerson_wheneEntringPerson_ThenRuturnPerson() throws Exception {
		//GIVEN
		PersonsModel person = new PersonsModel();
		person.setFirstName("wayne");
		person.setLastName("ROONEY");
		
		PersonsDTO personDTO = new PersonsDTO();
		person.setFirstName("wayne");
		person.setLastName("ROONEY");
		
		when(personsService.addPerson(person)).thenReturn(personDTO);
		//WHEN
		ResponseEntity<PersonsDTO> result = personsController.postPerson(person);	
		//THEN
		assertEquals(personDTO, result.getBody());
		verify(personsService, times(1)).addPerson(person);
		
	}
	
	@Test
	@DisplayName("Tester l'ajout d'une personne null ")
	void getPerson_wheneAddingAnNotFoundPerson_ThenRuturnException() throws Exception {
		//GIVEN		
		when(personsService.addPerson(null)).thenThrow(new PersonIntrovableExeption("La personne n'a pas pu etre ajouté"));
		//WHEN
		
		PersonIntrovableExeption thrown =assertThrows(PersonIntrovableExeption.class, () -> personsController.postPerson(null));

		//THEN
		assertEquals(thrown.getMessage(),"La personne n'a pas pu etre ajouté" );
		verify(personsService, times(1)).addPerson(null);
	}
	
	@Test
	@DisplayName("Tester la supprission d'une personne a partir de son non/prenom")
	void removePerson_wheneEntringFirstNameAndLastName_ThenRuturnPerson() throws Exception {
		//GIVEN
		PersonsDTO person = new PersonsDTO();
		person.setFirstName("wayne");
		person.setLastName("ROONEY");
		
		when(personsService.removePerson("wayne","ROONEY")).thenReturn(person);
		//WHEN
		ResponseEntity<PersonsDTO> result = personsController.deletePerson("wayne","ROONEY");	
		//THEN
		assertEquals(person, result.getBody());
		verify(personsService, times(1)).removePerson("wayne","ROONEY");
		
	}
	

	@Test
	@DisplayName("Tester la supprission d'une personne introuvable ")
	void deletePerson_wheneEntringAnNotFoundPerson_ThenRuturnException() throws Exception {
		//GIVEN		
		when(personsService.removePerson("wayne","ROONEY")).thenThrow(new PersonIntrovableExeption("La personne wayne ROONEY est introuvable"));
		//WHEN
		
		PersonIntrovableExeption thrown =assertThrows(PersonIntrovableExeption.class, () -> personsController.deletePerson("wayne","ROONEY"));

		//THEN
		assertEquals(thrown.getMessage(),"La personne wayne ROONEY est introuvable" );
		verify(personsService, times(1)).removePerson("wayne","ROONEY");
		
	}
	
	@Test
	@DisplayName("Tester la modif d'une personne")
	void putPerson_wheneEntringPerson_ThenRuturnPerson() throws Exception {
		//GIVEN
		PersonsModel person = new PersonsModel();
		person.setFirstName("wayne");
		person.setLastName("ROONEY");
		
		PersonsDTO personDTO = new PersonsDTO();
		person.setFirstName("wayne");
		person.setLastName("ROONEY");
		
		when(personsService.updatePerson(person)).thenReturn(personDTO);
		//WHEN
		ResponseEntity<PersonsDTO> result = personsController.putPerson(person);	
		//THEN
		assertEquals(personDTO, result.getBody());
		verify(personsService, times(1)).updatePerson(person);
		
	}
	
	@Test
	@DisplayName("Tester la modif d'une personne null ")
	void putPerson_wheneAddingAnNotFoundPerson_ThenRuturnException() throws Exception {
		//GIVEN		
		when(personsService.updatePerson(null)).thenThrow(new PersonIntrovableExeption("La personne wayne ROONEY est introuvable"));
		//WHEN
		
		PersonIntrovableExeption thrown =assertThrows(PersonIntrovableExeption.class, () -> personsController.putPerson(null));

		//THEN
		assertEquals(thrown.getMessage(),"La personne wayne ROONEY est introuvable" );
		verify(personsService, times(1)).updatePerson(null);
	}
}
