package controller;

import model.Prescription;
import service.PrescriptionManager;

import java.util.List;

/**
 * PrescriptionController acts as the intermediary between the UI layer
 * (e.g., Swing panels) and the PrescriptionManager service.
 *
 * Responsibilities:
 *  - Receive user actions related to prescriptions
 *  - Delegate prescription-related operations to the service layer
 *  - Return results back to the UI
 *  - Keep full domain logic encapsulated in the service layer
 */
public class PrescriptionController {

    private final PrescriptionManager prescriptionManager;

    /**
     * Constructs a PrescriptionController with a reference to the service layer.
     *
     * @param prescriptionManager PrescriptionManager service instance
     */
    public PrescriptionController(PrescriptionManager prescriptionManager) {
        this.prescriptionManager = prescriptionManager;
    }

    /**
     * Creates a new prescription with all required details.
     *
     * This method is useful for testing, future extensions,
     * or when all prescription data is available.
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
     * @return The newly created Prescription object
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
        return prescriptionManager.createPrescription(
                patientId,
                clinicianId,
                appointmentId,
                medicationName,
                dosage,
                frequency,
                durationDays,
                quantity,
                instructions,
                pharmacyName
        );
    }

    /**
     * Retrieves all prescriptions currently stored in memory.
     *
     * @return List of Prescription objects
     */
    public List<Prescription> getAllPrescriptions() {
        return prescriptionManager.getAllPrescriptions();
    }
}
