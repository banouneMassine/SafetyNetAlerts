package com.safetyNet.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetyNet.DTO.FireStationsDTO;
import com.safetyNet.exceptions.FireStationIntrouvableException;
import com.safetyNet.model.FirestationsModel;
import com.safetyNet.repository.FirestationsRepository;


@Service
public class FirestationsService {

	private static final Logger logger = LogManager.getLogger("FirestationsService");
	
	@Autowired
	FirestationsRepository firestationsRepository;
	public FireStationsDTO addFireStation( FirestationsModel newFirestations)
	{
		if(newFirestations !=  null)
    	{
			logger.info("Ajouter la station  "+ newFirestations.getAddress() );
			return this.convertToDTO(firestationsRepository.saveFireStation(newFirestations));
			
    	}
		
	    return null ;
	}
	
	public FireStationsDTO updateFireStations(FirestationsModel updateFireStations) throws FireStationIntrouvableException
	{
		
			if(firestationsRepository.updateFireStation(updateFireStations) == null) 
			{
				logger.error("La station  "+updateFireStations.getAddress() +" est introuvable" );
				throw new FireStationIntrouvableException("La station "+ updateFireStations.address + " est introuvable ");
			}
			logger.info("Modifier la station  "+ updateFireStations.getAddress() );
			return this.convertToDTO(firestationsRepository.updateFireStation(updateFireStations));

	}
	
	public FireStationsDTO deleteFireStations( String adresse) throws FireStationIntrouvableException
	{
		
			FirestationsModel FireStationToDelete =firestationsRepository.findByAdresse(adresse);
			if(FireStationToDelete == null ) 
				{
					logger.error("La station  "+ adresse +" est introuvable" );
					throw new FireStationIntrouvableException("La station "+ adresse + " est introuvable");
				}
			logger.info("Supprimer la station  "+ FireStationToDelete.getAddress() );
			return	this.convertToDTO(firestationsRepository.removeFireStation(FireStationToDelete));
    	
	}
	
	
	public List<FireStationsDTO> getFireStations() throws FireStationIntrouvableException
	{
		if(firestationsRepository.findAll().isEmpty()) throw new FireStationIntrouvableException("La liste des stations est vide");
		List<FireStationsDTO> listFireStationsDTO = new ArrayList<>();
		for(FirestationsModel firestation :  firestationsRepository.findAll())
		{
			listFireStationsDTO.add(this.convertToDTO(firestation));
		}
		logger.info("Récuperer la liste des stations");
		return listFireStationsDTO;
		
	}
	
	public FireStationsDTO getFireStation(String adresse) throws FireStationIntrouvableException
	{
		FirestationsModel myFirestationsModel = firestationsRepository.findByAdresse(adresse);
		if( myFirestationsModel == null)
			{
				logger.error("La station "+ adresse + "  est introuvable");
				throw new FireStationIntrouvableException("La station "+ adresse + "  est introuvable");
			}
		logger.info("Récuperer la liste " + adresse);
		return this.convertToDTO(myFirestationsModel);
	}
	
	public FireStationsDTO convertToDTO(FirestationsModel firestationsModel)
	{
		FireStationsDTO fireStationsDTO = new FireStationsDTO();
		fireStationsDTO.setAddress(firestationsModel.getAddress());
		fireStationsDTO.setStation(firestationsModel.getStation());
		
		return fireStationsDTO ; 
	}
	
}
