package repository;

import model.Staff;
import java.util.*;

/**
 * Repository class responsible for managing Staff data persistence.
 *
 * This class provides functionality to load staff records from a CSV file
 * and to save updated staff information back to persistent storage.
 */
public class StaffRepository {

    /** File path to the staff CSV data source */
    private final String path;

    /** CSV header defining the structure of staff records */
    private static final String HEADER =
            "staff_id,first_name,last_name,role,department,facility_id,phone_number,email," +
                    "employment_status,start_date,line_manager,access_level";

    /**
     * Constructs a StaffRepository with the specified CSV file path.
     *
     * @param path path to the staff CSV file
     */
    public StaffRepository(String path) {
        this.path = path;
    }

    /**
     * Loads all staff records from the CSV file.
     *
     * @return list of Staff objects
     */
    public List<Staff> loadAll() {
        List<Staff> list = new ArrayList<>();

        for (String[] r : CsvUtil.read(path)) {
            list.add(new Staff(
                    r[0], r[1], r[2], r[3],
                    r[4], r[5], r[6], r[7],
                    r[8], r[9], r[10], r[11]
            ));
        }
        return list;
    }

    /**
     * Saves all staff records to the CSV file.
     * Existing data will be overwritten.
     *
     * @param staff list of Staff objects to persist
     */
    public void saveAll(List<Staff> staff) {
        List<String> lines = new ArrayList<>();

        for (Staff s : staff) {
            lines.add(s.toCsv());
        }

        CsvUtil.write(path, HEADER, lines);
    }
}
