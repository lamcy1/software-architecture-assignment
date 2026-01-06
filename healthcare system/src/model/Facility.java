package model;

/**
 * Represents a healthcare facility within the system, such as a GP surgery,
 * hospital, or clinic.
 *
 * This class stores identifying, contact, and operational details for a
 * facility and supports CSV-based persistence.
 */
public class Facility {

    /** Unique identifier for the facility */
    public final String facilityId;

    /** Name of the healthcare facility */
    public final String facilityName;

    /** Type of facility (e.g. GP Surgery, Hospital, Clinic) */
    public final String facilityType;

    /** Physical address of the facility */
    public final String address;

    /** Postcode of the facility */
    public final String postcode;

    /** Contact phone number */
    public final String phoneNumber;

    /** Contact email address */
    public final String email;

    /** Opening hours of the facility */
    public final String openingHours;

    /** Name of the facility manager */
    public final String managerName;

    /** Capacity of the facility (stored as text) */
    public final String capacity;

    /** Specialities offered by the facility (stored as raw text, e.g. "A|B|C") */
    public final String specialitiesOffered;

    /**
     * Constructs a Facility object containing all required facility details.
     *
     * @param facilityId           unique identifier for the facility
     * @param facilityName         name of the healthcare facility
     * @param facilityType         category of facility (GP Surgery, Hospital, etc.)
     * @param address              facility address
     * @param postcode             facility postcode
     * @param phoneNumber          contact phone number
     * @param email                contact email address
     * @param openingHours         operating hours of the facility
     * @param managerName          name of the facility manager
     * @param capacity             operational capacity of the facility
     * @param specialitiesOffered  list of medical specialities offered
     */
    public Facility(String facilityId, String facilityName, String facilityType, String address,
                    String postcode, String phoneNumber, String email, String openingHours,
                    String managerName, String capacity, String specialitiesOffered) {

        this.facilityId = facilityId;
        this.facilityName = facilityName;
        this.facilityType = facilityType;
        this.address = address;
        this.postcode = postcode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.openingHours = openingHours;
        this.managerName = managerName;
        this.capacity = capacity;
        this.specialitiesOffered = specialitiesOffered;
    }

    /**
     * Converts this Facility object into a CSV-formatted string.
     * This method is used when persisting facility records to a CSV file.
     *
     * @return a single-line CSV representation of the facility
     */
    public String toCsv() {
        return String.join(",",
                safe(facilityId), safe(facilityName), safe(facilityType), safe(address),
                safe(postcode), safe(phoneNumber), safe(email), safe(openingHours),
                safe(managerName), safe(capacity), safe(specialitiesOffered)
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
        // Uses a conditional expression to handle null values concisely
        return s == null ? "" : s.replace(",", " ").replace("\n"," ").replace("\r"," ");
    }

    /**
     * Returns a concise, human-readable representation of the facility.
     * Useful for logging, debugging, and UI display.
     */
    @Override
    public String toString() {
        return facilityId + " - " + facilityName + " (" + facilityType + ")";
    }
}
