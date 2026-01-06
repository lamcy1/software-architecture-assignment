package view;

import javax.swing.*;
import java.awt.*;

/**
 * MainFrame represents the main application window
 * of the Healthcare Management System.
 *
 * Responsibilities:
 *  - Create and configure the main JFrame
 *  - Set window title, size, and default close operation
 *  - Initialize and display all main UI panels in a tabbed layout
 *  - Delegate user interactions to the respective panels and their controllers
 *
 * Note: MainFrame belongs to the View layer and contains no business logic.
 */
public class MainFrame extends JFrame {

    /**
     * Constructs the main application window and adds all panels.
     *
     * @param appointmentsPanel   Panel for managing appointments
     * @param prescriptionsPanel  Panel for managing prescriptions
     * @param referralsPanel      Panel for managing referrals
     * @param patientsPanel       Panel for managing patients
     * @param cliniciansPanel     Panel for managing clinicians
     */
    public MainFrame(
            JPanel appointmentsPanel,
            JPanel prescriptionsPanel,
            JPanel referralsPanel,
            JPanel patientsPanel,
            JPanel cliniciansPanel
    ) {
        setTitle("Healthcare Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Patients", patientsPanel);
        tabs.addTab("Clinicians", cliniciansPanel);
        tabs.addTab("Appointments", appointmentsPanel);
        tabs.addTab("Prescriptions", prescriptionsPanel);
        tabs.addTab("Referrals", referralsPanel);

        add(tabs);
    }

}
