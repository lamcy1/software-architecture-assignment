package model;

/**
 * Represents a non-clinical staff member working within the healthcare system.
 *
 * This class stores administrative and employment-related information for
 * staff members such as role, department, facility assignment, and access
 * privileges. It also supports CSV-based data persistence.
 */
public class Staff {

    /** Unique identifier assigned to the staff member */
    public final String staffId;

    /** Staff member's first name */
    public final String firstName;

    /** Staff member's last name */
    public final String lastName;

    /** Job role or position held by the staff member */
    public final String role;

    /** Department in which the staff member works */
    public final String department;

    /** Identifier of the facility where the staff member is employed */
    public final String facilityId;

    /** Contact phone number */
    public final String phoneNumber;

    /** Contact email address */
    public final String email;

    /** Employment status (e.g. Full-time, Part-time, Contract) */
    public final String employmentStatus;

    /** Date on which employment started */
    public final String startDate;

    /** Name or identifier of the staff member’s line manager */
    public final String lineManager;

    /** System access level assigned to the staff member */
    public final String accessLevel;

    /**
     * Constructs a new Staff object containing all required staff details.
     *
     * @param staffId            unique identifier of the staff member
     * @param firstName          staff member's first name
     * @param lastName           staff member's last name
     * @param role               staff role or job title
     * @param department         department the staff member works in
     * @param facilityId         identifier of the employing facility
     * @param phoneNumber        contact phone number
     * @param email              contact email address
     * @param employmentStatus   employment status
     * @param startDate          employment start date
     * @param lineManager        line manager of the staff member
     * @param accessLevel        system access level
     */
    public Staff(String staffId, String firstName, String lastName, String role, String department,
                 String facilityId, String phoneNumber, String email, String employmentStatus,
                 String startDate, String lineManager, String accessLevel) {

        this.staffId = staffId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.department = department;
        this.facilityId = facilityId;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.employmentStatus = employmentStatus;
        this.startDate = startDate;
        this.lineManager = lineManager;
        this.accessLevel = accessLevel;
    }

    /**
     * Converts this Staff object into a single CSV-formatted line.
     * Used when persisting staff records to a CSV file.
     *
     * @return a CSV representation of the staff member
     */
    public String toCsv() {
        return String.join(",",
                safe(staffId), safe(firstName), safe(lastName), safe(role), safe(department),
                safe(facilityId), safe(phoneNumber), safe(email), safe(employmentStatus),
                safe(startDate), safe(lineManager), safe(accessLevel)
        );
    }

    /**
     * Sanitises string values to ensure safe CSV output by:
     * - Replacing null values with empty strings
     * - Removing commas and line breaks to preserve CSV structure
     *
     * @param s input string
     * @return sanitised string suitable for CSV storage
     */
    private String safe(String s) {
        return s == null ? "" : s.replace(",", " ").replace("\n"," ").replace("\r"," ");
    }

    /**
     * Returns a concise, human-readable representation of the staff member.
     * Useful for logging, debugging, and displaying staff lists in the UI.
     */
    @Override
    public String toString() {
        return staffId + " - " + role + " " + firstName + " " + lastName;
    }
}
