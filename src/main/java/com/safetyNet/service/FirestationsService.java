package com.safetyNet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.safetyNet.exceptions.FireStationIntrouvableException;
import com.safetyNet.model.FirestationsModel;
import com.safetyNet.repository.FirestationsRepository;

import lombok.Data;

@Data
@Service
public class FirestationsService {

	@Autowired
	FirestationsRepository firestationsRepository;
	public FirestationsModel addFireStation( FirestationsModel newFirestations)
	{
		if(newFirestations !=  null)
    	{
			return firestationsRepository.saveFireStation(newFirestations);
    	}
	    return null ;
	}
	
	public FirestationsModel updateFireStations(FirestationsModel updateFireStations) throws FireStationIntrouvableException
	{
		if(updateFireStations !=  null)
    	{
			if(firestationsRepository.updateFireStation(updateFireStations) == null) throw new FireStationIntrouvableException("La station"+ updateFireStations.address + " est introuvable ");
			return firestationsRepository.updateFireStation(updateFireStations);
     	  
    	}
	    return null ; 
	}
	
	public FirestationsModel deleteFireStations( String adresse) throws FireStationIntrouvableException
	{
		if(adresse !=  null)
    	{
			FirestationsModel FireStationToDelete =firestationsRepository.findByAdresse(adresse);
			if(FireStationToDelete == null ) throw new FireStationIntrouvableException("La station"+ adresse + " est introuvable");
			return	firestationsRepository.removeFireStation(FireStationToDelete);
     	   
    	}
	  return null ; 
	}
	
	
	public List<FirestationsModel> getFireStations() throws FireStationIntrouvableException
	{
		if(firestationsRepository.findAll().isEmpty()) throw new FireStationIntrouvableException("La liste des stations est vide");
		return firestationsRepository.findAll();
		
	}
	
	public FirestationsModel getFireStation(String adresse) throws FireStationIntrouvableException
	{
		if(firestationsRepository.findByAdresse(adresse) == null) throw new FireStationIntrouvableException("La station "+ adresse + "  est vide");
		return firestationsRepository.findByAdresse(adresse);
	}
	
	
}
