package br.com.gpiagentini.med.voll.api.domain.appointment;

import br.com.gpiagentini.med.voll.api.domain.doctor.Specialty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentData(
        Long doctorId,
        @NotNull Long patientId,
        @NotNull @Future LocalDateTime date,
        Specialty specialty) {
}
