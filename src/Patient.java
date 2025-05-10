import java.util.Date;

import components.map.Map;

public class Patient {

    // Personal Information
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private String patientId;

    //Medical Informatino
    private Map<Date, String> notes; // contains notes and their dates

    // Contact Information
    private String address;
    private String phoneNumber;
    private String email;

    // Emergency Contact
    private String emergencyContactName;
    private String emergencyContactPhone;

    // Medical History
    private String allergies;
    private String currentMedications;
    private String medicalConditions;
    private String previousSurgeries;

    // Insurance Information
    private String insuranceProvider;
    private String insurancePolicyNumber;

    // Doctor's Information
    private String primaryCarePhysician;
    private String physicianContact;

    public Patient() {
        this.patientId = "0";
    }

    public String registerInfo(String args) {
        String status = "";

        String[] inputs = args.split(" ");

        for (String input : inputs) {
            String[] command = input.split(":");
            String value = command[1];
            String key = command[0];
            switch (key) {
                case "firstName":
                    this.firstName = value;
                    break;
                case "lastName":
                    this.lastName = value;
                    break;
                case "DOB":
                    this.dateOfBirth = value;
                    break;
                case "Gender":
                    this.gender = value;
                    break;
                case "Address":
                    this.address = value;
                    break;
                case "Phone":
                    this.phoneNumber = value;
                    break;
                case "Email":
                    this.email = value;
                    break;
                case "EmergencyContactName":
                    this.emergencyContactName = value;
                    break;
                case "EmergencyContactPhone":
                    this.emergencyContactPhone = value;
                    break;
                case "Allergies":
                    this.allergies = value;
                    break;
                case "CurrentMedications":
                    this.currentMedications = value;
                    break;
                case "MedicalConditions":
                    this.medicalConditions = value;
                    break;
                case "PreviousSurgeries":
                    this.previousSurgeries = value;
                    break;
                case "InsuranceProvider":
                    this.insuranceProvider = value;
                    break;
                case "InsurancePolicyNumber":
                    this.insurancePolicyNumber = value;
                    break;
                case "PrimaryCarePhysician":
                    this.primaryCarePhysician = value;
                    break;
                case "PhysicianContact":
                    this.physicianContact = value;
                    break;
                default:
                    System.out.println("Unknown key: " + key);
                    break;
            }
        }

        return status;
    }

    public void noteTake(Date date, String note) {
        this.notes.add(date, note);
    }

    public Patient(String args) {
        this.registerInfo(args);
    }
}
