package com.safetyNet.integration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetyNet.DTO.PersonsDTO;
import com.safetyNet.model.PersonsModel;


@SpringBootTest
@AutoConfigureMockMvc
class PersonsControllerIT {

	//@Autowired
	//private PersonsService personsService;
	
	@Autowired
	MockMvc mockMvc ;
	
	@Test
	@DisplayName("Tester la récupération de la liste des personnes")
	void getPersonsALL() throws Exception {
		//GIVEN 
		PersonsDTO person1 = new PersonsDTO();
		person1.setFirstName("John");
		person1.setLastName("Boyd");
		person1.setAddress("1509 Culver St"); 
		person1.setCity("Culver");
		person1.setZip(97451);
		person1.setPhone("841-874-6512");
		person1.setEmail("jaboyd@email.com");
		ObjectMapper objectMapper = new ObjectMapper();
		String person1Json = objectMapper.writeValueAsString(person1);
		
		//WHEN
		
		ResultActions  response = mockMvc.perform(MockMvcRequestBuilders.get("/personALL"));
		//THEN
		//response.andDo(MockMvcResultHandlers.print());
		response.andExpect(MockMvcResultMatchers.status().isOk());
		String responseBody = response.andReturn().getResponse().getContentAsString();
		
		
		assertThat(responseBody).contains(person1Json);
	}
	
	@Test
	@DisplayName("Tester la récupération d'une personne grace a son nom , prenome")
	void getPerson_whenEntringFirstAndLastName_thenRuternPerson() throws Exception {
		//GIVEN 
		PersonsDTO person1 = new PersonsDTO();
		person1.setFirstName("John");
		person1.setLastName("Boyd");
		person1.setAddress("1509 Culver St");
		person1.setCity("Culver");
		person1.setZip(97451);
		person1.setPhone("841-874-6512");
		person1.setEmail("jaboyd@email.com");
		ObjectMapper objectMapper = new ObjectMapper();
		String person1Json = objectMapper.writeValueAsString(person1);
		
		//WHEN
	
		ResultActions  response = mockMvc.perform(MockMvcRequestBuilders.get("/person/{firstName}/{lastName}", "John", "Boyd"));
		
		//THEN
		//response.andDo(MockMvcResultHandlers.print());
		response.andExpect(MockMvcResultMatchers.status().isOk());
		String responseBody = response.andReturn().getResponse().getContentAsString();
		
		
		
		assertThat(responseBody).isEqualTo(person1Json);
	} 
	
	@Test
	@DisplayName("Tester l'ajout  d'une personne")
	void postPerson_whenEntringAPerson_thenRuternPersonDTO() throws Exception {
		//GIVEN 
		PersonsModel person1 = new PersonsModel();
		person1.setFirstName("John");
		person1.setLastName("Snow");
		person1.setAddress("Royaume du nord");
		
		ObjectMapper objectMapper = new ObjectMapper();
		String person1Json = objectMapper.writeValueAsString(person1);
		
		//WHEN
	
		ResultActions  response = mockMvc.perform(MockMvcRequestBuilders.post("/person").contentType(MediaType.APPLICATION_JSON).content(person1Json));
		
		//THEN
	  	response.andDo(MockMvcResultHandlers.print());
		response.andExpect(MockMvcResultMatchers.status().isOk());
		String responseBody = response.andReturn().getResponse().getContentAsString();
		
		assertThat(responseBody).isEqualTo(person1Json);

	} 
	
	@Test
	@DisplayName("Tester la supprission d'une person  d'une personne")
	void deletePerson_whenEntringFirstAndLastName_thenRuternPerson() throws Exception {
		//GIVEN 
		PersonsDTO person1 = new PersonsDTO();
		person1.setFirstName("Felicia");
		person1.setLastName("Boyd");
		person1.setAddress("1509 Culver St");
		person1.setCity("Culver");
		person1.setZip(97451);
		person1.setPhone("841-874-6544");
		person1.setEmail("jaboyd@email.com");
		
	
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		String person1Json = objectMapper.writeValueAsString(person1);
		
		//WHEN
	
		ResultActions  response = mockMvc.perform(MockMvcRequestBuilders.delete("/person/{firstName}/{lastName}", "Felicia", "Boyd"));
		
		//THEN
		//response.andDo(MockMvcResultHandlers.print());
		response.andExpect(MockMvcResultMatchers.status().isOk());
		String responseBody = response.andReturn().getResponse().getContentAsString();
		
		assertThat(responseBody).isEqualTo(person1Json);

	} 
	
	@Test
	@DisplayName("Tester la modification  d'une personne")
	void putPerson_whenEntringAPerson_thenRuternPersonDTO() throws Exception {
		//GIVEN 
		PersonsModel person1 = new PersonsModel();
		person1.setFirstName("Roger");
		person1.setLastName("Boyd");
		person1.setAddress("Royaume du nord");
		person1.setCity("Culver");
		person1.setZip(97451);
		person1.setPhone("841-874-6544");
		person1.setEmail("jaboyd@email.com");
		
		 
		
		ObjectMapper objectMapper = new ObjectMapper();
		String person1Json = objectMapper.writeValueAsString(person1);
		
		//WHEN
	
		ResultActions  response = mockMvc.perform(MockMvcRequestBuilders.put("/person").contentType(MediaType.APPLICATION_JSON).content(person1Json));
		
		//THEN
	  	//response.andDo(MockMvcResultHandlers.print());
		response.andExpect(MockMvcResultMatchers.status().isOk());
		String responseBody = response.andReturn().getResponse().getContentAsString();
		
		assertThat(responseBody).isEqualTo(person1Json);

	} 

}
