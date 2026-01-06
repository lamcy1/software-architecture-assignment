package view;

import controller.AppointmentController;
import model.Appointment;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * AppointmentsPanel represents the Appointments UI screen
 * of the Healthcare Management System.
 *
 * Responsibilities:
 *  - Display appointment-related options to the user
 *  - Capture user input via Swing dialogs
 *  - Delegate all operations to the AppointmentController
 *
 * Note: This class belongs to the View layer and does NOT
 * contain any business logic or data persistence.
 */
public class AppointmentsPanel extends JPanel {

    private final AppointmentController controller;
    private final JTextArea output = new JTextArea(20, 70);

    /**
     * Constructs the AppointmentsPanel UI.
     *
     * Initializes buttons, layout, and action listeners.
     *
     * @param controller AppointmentController injected from MainFrame
     */
    public AppointmentsPanel(AppointmentController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        JButton viewBtn = new JButton("View Appointments");
        JButton createBtn = new JButton("Create Appointment");
        JButton cancelBtn = new JButton("Cancel Appointment");

        JPanel top = new JPanel();
        top.add(viewBtn);
        top.add(createBtn);
        top.add(cancelBtn);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(output), BorderLayout.CENTER);

        viewBtn.addActionListener(e -> viewAppointments());
        createBtn.addActionListener(e -> createAppointment());
        cancelBtn.addActionListener(e -> cancelAppointment());
    }

    /**
     * Retrieves all appointments from the controller
     * and displays them in the text area.
     *
     * Cancelled appointments are marked explicitly.
     */
    private void viewAppointments() {
        output.setText("");
        List<Appointment> list = controller.getAllAppointments();
        for (Appointment a : list) {
            String display = a.toString();
            if (a.status.equalsIgnoreCase("Cancelled")) {
                display += " [CANCELLED]";  // mark cancelled
            }
            output.append(display + "\n");
        }
    }

    /**
     * Prompts the user to enter appointment details using input dialogs
     * and creates a new appointment via the AppointmentController.
     *
     * After creation, the appointments list is refreshed.
     */
    private void createAppointment() {
        String patientId = JOptionPane.showInputDialog("Patient ID:");
        String clinicianId = JOptionPane.showInputDialog("Clinician ID:");
        String facilityId = JOptionPane.showInputDialog("Facility ID:");
        String date = JOptionPane.showInputDialog("Date (YYYY-MM-DD):");
        String time = JOptionPane.showInputDialog("Time (HH:MM):");
        String duration = JOptionPane.showInputDialog("Duration (minutes):");
        String type = JOptionPane.showInputDialog("Type:");
        String reason = JOptionPane.showInputDialog("Reason:");

        // Delegate creation to the controller
        controller.createAppointment(
                patientId, clinicianId, facilityId,
                date, time, duration, type, reason
        );

        JOptionPane.showMessageDialog(this, "Appointment created.");
        viewAppointments();
    }

    /**
     * Prompts the user to enter an appointment ID and
     * cancels the corresponding appointment via the controller.
     *
     * After cancellation, the appointments list is refreshed.
     */
    private void cancelAppointment() {
        String id = JOptionPane.showInputDialog("Appointment ID:");
        controller.cancelAppointment(id);
        JOptionPane.showMessageDialog(this, "Appointment cancelled.");
        viewAppointments();
    }
}
