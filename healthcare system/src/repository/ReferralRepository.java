package repository;

import model.Referral;
import java.util.*;

/**
 * Repository class responsible for managing Referral data persistence.
 *
 * This class handles loading referral records from a CSV file and
 * saving updated referral information back to persistent storage.
 */
public class ReferralRepository {

    /** File path to the referral CSV data source */
    private final String path;

    /** CSV header defining the referral data structure */
    private static final String HEADER =
            "referral_id,patient_id,referring_clinician_id,referred_to_clinician_id,referring_facility_id," +
                    "referred_to_facility_id,referral_date,urgency_level,referral_reason,clinical_summary," +
                    "requested_investigations,status,appointment_id,notes,created_date,last_updated";

    /**
     * Constructs a ReferralRepository with the specified CSV file path.
     *
     * @param path path to the referral CSV file
     */
    public ReferralRepository(String path) {
        this.path = path;
    }

    /**
     * Loads all referral records from the CSV file.
     *
     * @return list of Referral objects
     */
    public List<Referral> loadAll() {
        List<Referral> list = new ArrayList<>();

        for (String[] r : CsvUtil.read(path)) {
            list.add(new Referral(
                    r[0], r[1], r[2], r[3],
                    r[4], r[5], r[6], r[7],
                    r[8], r[9], r[10], r[11],
                    r[12], r[13], r[14], r[15]
            ));
        }
        return list;
    }

    /**
     * Saves all referral records to the CSV file.
     * Existing data will be overwritten.
     *
     * @param referrals list of Referral objects to persist
     */
    public void saveAll(List<Referral> referrals) {
        List<String> lines = new ArrayList<>();

        for (Referral r : referrals) {
            lines.add(r.toCsv());
        }

        CsvUtil.write(path, HEADER, lines);
    }
}
