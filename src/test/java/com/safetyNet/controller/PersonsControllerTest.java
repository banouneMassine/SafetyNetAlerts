package com.safetyNet.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetyNet.DTO.PersonsDTO;
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
	void test_getPersonsALL() throws Exception {
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
	@DisplayName("Tester la récupération d'une personne")
	void test_getPerson() throws Exception {
		// GIVEN
		PersonsDTO person = new PersonsDTO();
		person.setFirstName("John");
		person.setLastName("Boyd");
		person.setAddress("1509 Culver St");
		person.setCity("Culver");
		person.setEmail("jaboyd@email.com");
		person.setPhone("841-874-6512");
		person.setZip(97451);

		when(personsService.getPerson("John", "Boyd")).thenReturn(person);
		final MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/person/{firstName}/{lastName}",
				"John", "Boyd");
		// WHEN

	/*MvcResult response = mockMvc.perform(builder).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Boyd")).andReturn();

		// THENE
		verify(personsService).getPerson("John", "Boyd");
		PersonsDTO personRecuperer = objectMapper.readValue(response.getResponse().getContentAsString(),
				PersonsDTO.class);
		assertThat(personRecuperer.getFirstName()).isEqualTo("John");*/
	}

	@Test
	@DisplayName("Tester la créetion d'une personne")
	@Disabled
	void test_postPerson() throws Exception {
		// GIVEN
		PersonsModel person = new PersonsModel();
		person.setFirstName("Lionel");
		person.setLastName("MESSI");
		person.setAddress("1509 Culver St");
		person.setCity("Culver");
		person.setEmail("jaboyd@email.com");
		person.setPhone("841-874-6512");
		person.setZip(97451);

		PersonsDTO personDto = new PersonsDTO();
		personDto.setFirstName("Lionel");
		personDto.setLastName("MESSI");
		personDto.setAddress("1509 Culver St");
		personDto.setCity("Culver");
		personDto.setEmail("jaboyd@email.com");
		personDto.setPhone("841-874-6512");
		personDto.setZip(97451);

	/* 	when(personsService.addPerson(person)).thenReturn(personDto);
		final MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/person")
											.contentType(MediaType.APPLICATION_JSON)
											.content(objectMapper.writeValueAsString(person));

		// WHEN
		MvcResult response = mockMvc.perform(builder)
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Lionel"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("MESSI"))
				.andReturn();

		// THENE
		
		verify(personsService).addPerson(person);
		PersonsDTO personCreer = objectMapper.readValue(response.getResponse().getContentAsString(), PersonsDTO.class);
		assertThat(personCreer.getFirstName()).isEqualTo("Lionel");
	}*/
	}
}
