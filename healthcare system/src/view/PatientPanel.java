package view;

import controller.PatientController;
import model.Patient;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * PatientPanel provides the UI for managing patients in the Healthcare Management System.
 *
 * Responsibilities:
 *  - Display all patients
 *  - Capture user input to create new patients
 *  - Delete existing patients
 *
 * Note: This class belongs to the View layer and contains no business logic.
 *       All operations are delegated to the PatientController.
 */
public class PatientPanel extends JPanel {

    private final PatientController controller;
    private final JTextArea output = new JTextArea(20, 70);

    /**
     * Constructs the PatientPanel UI.
     *
     * Initializes buttons, layout, and action listeners.
     *
     * @param controller PatientController injected from MainFrame
     */
    public PatientPanel(PatientController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        // Buttons
        JButton viewBtn = new JButton("View Patients");
        JButton createBtn = new JButton("Create Patient");
        JButton deleteBtn = new JButton("Delete Patient");

        JPanel top = new JPanel();
        top.add(viewBtn);
        top.add(createBtn);
        top.add(deleteBtn);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(output), BorderLayout.CENTER);

        // Action listeners
        viewBtn.addActionListener(e -> viewPatients());
        createBtn.addActionListener(e -> createPatient());
        deleteBtn.addActionListener(e -> deletePatient());
    }

    /**
     * Retrieves all patients from the controller and displays them
     * in the text area.
     */
    private void viewPatients() {
        output.setText("");
        List<Patient> list = controller.getAllPatients();
        for (Patient p : list) {
            output.append(p.toString() + "\n");
        }
    }

    /**
     * Collects patient details from the user using input dialogs
     * and delegates creation to the controller.
     *
     * Shows a confirmation dialog on success, or an error dialog if creation fails.
     */
    private void createPatient() {
        try {
            String firstName = JOptionPane.showInputDialog(this, "First Name:");
            String lastName = JOptionPane.showInputDialog(this, "Last Name:");
            String dateOfBirth = JOptionPane.showInputDialog(this, "Date of Birth:");
            String nhsNumber = JOptionPane.showInputDialog(this, "NHS Number:");
            String gender = JOptionPane.showInputDialog(this, "Gender:");
            String phoneNumber = JOptionPane.showInputDialog(this, "Phone Number:");
            String email = JOptionPane.showInputDialog(this, "Email:");
            String address = JOptionPane.showInputDialog(this, "Address:");
            String postcode = JOptionPane.showInputDialog(this, "Postcode:");
            String emergencyName = JOptionPane.showInputDialog(this, "Emergency Contact Name:");
            String emergencyPhone = JOptionPane.showInputDialog(this, "Emergency Contact Phone:");
            String registrationDate = JOptionPane.showInputDialog(this, "Registration Date:");
            String gpSurgeryId = JOptionPane.showInputDialog(this, "GP Surgery ID:");

            controller.createPatient(firstName, lastName, dateOfBirth, nhsNumber, gender,
                    phoneNumber, email, address, postcode, emergencyName, emergencyPhone,
                    registrationDate, gpSurgeryId);

            JOptionPane.showMessageDialog(this, "Patient created successfully.");
            viewPatients();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error creating patient: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * Prompts the user to enter a patient ID and deletes
     * the corresponding patient via the controller.
     *
     * Displays a confirmation dialog if deletion succeeds,
     * or a "not found" message if the patient does not exist.
     * Refreshes the displayed list after deletion.
     */
    private void deletePatient() {
        String id = JOptionPane.showInputDialog(this, "Patient ID to delete:");
        boolean success = controller.deletePatient(id);
        if (success) {
            JOptionPane.showMessageDialog(this, "Patient deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Patient not found.");
        }
        viewPatients();
    }
}
