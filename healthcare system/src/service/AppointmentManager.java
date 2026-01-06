package service;

/*
  AppointmentManager is responsible for managing all appointment-related operations.

  It serves as the service layer between:
  - AppointmentRepository (handles data persistence via CSV)
  - Appointment model (represents the appointment domain object)

  Responsibilities of this class include:
  - Loading existing appointments from the CSV file into memory
  - Creating new appointments with unique IDs
  - Updating appointment status (e.g., cancelling appointments)
  - Persisting all changes back to the CSV file
*/

import model.Appointment;
import repository.AppointmentRepository;
import utility.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class AppointmentManager {

    private final AppointmentRepository repository;
    private final List<Appointment> appointments;

    /**
     * Constructs an AppointmentManager and loads all appointments from the repository.
     *
     * @param repository the AppointmentRepository for CSV persistence
     */
    public AppointmentManager(AppointmentRepository repository) {
        this.repository = repository;
        this.appointments = repository.loadAll();
    }

    /**
     * Creates a new appointment, assigns it a unique ID, and saves it to the repository.
     *
     * @param patientId       the ID of the patient
     * @param clinicianId     the ID of the clinician
     * @param facilityId      the ID of the facility
     * @param appointmentDate the date of the appointment (YYYY-MM-DD)
     * @param appointmentTime the time of the appointment (HH:MM)
     * @param durationMinutes the duration of the appointment in minutes
     * @param appointmentType the type of the appointment (e.g., Consultation)
     * @param reasonForVisit  the reason for the appointment
     * @return the newly created Appointment object
     */
    public Appointment createAppointment(
            String patientId,
            String clinicianId,
            String facilityId,
            String appointmentDate,
            String appointmentTime,
            String durationMinutes,
            String appointmentType,
            String reasonForVisit
    ) {
        // Collect existing appointment IDs to avoid duplicates
        List<String> ids = new ArrayList<>();
        for (Appointment a : appointments) {
            ids.add(a.appointmentId);
        }

        // Generate a new unique ID and set timestamps
        String newId = IdGenerator.nextId("A", ids);
        String today = java.time.LocalDate.now().toString();

        // Create the appointment object
        Appointment appointment = new Appointment(
                newId,
                patientId,
                clinicianId,
                facilityId,
                appointmentDate,
                appointmentTime,
                durationMinutes,
                appointmentType,
                "Scheduled",
                reasonForVisit,
                "",
                today,
                today
        );

        // Add to in-memory list and persist
        appointments.add(appointment);
        repository.saveAll(appointments);

        return appointment;
    }

    /**
     * Cancels an existing appointment by updating its status to "Cancelled".
     *
     * @param appointmentId the ID of the appointment to cancel
     */
    public void cancelAppointment(String appointmentId) {
        String today = java.time.LocalDate.now().toString();

        for (Appointment a : appointments) {
            if (a.appointmentId.equals(appointmentId)) {
                a.status = "Cancelled";
                a.lastModified = today;
                break;
            }
        }

        repository.saveAll(appointments);
    }

    /**
     * Retrieves all appointments currently loaded in memory.
     *
     * @return a List of Appointment objects
     */
    public List<Appointment> getAllAppointments() {
        return appointments;
    }
}
