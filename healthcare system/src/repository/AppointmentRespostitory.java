package repository;

import model.Appointment;
import java.util.*;

/**
 * Repository class responsible for managing Appointment data persistence.
 *
 * This class handles loading appointment records from a CSV file
 * and saving updated appointment data back to the file.
 */
public class AppointmentRepository {

    /** File path to the appointments CSV file */
    private final String path;

    /** CSV header used when writing appointment data */
    private static final String HEADER =
            "appointment_id,patient_id,clinician_id,facility_id,appointment_date,appointment_time," +
                    "duration_minutes,appointment_type,status,reason_for_visit,notes,created_date,last_modified";

    /**
     * Creates a new AppointmentRepository using the specified file path.
     *
     * @param path path to the CSV file storing appointment records
     */
    public AppointmentRepository(String path) {
        this.path = path;
    }

    /**
     * Loads all appointment records from the CSV file.
     *
     * @return a list of Appointment objects
     */
    public List<Appointment> loadAll() {
        List<Appointment> list = new ArrayList<>();

        for (String[] r : CsvUtil.read(path)) {
            list.add(new Appointment(
                    r[0], r[1], r[2], r[3],
                    r[4], r[5], r[6], r[7],
                    r[8], r[9], r[10], r[11], r[12]
            ));
        }
        return list;
    }

    /**
     * Saves all appointment records to the CSV file.
     *
     * Existing data in the file will be overwritten.
     *
     * @param appointments list of Appointment objects to be saved
     */
    public void saveAll(List<Appointment> appointments) {
        List<String> lines = new ArrayList<>();

        for (Appointment a : appointments) {
            lines.add(a.toCsv());
        }

        CsvUtil.write(path, HEADER, lines);
    }
}
