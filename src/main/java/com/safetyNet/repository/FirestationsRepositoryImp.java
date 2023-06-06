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
			if(fireStation.getAddress().equalsIgnoreCase(adresse))
			{
				return fireStation;
			}
				
		}
		return null;
	}
	
	@Override
	public List<FirestationsModel> findByStationNumber(int stationNumber) {
		List<FirestationsModel> listDeStation = new ArrayList<>();
		for(FirestationsModel fireStation : this.listOfFireStations)
		{
			if(fireStation.getStation() == stationNumber)
			{
				listDeStation.add(fireStation);
			}
				
		}
		return listDeStation;
	}
	
	@Override 
	public FirestationsModel  saveFireStation( FirestationsModel newFirestations)
	{
		if(this.listOfFireStations.add(newFirestations))
		{
			return newFirestations ;
		}
		return null ; 
		 
	}
	
	@Override
	public FirestationsModel updateFireStation(FirestationsModel updateFireStations)
	{
		FirestationsModel FireStationsToModify = this.findByAdresse(updateFireStations.getAddress());
		if(FireStationsToModify != null )
		{
			FireStationsToModify.setStation(updateFireStations.getStation());
			return FireStationsToModify;
		}
		return null ;
		
	}
	
	@Override
	public FirestationsModel removeFireStation(FirestationsModel FireStationToDelete)
	{
		
		 if( this.listOfFireStations.remove(FireStationToDelete))
		 {
			 return FireStationToDelete ; 
		 }
		 return null ; 
	}

}
