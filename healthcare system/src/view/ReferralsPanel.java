package view;

import controller.ReferralController;
import model.Referral;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * ReferralsPanel represents the UI screen for managing referrals
 * in the Healthcare Management System.
 *
 * Responsibilities:
 *  - Display all referrals in a text area
 *  - Collect user input to create new referrals
 *  - Delete existing referrals
 *  - Delegate all creation and deletion logic to the ReferralController
 *
 * Note: This class belongs to the View layer and contains no business or persistence logic.
 */
public class ReferralsPanel extends JPanel {

    private final ReferralController controller;
    private final JTextArea output = new JTextArea(20, 70);

    /**
     * Constructs the ReferralsPanel UI.
     *
     * Initializes layout, buttons, and action listeners.
     *
     * @param controller ReferralController injected from MainFrame
     */
    public ReferralsPanel(ReferralController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        // Buttons for viewing, creating, and deleting referrals
        JButton viewBtn = new JButton("View Referrals");
        JButton createBtn = new JButton("Create Referral");
        JButton deleteBtn = new JButton("Delete Referral");

        JPanel top = new JPanel();
        top.add(viewBtn);
        top.add(createBtn);
        top.add(deleteBtn);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(output), BorderLayout.CENTER);

        // Button actions
        viewBtn.addActionListener(e -> viewReferrals());
        createBtn.addActionListener(e -> createReferral());
        deleteBtn.addActionListener(e -> deleteReferral());
    }

    /**
     * Retrieves all referrals from the controller
     * and displays them in the text area.
     */
    private void viewReferrals() {
        output.setText("");
        List<Referral> list = controller.getAllReferrals();
        for (Referral r : list) {
            output.append(r.toString() + "\n"); // Uses model's toString()
        }
    }

    /**
     * Collects referral details from the user using input dialogs
     * and delegates creation to the controller.
     *
     * Shows a confirmation dialog on success, or an error dialog if creation fails.
     */
    private void createReferral() {
        try {
            String patientId = JOptionPane.showInputDialog(this, "Patient ID:");
            String referringClinicianId = JOptionPane.showInputDialog(this, "Referring Clinician ID:");
            String referredToClinicianId = JOptionPane.showInputDialog(this, "Referred To Clinician ID:");
            String referringFacilityId = JOptionPane.showInputDialog(this, "Referring Facility ID:");
            String referredToFacilityId = JOptionPane.showInputDialog(this, "Referred To Facility ID:");
            String urgencyLevel = JOptionPane.showInputDialog(this, "Urgency Level (Routine / Urgent / Emergency):");
            String referralReason = JOptionPane.showInputDialog(this, "Referral Reason:");
            String clinicalSummary = JOptionPane.showInputDialog(this, "Clinical Summary:");
            String appointmentId = JOptionPane.showInputDialog(this, "Appointment ID:");

            controller.createReferral(
                    patientId,
                    referringClinicianId,
                    referredToClinicianId,
                    referringFacilityId,
                    referredToFacilityId,
                    urgencyLevel,
                    referralReason,
                    clinicalSummary,
                    appointmentId
            );

            JOptionPane.showMessageDialog(this, "Referral created successfully.");
            viewReferrals();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error creating referral: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * Prompts the user to enter a referral ID and deletes
     * the corresponding referral via the controller.
     
     * Displays a confirmation dialog if deletion succeeds,
     * or an error dialog if the referral does not exist or fails to delete.
     * Refreshes the displayed list after deletion.
     */
    private void deleteReferral() {
        String referralId = JOptionPane.showInputDialog(this, "Enter Referral ID to delete:");
        if (referralId != null && !referralId.isEmpty()) {
            try {
                controller.deleteReferral(referralId);
                JOptionPane.showMessageDialog(this, "Referral deleted successfully.");
                viewReferrals();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error deleting referral: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}

