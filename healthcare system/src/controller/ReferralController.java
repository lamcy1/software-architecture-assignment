package controller;

import model.Referral;
import service.ReferralManager;

import java.util.List;

/**
 * ReferralController acts as the intermediary between the UI layer
 * and the ReferralManager singleton service.
 *
 * Responsibilities:
 *  - Receive user actions related to referrals
 *  - Delegate referral operations to the ReferralManager service
 *  - Return results back to the UI
 *  - Keep full domain logic encapsulated in the service layer
 */
public class ReferralController {

    private final ReferralManager referralManager;

    /**
     * Constructs a ReferralController using the ReferralManager singleton.
     *
     * @param referralManager ReferralManager singleton instance
     */
    public ReferralController(ReferralManager referralManager) {
        this.referralManager = referralManager;
    }

    /**
     * Creates a new referral with all required details.
     *
     * This method is used when all referral data is available.
     *
     * @param patientId               ID of the patient
     * @param referringClinicianId    ID of the clinician making the referral
     * @param referredToClinicianId   ID of the clinician being referred to
     * @param referringFacilityId     ID of the referring facility
     * @param referredToFacilityId    ID of the receiving facility
     * @param urgencyLevel            Urgency of the referral (e.g., Routine, Urgent)
     * @param referralReason          Reason for referral
     * @param clinicalSummary         Clinical summary for the referral
     * @param appointmentId           Related appointment ID
     * @return The newly created Referral object
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
        return referralManager.createReferral(
                patientId,
                referringClinicianId,
                referredToClinicianId,
                referringFacilityId,
                referredToFacilityId,
                urgencyLevel,
                referralReason,
                clinicalSummary,
                appointmentId
        );
    }

    /**
     * Retrieves all referrals currently stored in memory.
     *
     * @return List of Referral objects
     */
    public List<Referral> getAllReferrals() {
        return referralManager.getAllReferrals();
    }

    /**
     * Deletes a referral by its unique ID.
     *
     * Delegates deletion to the ReferralManager service.
     *
     * @param referralId ID of the referral to delete
     * @throws IllegalArgumentException if the referral does not exist
     */
    public void deleteReferral(String referralId) {
        referralManager.deleteReferral(referralId);
    }
}

