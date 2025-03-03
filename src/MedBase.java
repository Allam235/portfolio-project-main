import java.util.Date;

import components.map.Map;
import components.map.Map1L;

public class MedBase {

    Map<Integer, Patient> patientBase = new Map1L<>();

    int baseId;

    public MedBase() {
        this.baseId = 1;
    }

    public MedBase(int baseId) {
        this.baseId = baseId;
    }

    public boolean patientNewRecord(int id, String args) {
        boolean check = true; //check if patient record was updated
        Patient p = new Patient(args);
        this.patientBase.add(id, p);
        return check;

    }

    public void updatePatient(int id, String args) {
        Patient p = this.patientBase.remove(id).value();
        p.registerInfo(args);
        this.patientBase.add((Integer) id, p);
    }

    public void note(int id, Date date, String msg) {
        Patient p = this.patientBase.remove(id).value();
        p.noteTake(date, msg);
        this.patientBase.add((Integer) id, p);
    }

}
