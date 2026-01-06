package model;

/**
 * Represents a patient registered within the healthcare system.
 *
 * This class stores personal, contact, and registration details
 * associated with a patient and supports CSV-based persistence.
 */
public class Patient {

    /** Unique identifier assigned to the patient */
    public final String patientId;

    /** Patient's first name */
    public final String firstName;

    /** Patient's last name */
    public final String lastName;

    /** Patient's date of birth */
    public final String dateOfBirth;

    /** Patient's NHS number */
    public final String nhsNumber;

    /** Patient's gender */
    public final String gender;

    /** Patient's contact phone number */
    public final String phoneNumber;

    /** Patient's email address */
    public final String email;

    /** Patient's residential address */
    public final String address;

    /** Patient's postcode */
    public final String postcode;

    /** Name of the patient's emergency contact */
    public final String emergencyContactName;

    /** Phone number of the patient's emergency contact */
    public final String emergencyContactPhone;

    /** Date the patient registered with the healthcare system */
    public final String registrationDate;

    /** Identifier of the GP surgery the patient is registered with */
    public final String gpSurgeryId;

    /**
     * Constructs a Patient object containing all required patient details.
     *
     * @param patientId               unique identifier for the patient
     * @param firstName               patient's first name
     * @param lastName                patient's last name
     * @param dateOfBirth             patient's date of birth
     * @param nhsNumber               NHS registration number
     * @param gender                  patient's gender
     * @param phoneNumber             contact phone number
     * @param email                   email address
     * @param address                 residential address
     * @param postcode                postcode
     * @param emergencyContactName    name of emergency contact
     * @param emergencyContactPhone   emergency contact phone number
     * @param registrationDate        date of registration
     * @param gpSurgeryId             identifier of the GP surgery
     */
    public Patient(String patientId, String firstName, String lastName, String dateOfBirth,
                   String nhsNumber, String gender, String phoneNumber, String email,
                   String address, String postcode, String emergencyContactName,
                   String emergencyContactPhone, String registrationDate, String gpSurgeryId) {

        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.nhsNumber = nhsNumber;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactPhone = emergencyContactPhone;
        this.registrationDate = registrationDate;
        this.gpSurgeryId = gpSurgeryId;
    }

    /**
     * Converts this Patient object into a CSV-formatted string.
     * This method is used when persisting patient records to a CSV file.
     *
     * @return a single-line CSV representation of the patient
     */
    public String toCsv() {
        return String.join(",",
                safe(patientId), safe(firstName), safe(lastName), safe(dateOfBirth),
                safe(nhsNumber), safe(gender), safe(phoneNumber), safe(email),
                safe(address), safe(postcode), safe(emergencyContactName),
                safe(emergencyContactPhone), safe(registrationDate), safe(gpSurgeryId)
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
     * Returns a human-readable summary of the patient's details.
     * Useful for debugging, logging, and UI display.
     */
    @Override
    public String toString() {
        return String.format(
                "ID: %s | Name: %s %s | DOB: %s | NHS No: %s | Gender: %s | Phone: %s | Email: %s | Address: %s | Postcode: %s | Emergency Contact: %s (%s) | Registered: %s | GP Surgery ID: %s",
                patientId,
                firstName,
                lastName,
                dateOfBirth,
                nhsNumber,
                gender,
                phoneNumber,
                email,
                address,
                postcode,
                emergencyContactName,
                emergencyContactPhone,
                registrationDate,
                gpSurgeryId
        );
    }
}
