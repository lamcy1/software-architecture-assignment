package view;

import controller.PrescriptionController;
import model.Prescription;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * PrescriptionsPanel represents the UI screen for managing prescriptions
 * in the Healthcare Management System.
 *
 * Responsibilities:
 *  - Display all prescriptions in a text area
 *  - Collect user input to create new prescriptions
 *  - Delegate all creation and retrieval logic to the PrescriptionController
 *
 * Note: This class belongs to the View layer and contains no business or persistence logic.
 */
public class PrescriptionsPanel extends JPanel {

    private final PrescriptionController controller;
    private final JTextArea output = new JTextArea(20, 70);

    /**
     * Constructs the PrescriptionsPanel UI.
     *
     * Initializes layout, buttons, and action listeners.
     *
     * @param controller PrescriptionController injected from MainFrame
     */
    public PrescriptionsPanel(PrescriptionController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        // Buttons for viewing and creating prescriptions
        JButton viewBtn = new JButton("View Prescriptions");
        JButton createBtn = new JButton("Create Prescription");

        JPanel top = new JPanel();
        top.add(viewBtn);
        top.add(createBtn);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(output), BorderLayout.CENTER);

        // Button actions
        viewBtn.addActionListener(e -> viewPrescriptions());
        createBtn.addActionListener(e -> createPrescription());
    }

    /**
     * Retrieves all prescriptions from the controller
     * and displays them in the text area.
     */
    private void viewPrescriptions() {
        output.setText("");
        List<Prescription> list = controller.getAllPrescriptions();
        for (Prescription p : list) {
            output.append(p.toString() + "\n"); // Uses model's toString()
        }
    }

    /**
     * Collects prescription details from the user using input dialogs
     * and delegates creation to the controller.
     *
     * Shows a confirmation dialog on success, or an error dialog if creation fails.
     */
    private void createPrescription() {
        try {
            String patientId = JOptionPane.showInputDialog(this, "Patient ID:");
            String clinicianId = JOptionPane.showInputDialog(this, "Clinician ID:");
            String appointmentId = JOptionPane.showInputDialog(this, "Appointment ID:");
            String medicationName = JOptionPane.showInputDialog(this, "Medication Name:");
            String dosage = JOptionPane.showInputDialog(this, "Dosage:");
            String frequency = JOptionPane.showInputDialog(this, "Frequency:");
            String durationDays = JOptionPane.showInputDialog(this, "Duration (days):");
            String quantity = JOptionPane.showInputDialog(this, "Quantity:");
            String instructions = JOptionPane.showInputDialog(this, "Instructions:");
            String pharmacyName = JOptionPane.showInputDialog(this, "Pharmacy Name:");

            controller.createPrescription(
                    patientId,
                    clinicianId,
                    appointmentId,
                    medicationName,
                    dosage,
                    frequency,
                    durationDays,
                    quantity,
                    instructions,
                    pharmacyName
            );

            JOptionPane.showMessageDialog(this, "Prescription created successfully.");
            viewPrescriptions();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error creating prescription: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
