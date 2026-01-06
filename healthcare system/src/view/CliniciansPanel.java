package view;

import controller.ClinicianController;
import model.Clinician;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * CliniciansPanel represents the UI screen for managing clinicians/consultants
 * in the Healthcare Management System.
 *
 * Responsibilities:
 *  - Display all clinicians
 *  - Capture user input to create new clinicians
 *  - Delete existing clinicians
 *
 * Note: This class belongs to the View layer and does NOT contain
 * any business logic or data persistence.
 */
public class CliniciansPanel extends JPanel {

    private final ClinicianController controller;
    private final JTextArea output = new JTextArea(20, 70);

    /**
     * Constructs the CliniciansPanel UI.
     *
     * Initializes buttons, layout, and action listeners.
     *
     * @param controller ClinicianController injected from MainFrame
     */
    public CliniciansPanel(ClinicianController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        JButton viewBtn = new JButton("View Clinicians");
        JButton createBtn = new JButton("Create Clinicians");
        JButton deleteBtn = new JButton("Delete Clinicians");

        JPanel top = new JPanel();
        top.add(viewBtn);
        top.add(createBtn);
        top.add(deleteBtn);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(output), BorderLayout.CENTER);

        viewBtn.addActionListener(e -> viewClinicians());
        createBtn.addActionListener(e -> createClinician());
        deleteBtn.addActionListener(e -> deleteClinician());
    }

    /**
     * Retrieves all clinicians from the controller and displays them
     * in the text area.
     */
    private void viewClinicians() {
        output.setText("");
        List<Clinician> list = controller.getAllClinicians();
        for (Clinician c : list) {
            output.append(c.toString() + "\n");
        }
    }

    /**
     * Collects clinician details from the user using input dialogs
     * and delegates creation to the controller.
     *
     * Shows a confirmation dialog on success, or an error dialog if creation fails.
     */
    private void createClinician() {
        try {
            String firstName = JOptionPane.showInputDialog(this, "First Name:");
            String lastName = JOptionPane.showInputDialog(this, "Last Name:");
            String title = JOptionPane.showInputDialog(this, "Title (GP / Nurse / Specialist):");
            String speciality = JOptionPane.showInputDialog(this, "Speciality:");
            String gmcNumber = JOptionPane.showInputDialog(this, "GMC Number:");
            String phone = JOptionPane.showInputDialog(this, "Phone Number:");
            String email = JOptionPane.showInputDialog(this, "Email:");
            String workplaceId = JOptionPane.showInputDialog(this, "Workplace Facility ID:");
            String workplaceType = JOptionPane.showInputDialog(this, "Workplace Type:");
            String employmentStatus = JOptionPane.showInputDialog(this, "Employment Status:");
            String startDate = JOptionPane.showInputDialog(this, "Start Date (YYYY-MM-DD):");

            controller.createClinician(
                    firstName,
                    lastName,
                    title,
                    speciality,
                    gmcNumber,
                    phone,
                    email,
                    workplaceId,
                    workplaceType,
                    employmentStatus,
                    startDate
            );

            JOptionPane.showMessageDialog(this, "Clinician created successfully.");
            viewClinicians();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error creating clinician: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * Prompts the user to enter a clinician ID and deletes
     * the corresponding clinician via the controller.
     *
     * Refreshes the displayed list after deletion.
     */
    private void deleteClinician() {
        String id = JOptionPane.showInputDialog(this, "Consultant ID:");
        controller.deleteClinician(id);
        JOptionPane.showMessageDialog(this, "Consultant deleted.");
        viewClinicians();
    }
}
