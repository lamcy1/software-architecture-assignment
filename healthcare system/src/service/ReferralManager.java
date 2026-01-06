package service;

import model.Referral;
import repository.ReferralRepository;
import utility.IdGenerator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ReferralManager is a singleton service responsible for managing referrals.
 *
 * Responsibilities include:
 *  - Creating new referrals
 *  - Deleting referrals by ID
 *  - Retrieving all referrals
 *  - Delegating CSV persistence to ReferralRepository
 *  - Simulating external system outputs (emails, EHR logs)
 *
 * Only one instance of ReferralManager exists to ensure consistent state.
 */
public class ReferralManager {

    private static ReferralManager instance;

    private final ReferralRepository repository;
    private final List<Referral> referrals;

    /**
     * Private constructor to enforce singleton pattern.
     *
     * Loads all existing referrals from the repository into memory.
     *
     * @param repository ReferralRepository instance for CSV persistence
     */
    private ReferralManager(ReferralRepository repository) {
        this.repository = repository;
        this.referrals = repository.loadAll();
    }

    /**
     * Returns the single instance of ReferralManager.
     *
     * @param repository ReferralRepository instance (used only on first call)
     * @return the singleton ReferralManager instance
     */
    public static ReferralManager getInstance(ReferralRepository repository) {
        if (instance == null) {
            instance = new ReferralManager(repository);
        }
        return instance;
    }

    /**
     * Creates a new referral and persists it.
     *
     * Generates a unique referral ID, sets creation date, and triggers
     * system side effects such as email and EHR file generation.
     *
     * @param patientId               ID of the patient
     * @param referringClinicianId    ID of the clinician making the referral
     * @param referredToClinicianId   ID of the clinician being referred to
     * @param referringFacilityId     ID of the facility making the referral
     * @param referredToFacilityId    ID of the facility receiving the referral
     * @param urgencyLevel            Urgency of the referral (e.g., Routine, Urgent)
     * @param referralReason          Reason for referral
     * @param clinicalSummary         Clinical summary of the patient
     * @param appointmentId           Related appointment ID
     * @return the newly created Referral object
     */
    public Referral createReferral(
            String patientId,
            String referringClinicianId,
            String referredToClinicianId,
            String referringFacilityId,
            String referredToFacilityId,
            String urgencyLevel,
            String referralReason,
            String clinicalSummary,
            String appointmentId
    ) {
        List<String> ids = new ArrayList<>();
        for (Referral r : referrals) {
            ids.add(r.referralId);
        }

        // Generate unique referral ID with prefix "R"
        String newId = IdGenerator.nextId("R", ids);
        String today = java.time.LocalDate.now().toString();

        Referral referral = new Referral(
                newId,
                patientId,
                referringClinicianId,
                referredToClinicianId,
                referringFacilityId,
                referredToFacilityId,
                today,
                urgencyLevel,
                referralReason,
                clinicalSummary,
                "",
                "Sent",     // Status of referral
                appointmentId,
                "",
                today,      // Created date
                today       // Last modified date
        );

        // Add to in-memory list, persist, and generate system files
        referrals.add(referral);
        repository.saveAll(referrals);
        generateReferralFiles(referral);
        return referral;
    }

    /**
     * Deletes a referral by its unique ID.
     *
     * @param referralId ID of the referral to delete
     * @throws IllegalArgumentException if the referral does not exist
     */
    public void deleteReferral(String referralId) {
        boolean removed = referrals.removeIf(r -> r.referralId.equals(referralId));
        if (!removed) {
            throw new IllegalArgumentException("Referral ID not found: " + referralId);
        }
        repository.saveAll(referrals); // persist changes
    }

    /**
     * Returns all referrals currently loaded in memory.
     *
     * @return List of Referral objects
     */
    public List<Referral> getAllReferrals() {
        return referrals;
    }

    /**
     * Simulates external system outputs for a referral.
     *
     * Generates two text files:
     *  1. Email notification
     *  2. EHR log
     *
     * @param r Referral object for which files are generated
     */
    private void generateReferralFiles(Referral r) {
        try {
            FileWriter email = new FileWriter("referral_email_" + r.referralId + ".txt");
            email.write("Referral ID: " + r.referralId + "\n");
            email.write("Patient ID: " + r.patientId + "\n");
            email.write("Urgency: " + r.urgencyLevel + "\n");
            email.write("Reason: " + r.referralReason + "\n");
            email.write("Clinical Summary:\n" + r.clinicalSummary + "\n");
            email.close();

            FileWriter ehr = new FileWriter("referral_ehr_" + r.referralId + ".txt");
            ehr.write("Referral recorded in EHR\n");
            ehr.write("Referral ID: " + r.referralId + "\n");
            ehr.write("Status: " + r.status + "\n");
            ehr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
