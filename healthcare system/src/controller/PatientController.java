package controller;

import model.Patient;
import service.PatientManager;

import java.util.List;

/**
 * PatientController acts as the intermediary between the UI layer
 * and the PatientManager service.
 *
 * Responsibilities:
 *  - Receive user actions related to patients
 *  - Delegate patient-related operations to the service layer
 *  - Return results back to the UI
 */
public class PatientController {

    private final PatientManager patientManager;

    /**
     * Constructs a PatientController with a reference to the service layer.
     *
     * @param patientManager PatientManager service instance
     */
    public PatientController(PatientManager patientManager) {
        this.patientManager = patientManager;
    }

    /**
     * Creates a new patient with full details.
     *
     * Delegates creation to the PatientManager service.
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
     * @return The newly created Patient object
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
        return patientManager.createPatient(
                firstName, lastName, dateOfBirth, nhsNumber, gender,
                phoneNumber, email, address, postcode, emergencyContactName,
                emergencyContactPhone, registrationDate, gpSurgeryId
        );
    }

    /**
     * Retrieves all patients currently stored in the system.
     *
     * @return List of Patient objects
     */
    public List<Patient> getAllPatients() {
        return patientManager.getAllPatients();
    }

    /**
     * Deletes a patient by their ID.
     *
     * Delegates the deletion to the PatientManager service.
     *
     * @param patientId ID of the patient to delete
     * @return true if deletion was successful, false otherwise
     */
    public boolean deletePatient(String patientId) {
        return patientManager.deletePatient(patientId);
    }
}
