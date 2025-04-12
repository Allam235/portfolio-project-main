import components.map.Map;
import components.map.Map1L;
import components.sequence.Sequence;

public class MedBaseKernal extends Standard<MedBase> {

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

    private Map<String, Map<PatientKey, String>> patientBase = new Map1L<>();

    public int baseId;

    /**
     * Formats an array of {@code Query} records into a {@code String} where
     * each {@code Query} record is seperated by a " ".
     *
     * @param info
     *            the array of {@code Query} objects
     * @return a {@code String} representing the {@code info} array
     * @requires info ≠ null
     * @ensures <pre>
     * [result is a concatenation of each key value pair separated by "   ",
     * with no trailing spaces]
     * </pre>
     */
    private static String queryToString(Query... info) {
    }

    /**
     * Fills the given {@code Map<PatientKey, String>} from parsing String
     * {@code info}.
     *
     * @param patientInfo
     *            map of {@code PatientKey} and {@code String} to be filled
     * @param info
     *            formatted {@code String} containing data to fill
     *            {@code patientInfo}
     * @replaces patientInfo
     * @requires info ≠ null
     * @requires <pre>
     * [info is a non-null string containing the format
     *  "key:value   key:value   ..." where key corresponds to enum {@code PatientKey}]
     * </pre>
     * @ensures <pre>
     * patientInfo = [map contains parsed key-value pairs from info]
     * </pre>
     */
    private static void stringToMap(Map<PatientKey, String> patientInfo,
            String info) {
    }

    /**
     * Updates or creates a value in {@code patientBase} map using the provided
     * patient ID and an array of type {@code Query}.
     *
     * @param patientId
     *            the ID of the patient
     * @param quePatientInfo
     *            array of type {@code Query} representing patient info
     *
     * @return the updated patient info map
     * @updates patientBase
     * @requires PatientId ≠ null
     * @ensures <pre>
     * [patientBase contains an entry with the updated data for
     * PatientId from {@code quePatientInfo}]
     * </pre>
     */
    public Map<PatientKey, String> updateInfo(String patientId,
            Query... quePatientInfo) {
    }

    /**
     * Updates or creates a value in {@code patientBase} map using the provided
     * {@code patientId} and {@code strPatientInfo}.
     *
     * @param patientId
     *            the ID of the patient
     * @param strPatientInfo
     *            {@code String} representing patient info
     *
     * @return the updated patient info map
     * @updates patientBase
     * @requires PatientId ≠ null
     * @ensures <pre>
     * [patientBase contains an entry with the updated data for
     * PatientId from {@code strPatientInfo}]
     * </pre>
     */
    public Map<PatientKey, String> updateInfo(String patientId,
            String strPatientInfo) {
    }

    /**
     * Creates sequence of type {@code <Map<PatientKey, String>} which contains
     * {@code <Map<PatientKey, String>} from {@code patientBase} that contain
     * same key value pairs parsed from {@code searchBy}.
     *
     * @param searchBy
     *            formatted string representing a
     *            {@code <Map<PatientKey, String>}
     * @return sequence of {@code <Map<PatientKey, String>} that match the
     *         search criteria
     * @updates patientBase
     * @requires <pre>
     * searchBy ≠ null and [searchBy is a valid formatted patient info string]
     * </pre>
     * @ensures <pre>
     * [returns a sequence of {@code <Map<PatientKey, String>} where all key value
     * pairs are the same as the key value pairs in searchBy]
     * </pre>
     */
    public Sequence<Map<PatientKey, String>> search(String searchBy) {
    }

    /**
     * Creates sequence of type {@code <Map<PatientKey, String>} which contains
     * {@code <Map<PatientKey, String>} from {@code patientBase} that contain
     * same key value pairs parsed from {@code searchBy}.
     *
     * @param searchBy
     *            {@code <Map<PatientKey, String>} containing patient data
     * @return sequence of {@code <Map<PatientKey, String>} that match the
     *         search criteria
     * @updates patientBase
     * @requires searchBy ≠ null
     * @ensures <pre>
     * [returns a sequence of {@code <Map<PatientKey, String>} where all key value
     * pairs are the same as the key value pairs in searchBy]
     * </pre>
     */
    public Sequence<Map<PatientKey, String>> search(Query... searchBy) {
    }

    /**
     * Returns map containg patient data.
     *
     * @param patientId
     *            the ID of the patient
     *
     * @return map of patient data
     *
     * @requires patientId ≠ null and patientBase.hasKey(patientId)
     *
     * @ensures <pre> [returns the map of patient data where
     * value(PatientKey.PATIENT_ID) = patientId] </pre>
     */
    public Map<PatientKey, String> patientRecord(String patientId) {
    }

}
