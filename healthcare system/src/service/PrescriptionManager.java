package service;

import model.Prescription;
import repository.PrescriptionRepository;
import utility.IdGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * PrescriptionManager handles all business logic related to prescriptions.
 *
 * Responsibilities include:
 *  - Creating new prescriptions for patients
 *  - Retrieving all prescriptions
 *  - Delegating CSV persistence to PrescriptionRepository
 *
 * Typically, prescriptions are created after appointments.
 */
public class PrescriptionManager {

    private final PrescriptionRepository repository;
    private final List<Prescription> prescriptions;

    /**
     * Constructs a PrescriptionManager and loads all existing prescriptions
     * from the repository into memory.
     *
     * @param repository PrescriptionRepository instance for CSV persistence
     */
    public PrescriptionManager(PrescriptionRepository repository) {
        this.repository = repository;
        this.prescriptions = repository.loadAll();
    }

    /**
     * Creates and issues a new prescription for a patient.
     *
     * Generates a unique prescription ID and sets creation and issue dates.
     * Persists the updated list to CSV.
     *
     * @param patientId       ID of the patient
     * @param clinicianId     ID of the prescribing clinician
     * @param appointmentId   ID of the related appointment
     * @param medicationName  Name of the medication
     * @param dosage          Dosage information
     * @param frequency       Frequency of administration
     * @param durationDays    Duration of treatment in days
     * @param quantity        Total quantity prescribed
     * @param instructions    Additional instructions for the patient
     * @param pharmacyName    Name of the pharmacy
     * @return the newly created Prescription object
     */
    public Prescription createPrescription(
            String patientId,
            String clinicianId,
            String appointmentId,
            String medicationName,
            String dosage,
            String frequency,
            String durationDays,
            String quantity,
            String instructions,
            String pharmacyName
    ) {
        List<String> ids = new ArrayList<>();
        for (Prescription p : prescriptions) {
            ids.add(p.prescriptionId);
        }

        // Generate unique prescription ID with prefix "RX"
        String newId = IdGenerator.nextId("RX", ids);
        String today = java.time.LocalDate.now().toString();

        Prescription prescription = new Prescription(
                newId,
                patientId,
                clinicianId,
                appointmentId,
                today,
                medicationName,
                dosage,
                frequency,
                durationDays,
                quantity,
                instructions,
                pharmacyName,
                "Issued",  // Status of the prescription
                today,     // Created date
                ""         // Last modified (empty initially)
        );

        // Add to in-memory list and persist to CSV
        prescriptions.add(prescription);
        repository.saveAll(prescriptions);
        return prescription;
    }

    /**
     * Returns all prescriptions currently loaded in memory.
     *
     * @return List of Prescription objects
     */
    public List<Prescription> getAllPrescriptions() {
        return prescriptions;
    }
}
