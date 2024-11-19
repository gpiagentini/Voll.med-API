package br.com.gpiagentini.med.voll.api.domain.doctor;

import br.com.gpiagentini.med.voll.api.domain.address.AddressRecordData;
import br.com.gpiagentini.med.voll.api.domain.appointment.Appointment;
import br.com.gpiagentini.med.voll.api.domain.patient.Patient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import javax.print.Doc;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Should return null when the doctor is not available in the desired date.")
    void getRandonDoctorAvailableAtDate() {
        var nextMonday = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var doctor = cadastrarMedico("Doctor", "gmtpiagentini-test@gmail.com", "12345", Specialty.CARDIOLOGIA);
        var patient = cadastrarPaciente("Teste");
        cadastrarConsulta(doctor, patient, nextMonday);
        var freeDoctor = repository.getRandonDoctor(Specialty.CARDIOLOGIA, nextMonday);
        assertThat(freeDoctor).isNull();
    }

    private void cadastrarConsulta(Doctor medico, Patient paciente, LocalDateTime data) {
        em.persist(new Appointment(null, medico, paciente, data));
    }

    private Doctor cadastrarMedico(String nome, String email, String crm, Specialty especialidade) {
        var medico = new Doctor(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private Patient cadastrarPaciente(String nome) {
        var paciente = new Patient();
        paciente.setName(nome);
        em.persist(paciente);
        return paciente;
    }

    private DoctorRegisterRequest dadosMedico(String nome, String email, String crm, Specialty especialidade) {
        return new DoctorRegisterRequest(
                nome,
                email,
                "61999999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private AddressRecordData dadosEndereco() {
        return new AddressRecordData(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}