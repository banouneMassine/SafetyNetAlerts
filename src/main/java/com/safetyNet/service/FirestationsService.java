package com.safetyNet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
	
	public ResponseEntity<String>  updateFireStations(FirestationsModel updateFireStations)
	{
		if(updateFireStations !=  null)
    	{
			firestationsRepository.updateFireStation(updateFireStations);
     	    return ResponseEntity.ok("la fireStation a été mise à jour avec succès.");
    	}
	    else 
	    {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	public ResponseEntity<String> deleteFireStations( String adresse)
	{
		if(adresse !=  null)
    	{
			firestationsRepository.removeFireStation(adresse);
     	    return ResponseEntity.ok("la fireStation a été supprimée avec succès.");
    	}
	    else 
	    {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	
	public List<FirestationsModel> getFireStations()
	{
		return firestationsRepository.findAll();
		
	}
	
	public FirestationsModel getFireStation(String adresse)
	{
		return firestationsRepository.findByAdresse(adresse);
	}
	
	
}
