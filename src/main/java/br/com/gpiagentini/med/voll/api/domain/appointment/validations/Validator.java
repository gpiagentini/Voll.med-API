package br.com.gpiagentini.med.voll.api.domain.appointment.validations;

import br.com.gpiagentini.med.voll.api.domain.appointment.AppointmentData;

public interface Validator {
    void validate(AppointmentData data);
}
