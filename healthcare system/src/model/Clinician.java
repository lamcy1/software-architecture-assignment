package model;

/**
 * Represents a clinician registered within the healthcare system.
 *
 * This class encapsulates professional and employment-related information
 * about a clinician, including their role, speciality, workplace details,
 * and current employment status. The model also supports CSV persistence.
 */
public class Clinician {

    /** Unique identifier assigned to the clinician */
    public final String clinicianId;

    /** Clinician's first name */
    public final String firstName;

    /** Clinician's last name */
    public final String lastName;

    /** Professional title or role (e.g. GP, Nurse, Specialist) */
    public final String title;

    /** Area of medical expertise or speciality */
    public final String speciality;

    /** General Medical Council (GMC) registration number */
    public final String gmcNumber;

    /** Clinician's contact phone number */
    public final String phoneNumber;

    /** Clinician's email address */
    public final String email;

    /** Identifier of the facility where the clinician is employed */
    public final String workplaceId;

    /** Type of workplace (e.g. GP Surgery, Hospital) */
    public final String workplaceType;

    /** Current employment status (e.g. Full-time, Part-time) */
    public final String employmentStatus;

    /** Date when the clinician started employment */
    public final String startDate;

    /**
     * Constructs a Clinician object with all required professional details.
     *
     * @param clinicianId       unique identifier for the clinician
     * @param firstName         clinician's first name
     * @param lastName          clinician's last name
     * @param title             clinician role (GP, Nurse, Specialist)
     * @param speciality        clinician's medical speciality
     * @param gmcNumber         GMC registration number
     * @param phoneNumber       contact phone number
     * @param email             email address
     * @param workplaceId       identifier of the employing facility
     * @param workplaceType     type of healthcare workplace
     * @param employmentStatus  clinician's employment status
     * @param startDate         employment start date
     */
    public Clinician(String clinicianId, String firstName, String lastName, String title,
                     String speciality, String gmcNumber, String phoneNumber, String email,
                     String workplaceId, String workplaceType, String employmentStatus, String startDate) {

        this.clinicianId = clinicianId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.speciality = speciality;
        this.gmcNumber = gmcNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.workplaceId = workplaceId;
        this.workplaceType = workplaceType;
        this.employmentStatus = employmentStatus;
        this.startDate = startDate;
    }

    /**
     * Determines whether the clinician's role is General Practitioner (GP).
     *
     * @return true if the clinician's title is "GP", otherwise false
     */
    public boolean isGp() {
        return "GP".equalsIgnoreCase(title);
    }

    /**
     * Converts this Clinician object into a CSV-formatted string.
     * This method is used when storing clinician records in a CSV file.
     *
     * @return a single-line CSV representation of the clinician
     */
    public String toCsv() {
        return String.join(",",
                safe(clinicianId), safe(firstName), safe(lastName), safe(title),
                safe(speciality), safe(gmcNumber), safe(phoneNumber), safe(email),
                safe(workplaceId), safe(workplaceType), safe(employmentStatus), safe(startDate)
        );
    }

    /**
     * Sanitises string values for safe CSV output by:
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
     * Returns a human-readable representation of the clinician.
     * Useful for debugging, logging, and UI display purposes.
     */
    @Override
    public String toString() {
        return String.format(
                "ID: %s | Name: %s %s %s | Speciality: %s | GMC: %s | Phone: %s | Email: %s | Workplace ID: %s | Workplace Type: %s | Employment Status: %s | Start Date: %s",
                clinicianId,
                title,
                firstName,
                lastName,
                speciality,
                gmcNumber,
                phoneNumber,
                email,
                workplaceId,
                workplaceType,
                employmentStatus,
                startDate
        );
    }
}
