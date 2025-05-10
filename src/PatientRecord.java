public record PatientRecord(String firstName, String lastName) {

    public static void main(String[] args) {
        PatientRecord p1 = new PatientRecord("Jeremy", "Grifski");
        System.out.println(p1);
    }
}
