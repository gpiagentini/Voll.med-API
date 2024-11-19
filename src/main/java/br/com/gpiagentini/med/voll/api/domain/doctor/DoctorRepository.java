package br.com.gpiagentini.med.voll.api.domain.doctor;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

/**
 * Interface that extends a JpaRepository from Spring and receives two Generics
 *  1 - Type of data
 *  2 - type of primary key
 */
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByActiveTrue(Pageable paginator);

    @Query("""
            SELECT d FROM Doctor d
            WHERE
            d.active = true
            AND
            d.specialty = :speciality
            AND d.id NOT IN (
                select a.doctor.id from Appointment a
                where a.date = :date
            )
            ORDER BY RAND()
            LIMIT 1
            """)
    Doctor getRandonDoctor(Specialty speciality, LocalDateTime date);
}
