package br.com.gpiagentini.med.voll.api.domain.appointment.validations;

import br.com.gpiagentini.med.voll.api.domain.appointment.AppointmentData;
import br.com.gpiagentini.med.voll.api.domain.appointment.ValidationException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class ScheduleInAdvanceValidation implements Validator{

    @Override
    public void validate(AppointmentData data) {
        var appointmentData = data.date();
        var difference = Duration.between(LocalDateTime.now(),appointmentData).toMinutes();
        if(difference < 30) {
            throw new ValidationException("Appointments must be scheduled at least 30 minutes in advance.");
        }
    }
}
