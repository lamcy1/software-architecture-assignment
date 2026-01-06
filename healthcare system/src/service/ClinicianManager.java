package service;

import model.Clinician;
import repository.ClinicianRepository;
import utility.IdGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * ClinicianManager is the service layer responsible for all business logic
 * related to clinicians/consultants.
 *
 * Responsibilities include:
 *  - Maintaining an in-memory list of clinicians
 *  - Creating new clinicians with unique generated IDs
 *  - Deleting clinicians
 *  - Retrieving all clinicians or a specific clinician by ID
 *  - Delegating CSV persistence to ClinicianRepository
 */
public class ClinicianManager {

    private final ClinicianRepository repository;
    private final List<Clinician> clinicians;

    /**
     * Constructs a ClinicianManager and loads all existing clinicians
     * from the CSV repository into memory.
     *
     * @param repository ClinicianRepository instance injected from Main
     */
    public ClinicianManager(ClinicianRepository repository) {
        this.repository = repository;
        this.clinicians = repository.loadAll();
    }

    /**
     * Creates a new clinician with full details as per the Clinician model.
     *
     * Generates a unique clinician ID and persists the updated list to CSV.
     *
     * @param firstName        clinician's first name
     * @param lastName         clinician's last name
     * @param title            clinician's title (e.g., Dr., Prof.)
     * @param speciality       clinician's speciality
     * @param gmcNumber        clinician's GMC number
     * @param phoneNumber      contact number
     * @param email            email address
     * @param workplaceId      associated workplace ID
     * @param workplaceType    type of workplace (e.g., Hospital, Clinic)
     * @param employmentStatus employment status (e.g., Full-time, Part-time)
     * @param startDate        start date of employment
     * @return the newly created Clinician object
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
        List<String> existingIds = new ArrayList<>();
        for (Clinician c : clinicians) {
            existingIds.add(c.clinicianId);
        }

        // Generate unique ID with prefix "C"
        String newId = IdGenerator.nextId("C", existingIds);

        Clinician clinician = new Clinician(
                newId,
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

        // Add to in-memory list and persist
        clinicians.add(clinician);
        repository.saveAll(clinicians);

        return clinician;
    }

    /**
     * Returns all clinicians currently stored in memory.
     *
     * @return List of Clinician objects
     */
    public List<Clinician> getAllClinicians() {
        return clinicians;
    }

    /**
     * Deletes a clinician by their ID.
     *
     * If the ID does not exist, no changes are made.
     *
     * @param clinicianId ID of the clinician to delete
     */
    public void deleteClinician(String clinicianId) {
        clinicians.removeIf(c -> c.clinicianId.equalsIgnoreCase(clinicianId));
        // Persist the updated list
        repository.saveAll(clinicians);
    }

    /**
     * Retrieves a single clinician by ID.
     *
     * Useful for other modules that need clinician details.
     *
     * @param clinicianId ID of the clinician to retrieve
     * @return Clinician object if found, otherwise null
     */
    public Clinician getClinicianById(String clinicianId) {
        for (Clinician c : clinicians) {
            if (c.clinicianId.equalsIgnoreCase(clinicianId)) {
                return c;
            }
        }
        return null;
    }
}
