package com.safetyNet.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

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
import com.safetyNet.DTO.ChildDTO;
import com.safetyNet.DTO.FireDTO;
import com.safetyNet.DTO.FloodDTO;
import com.safetyNet.DTO.PersonInfoDTO;
import com.safetyNet.DTO.PersonsCoveredByTheFirestationDTO;

@SpringBootTest
@AutoConfigureMockMvc
class GestionDesUrlsControllerIT {

	@Autowired
	MockMvc mockMvc;

	@Test
	@DisplayName("Tester la récupération de la liste des personnes couvertes par la station correspondante")
	void listOfPeopleCoveredByFirestation_wheneEntringStationNumber_ThenRuturnListOfPersons() throws Exception {
		// GIVEN
		int stationNumber = 1;

		PersonsCoveredByTheFirestationDTO person1 = new PersonsCoveredByTheFirestationDTO();
		person1.setFirstName("Peter");
		person1.setLastName("Duncan");
		person1.setAdresse("644 Gershwin Cir");
		person1.setPhone("841-874-6512");

		ObjectMapper objectMapper = new ObjectMapper();
		String person1Json = objectMapper.writeValueAsString(person1);

		// WHEN

		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/firestation")
				.param("stationNumber", String.valueOf(stationNumber)).contentType(MediaType.APPLICATION_JSON));
		// THEN
		// response.andDo(MockMvcResultHandlers.print());
		response.andExpect(MockMvcResultMatchers.status().isOk());
		String responseBody = response.andReturn().getResponse().getContentAsString();

		assertThat(responseBody).contains(person1Json);
	}

	@Test
	@DisplayName("Tester la récupération de la liste des enfants d'une adresse")
	void listOfChildLivingAtThisAddress_wheneEntringAdresse_ThenRuturnListOfChild() throws Exception {
		String address = "834 Binoc Ave";
		ChildDTO childDTO = new ChildDTO();
		childDTO.setFirstName("Tessa");
		childDTO.setLastName("Carman");
		childDTO.setAge(11);
		childDTO.setFamilles(new ArrayList<>());

		ObjectMapper objectMapper = new ObjectMapper();
		String childJson = objectMapper.writeValueAsString(childDTO);

		// WHEN

		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/childAlert").param("address", address)
				.contentType(MediaType.APPLICATION_JSON));
		// THEN
		// response.andDo(MockMvcResultHandlers.print());
		response.andExpect(MockMvcResultMatchers.status().isOk());
		String responseBody = response.andReturn().getResponse().getContentAsString();

		assertThat(responseBody).contains(childJson);

	}

	@Test
	@DisplayName("Tester la récupération de laliste des numéros de téléphone des résidents desservis par la caserne de pompiers ")
	void listOfNumberPhoneByFireStation_wheneEntringStationNumber_ThenRuturnListOfNumberPhone()
			throws Exception {
		int stationNumber = 2;
		String NumberPhoneExpected = "841-874-6513";

		// WHEN

		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/phoneAlert")
				.param("stationNumber", String.valueOf(stationNumber)).contentType(MediaType.APPLICATION_JSON));
		// THEN
		//response.andDo(MockMvcResultHandlers.print());
		response.andExpect(MockMvcResultMatchers.status().isOk());
		String responseBody = response.andReturn().getResponse().getContentAsString();

		assertThat(responseBody).contains(NumberPhoneExpected);
	}

	@Test
	@DisplayName("Tester la récupération des adresses mail de tous les habitants de la ville")
	void listOfEmailByCity_wheneEntringcity_ThenRuturnListOfEmails() throws Exception {
		// GIVEN
		String city = "Culver";
		String adresseEmailExpected =  "lily@email.com";
		// WHEN

		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/communityEmail")
				.param("city", city).contentType(MediaType.APPLICATION_JSON));
		// THEN
		//response.andDo(MockMvcResultHandlers.print());
		response.andExpect(MockMvcResultMatchers.status().isOk());
		String responseBody = response.andReturn().getResponse().getContentAsString();

		assertThat(responseBody).contains(adresseEmailExpected);
	}
	
	@Test
	@DisplayName("Tester la récupération de la lists des habitants vivant à l’adresse donnée ainsi que le numéro de la station desservant")
	void listOfPersonsWithFireStationByAdresse_wheneEntringAdresse_ThenRuturnListOfPersoneAndFireStation() throws Exception
	{
		// GIVEN
		String adresse = "748 Townings Dr";
		
		FireDTO fireDTO = new FireDTO();
		fireDTO.setFirstName("Foster");
		fireDTO.setLastName("Shepard");
		fireDTO.setPhone("841-874-6544");
		fireDTO.setAge(43);
		fireDTO.setMedications(new ArrayList<>());
		fireDTO.setAllergies(new ArrayList<>());
		
		ObjectMapper objectMapper = new ObjectMapper();
		String fireDTOJson = objectMapper.writeValueAsString(fireDTO);

		// WHEN

		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/fire")
				.param("address", adresse)
				.contentType(MediaType.APPLICATION_JSON));
		// THEN
		// response.andDo(MockMvcResultHandlers.print());
		response.andExpect(MockMvcResultMatchers.status().isOk());
		String responseBody = response.andReturn().getResponse().getContentAsString();

		assertThat(responseBody).contains(fireDTOJson);
		
	}
	

	@Test
	@DisplayName("Tester la récupération de la lists de tous les foyers desservis par la caserne")
	void listOFHomesServedByFireStations_wheneEntringStationNumber_ThenRuturnListOfFoyer() throws Exception
	{
		// GIVEN		
		
		FloodDTO floodDTO = new FloodDTO();
		floodDTO.setAdresse("748 Townings Dr");
		FireDTO fireDTO = new FireDTO();
		
		fireDTO.setFirstName("Foster");
		fireDTO.setLastName("Shepard");
		fireDTO.setPhone("841-874-6544");
		fireDTO.setAge(43);
		fireDTO.setMedications(new ArrayList<>());
		fireDTO.setAllergies(new ArrayList<>());

		
		List<FireDTO> listFireDTO = new ArrayList<>();
		listFireDTO.add(fireDTO);
		floodDTO.setFireDTO(listFireDTO);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String floodDTOJson = objectMapper.writeValueAsString(floodDTO);
		
		// WHEN

		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/flood").param("stations",  "3")
				.contentType(MediaType.APPLICATION_JSON));
		// THEN
		// response.andDo(MockMvcResultHandlers.print());
		response.andExpect(MockMvcResultMatchers.status().isOk());
		String responseBody = response.andReturn().getResponse().getContentAsString();

		assertThat(responseBody).contains(floodDTOJson);
	}
	@Test
	@DisplayName("Tester la récupération des informations d'une personne")
	void listOfPersonInfos_wheneEntringFirstAndLastName_ThenRuturnListOfInfos() throws Exception
	{
		//GIVEN
		PersonInfoDTO personInfoDTO = new PersonInfoDTO();
		personInfoDTO.setLastName("Shepard");
		personInfoDTO.setAddress("748 Townings Dr");
		personInfoDTO.setAge(43) ;
		personInfoDTO.setEmail("jaboyd@email.com");
		personInfoDTO.setAllergies(new ArrayList<>());
		personInfoDTO.setMedications(new ArrayList<>());
		personInfoDTO.setFamilles(new ArrayList<>());
		
		ObjectMapper objectMapper = new ObjectMapper();
		String personInfoDTOJson = objectMapper.writeValueAsString(personInfoDTO);
		
		// WHEN

		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/personInfo")
				.param("firstName",  "Foster")
				.param("lastName",  "Shepard")
				.contentType(MediaType.APPLICATION_JSON));
		// THEN
		 response.andDo(MockMvcResultHandlers.print());
		response.andExpect(MockMvcResultMatchers.status().isOk());
		String responseBody = response.andReturn().getResponse().getContentAsString();

		assertThat(responseBody).contains(personInfoDTOJson);
		
	}
}
