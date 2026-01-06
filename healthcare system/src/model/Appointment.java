package model;

/**
 * Appointment represents a scheduled meeting between a patient and a clinician
 * at a specific healthcare facility.
 *
 * This class stores all relevant appointment details such as date, time,
 * duration, status, and notes, and supports CSV persistence.
 */
public class Appointment {

    /** Unique identifier for the appointment */
    public final String appointmentId;

    /** Identifier of the patient attending the appointment */
    public final String patientId;

    /** Identifier of the clinician assigned to the appointment */
    public final String clinicianId;

    /** Identifier of the healthcare facility */
    public final String facilityId;

    /** Scheduled appointment date in YYYY-MM-DD format */
    public String appointmentDate;

    /** Scheduled appointment time in HH:MM format */
    public String appointmentTime;

    /** Duration of the appointment in minutes */
    public String durationMinutes;

    /** Type of appointment (e.g., Consultation, Follow-up) */
    public String appointmentType;

    /** Current status of the appointment (Scheduled, Cancelled, Completed, etc.) */
    public String status;

    /** Reason provided for the appointment */
    public String reasonForVisit;

    /** Additional notes related to the appointment */
    public String notes;

    /** Date the appointment record was created */
    public final String createdDate;

    /** Date the appointment record was last updated */
    public String lastModified;

    /**
     * Creates a new Appointment instance with all required attributes.
     *
     * @param appointmentId     unique identifier of the appointment
     * @param patientId         patient identifier
     * @param clinicianId       clinician identifier
     * @param facilityId        healthcare facility identifier
     * @param appointmentDate   appointment date (YYYY-MM-DD)
     * @param appointmentTime   appointment time (HH:MM)
     * @param durationMinutes   appointment duration in minutes
     * @param appointmentType   type of appointment
     * @param status            appointment status
     * @param reasonForVisit    reason for scheduling the appointment
     * @param notes             optional additional notes
     * @param createdDate       date the record was created
     * @param lastModified      date the record was last modified
     */
    public Appointment(String appointmentId, String patientId, String clinicianId, String facilityId,
                       String appointmentDate, String appointmentTime, String durationMinutes,
                       String appointmentType, String status, String reasonForVisit, String notes,
                       String createdDate, String lastModified) {

        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.clinicianId = clinicianId;
        this.facilityId = facilityId;

        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.durationMinutes = durationMinutes;
        this.appointmentType = appointmentType;
        this.status = status;
        this.reasonForVisit = reasonForVisit;
        this.notes = notes;

        this.createdDate = createdDate;
        this.lastModified = lastModified;
    }

    /**
     * Converts the appointment object into a CSV-formatted string.
     * This method is used when persisting appointment records to a CSV file.
     *
     * @return a single-line CSV representation of the appointment
     */
    public String toCsv() {
        return String.join(",",
                safe(appointmentId),
                safe(patientId),
                safe(clinicianId),
                safe(facilityId),
                safe(appointmentDate),
                safe(appointmentTime),
                safe(durationMinutes),
                safe(appointmentType),
                safe(status),
                safe(reasonForVisit),
                safe(notes),
                safe(createdDate),
                safe(lastModified)
        );
    }

    /**
     * Sanitises a string for safe CSV storage by:
     * - Replacing null values with empty strings
     * - Removing commas and line breaks to avoid corrupting CSV structure
     *
     * @param s input string
     * @return sanitised string safe for CSV output
     */
    private String safe(String s) {
        return s == null ? "" : s.replace(",", " ").replace("\n", " ").replace("\r", " ");
    }

    /**
     * Returns a human-readable summary of the appointment.
     * Useful for displaying appointment details in console output.
     */
    @Override
    public String toString() {
        return String.format(
                "ID: %s | Patient: %s | Clinician: %s | Facility: %s | Date: %s | Time: %s | Duration: %s mins | Type: %s | Status: %s | Reason: %s",
                appointmentId,
                patientId,
                clinicianId,
                facilityId,
                appointmentDate,
                appointmentTime,
                durationMinutes,
                appointmentType,
                status,
                reasonForVisit
        );
    }
}
