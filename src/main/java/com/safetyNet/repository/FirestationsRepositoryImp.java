package com.safetyNet.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetyNet.model.FirestationsModel;


@Repository
public class FirestationsRepositoryImp implements FirestationsRepository {

	
	private List<FirestationsModel> listOfFireStations = new ArrayList<>();
	
	@Override
	public void addFirestation(FirestationsModel firestations) {
		this.listOfFireStations.add(firestations);
	}
	
	@Override
	public List<FirestationsModel> findAll() {
		return this.listOfFireStations;
	}

	@Override
	public FirestationsModel findByAdresse(String adresse) {
		for(FirestationsModel fireStation : this.listOfFireStations)
		{
			if(fireStation.address.equalsIgnoreCase(adresse))
			{
				return fireStation;
			}
				
		}
		return null;
	}
	
	@Override 
	public void  saveFireStation( FirestationsModel newFirestations)
	{
		this.listOfFireStations.add(newFirestations);
	}
	
	@Override
	public void updateFireStation(FirestationsModel updateFireStations)
	{
		FirestationsModel FireStationsToModify = this.findByAdresse(updateFireStations.address);
		FireStationsToModify.station= updateFireStations.station;
		
	}
	
	@Override
	public void removeFireStation(String adresse)
	{
		FirestationsModel FireStationToDelete =this.findByAdresse(adresse);
		this.listOfFireStations.remove(FireStationToDelete);
	}

}
