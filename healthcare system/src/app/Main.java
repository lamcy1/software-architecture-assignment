package app;

import controller.*;
import repository.*;
import service.*;
import view.*;

import javax.swing.*;

/**
 * Main entry point for the Healthcare Management System.
 *
 * Responsibilities:
 *  - Initialize CSV-backed repositories for all entities
 *  - Set up service layer (managers) for business logic
 *  - Set up controllers to handle user actions
 *  - Construct and display the Swing UI with all relevant panels
 */
public class Main {

    public static void main(String[] args) {

        // Ensure the Swing UI is created on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                /* =========================
                   Repositories (Data Layer)
                   ========================= */
                PatientRepository patientRepo = new PatientRepository("healthcare system/src/patients.csv");
                ClinicianRepository clinicianRepo = new ClinicianRepository("healthcare system/src/clinicians.csv");
                FacilityRepository facilityRepo = new FacilityRepository("healthcare system/src/facilities.csv");
                AppointmentRepository appointmentRepo = new AppointmentRepository("healthcare system/src/appointments.csv");
                PrescriptionRepository prescriptionRepo = new PrescriptionRepository("healthcare system/src/prescriptions.csv");
                ReferralRepository referralRepo = new ReferralRepository("healthcare system/src/referrals.csv");
                StaffRepository staffRepo = new StaffRepository("healthcare system/src/staff.csv");

                /* =========================
                   Services / Managers (Business Logic Layer)
                   ========================= */
                PatientManager patientManager = new PatientManager(patientRepo);
                AppointmentManager appointmentManager = new AppointmentManager(appointmentRepo);
                PrescriptionManager prescriptionManager = new PrescriptionManager(prescriptionRepo);
                ReferralManager referralManager = ReferralManager.getInstance(referralRepo); // Singleton

                ClinicianManager clinicianManager = new ClinicianManager(clinicianRepo);

                /* =========================
                   Controllers (Handles UI Actions)
                   ========================= */
                PatientController patientController = new PatientController(patientManager);
                AppointmentController appointmentController = new AppointmentController(appointmentManager);
                PrescriptionController prescriptionController = new PrescriptionController(prescriptionManager);
                ReferralController referralController = new ReferralController(referralManager);

                ClinicianController clinicianController = new ClinicianController(clinicianManager);

                /* =========================
                   Views / UI Panels (Swing Components)
                   ========================= */
                PatientPanel patientsPanel = new PatientPanel(patientController);
                AppointmentsPanel appointmentsPanel = new AppointmentsPanel(appointmentController);
                PrescriptionsPanel prescriptionsPanel = new PrescriptionsPanel(prescriptionController);
                ReferralsPanel referralsPanel = new ReferralsPanel(referralController);

                CliniciansPanel clinicianPanel = new CliniciansPanel(clinicianController);

                /* =========================
                   Main Application Frame
                   ========================= */
                MainFrame frame = new MainFrame(
                        appointmentsPanel,
                        prescriptionsPanel,
                        referralsPanel,
                        patientsPanel,
                        clinicianPanel
                );

                // Display the main window
                frame.setVisible(true);
            }
        });
    }
}
