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
	public ResponseEntity<String> addFireStation( FirestationsModel newFirestations)
	{
		if(newFirestations !=  null)
    	{
			firestationsRepository.saveFireStation(newFirestations);
     	    return ResponseEntity.ok("la fireStation est ajoutée avec succès.");
    	}
	    else 
	    {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	public ResponseEntity<String>  updateFireStations(FirestationsModel updateFireStations) throws FireStationIntrouvableException
	{
		if(updateFireStations !=  null)
    	{
			if(firestationsRepository.updateFireStation(updateFireStations) == null) throw new FireStationIntrouvableException("La station"+ updateFireStations.address + " est introuvable ");
			firestationsRepository.updateFireStation(updateFireStations);
     	    return ResponseEntity.ok("la fireStation a été mise à jour avec succès.");
    	}
	    else 
	    {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	public ResponseEntity<String> deleteFireStations( String adresse) throws FireStationIntrouvableException
	{
		if(adresse !=  null)
    	{
			FirestationsModel FireStationToDelete =firestationsRepository.findByAdresse(adresse);
			if(FireStationToDelete == null ) throw new FireStationIntrouvableException("La station"+ adresse + " est introuvable");
			firestationsRepository.removeFireStation(FireStationToDelete);
     	    return ResponseEntity.ok("la fireStation a été supprimée avec succès.");
    	}
	    else 
	    {
	        return ResponseEntity.notFound().build();
	    }
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
