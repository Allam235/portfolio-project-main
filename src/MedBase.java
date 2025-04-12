import components.map.Map;
import components.map.Map.Pair;
import components.map.Map1L;
import components.sequence.Sequence;
import components.sequence.Sequence1L;

public class MedBase {

    public record Query(PatientKey key, String lookup) {
    }

    private enum PatientKey {

        // Personal Information
        FIRST_NAME, LAST_NAME, DATE_OF_BIRTH, GENDER, PATIENT_ID,

        // Medical Information
        NOTES,

        // Contact Information
        ADDRESS, PHONE_NUMBER, EMAIL,

        // Emergency Contact
        EMERGENCY_CONTACT_NAME, EMERGENCY_CONTACT_PHONE,

        // Medical History
        ALLERGIES, CURRENT_MEDICATIONS, MEDICAL_CONDITIONS, PREVIOUS_SURGERIES,

        // Insurance Information
        INSURANCE_PROVIDER, INSURANCE_POLICY_NUMBER,

        // Doctor's Information
        PRIMARY_CARE_PHYSICIAN, PHYSICIAN_CONTACT;

    }

    // Map is Patient Id and its connected Patient
    private Map<String, Map<PatientKey, String>> patientBase = new Map1L<>();

    public int baseId;

    public MedBase() {
        this.baseId = 1;
    }

    public MedBase(int baseId) {
        this.baseId = baseId;
    }

    private static String queryToString(Query... info) {
        String strInfo = "";
        System.out.println("Length" + info.length);
        for (int i = 0; i < info.length; i++) {
            strInfo += info[i].key() + ":" + info[i].lookup + "   ";
        }
        strInfo.substring(0, strInfo.length() - 3);

        System.out.println(strInfo);
        return strInfo;
    }

    // converts a string into a map represented by string data
    private static void stringToMap(Map<PatientKey, String> patientInfo,
            String info) {
        // Split the input string into individual key-value pairs.
        String[] inputs = info.split("   ");

        for (String input : inputs) {
            String[] command = input.split(":");
            String key = command[0];
            String value = command[1];

            // Match the key to the appropriate PatientKey enum and add to the map.
            switch (key.toLowerCase().replace("-", "").replace("_", "")) {
                case "firstname":
                    patientInfo.add(PatientKey.FIRST_NAME, value);
                    break;
                case "lastname":
                    patientInfo.add(PatientKey.LAST_NAME, value);
                    break;
                case "dob":
                    patientInfo.add(PatientKey.DATE_OF_BIRTH, value);
                    break;
                case "gender":
                    patientInfo.add(PatientKey.GENDER, value);
                    break;
                case "patientid":
                    patientInfo.add(PatientKey.PATIENT_ID, value);
                    break;
                case "address":
                    patientInfo.add(PatientKey.ADDRESS, value);
                    break;
                case "phone":
                    patientInfo.add(PatientKey.PHONE_NUMBER, value);
                    break;
                case "email":
                    patientInfo.add(PatientKey.EMAIL, value);
                    break;
                case "emergencycontactname":
                    patientInfo.add(PatientKey.EMERGENCY_CONTACT_NAME, value);
                    break;
                case "emergencycontactphone":
                    patientInfo.add(PatientKey.EMERGENCY_CONTACT_PHONE, value);
                    break;
                case "allergies":
                    patientInfo.add(PatientKey.ALLERGIES, value);
                    break;
                case "currentmedications":
                    patientInfo.add(PatientKey.CURRENT_MEDICATIONS, value);
                    break;
                case "medicalconditions":
                    patientInfo.add(PatientKey.MEDICAL_CONDITIONS, value);
                    break;
                case "previoussurgeries":
                    patientInfo.add(PatientKey.PREVIOUS_SURGERIES, value);
                    break;
                case "insuranceprovider":
                    patientInfo.add(PatientKey.INSURANCE_PROVIDER, value);
                    break;
                case "insurancepolicynumber":
                    patientInfo.add(PatientKey.INSURANCE_POLICY_NUMBER, value);
                    break;
                case "primarycarephysician":
                    patientInfo.add(PatientKey.PRIMARY_CARE_PHYSICIAN, value);
                    break;
                case "physiciancontact":
                    patientInfo.add(PatientKey.PHYSICIAN_CONTACT, value);
                    break;
                default:
                    System.out.println("Unknown key:" + key);
                    break;
            }
        }
    }

    public Map<PatientKey, String> updateInfo(String PatientId,
            Query... quePatientInfo) {
        String status = "";

        // Create a map to store the patient information.
        Map<PatientKey, String> patientInfo = new Map1L<>();

        if (this.patientBase.hasKey(PatientId)) {
            patientInfo.transferFrom(this.patientBase.value(PatientId));
        }

        // Split the input string into individual key-value pairs.
        stringToMap(patientInfo, queryToString(quePatientInfo));

        // if string info did not contain an id, update it with argument patient id
        if (!patientInfo.hasKey(PatientKey.PATIENT_ID)) {
            patientInfo.add(PatientKey.PATIENT_ID, PatientId);
        } else {
            // update patientId variable with patientId from string data
            PatientId = patientInfo.value(PatientKey.PATIENT_ID);
        }
        // updates patientBase with new updated Patient Info
        this.patientBase.add(PatientId, patientInfo);
        return patientInfo;
    }

    public Map<PatientKey, String> updateInfo(String PatientId,
            String strPatientInfo) {
        String status = "";

        // Create a map to store the patient information.
        Map<PatientKey, String> patientInfo = new Map1L<>();

        if (this.patientBase.hasKey(PatientId)) {
            patientInfo.transferFrom(this.patientBase.value(PatientId));
        }

        // Split the input string into individual key-value pairs.
        stringToMap(patientInfo, strPatientInfo);

        // if string info did not contain an id, update it with argument patient id
        if (!patientInfo.hasKey(PatientKey.PATIENT_ID)) {
            patientInfo.add(PatientKey.PATIENT_ID, PatientId);
        } else {
            // update patientId variable with patientId from string data
            PatientId = patientInfo.value(PatientKey.PATIENT_ID);
        }
        // updates patientBase with new updated Patient Info
        this.patientBase.add(PatientId, patientInfo);
        return patientInfo;
    }

    public Sequence<Map<PatientKey, String>> search(String searchBy) {
        Map<PatientKey, String> searchPatientInfo = new Map1L<>();
        Sequence<Map<PatientKey, String>> matchedPatients = new Sequence1L<>();
        stringToMap(searchPatientInfo, searchBy);
        boolean match;

        Map<String, Map<PatientKey, String>> tempBase = this.patientBase
                .newInstance();
        Map<PatientKey, String> patient = new Map1L<>();
        Map<PatientKey, String> searchTemp = new Map1L<>();
        // search through all patients through base
        while (this.patientBase.size() >= 1) {
            match = true;
            // pick random patient
            patient = new Map1L<>();
            patient.transferFrom(this.patientBase.removeAny().value());
            // search through all info known about the searched patient
            while (searchPatientInfo.size() >= 1) {
                // a piece of info from the search
                Pair<PatientKey, String> info = searchPatientInfo.removeAny();
                // check if info matches randomely picked patient, if not set match false;
                if (patient.hasKey(info.key())
                        && !patient.value(info.key()).equals(info.value())) {
                    match = false;
                }
                // add the info back to a temp searchPatient
                searchTemp.add(info.key(), info.value());
            }
            // transfer temp searchPatient to searchTemp
            searchPatientInfo.transferFrom(searchTemp);
            // add checked patient to tempBase
            tempBase.add(patient.value(PatientKey.PATIENT_ID), patient);
            // patient info matches
            if (match) {
                matchedPatients.add(0, patient);
            }

        }
        // transfer all patients back to patientBase
        this.patientBase.transferFrom(tempBase);
        return matchedPatients;

    }

    public Sequence<Map<PatientKey, String>> search(Query... searchBy) {
        Sequence<Map<PatientKey, String>> matchedPatients = new Sequence1L<>();

        boolean match;

        Map<String, Map<PatientKey, String>> tempBase = this.patientBase
                .newInstance();
        Map<PatientKey, String> patient = new Map1L<>();
        // search through all patients through base
        while (this.patientBase.size() >= 1) {
            match = true;
            // pick random patient
            patient = new Map1L<>();
            patient.transferFrom(this.patientBase.removeAny().value());
            // search through all info known about the searched patient
            for (int i = 0; i < searchBy.length && match; i++) {
                // check if info matches randomely picked patient, if not set match false;
                if (patient.hasKey(searchBy[i].key())
                        && !patient.value(searchBy[i].key())
                                .equals(searchBy[i].lookup())) {
                    match = false;
                }
            }
            // add checked patient to tempBase
            tempBase.add(patient.value(PatientKey.PATIENT_ID), patient);
            // patient info matches
            if (match) {
                matchedPatients.add(0, patient);
            }

        }
        // transfer all patients back to patientBase
        this.patientBase.transferFrom(tempBase);
        return matchedPatients;

    }

    /*
     * Returns map containg patient data
     *
     * @param patientId the patient id
     *
     * @return Map<PatientKey, String>
     *
     * @ensures Map<PatientKey, String>.value(PatientKey.PATIENTID) == patientId
     */
    public Map<PatientKey, String> patientRecord(String patientId) {
        return this.patientBase.value(patientId);
    }

    public static void main(String[] args) {
        MedBase patientData = new MedBase();
        // Patient 1: Full Data
        // JD123456;

        // Patient 1:
        String patient1Info = "FirstName:John   LastName:Doe   DOB:1985-05-15   Gender:Male   PatientID:JD123456   Address:123 Main St, Springfield, IL   Phone:+1 555-1234   Email:johndoe@example.com   EmergencyContactName:Jane Doe   EmergencyContactPhone:+1 555-5678   Allergies:Penicillin   CurrentMedications:Aspirin   MedicalConditions:Hypertension   PreviousSurgeries:Appendectomy   InsuranceProvider:HealthCo   InsurancePolicyNumber:HC987654321   PrimaryCarePhysician:Dr. Smith   PhysicianContact:+1 555-8765";
        patientData.updateInfo("JD123456", patient1Info);

        // Patient 2:
        Query info1 = new Query(PatientKey.FIRST_NAME, "Mary");
        Query info2 = new Query(PatientKey.LAST_NAME, "Johnson");
        Query info3 = new Query(PatientKey.PATIENT_ID, "MJ1234");
        String patient2Info = "FirstName:Mary   LastName:Johnson   PatientID:MJ1234   DOB:1990-08-22   Address:456 Oak Rd, Madison, WI";
        patientData.updateInfo("MJ1234", info1, info2, info3);

        // Patient 3:
        String patient3Info = "FirstName:Emily   LastName:Lane   PatientID:EL8325";
        patientData.updateInfo("EL8325", patient3Info);

        System.out.println(patientData.search(info1, info2).remove(0)
                .value(PatientKey.FIRST_NAME));

    }

}
