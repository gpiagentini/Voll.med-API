package br.com.gpiagentini.med.voll.api.controller;

import br.com.gpiagentini.med.voll.api.domain.appointment.AppointmentData;
import br.com.gpiagentini.med.voll.api.domain.appointment.AppointmentDetailsData;
import br.com.gpiagentini.med.voll.api.domain.appointment.AppointmentScheduler;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("appointment")
@SecurityRequirement(name = "bearer-key")
public class AppointmentController {

    @Autowired
    private AppointmentScheduler appointmentScheduler;

    @PostMapping
    @Transactional
    public ResponseEntity schedule(@RequestBody @Valid AppointmentData data){
        appointmentScheduler.schedule(data);
        return ResponseEntity.ok(new AppointmentDetailsData(null, null, null, null));
    }


}
