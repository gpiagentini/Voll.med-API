package br.com.gpiagentini.med.voll.api.domain.appointment;

import br.com.gpiagentini.med.voll.api.domain.appointment.validations.Validator;
import br.com.gpiagentini.med.voll.api.domain.doctor.Doctor;
import br.com.gpiagentini.med.voll.api.domain.doctor.DoctorRepository;
import br.com.gpiagentini.med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentScheduler {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private List<Validator> validators; // Look for all the classes that implements this interface

    public void schedule(AppointmentData data) {
        if (data.doctorId() != null && !doctorRepository.existsById(data.doctorId())) {
            throw new ValidationException("Doctor not found.");
        }

        if (!patientRepository.existsById(data.patientId())) {
            throw new ValidationException("Patient not found.");
        }

        validators.forEach(v -> v.validate(data));

        var patient = patientRepository.getReferenceById(data.patientId());
        var doctor = chooseDoctor(data);
        var appointment = new Appointment(null, doctor, patient, data.date());
        appointmentRepository.save(appointment);
    }

    private Doctor chooseDoctor(AppointmentData data) {
        if (data.doctorId() != null) {
            return doctorRepository.getReferenceById(data.doctorId());
        }
        if (data.specialty() == null) {
            throw new ValidationException("Speciality is required when Doctor is not specified.");
        }
        var doctor = doctorRepository.getRandonDoctor(data.specialty(), data.date());
        if (doctor == null)
            throw new ValidationException("There is no available doctor.");
        return doctor;
    }

}
