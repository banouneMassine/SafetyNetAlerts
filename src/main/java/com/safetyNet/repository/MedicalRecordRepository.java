package com.safetyNet.repository;

import java.util.List;

import com.safetyNet.model.MedicalRecordsModel;



public interface MedicalRecordRepository {
	
	//CRUD
	public List<MedicalRecordsModel> findAll();// Get ALL MedicalRecords
	public MedicalRecordsModel findByfirstName(String firstName , String lastName);// Get one MedicalRecord

	public void saveMedicalRecord(MedicalRecordsModel newMedicalRecord );
	public MedicalRecordsModel updateMedicalRecord(MedicalRecordsModel MedicalRecordModify);
	public void deleteMedicalRecordsModel(MedicalRecordsModel MedicalRecordDelete );
	
	//autre
	public void addMedicalRecord(MedicalRecordsModel MedicalRecord);
	

}
