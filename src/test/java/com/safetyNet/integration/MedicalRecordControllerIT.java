package com.safetyNet.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

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
import com.safetyNet.model.MedicalRecordsModel;


@SpringBootTest
@AutoConfigureMockMvc
class MedicalRecordControllerIT {

	@Autowired
	MockMvc mockMvc ;
	
	@Test
	@DisplayName("Tester la récupération de la liste des MedicalRecords")
	void getPersonsALL() throws Exception {
		//GIVEN 
		MedicalRecordsModel medicalRecord =new MedicalRecordsModel();
		medicalRecord.setFirstName("Roger");
		medicalRecord.setLastName("Boyd");
		medicalRecord.setBirthdate("09/06/2017");
		medicalRecord.setAllergies(new ArrayList<>());
		medicalRecord.setMedications(new ArrayList<>());
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		String medicalRecordJson = objectMapper.writeValueAsString(medicalRecord);
		
		//WHEN
		
		ResultActions  response = mockMvc.perform(MockMvcRequestBuilders.get("/medicalRecordALL"));
		//THEN
		//response.andDo(MockMvcResultHandlers.print());
		response.andExpect(MockMvcResultMatchers.status().isOk());
		String responseBody = response.andReturn().getResponse().getContentAsString();
		
		
		assertThat(responseBody).contains(medicalRecordJson);
	}
	
	@Test
	@DisplayName("Tester la récupération d'und'un dossier d'une personne grace a son nom , prenome")
	void getPerson_whenEntringFirstAndLastName_thenRuternMedicalRecord() throws Exception {
		//GIVEN 
		MedicalRecordsModel medicalRecord =new MedicalRecordsModel();
		medicalRecord.setFirstName("Roger");
		medicalRecord.setLastName("Boyd");
		medicalRecord.setBirthdate("09/06/2017");
		medicalRecord.setAllergies(new ArrayList<>());
		medicalRecord.setMedications(new ArrayList<>());
		
		ObjectMapper objectMapper = new ObjectMapper();
		String medicalRecordJson = objectMapper.writeValueAsString(medicalRecord);
		
		//WHEN
	
		ResultActions  response = mockMvc.perform(MockMvcRequestBuilders.get("/medicalRecord/{firstName}/{lastName}", "Roger", "Boyd"));
		
		//THEN
		//response.andDo(MockMvcResultHandlers.print());
		response.andExpect(MockMvcResultMatchers.status().isOk());
		String responseBody = response.andReturn().getResponse().getContentAsString();
		
		
		
		assertThat(responseBody).isEqualTo(medicalRecordJson);
	} 
	
	@Test
	@DisplayName("Tester l'ajout  d'un dossier")
	void postPerson_whenEntringAPerson_thenRuternPersonDTO() throws Exception {
		//GIVEN 
		MedicalRecordsModel medicalRecord =new MedicalRecordsModel();
		medicalRecord.setFirstName("Jhon");
		medicalRecord.setLastName("Snow");
		medicalRecord.setBirthdate("09/06/2017");
		
		ObjectMapper objectMapper = new ObjectMapper();
		String medicalRecordJson = objectMapper.writeValueAsString(medicalRecord);
		
		//WHEN
	
		ResultActions  response = mockMvc.perform(MockMvcRequestBuilders.post("/medicalRecord").contentType(MediaType.APPLICATION_JSON).content(medicalRecordJson));
		
		//THEN
	  	response.andDo(MockMvcResultHandlers.print());
		response.andExpect(MockMvcResultMatchers.status().isOk());
		String responseBody = response.andReturn().getResponse().getContentAsString();
		
		assertThat(responseBody).isEqualTo(medicalRecordJson);

	} 
	
	@Test
	@DisplayName("Tester la supprission d'une person  d'une personne")
	void deleteMedicalRecaord_whenEntringFirstAndLastName_thenRuternMedicalRecord() throws Exception {
		//GIVEN 
		MedicalRecordsModel medicalRecord =new MedicalRecordsModel();
		medicalRecord.setFirstName("Foster");
		medicalRecord.setLastName("Shepard");
		medicalRecord.setBirthdate("01/08/1980");
		medicalRecord.setAllergies(new ArrayList<>());
		medicalRecord.setMedications(new ArrayList<>());
		
		
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		String medicalRecordJson = objectMapper.writeValueAsString(medicalRecord);
		
		//WHEN
	
		ResultActions  response = mockMvc.perform(MockMvcRequestBuilders.delete("/medicalRecord/{firstName}/{lastName}", "Foster", "Shepard"));
		
		//THEN
		//response.andDo(MockMvcResultHandlers.print());
		response.andExpect(MockMvcResultMatchers.status().isOk());
		String responseBody = response.andReturn().getResponse().getContentAsString();
		
		assertThat(responseBody).isEqualTo(medicalRecordJson);

	} 
	
	@Test
	@DisplayName("Tester la modification  d'un dossier d'une persone")
	void putMedicalRecaord_whenEntringAMedicalRecaord_thenRuternMedicalRecaordDTO() throws Exception {
		//GIVEN 
		MedicalRecordsModel medicalRecord =new MedicalRecordsModel();
		medicalRecord.setFirstName("Lily");
		medicalRecord.setLastName("Cooper");
		medicalRecord.setBirthdate("01/01/2010");
		medicalRecord.setAllergies(new ArrayList<>());
		medicalRecord.setMedications(new ArrayList<>());
		
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		String medicalRecordJson = objectMapper.writeValueAsString(medicalRecord);
		
		//WHEN
	
		ResultActions  response = mockMvc.perform(MockMvcRequestBuilders.put("/medicalRecord").contentType(MediaType.APPLICATION_JSON).content(medicalRecordJson));
		
		//THEN
	  	//response.andDo(MockMvcResultHandlers.print());
		response.andExpect(MockMvcResultMatchers.status().isOk());
		String responseBody = response.andReturn().getResponse().getContentAsString();
		
		assertThat(responseBody).isEqualTo(medicalRecordJson);

	} 
}
