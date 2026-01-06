package repository;

import model.Prescription;
import java.util.*;

/**
 * Repository class responsible for managing Prescription data persistence.
 *
 * This class provides methods to load prescription records from a CSV file
 * and save updated prescription data back to the file.
 */
public class PrescriptionRepository {

    /** File path of the prescription CSV data source */
    private final String path;

    /** CSV header defining the prescription data structure */
    private static final String HEADER =
            "prescription_id,patient_id,clinician_id,appointment_id,prescription_date,medication_name," +
                    "dosage,frequency,duration_days,quantity,instructions,pharmacy_name,status,issue_date,collection_date";

    /**
     * Constructs a PrescriptionRepository with the specified CSV file path.
     *
     * @param path path to the prescription CSV file
     */
    public PrescriptionRepository(String path) {
        this.path = path;
    }

    /**
     * Loads all prescription records from the CSV file.
     *
     * @return list of Prescription objects
     */
    public List<Prescription> loadAll() {
        List<Prescription> list = new ArrayList<>();

        for (String[] r : CsvUtil.read(path)) {
            list.add(new Prescription(
                    r[0], r[1], r[2], r[3],
                    r[4], r[5], r[6], r[7],
                    r[8], r[9], r[10], r[11],
                    r[12], r[13], r[14]
            ));
        }
        return list;
    }

    /**
     * Saves all prescription records to the CSV file.
     * Existing data will be overwritten.
     *
     * @param prescriptions list of Prescription objects to persist
     */
    public void saveAll(List<Prescription> prescriptions) {
        List<String> lines = new ArrayList<>();

        for (Prescription p : prescriptions) {
            lines.add(p.toCsv());
        }

        CsvUtil.write(path, HEADER, lines);
    }
}
