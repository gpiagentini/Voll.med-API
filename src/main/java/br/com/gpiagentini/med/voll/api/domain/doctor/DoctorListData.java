package br.com.gpiagentini.med.voll.api.domain.doctor;

public record DoctorListData(Long id,String nome, String email, String crm, Specialty especialidade) {

    public DoctorListData(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
