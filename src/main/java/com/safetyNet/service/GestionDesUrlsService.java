package com.safetyNet.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetyNet.DTO.ChildDTO;
import com.safetyNet.DTO.FireDTO;
import com.safetyNet.DTO.FloodDTO;
import com.safetyNet.DTO.PersonInfoDTO;
import com.safetyNet.DTO.PersonsCoveredByTheFirestationDTO;
import com.safetyNet.model.FirestationsModel;
import com.safetyNet.model.MedicalRecordsModel;
import com.safetyNet.model.PersonsModel;
import com.safetyNet.repository.FirestationsRepository;
import com.safetyNet.repository.MedicalRecordRepository;
import com.safetyNet.repository.PersonsRepository;

@Service
public class GestionDesUrlsService {

	private static final Logger logger = LogManager.getLogger("GestionDesUrlsService");
	
	@Autowired
	PersonsRepository personsRepository;

	@Autowired
	FirestationsRepository firestationsRepository;

	@Autowired
	MedicalRecordRepository medicalRecordRepository;

	List<PersonsModel> listDesPersonnes;
	List<FirestationsModel> listDeStation;
	List<MedicalRecordsModel> listDesDossiers;

	// Cette url doit retourner une liste des personnes couvertes par la caserne de
	// pompiers correspondante
	public List<PersonsCoveredByTheFirestationDTO> getListOfPeopleCoveredByTheFirestation(int stationNumber) // il reste des choses a ajouté																										
	{
		listDesPersonnes = personsRepository.findAll();
		listDeStation = firestationsRepository.findByStationNumber(stationNumber);
		List<PersonsCoveredByTheFirestationDTO> listDesPersonnesCorrespondants = new ArrayList<>();
		for (FirestationsModel fireStation : listDeStation) {
 
			for (PersonsModel personne : listDesPersonnes) {
				if (fireStation.getAddress().equals(personne.getAddress())) {
					PersonsCoveredByTheFirestationDTO personsCoveredByTheFirestationDTO = new PersonsCoveredByTheFirestationDTO();
					personsCoveredByTheFirestationDTO.setFirstName(personne.getFirstName());
					personsCoveredByTheFirestationDTO.setLastName(personne.getLastName());
					personsCoveredByTheFirestationDTO.setAdresse(personne.getAddress());
					personsCoveredByTheFirestationDTO.setPhone(personne.getPhone());

					listDesPersonnesCorrespondants.add(personsCoveredByTheFirestationDTO);
				}
			}

		}
		logger.info("Récuperer la liste des personnes couvertes par la caserne de pompiers numéro "+ stationNumber);
		return listDesPersonnesCorrespondants;
	}

	// Cette url doit retourner une liste d'enfants habitant à cette adresse
	public List<ChildDTO> getListOfChild(String address) {

		listDesPersonnes = personsRepository.findByAdresse(address);
		listDesDossiers = medicalRecordRepository.findAll();
		List<ChildDTO> listDesEnfantsCorrespondants = new ArrayList<>();
		for (PersonsModel personnes : listDesPersonnes) {
			for (MedicalRecordsModel enfants : listDesDossiers) {

				if (personnes.getFirstName().equals(enfants.getFirstName())
						&& personnes.getLastName().equals(enfants.getLastName())
						&& GestionDesUrlsService.calculateAge(enfants.getBirthdate()) <= 18) { 
					ChildDTO childDTO = new ChildDTO();
					childDTO.setFirstName(personnes.getFirstName());
					childDTO.setLastName(personnes.getLastName());
					childDTO.setAge(GestionDesUrlsService.calculateAge(enfants.getBirthdate()));
					childDTO.setFamilles(personsRepository.getFamilles(personnes));

					listDesEnfantsCorrespondants.add(childDTO);
				}
			}

		}
		logger.info("Récuperer la liste d'enfants habitant à l'adresse "+ address);
		return listDesEnfantsCorrespondants;
	}

	// Cette url doit retourner une liste des numéros de téléphone des résidents
	// desservis par la caserne de pompiers
	public List<String> getListofNumber(int stationNumber) {
 
		List<String> listDesNumurosCorrespondants = new ArrayList<>();
		List<PersonsCoveredByTheFirestationDTO> listPesronnes = getListOfPeopleCoveredByTheFirestation(stationNumber);
		for (PersonsCoveredByTheFirestationDTO personsCoveredByTheFirestationDTO : listPesronnes ) {
	
			listDesNumurosCorrespondants.add(personsCoveredByTheFirestationDTO.getPhone());
		}
		logger.info("Récuperer la liste des numéros de téléphone des résidents desservis par la caserne de pompiers numéro "+ stationNumber);
		return listDesNumurosCorrespondants;
	}

	// Cette url doit retourner la liste des habitants vivant à l’adresse donnée
	// ainsi que le numéro de la caserne de pompiers la desservant
	public List<FireDTO> getListOfPersonAndFirestation(String adresse) {
		listDesPersonnes = personsRepository.findByAdresse(adresse);
		listDesDossiers = medicalRecordRepository.findAll();

		List<FireDTO> listdesHabitants = new ArrayList<>();

		for (PersonsModel habitant : listDesPersonnes) {
			for (MedicalRecordsModel medicalRecord : listDesDossiers) {
				if (habitant.getFirstName().equalsIgnoreCase(medicalRecord.getFirstName())
						&& habitant.getLastName().equalsIgnoreCase(medicalRecord.getLastName())) {

					FireDTO fireDTO = new FireDTO();
					
					fireDTO.setFirstName(habitant.getFirstName());
					fireDTO.setLastName(habitant.getLastName());
					fireDTO.setPhone(habitant.getPhone());
					fireDTO.setAge(GestionDesUrlsService.calculateAge(medicalRecord.birthdate));
					fireDTO.setMedications(medicalRecord.getMedications());
					fireDTO.setAllergies(medicalRecord.getAllergies());

					listdesHabitants.add(fireDTO);
				}

			}
		}
		logger.info("Récuperer la liste des habitants vivant à l’adresse "+ adresse + " ainsi que le numéro de la caserne de pompiers la desservant ");
		return listdesHabitants;
 
	}

	public List<FloodDTO> getListOfServedByFireStations(List<Integer> stations) {

		for (int station : stations) { 
			listDeStation = firestationsRepository.findByStationNumber(station);
			listDesPersonnes = personsRepository.findAll();
			listDesDossiers = medicalRecordRepository.findAll();

			
			List<FloodDTO> listdesFoyer = new ArrayList<>();
			for (FirestationsModel fireStation : listDeStation) {
				FloodDTO floodDTO = new FloodDTO();
				floodDTO.setAdresse(fireStation.getAddress());

				List<FireDTO> listdesHabitants = new ArrayList<>();
				
				for (PersonsModel personne : listDesPersonnes) {

					for (MedicalRecordsModel medicalRecord : listDesDossiers) {
						if (personne.getFirstName().equalsIgnoreCase(medicalRecord.getFirstName())
								&& personne.getLastName().equalsIgnoreCase(medicalRecord.getLastName())) {

							if (fireStation.getAddress().equalsIgnoreCase(personne.getAddress())) {

								
								FireDTO fireDTO = new FireDTO();
								
								fireDTO.setFirstName(personne.getFirstName());
								fireDTO.setLastName(personne.getLastName());
								fireDTO.setPhone(personne.getPhone());
								fireDTO.setAge(GestionDesUrlsService.calculateAge(medicalRecord.birthdate));
								fireDTO.setMedications(medicalRecord.getMedications());
								fireDTO.setAllergies(medicalRecord.getAllergies());

								listdesHabitants.add(fireDTO);

							}
						}
					}

				}
				floodDTO.setFireDTO(listdesHabitants);
				listdesFoyer.add(floodDTO);

			}
			logger.info("Récuperer la liste de tous les foyers desservis par les casernes "+ stations);
			return listdesFoyer;
		}
		return null;

	}
	
	public PersonInfoDTO getPersonInfos (String firstName , String lastName)
	{
		PersonsModel person = personsRepository.findByName(firstName,lastName);
		List<PersonsModel> familles = personsRepository.getFamilles(person);
		listDesDossiers = medicalRecordRepository.findAll();
		logger.info("Récuperer les information de la personne " +firstName+ "  "+ lastName );
		for(MedicalRecordsModel medicalRecord : listDesDossiers)
		{
			if(person.getFirstName().equalsIgnoreCase(medicalRecord.getFirstName()) && person.getLastName().equalsIgnoreCase(medicalRecord.getLastName()))
			{
				PersonInfoDTO personInfoDTO = new PersonInfoDTO();
				personInfoDTO.setLastName(person.getLastName());
				personInfoDTO.setAddress(person.getAddress());
				personInfoDTO.setAge(GestionDesUrlsService.calculateAge(medicalRecord.getBirthdate()));
				personInfoDTO.setEmail(person.getEmail());
				personInfoDTO.setAllergies(medicalRecord.getAllergies());
				personInfoDTO.setMedications(medicalRecord.getMedications());
				personInfoDTO.setFamilles(familles);
				return personInfoDTO ;
			}

		}
		
		return null ; 
		
	}

	// Cette url doit retourner les adresses mail de tous les habitants de la ville.
	public List<String> getListOFEmail(String city) {
		listDesPersonnes = personsRepository.findAll();
		List<String> listDesEmailCorrespondants = new ArrayList<>();
		for (PersonsModel person : listDesPersonnes) {
			if (person.getCity().equalsIgnoreCase(city)) {	
				listDesEmailCorrespondants.add(person.getEmail());
			}
		}
		logger.info("Récuperer la liste des  adresses mails de tous les habitants de la ville " + city);
		return listDesEmailCorrespondants;
	}

	/***** calculer lage d'une personne ***/
	public static int calculateAge(String dateOfBirth) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate birthDate = LocalDate.parse(dateOfBirth, formatter);
		LocalDate currentDate = LocalDate.now();
		Period period = Period.between(birthDate, currentDate);
		return period.getYears() + 1;
	}

}
