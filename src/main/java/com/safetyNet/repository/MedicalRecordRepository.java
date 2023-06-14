package com.safetyNet.repository;

import java.util.List;

import com.safetyNet.model.MedicalRecordsModel;



public interface MedicalRecordRepository {
	
	//CRUD
	public List<MedicalRecordsModel> findAll();// Get ALL MedicalRecords
	public MedicalRecordsModel findByName(String firstName , String lastName);// Get one MedicalRecord

	public MedicalRecordsModel saveMedicalRecord(MedicalRecordsModel newMedicalRecord );
	public MedicalRecordsModel updateMedicalRecord(MedicalRecordsModel MedicalRecordModify);
	public MedicalRecordsModel deleteMedicalRecordsModel(MedicalRecordsModel MedicalRecordDelete );
	
	//autre
	public void addMedicalRecord(MedicalRecordsModel MedicalRecord);
	

}
