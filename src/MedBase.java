import components.map.Map;
import components.sequence.Sequence;

public class MedBase extends MedBaseKernal {

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

}
