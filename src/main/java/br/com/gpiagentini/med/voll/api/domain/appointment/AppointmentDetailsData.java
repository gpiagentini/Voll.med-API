package br.com.gpiagentini.med.voll.api.domain.appointment;

import java.time.LocalDateTime;

public record AppointmentDetailsData(Long id, Long doctorId, Long patientId, LocalDateTime date) {
}
