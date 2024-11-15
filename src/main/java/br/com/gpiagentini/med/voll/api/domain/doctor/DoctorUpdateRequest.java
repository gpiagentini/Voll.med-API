package br.com.gpiagentini.med.voll.api.domain.doctor;

import br.com.gpiagentini.med.voll.api.domain.address.AddressRecordData;
import jakarta.validation.constraints.NotNull;

public record DoctorUpdateRequest(
        @NotNull Long id,
        String nome,
        String telefone,
        AddressRecordData addressData) {
}
