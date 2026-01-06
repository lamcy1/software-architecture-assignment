package controller;

import model.Clinician;
import service.ClinicianManager;

import java.util.List;

/**
 * ClinicianController acts as the intermediary between the UI layer
 * and the ClinicianManager service.
 *
 * Responsibilities:
 *  - Receive user actions related to clinicians
 *  - Delegate clinician-related operations to the service layer
 *  - Return results back to the UI
 */
public class ClinicianController {

    private final ClinicianManager manager;

    /**
     * Constructs a ClinicianController with a reference to the service layer.
     *
     * @param manager ClinicianManager service instance
     */
    public ClinicianController(ClinicianManager manager) {
        this.manager = manager;
    }

    /**
     * Retrieves all clinicians currently stored.
     *
     * @return List of Clinician objects
     */
    public List<Clinician> getAllClinicians() {
        return manager.getAllClinicians();
    }

    /**
     * Creates a new clinician with full details.
     *
     * Delegates creation to the ClinicianManager service.
     *
     * @param firstName        Clinician's first name
     * @param lastName         Clinician's last name
     * @param title            Clinician's title (e.g., Dr., Prof.)
     * @param speciality       Clinician's speciality
     * @param gmcNumber        GMC number
     * @param phoneNumber      Contact number
     * @param email            Email address
     * @param workplaceId      Associated workplace ID
     * @param workplaceType    Type of workplace (e.g., Hospital, Clinic)
     * @param employmentStatus Employment status (e.g., Full-time, Part-time)
     * @param startDate        Start date of employment
     * @return The newly created Clinician object
     */
    public Clinician createClinician(
            String firstName,
            String lastName,
            String title,
            String speciality,
            String gmcNumber,
            String phoneNumber,
            String email,
            String workplaceId,
            String workplaceType,
            String employmentStatus,
            String startDate
    ) {
        return manager.createClinician(
                firstName,
                lastName,
                title,
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

    /**
     * Deletes a clinician by their ID.
     *
     * Delegates the deletion to the ClinicianManager service.
     *
     * @param id ID of the clinician to delete
     */
    public void deleteClinician(String id) {
        manager.deleteClinician(id);
    }
}
