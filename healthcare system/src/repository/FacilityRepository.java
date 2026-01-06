package repository;

import model.Facility;
import java.util.*;

/**
 * Repository class responsible for loading and saving Facility data
 * using CSV-based persistence.
 */
public class FacilityRepository {

    /** File path of the CSV data source */
    private final String path;

    /** CSV header defining the Facility data structure */
    private static final String HEADER =
            "facility_id,facility_name,facility_type,address,postcode,phone_number,email,opening_hours," +
                    "manager_name,capacity,specialities_offered";

    /**
     * Constructs a FacilityRepository with the specified CSV file path.
     *
     * @param path path to the facility CSV file
     */
    public FacilityRepository(String path) {
        this.path = path;
    }

    /**
     * Loads all facility records from the CSV file.
     *
     * @return list of Facility objects
     */
    public List<Facility> loadAll() {
        List<Facility> list = new ArrayList<>();

        for (String[] r : CsvUtil.read(path)) {
            list.add(new Facility(
                    r[0], r[1], r[2],
                    r[3], r[4], r[5],
                    r[6], r[7], r[8],
                    r[9], r[10]
            ));
        }
        return list;
    }

    /**
     * Saves all facility records to the CSV file.
     * Existing data will be overwritten.
     *
     * @param facilities list of Facility objects to persist
     */
    public void saveAll(List<Facility> facilities) {
        List<String> lines = new ArrayList<>();

        for (Facility f : facilities) {
            lines.add(f.toCsv());
        }

        CsvUtil.write(path, HEADER, lines);
    }
}
