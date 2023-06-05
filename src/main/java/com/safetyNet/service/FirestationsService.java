package com.safetyNet.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetyNet.DTO.FireStationsDTO;
import com.safetyNet.exceptions.FireStationIntrouvableException;
import com.safetyNet.model.FirestationsModel;
import com.safetyNet.repository.FirestationsRepository;


@Service
public class FirestationsService {

	@Autowired
	FirestationsRepository firestationsRepository;
	public FireStationsDTO addFireStation( FirestationsModel newFirestations)
	{
		if(newFirestations !=  null)
    	{
			return this.convertToDTO(firestationsRepository.saveFireStation(newFirestations));
    	}
	    return null ;
	}
	
	public FireStationsDTO updateFireStations(FirestationsModel updateFireStations) throws FireStationIntrouvableException
	{
		if(updateFireStations !=  null)
    	{
			if(firestationsRepository.updateFireStation(updateFireStations) == null) throw new FireStationIntrouvableException("La station"+ updateFireStations.address + " est introuvable ");
			return this.convertToDTO(firestationsRepository.updateFireStation(updateFireStations));
     	  
    	}
	    return null ; 
	}
	
	public FireStationsDTO deleteFireStations( String adresse) throws FireStationIntrouvableException
	{
		if(adresse !=  null)
    	{
			FirestationsModel FireStationToDelete =firestationsRepository.findByAdresse(adresse);
			if(FireStationToDelete == null ) throw new FireStationIntrouvableException("La station"+ adresse + " est introuvable");
			return	this.convertToDTO(firestationsRepository.removeFireStation(FireStationToDelete));
     	   
    	}
	  return null ; 
	}
	
	
	public List<FireStationsDTO> getFireStations() throws FireStationIntrouvableException
	{
		if(firestationsRepository.findAll().isEmpty()) throw new FireStationIntrouvableException("La liste des stations est vide");
		List<FireStationsDTO> listFireStationsDTO = new ArrayList<>();
		for(FirestationsModel firestation :  firestationsRepository.findAll())
		{
			listFireStationsDTO.add(this.convertToDTO(firestation));
		}
		return listFireStationsDTO;
		
	}
	
	public FirestationsModel getFireStation(String adresse) throws FireStationIntrouvableException
	{
		if(firestationsRepository.findByAdresse(adresse) == null) throw new FireStationIntrouvableException("La station "+ adresse + "  est vide");
		return firestationsRepository.findByAdresse(adresse);
	}
	
	public FireStationsDTO convertToDTO(FirestationsModel firestationsModel)
	{
		FireStationsDTO fireStationsDTO = new FireStationsDTO();
		fireStationsDTO.setAddress(firestationsModel.getAddress());
		fireStationsDTO.setStation(firestationsModel.getStation());
		
		return fireStationsDTO ; 
	}
	
}
