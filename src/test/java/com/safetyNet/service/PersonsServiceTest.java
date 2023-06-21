package com.safetyNet.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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

import com.safetyNet.DTO.PersonsDTO;
import com.safetyNet.exceptions.PersonIntrovableExeption;
import com.safetyNet.model.PersonsModel;
import com.safetyNet.repository.PersonsRepository;

@ExtendWith(MockitoExtension.class)
class PersonsServiceTest {

	@Mock
	PersonsRepository personsRepository;
	
	 @InjectMocks
	 private PersonsService personService;
	
	@Test
	@DisplayName("Tester la récupération de la liste des personnes ")
	void getPersons_RuternListOfAllPersonsDTO()
	{
		//GIVENE
		List<PersonsModel> listPersons = new ArrayList<>();
		 
		listPersons.add(new PersonsModel());
		listPersons.add(new PersonsModel());
		when( personsRepository.findAll()).thenReturn(listPersons);
		
		//WHEN
		List<PersonsDTO> listPersonsDTO = personService.getPersons();
		
		//THENE
		verify(personsRepository, times(1)).findAll();
		assertThat(listPersonsDTO.size()).isEqualTo(2);
	}
	
	@Test
	@DisplayName("Tester la récupération de la liste des personnes ")
	void getPerson_whenEnteringTheFirstAndLastName_thenReturnThePerson()
	{
		//GIVENE
		PersonsModel person1 = new PersonsModel();
		person1.setFirstName("Alban");
		person1.setLastName("Boyd");
		person1.setAddress("1509 Culver St");
		
		when( personsRepository.findByName(any(String.class), any(String.class))).thenReturn(person1);
		
		//WHEN 
		PersonsDTO personsDTO = assertDoesNotThrow(() -> personService.getPerson("Alban","Boyd"));
		
		//THENE
		verify(personsRepository , times(1)).findByName(any(String.class), any(String.class));
		assertThat(personsDTO.getFirstName()).isEqualTo("Alban");
	}
	
	@Test
	@DisplayName("Tester que quand la personne recherchée n'est pas trouvée alors une exception est levée  ")
	void getPerson_whenEnteringAnUnidentifiedName_thenReturnException()
	{
		//GIVEN
		when( personsRepository.findByName(any(String.class), any(String.class))).thenReturn(null);
		String firstName = "Alban";
		String lastName = "Boyd";
		String exceptionMessage = "La personne " +firstName+ " "+ lastName + " est introuvable";
		
		//WHEN 
		PersonIntrovableExeption thrown = assertThrows(PersonIntrovableExeption.class, () -> personService.getPerson(firstName,lastName));
		                             
		//THENE
		verify(personsRepository , times(1)).findByName(any(String.class), any(String.class));
	    assertThat(thrown.getMessage()).isEqualTo(exceptionMessage);
	}
	
	@Test
	@DisplayName("Tester l'ajout d'une personne ")
	void addPerson_whenEnteringanPerson_thenRuternIt()
	{
		//GIVEN
		PersonsModel person = new PersonsModel();
		person.setFirstName("Alban");
		person.setLastName("Boyd");
		person.setAddress("1509 Culver St");
		
		when(personsRepository.save(person)).thenReturn(person);
		
		//WHENE
		
		PersonsDTO personsDTO = assertDoesNotThrow(() -> personService.addPerson(person));
		
		//THENE 
		verify(personsRepository , times(1)).save(any(PersonsModel.class));
		assertThat(personsDTO.getLastName()).isEqualTo(person.getLastName());
		assertThat(personsDTO.getFirstName()).isEqualTo(person.getFirstName());
	}
	
	@Test
	@DisplayName("Tester l'impossibilité l'ajout d'une personne nulle ")
	void addPerson_whenEnteringanNullPerson_thenRuternException()
	{
		//GIVEN
		String exceptionMessage = "La personne n'a pas pu etre ajouté";
		PersonsModel person = null ;
		
		//WHENE
		PersonIntrovableExeption thrown = assertThrows(PersonIntrovableExeption.class, () -> personService.addPerson(person));
		
		//THENE 
		assertThat(thrown.getMessage()).isEqualTo(exceptionMessage);
		
	}
	
	@Test
	@DisplayName("Tester la supprission d'une personne")
	void removePerson_whenEnteringAnPerson_thenDeleteIt()
	{
		//GIVEN
		PersonsModel person = new PersonsModel();
		person.setFirstName("test");
		person.setLastName("Test");
		person.setAddress("1509 Culver St");
		when(personsRepository.findByName(any(String.class), any(String.class))).thenReturn(person);
		when(personsRepository.deletePerson(person)).thenReturn(person);
		
		//WHENE
		PersonsDTO personsDTO = assertDoesNotThrow(() -> personService.removePerson("test", "Test"));
		
		//THENE
		verify(personsRepository,times(1)).findByName(any(String.class), any(String.class));
		verify(personsRepository,times(1)).deletePerson(any(PersonsModel.class));
		assertThat(personsDTO.getFirstName()).isEqualTo("test");
		assertThat(personsDTO.getLastName()).isEqualTo("Test");
	}
	
	
	@Test
	@DisplayName("Tester l'impossibilité de supprimer une personne")
	void removePerson_whenEnteringAnNotFoundPerson_thenRuternExceptiont()
	{
		//GIVENE
		String firstName ="test";
		String lastName = "Test";
		when(personsRepository.findByName(any(String.class), any(String.class))).thenReturn(null);
		
		//WHENE
		PersonIntrovableExeption thrown = assertThrows(PersonIntrovableExeption.class, () ->personService.removePerson("test", "Test"));
		
		//THENE
		verify(personsRepository,times(1)).findByName(any(String.class), any(String.class));
		assertThat(thrown.getMessage()).isEqualTo("La personne "+firstName+" "+lastName +" est introuvable");
	}
	
	@Test
	@DisplayName("Tester la modification  d'une personne")
	void updatePerson_whenEnteringAnPerson_thenUpdateIt()
	{
		
		//GIVEN
		PersonsModel person = new PersonsModel();
		person.setFirstName("wayne");
		person.setLastName("rooney");
		person.setAddress("1509 Culver St");
		when(personsRepository.updatePerson(any(PersonsModel.class))).thenReturn(person);
	
		//WHENE
		PersonsDTO personsDTO = assertDoesNotThrow(() -> personService.updatePerson(person)) ;
		//THENE
		verify(personsRepository,times(2)).updatePerson(any(PersonsModel.class));
		assertThat(personsDTO.getFirstName()).isEqualTo("wayne");
		assertThat(personsDTO.getLastName()).isEqualTo("rooney");
	}
	@Test
	@DisplayName("Tester l'impossibilité de modifier une personne quand celle ci n'est pas trouvée")
	void updatePerson_whenEnteringANotFoundPerson_thenReturnException()
	{
		//GIVEN
		PersonsModel person = new PersonsModel();
		person.setFirstName("Cristiano");
		person.setLastName("Ronaldo");
		
		when(personsRepository.updatePerson(person)).thenReturn(null);
		
		//WHENE
		PersonIntrovableExeption thrown = assertThrows(PersonIntrovableExeption.class,()-> personService.updatePerson(person) );
		
		//THENE
		verify(personsRepository,times(1)).updatePerson(any(PersonsModel.class));
		assertThat(thrown.getMessage()).isEqualTo("La personne " +person.firstName+ " "+ person.lastName + " est introuvable");		
				
		
	}
}
