package controller;

import model.Appointment;
import service.AppointmentManager;

import java.util.List;

/**
 * AppointmentController serves as the intermediary between
 * the UI layer (e.g., Swing) and the AppointmentManager service.
 *
 * Responsibilities:
 *  - Receive user actions from the view
 *  - Delegate appointment-related operations to the service layer
 *  - Return results back to the UI
 */
public class AppointmentController {

    private final AppointmentManager appointmentManager;

    /**
     * Constructs the AppointmentController with a reference to
     * the AppointmentManager service.
     *
     * @param appointmentManager Service layer instance for appointments
     */
    public AppointmentController(AppointmentManager appointmentManager) {
        this.appointmentManager = appointmentManager;
    }

    /**
     * Creates a new appointment and delegates to the service layer.
     *
     * @param patientId       ID of the patient
     * @param clinicianId     ID of the clinician
     * @param facilityId      ID of the facility
     * @param appointmentDate Date of the appointment (YYYY-MM-DD)
     * @param appointmentTime Time of the appointment (HH:MM)
     * @param durationMinutes Duration of the appointment in minutes
     * @param appointmentType Type of appointment (e.g., Consultation)
     * @param reasonForVisit  Reason for the appointment
     * @return The newly created Appointment object
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
        return appointmentManager.createAppointment(
                patientId,
                clinicianId,
                facilityId,
                appointmentDate,
                appointmentTime,
                durationMinutes,
                appointmentType,
                reasonForVisit
        );
    }

    /**
     * Cancels an existing appointment by ID.
     *
     * Delegates the cancellation to the AppointmentManager service.
     *
     * @param appointmentId ID of the appointment to cancel
     */
    public void cancelAppointment(String appointmentId) {
        appointmentManager.cancelAppointment(appointmentId);
    }

    /**
     * Retrieves all appointments currently stored.
     *
     * @return List of Appointment objects
     */
    public List<Appointment> getAllAppointments() {
        return appointmentManager.getAllAppointments();
    }
}
