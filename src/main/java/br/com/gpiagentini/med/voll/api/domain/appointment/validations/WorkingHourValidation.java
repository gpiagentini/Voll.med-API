package br.com.gpiagentini.med.voll.api.domain.appointment.validations;

import br.com.gpiagentini.med.voll.api.domain.appointment.AppointmentData;
import br.com.gpiagentini.med.voll.api.domain.appointment.ValidationException;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;

@Service
public class WorkingHourValidation implements Validator {

    @Override
    public void validate(AppointmentData data) {
        var appointmentData = data.date();
        var isSunday = appointmentData.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var isClosed = appointmentData.getHour() < 7 || appointmentData.getHour() > 18;
        if(isSunday || isClosed) {
            throw new ValidationException("Appoint out of clinic working hour.");
        }
    }

}
