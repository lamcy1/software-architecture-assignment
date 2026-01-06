package model;

/**
 * Represents a referral issued for a patient from one clinician or healthcare
 * facility to another clinician or facility.
 *
 * This class stores referral-related information such as urgency,
 * clinical justification, requested investigations, associated appointments,
 * and the current referral status. It also supports persistence using CSV files.
 */
public class Referral {

    /** Unique identifier assigned to the referral (e.g. R###) */
    public String referralId;

    /** Identifier of the patient being referred */
    public final String patientId;

    /** Identifier of the clinician who initiated the referral */
    public final String referringClinicianId;

    /** Identifier of the clinician or specialist receiving the referral */
    public final String referredToClinicianId;

    /** Identifier of the facility where the referral originated */
    public final String referringFacilityId;

    /** Identifier of the facility to which the referral is sent */
    public final String referredToFacilityId;

    /** Date on which the referral was created */
    public final String referralDate;

    /** Urgency level assigned to the referral */
    public final String urgencyLevel;

    /** Clinical or administrative reason for making the referral */
    public final String referralReason;

    /** Brief clinical summary supporting the referral decision */
    public final String clinicalSummary;

    /** Investigations requested as part of the referral (raw format, e.g. ECG|Echo) */
    public final String requestedInvestigations;

    /** Current status of the referral (e.g. Pending, Accepted, Completed) */
    public String status;

    /** Identifier of a related appointment, if applicable (may be empty) */
    public final String appointmentId;

    /** Additional notes related to the referral (may be empty) */
    public final String notes;

    /** Date on which the referral record was initially created */
    public final String createdDate;

    /** Date on which the referral record was last updated */
    public String lastUpdated;

    /**
     * Constructs a new Referral object containing all relevant referral details.
     *
     * @param referralId              unique identifier of the referral
     * @param patientId               identifier of the referred patient
     * @param referringClinicianId    clinician who initiated the referral
     * @param referredToClinicianId   clinician receiving the referral
     * @param referringFacilityId     facility where the referral originated
     * @param referredToFacilityId    facility receiving the referral
     * @param referralDate            date the referral was made
     * @param urgencyLevel            urgency level assigned to the referral
     * @param referralReason          reason for initiating the referral
     * @param clinicalSummary         brief supporting clinical summary
     * @param requestedInvestigations requested investigations (raw format)
     * @param status                  current referral status
     * @param appointmentId           linked appointment identifier (optional)
     * @param notes                   additional notes (optional)
     * @param createdDate             date the referral record was created
     * @param lastUpdated             date the referral was last updated
     */
    public Referral(String referralId, String patientId, String referringClinicianId, String referredToClinicianId,
                    String referringFacilityId, String referredToFacilityId, String referralDate, String urgencyLevel,
                    String referralReason, String clinicalSummary, String requestedInvestigations, String status,
                    String appointmentId, String notes, String createdDate, String lastUpdated) {

        this.referralId = referralId;
        this.patientId = patientId;
        this.referringClinicianId = referringClinicianId;
        this.referredToClinicianId = referredToClinicianId;
        this.referringFacilityId = referringFacilityId;
        this.referredToFacilityId = referredToFacilityId;
        this.referralDate = referralDate;
        this.urgencyLevel = urgencyLevel;
        this.referralReason = referralReason;
        this.clinicalSummary = clinicalSummary;
        this.requestedInvestigations = requestedInvestigations;
        this.status = status;
        this.appointmentId = appointmentId;
        this.notes = notes;
        this.createdDate = createdDate;
        this.lastUpdated = lastUpdated;
    }

    /**
     * Converts this Referral object into a single CSV-formatted line.
     * Used when saving referral records to persistent CSV storage.
     *
     * @return a CSV representation of the referral
     */
    public String toCsv() {
        return String.join(",",
                safe(referralId), safe(patientId), safe(referringClinicianId), safe(referredToClinicianId),
                safe(referringFacilityId), safe(referredToFacilityId), safe(referralDate), safe(urgencyLevel),
                safe(referralReason), safe(clinicalSummary), safe(requestedInvestigations), safe(status),
                safe(appointmentId), safe(notes), safe(createdDate), safe(lastUpdated)
        );
    }

    /**
     * Sanitises string values to ensure safe CSV output by:
     * <ul>
     *   <li>Replacing null values with empty strings</li>
     *   <li>Removing commas and line breaks to maintain CSV integrity</li>
     * </ul>
     *
     * @param s input string
     * @return sanitised string suitable for CSV storage
     */
    private String safe(String s) {
        return s == null ? "" : s.replace(",", " ").replace("\n"," ").replace("\r"," ");
    }

    /**
     * Returns a concise, human-readable summary of the referral.
     * Useful for debugging, logging, and user interface display.
     *
     * @return formatted string representation of the referral
     */
    @Override
    public String toString() {
        return String.format(
                "ID: %s | Patient: %s | From Clinician: %s | To Clinician: %s | From Facility: %s | To Facility: %s | Date: %s | Urgency: %s | Reason: %s | Status: %s",
                referralId,
                patientId,
                referringClinicianId,
                referredToClinicianId,
                referringFacilityId,
                referredToFacilityId,
                referralDate,
                urgencyLevel,
                referralReason,
                status
        );
    }
}
