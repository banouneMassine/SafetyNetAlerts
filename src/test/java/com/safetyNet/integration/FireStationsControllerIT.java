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
import com.safetyNet.model.FirestationsModel;

@SpringBootTest
@AutoConfigureMockMvc
class FireStationsControllerIT {

	@Autowired
	MockMvc mockMvc ;
	
	@Test
	@DisplayName("Tester la récupération de la liste des stations")
	void getPersonsALL() throws Exception {
		//GIVEN 
		FirestationsModel fireStation = new FirestationsModel();
		fireStation.setAddress("1509 Culver St");
		fireStation.setStation(3);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String fireStationJson = objectMapper.writeValueAsString(fireStation);
		
		//WHEN
		ResultActions  response = mockMvc.perform(MockMvcRequestBuilders.get("/firestationAll"));
		//THEN
		//response.andDo(MockMvcResultHandlers.print());
		response.andExpect(MockMvcResultMatchers.status().isOk());
		String responseBody = response.andReturn().getResponse().getContentAsString();
		
		
		assertThat(responseBody).contains(fireStationJson);
	}
	
	@Test
	@DisplayName("Tester la récupération d'une station a l'aide a son adresse")
	void getPerson_whenEntringAdresse_thenRuternFireStation() throws Exception {
		//GIVEN 
		FirestationsModel fireStation = new FirestationsModel();
		fireStation.setAddress("1509 Culver St");
		fireStation.setStation(3);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String fireStationJson = objectMapper.writeValueAsString(fireStation);
		
		//WHEN
		ResultActions  response = mockMvc.perform(MockMvcRequestBuilders.get("/firestation/{adresse}","1509 Culver St"));
		
		//THEN
		//response.andDo(MockMvcResultHandlers.print());
		response.andExpect(MockMvcResultMatchers.status().isOk());
		String responseBody = response.andReturn().getResponse().getContentAsString();
		
		
		
		assertThat(responseBody).isEqualTo(fireStationJson);
	} 
	
	
	@Test
	@DisplayName("Tester l'ajout  d'une station")
	void postFireStation_whenEntringAFireStation_thenRuternFireStationDTO() throws Exception {
		//GIVEN 
		FirestationsModel fireStation = new FirestationsModel();
		fireStation.setAddress("adresse de station");
		fireStation.setStation(10);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String fireStationJson = objectMapper.writeValueAsString(fireStation);
		
		//WHEN
		ResultActions  response = mockMvc.perform(MockMvcRequestBuilders.post("/firestation").contentType(MediaType.APPLICATION_JSON).content(fireStationJson));
		
		//THEN
	  	response.andDo(MockMvcResultHandlers.print());
		response.andExpect(MockMvcResultMatchers.status().isOk());
		String responseBody = response.andReturn().getResponse().getContentAsString();
		
		assertThat(responseBody).isEqualTo(fireStationJson);

	} 
	
	@Test
	@DisplayName("Tester la supprission d'une station")
	void deleteFireStation_whenEntringAdresse_thenRuternFireStationDTO() throws Exception {
		//GIVEN 
		FirestationsModel fireStation = new FirestationsModel();
		fireStation.setAddress("489 Manchester St");
		fireStation.setStation(4);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String fireStationJson = objectMapper.writeValueAsString(fireStation);
		
		//WHEN
		ResultActions  response = mockMvc.perform(MockMvcRequestBuilders.delete("/firestation/{adresse}", "489 Manchester St"));
		
		//THEN
		//response.andDo(MockMvcResultHandlers.print());
		response.andExpect(MockMvcResultMatchers.status().isOk());
		String responseBody = response.andReturn().getResponse().getContentAsString();
		
		assertThat(responseBody).isEqualTo(fireStationJson);

	} 
	
	@Test
	@DisplayName("Tester la modification  d'une station")
	void putFireStation_whenEntringAFireStation_thenRuternFireStationDTO() throws Exception {
		//GIVEN 
		FirestationsModel fireStation = new FirestationsModel();
		fireStation.setAddress("892 Downing Ct");
		fireStation.setStation(11);

		ObjectMapper objectMapper = new ObjectMapper();
		String fireStationJson = objectMapper.writeValueAsString(fireStation);
		
		//WHEN
		ResultActions  response = mockMvc.perform(MockMvcRequestBuilders.put("/firestation").contentType(MediaType.APPLICATION_JSON).content(fireStationJson));
		
		//THEN
	  	//response.andDo(MockMvcResultHandlers.print());
		response.andExpect(MockMvcResultMatchers.status().isOk());
		String responseBody = response.andReturn().getResponse().getContentAsString();
		
		assertThat(responseBody).isEqualTo(fireStationJson);

	}

}
