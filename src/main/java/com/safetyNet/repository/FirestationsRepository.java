package com.safetyNet.repository;

import java.util.List;

import com.safetyNet.model.FirestationsModel;



public interface FirestationsRepository {

	//CRUD
		public List<FirestationsModel> findAll();// Get ALL Firestations
		public FirestationsModel findByAdresse(String adresse);// Get one Firestation
		public FirestationsModel saveFireStation(FirestationsModel newFirestations);
		public FirestationsModel updateFireStation(FirestationsModel updateFireStations);
		public FirestationsModel removeFireStation(FirestationsModel FireStationToDelete);
		
		// autres methodes
		void addFirestation(FirestationsModel firestations);
		List<FirestationsModel> findByStationNumber(int stationNumber);
}
