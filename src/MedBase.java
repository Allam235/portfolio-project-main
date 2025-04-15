import components.map.Map;
import components.sequence.Sequence;

public interface MedBase extends MedBaseKernel {

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
    public Sequence<Map<PatientKey, String>> search(String searchBy);

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
    public Sequence<Map<PatientKey, String>> search(Query... searchBy);

    /**
     * Finds and returns all patient Ids in the database that have matching
     * first name, last name, and date of birth.
     *
     * @return a {@code Sequence} containing pairs of patient Ids who have
     *         matching first name, last name, and date of birth
     * @ensures <pre>
    * For all indices i and j in the returned sequence such that
    * i%2 == 0 and i == j+1:
    *     patientBase.value(returned[i])[FIRST_NAME] =
    *         patientBase.value(returned[j])[FIRST_NAME] and
    *     patientBase.value(returned[i])[LAST_NAME] =
    *         patientBase.value(returned[j])[LAST_NAME] and
    *     patientBase.value(returned[i])[DATE_OF_BIRTH] =
    *         patientBase.value(returned[j])[DATE_OF_BIRTH]
    * </pre>
     */
    public Sequence<Map<PatientKey, String>> findDuplicates();

}
