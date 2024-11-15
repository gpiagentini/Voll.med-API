package br.com.gpiagentini.med.voll.api.domain.doctor;

import br.com.gpiagentini.med.voll.api.domain.address.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="doctor")
@Entity(name="Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private Address address;
    private Boolean active;


    public Doctor(DoctorRegisterRequest registerData) {
        this.name = registerData.nome();
        this.email = registerData.email();
        this.crm = registerData.crm();
        this.phone = registerData.telefone();
        this.specialty = registerData.especialidade();
        this.address = new Address(registerData.endereco());
        this.active = true;
    }

    public void updateInformation(DoctorUpdateRequest updateDoctorRequest) {
        if(updateDoctorRequest.nome() != null)
            this.name = updateDoctorRequest.nome();
        if(updateDoctorRequest.telefone() != null)
            this.phone = updateDoctorRequest.telefone();
        if(updateDoctorRequest.addressData() != null)
            this.address.updateInformation(updateDoctorRequest.addressData());
    }


    public void disableDoctor() {
        this.active = false;
    }
}
