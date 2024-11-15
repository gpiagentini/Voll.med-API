package br.com.gpiagentini.med.voll.api.domain.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface that extends a JpaRepository from Spring and receives two Generics
 *  1 - Type of data
 *  2 - type of primary key
 */
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByActiveTrue(Pageable paginator);
}
