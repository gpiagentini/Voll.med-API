package br.com.gpiagentini.med.voll.api.domain.doctor;

import br.com.gpiagentini.med.voll.api.domain.address.Address;

public record DoctorDetailsData(Long id, String nome, String email, String crm, String telefone, Specialty especialidade, Address endereco) {

    public DoctorDetailsData(Doctor doctor){
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getPhone(), doctor.getSpecialty(), doctor.getAddress());
    }
}
