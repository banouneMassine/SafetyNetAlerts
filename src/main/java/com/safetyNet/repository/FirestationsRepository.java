package com.safetyNet.repository;

import java.util.List;

import com.safetyNet.model.FirestationsModel;



public interface FirestationsRepository {

	//CRUD
		public List<FirestationsModel> findAll();// Get ALL Firestations
		public FirestationsModel findByAdresse(String adresse);// Get one Firestation
		void saveFireStation(FirestationsModel newFirestations);
		void addFirestation(FirestationsModel firestations);
		FirestationsModel updateFireStation(FirestationsModel updateFireStations);
		
		void removeFireStation(FirestationsModel FireStationToDelete);
		

}
