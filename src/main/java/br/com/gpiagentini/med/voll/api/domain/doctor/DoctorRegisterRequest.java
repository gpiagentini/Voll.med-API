package br.com.gpiagentini.med.voll.api.domain.doctor;

import br.com.gpiagentini.med.voll.api.domain.address.AddressRecordData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DoctorRegisterRequest(
        @NotBlank(message = "Name cannot be blank") String nome,
        @NotBlank @Email String email,
        @NotBlank String telefone,
        @NotBlank @Pattern(regexp = "\\d{4,6}") String crm,
        @NotNull Specialty especialidade,
        @NotNull @Valid AddressRecordData endereco) {
}
