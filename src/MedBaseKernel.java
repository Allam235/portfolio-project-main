import components.map.Map;
import components.standard.Standard;

public interface MedBaseKernel extends Standard<MedBase> {

        public record Query(PatientKey key, String lookup) {
        }

        /**
         * Formats an array of {@code Query} records into a {@code String} where
         * each {@code Query} record is seperated by a " ".
         *
         * @param info
         *                the array of {@code Query} objects
         * @return a {@code String} representing the {@code info} array
         * @requires info ≠ null
         * @ensures <pre>
         * [result is a concatenation of each key value pair separated by "   ",
         * with no trailing spaces]
         * </pre>
         */
        private static String queryToString(Query... info) {
                return "";
        }

        /**
         * Fills the given {@code Map<PatientKey, String>} from parsing String
         * {@code info}.
         *
         * @param patientInfo
         *                map of {@code PatientKey} and {@code String} to be
         *                filled
         * @param info
         *                formatted {@code String} containing data to fill
         *                {@code patientInfo}
         * @replaces patientInfo
         * @requires info ≠ null
         * @requires <pre>
         * [info is a non-null string containing the format
         *  "key:value   key:value   ..." where key
         * corresponds to enum {@code PatientKey}]
         * </pre>
         * @ensures <pre>
         * patientInfo = [map contains parsed key-value pairs from info]
         * </pre>
         */
        private static void stringToMap(Map<PatientKey, String> patientInfo,
                        String info) {
        }

        /**
         * Updates or creates a value in {@code patientBase} map using the
         * provided patient ID and an array of type {@code Query}.
         *
         * @param patientId
         *                the ID of the patient
         * @param quePatientInfo
         *                array of type {@code Query} representing patient info
         *
         * @return the updated patient info map
         * @updates patientBase
         * @requires PatientId ≠ null
         * @ensures <pre>
         * [patientBase contains an entry with the updated data for
         * PatientId from {@code quePatientInfo}]
         * </pre>
         */
        Map<PatientKey, String> updateInfo(String patientId,
                        Query... quePatientInfo);

        /**
         * Updates or creates a value in {@code patientBase} map using the
         * provided {@code patientId} and {@code strPatientInfo}.
         *
         * @param patientId
         *                the ID of the patient
         * @param strPatientInfo
         *                {@code String} representing patient info
         *
         * @return the updated patient info map
         * @updates patientBase
         * @requires PatientId ≠ null
         * @ensures <pre>
         * [patientBase contains an entry with the updated data for
         * PatientId from {@code strPatientInfo}]
         * </pre>
         */
        Map<PatientKey, String> updateInfo(String patientId,
                        String strPatientInfo);

        /**
         * Returns map containg patient data, or null if {@code patientId} does
         * not exist.
         *
         * @param patientId
         *                the ID of the patient
         *
         * @return map of patient data
         *
         * @requires patientId ≠ null
         *
         * @ensures <pre> [returns the map of patient data where
         * value(PatientKey.PATIENT_ID) = patientId] </pre>
         */
        Map<PatientKey, String> patientRecord(String patientId);

        /**
         * Removes map containg patient data from {@code patientBase}.
         *
         * @param patientId
         *                the ID of the patient
         *
         * @requires patientId ≠ null and patientBase.hasKey(patientId)
         *
         * @ensures <pre> [returns the map of patient data where
         * value(PatientKey.PATIENT_ID) = patientId] </pre>
         */
        void removePatientRecord(String patientId);

}
