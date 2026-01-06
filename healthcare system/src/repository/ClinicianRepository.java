package repository;

import model.Clinician;
import java.util.*;

/**
 * Repository class responsible for managing Clinician data persistence.
 *
 * This class provides methods to load clinician records from a CSV file
 * and save updated clinician data back to the file.
 */
public class ClinicianRepository {

    /** File path to the clinicians CSV file */
    private final String path;

    /** CSV header used when writing clinician data */
    private static final String HEADER =
            "clinician_id,first_name,last_name,title,speciality,gmc_number,phone_number,email," +
                    "workplace_id,workplace_type,employment_status,start_date";

    /**
     * Creates a new ClinicianRepository using the specified file path.
     *
     * @param path path to the CSV file storing clinician records
     */
    public ClinicianRepository(String path) {
        this.path = path;
    }

    /**
     * Loads all clinician records from the CSV file.
     *
     * @return a list of Clinician objects
     */
    public List<Clinician> loadAll() {
        List<Clinician> list = new ArrayList<>();

        for (String[] r : CsvUtil.read(path)) {
            list.add(new Clinician(
                    r[0], r[1], r[2], r[3],
                    r[4], r[5], r[6], r[7],
                    r[8], r[9], r[10], r[11]
            ));
        }
        return list;
    }

    /**
     * Saves all clinician records to the CSV file.
     *
     * Existing data in the file will be overwritten.
     *
     * @param clinicians list of Clinician objects to be saved
     */
    public void saveAll(List<Clinician> clinicians) {
        List<String> lines = new ArrayList<>();

        for (Clinician c : clinicians) {
            lines.add(c.toCsv());
        }

        CsvUtil.write(path, HEADER, lines);
    }
}
