package repository;

import model.Patient;
import java.util.*;

/**
 * Repository class responsible for managing Patient data persistence.
 *
 * This class provides functionality to load patient records from a CSV file
 * and save updated patient records back to the file.
 */
public class PatientRepository {

    /** File path of the patient CSV data source */
    private final String path;

    /** CSV header defining the patient data structure */
    private static final String HEADER =
            "patient_id,first_name,last_name,date_of_birth,nhs_number,gender,phone_number,email," +
                    "address,postcode,emergency_contact_name,emergency_contact_phone,registration_date,gp_surgery_id";

    /**
     * Constructs a PatientRepository with the specified CSV file path.
     *
     * @param path path to the patient CSV file
     */
    public PatientRepository(String path) {
        this.path = path;
    }

    /**
     * Loads all patient records from the CSV file.
     *
     * @return list of Patient objects
     */
    public List<Patient> loadAll() {
        List<Patient> list = new ArrayList<>();

        for (String[] r : CsvUtil.read(path)) {
            list.add(new Patient(
                    r[0], r[1], r[2], r[3],
                    r[4], r[5], r[6], r[7],
                    r[8], r[9], r[10], r[11],
                    r[12], r[13]
            ));
        }
        return list;
    }

    /**
     * Saves all patient records to the CSV file.
     * Existing data will be overwritten.
     *
     * @param patients list of Patient objects to persist
     */
    public void saveAll(List<Patient> patients) {
        List<String> lines = new ArrayList<>();

        for (Patient p : patients) {
            lines.add(p.toCsv());
        }

        CsvUtil.write(path, HEADER, lines);
    }
}
