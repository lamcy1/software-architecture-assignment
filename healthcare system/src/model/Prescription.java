package model;

/**
 * Represents a prescription issued to a patient by a clinician.
 *
 * This class stores medication, dosage, dispensing, and status
 * information related to a prescription and supports CSV persistence.
 */
public class Prescription {

    /** Unique identifier for the prescription (e.g. RX###) */
    public String prescriptionId;

    /** Identifier of the patient receiving the prescription */
    public final String patientId;

    /** Identifier of the clinician issuing the prescription */
    public final String clinicianId;

    /** Identifier of the appointment associated with the prescription */
    public final String appointmentId;

    /** Date the prescription record was created */
    public final String prescriptionDate;

    /** Name of the prescribed medication */
    public final String medicationName;

    /** Dosage instructions (e.g. "500mg") */
    public final String dosage;

    /** Frequency of medication intake (e.g. "Twice a day") */
    public final String frequency;

    /** Duration of the treatment in days */
    public final String durationDays;

    /** Total quantity of medication prescribed */
    public final String quantity;

    /** Additional usage instructions for the patient */
    public final String instructions;

    /** Name of the pharmacy responsible for dispensing */
    public final String pharmacyName;

    /** Current status of the prescription (e.g. Issued, Collected) */
    public String status;

    /** Date the prescription was officially issued */
    public final String issueDate;

    /** Date the prescription was collected by the patient (may be empty) */
    public String collectionDate;

    /**
     * Constructs a Prescription object with all required prescription details.
     *
     * @param prescriptionId    unique identifier for the prescription
     * @param patientId         identifier of the patient
     * @param clinicianId       identifier of the prescribing clinician
     * @param appointmentId     related appointment identifier
     * @param prescriptionDate  date the prescription was created
     * @param medicationName    name of the prescribed medication
     * @param dosage            dosage instructions
     * @param frequency         frequency of intake
     * @param durationDays      treatment duration in days
     * @param quantity          total quantity prescribed
     * @param instructions      additional patient instructions
     * @param pharmacyName      dispensing pharmacy name
     * @param status            current prescription status
     * @param issueDate         date the prescription was issued
     * @param collectionDate    date the prescription was collected
     */
    public Prescription(String prescriptionId, String patientId, String clinicianId, String appointmentId,
                        String prescriptionDate, String medicationName, String dosage, String frequency,
                        String durationDays, String quantity, String instructions, String pharmacyName,
                        String status, String issueDate, String collectionDate) {

        this.prescriptionId = prescriptionId;
        this.patientId = patientId;
        this.clinicianId = clinicianId;
        this.appointmentId = appointmentId;
        this.prescriptionDate = prescriptionDate;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.frequency = frequency;
        this.durationDays = durationDays;
        this.quantity = quantity;
        this.instructions = instructions;
        this.pharmacyName = pharmacyName;
        this.status = status;
        this.issueDate = issueDate;
        this.collectionDate = collectionDate;
    }

    /**
     * Converts this Prescription object into a CSV-formatted string.
     * This method is used when persisting prescription records to a CSV file.
     *
     * @return a single-line CSV representation of the prescription
     */
    public String toCsv() {
        return String.join(",",
                safe(prescriptionId), safe(patientId), safe(clinicianId), safe(appointmentId),
                safe(prescriptionDate), safe(medicationName), safe(dosage), safe(frequency),
                safe(durationDays), safe(quantity), safe(instructions), safe(pharmacyName),
                safe(status), safe(issueDate), safe(collectionDate)
        );
    }

    /**
     * Sanitises string values to ensure safe CSV output by:
     * - Replacing null values with empty strings
     * - Removing commas and line breaks to preserve CSV structure
     *
     * @param s input string
     * @return sanitised string safe for CSV storage
     */
    private String safe(String s) {
        return s == null ? "" : s.replace(",", " ").replace("\n"," ").replace("\r"," ");
    }

    /**
     * Returns a human-readable summary of the prescription.
     * Useful for debugging, logging, and UI display.
     */
    @Override
    public String toString() {
        return String.format(
                "ID: %s | Patient: %s | Clinician: %s | Appointment: %s | Medication: %s | Dosage: %s | Frequency: %s | Duration: %s days | Quantity: %s | Pharmacy: %s | Status: %s",
                prescriptionId,
                patientId,
                clinicianId,
                appointmentId,
                medicationName,
                dosage,
                frequency,
                durationDays,
                quantity,
                pharmacyName,
                status
        );
    }
}
