package service;

import model.Patient;
import repository.PatientRepository;
import utility.IdGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * PatientManager handles all business logic related to patients.
 *
 * Responsibilities include:
 *  - Creating new patients with unique IDs
 *  - Retrieving all patients
 *  - Deleting patients by ID
 *  - Delegating CSV persistence to PatientRepository
 */
public class PatientManager {

    private final PatientRepository repository;
    private final List<Patient> patients;

    /**
     * Constructs a PatientManager and loads all existing patients
     * from the repository into memory.
     *
     * @param repository PatientRepository instance for CSV persistence
     */
    public PatientManager(PatientRepository repository) {
        this.repository = repository;
        this.patients = repository.loadAll();
    }

    /**
     * Creates a new patient with full details.
     *
     * Generates a unique patient ID and persists the updated list to CSV.
     *
     * @param firstName                Patient's first name
     * @param lastName                 Patient's last name
     * @param dateOfBirth              Date of birth (YYYY-MM-DD)
     * @param nhsNumber                NHS number
     * @param gender                   Gender
     * @param phoneNumber              Contact number
     * @param email                    Email address
     * @param address                  Residential address
     * @param postcode                 Postal code
     * @param emergencyContactName     Name of emergency contact
     * @param emergencyContactPhone    Phone of emergency contact
     * @param registrationDate         Date of registration
     * @param gpSurgeryId              Associated GP surgery ID
     * @return the newly created Patient object
     */
    public Patient createPatient(
            String firstName,
            String lastName,
            String dateOfBirth,
            String nhsNumber,
            String gender,
            String phoneNumber,
            String email,
            String address,
            String postcode,
            String emergencyContactName,
            String emergencyContactPhone,
            String registrationDate,
            String gpSurgeryId
    ) {
        List<String> ids = new ArrayList<>();
        for (Patient p : patients) {
            ids.add(p.patientId);
        }

        // Generate unique ID with prefix "P"
        String newId = IdGenerator.nextId("P", ids);

        Patient patient = new Patient(
                newId, firstName, lastName, dateOfBirth,
                nhsNumber, gender, phoneNumber, email,
                address, postcode, emergencyContactName,
                emergencyContactPhone, registrationDate, gpSurgeryId
        );

        // Add to in-memory list and persist
        patients.add(patient);
        repository.saveAll(patients);
        return patient;
    }

    /**
     * Returns all patients currently loaded in memory.
     *
     * @return List of Patient objects
     */
    public List<Patient> getAllPatients() {
        return patients;
    }

    /**
     * Deletes a patient by ID.
     *
     * If patient exists, removes from in-memory list and updates CSV.
     * Returns true if deletion was successful, false otherwise.
     *
     * @param patientId ID of the patient to delete
     * @return boolean indicating if deletion was successful
     */
    public boolean deletePatient(String patientId) {
        Patient toRemove = null;
        for (Patient p : patients) {
            if (p.patientId.equals(patientId)) {
                toRemove = p;
                break;
            }
        }
        if (toRemove != null) {
            patients.remove(toRemove);
            repository.saveAll(patients);
            return true;
        }
        return false;
    }
}

