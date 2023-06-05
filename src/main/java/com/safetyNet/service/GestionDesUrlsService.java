package com.safetyNet.service;


import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetyNet.DTO.ChildDTO;
import com.safetyNet.DTO.EmailByCityDTO;
import com.safetyNet.DTO.PersonsCoveredByTheFirestationDTO;
import com.safetyNet.DTO.PhoneNumberByStationNumberDTO;
import com.safetyNet.model.FirestationsModel;
import com.safetyNet.model.MedicalRecordsModel;
import com.safetyNet.model.PersonsModel;
import com.safetyNet.repository.FirestationsRepository;
import com.safetyNet.repository.MedicalRecordRepository;
import com.safetyNet.repository.PersonsRepository;

@Service
public class GestionDesUrlsService {
	

	@Autowired
	PersonsRepository personsRepository;
	
	@Autowired
	FirestationsRepository firestationsRepository;
	
	@Autowired 
	MedicalRecordRepository medicalRecordRepository;
	
	
	
	List<PersonsModel> listDesPersonnes ;
	List<FirestationsModel> listDeStation;
	List<MedicalRecordsModel> listDesDossiers;
	
	//Cette url doit retourner une liste des personnes couvertes par la caserne de pompiers correspondante
	public List<PersonsCoveredByTheFirestationDTO> getListOfPeopleCoveredByTheFirestation(int stationNumber ) // il reste des choses a ajouté 
	{
		listDesPersonnes = personsRepository.findAll();
		listDeStation= firestationsRepository.findAll();
		List<PersonsCoveredByTheFirestationDTO> listDesPersonnesCorrespondants = new ArrayList<>();
		for(FirestationsModel fireStation : listDeStation)
		{
			if(fireStation.station == stationNumber)
			{
				for(PersonsModel personne : listDesPersonnes)
				{
					if(fireStation.address.equals(personne.address))
						{
							PersonsCoveredByTheFirestationDTO personsCoveredByTheFirestationDTO = new PersonsCoveredByTheFirestationDTO();
							personsCoveredByTheFirestationDTO.setFirstName(personne.getFirstName());
							personsCoveredByTheFirestationDTO.setLastName(personne.getLastName());
							personsCoveredByTheFirestationDTO.setAdresse( personne.getAddress());
							personsCoveredByTheFirestationDTO.setPhone(personne.getPhone());
							
							listDesPersonnesCorrespondants.add(personsCoveredByTheFirestationDTO);
						}
				}
			
			}
		}
		return listDesPersonnesCorrespondants; 
	}
	
	
	//Cette url doit retourner une liste d'enfants  habitant à cette adresse
	public List<ChildDTO> getListOfChild(String address)
	{
		
		listDesPersonnes = personsRepository.findAll();
		listDesDossiers= medicalRecordRepository.findAll();
		List<ChildDTO> listDesEnfantsCorrespondants= new ArrayList<>();
		for(PersonsModel personnes : listDesPersonnes)
		{
			if(personnes.address.equals(address))
			{
				for(MedicalRecordsModel enfants : listDesDossiers )
				{
				
					if(personnes.firstName.equals(enfants.firstName) && personnes.lastName.equals(enfants.lastName) &&  GestionDesUrlsService.calculateAge(enfants.birthdate) <= 18)
					{
						ChildDTO childDTO = new ChildDTO();
						childDTO.setFirstName(personnes.getFirstName());
						childDTO.setLastName(personnes.getLastName());
						childDTO.setAge(GestionDesUrlsService.calculateAge(enfants.getBirthdate()));
						childDTO.setFamilles(personsRepository.getFamilles(personnes));
						
						listDesEnfantsCorrespondants.add(childDTO); 
					}
				}
			}
		}
		
		return listDesEnfantsCorrespondants; 
	}
	
	
	//Cette url doit retourner une liste des numéros de téléphone des résidents desservis par la caserne de pompiers
	public List<PhoneNumberByStationNumberDTO> getListofNumber(int stationNumber)
	{
		
		List<PhoneNumberByStationNumberDTO> listDesNumurosCorrespondants = new ArrayList<>();
		 
		for (PersonsCoveredByTheFirestationDTO personsCoveredByTheFirestationDTO : getListOfPeopleCoveredByTheFirestation(stationNumber))
		{
			PhoneNumberByStationNumberDTO phoneNumberByStationNumberDTO = new PhoneNumberByStationNumberDTO();
			phoneNumberByStationNumberDTO.setPhone(personsCoveredByTheFirestationDTO.getPhone());
			listDesNumurosCorrespondants.add(phoneNumberByStationNumberDTO);
		}
		return listDesNumurosCorrespondants ; 
	}
	
	//Cette url doit retourner les adresses mail de tous les habitants de la ville.
	public List<EmailByCityDTO> getListOFEmail(String city)
	{
		listDesPersonnes = personsRepository.findAll();
		List<EmailByCityDTO> listDesEmailCorrespondants = new ArrayList<>();
		for(PersonsModel person : listDesPersonnes)
		{
			if(person.getCity().equalsIgnoreCase(city))
			{
				EmailByCityDTO emailByCityDTO = new EmailByCityDTO();
				emailByCityDTO.setEmail(person.getEmail());
				listDesEmailCorrespondants.add(emailByCityDTO);
			}
		}
		return listDesEmailCorrespondants;
	}
	
	
	/*****calculer lage d'une personne***/
	public static int calculateAge(String dateOfBirth) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    LocalDate birthDate = LocalDate.parse(dateOfBirth, formatter);
	    LocalDate currentDate = LocalDate.now();
	    Period period = Period.between(birthDate, currentDate);
	    return period.getYears()+1;
	}
	
}


