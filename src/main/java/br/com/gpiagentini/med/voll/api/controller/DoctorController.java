package br.com.gpiagentini.med.voll.api.controller;

import br.com.gpiagentini.med.voll.api.domain.doctor.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/doctors")
@SecurityRequirement(name = "bearer-key")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DoctorDetailsData> createDoctor(@RequestBody @Valid DoctorRegisterRequest registerData, UriComponentsBuilder uriBuilder){
        var newDoctor = new Doctor(registerData);
        doctorRepository.save(newDoctor);
        var uri = uriBuilder.path("/doctors/{id}").buildAndExpand(newDoctor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DoctorDetailsData(newDoctor));
    }

    @GetMapping
    public ResponseEntity<Page<DoctorListData>> getDoctorList(@PageableDefault(size = 10, sort = {"name"}, direction = Sort.Direction.ASC) Pageable paginator){
        var contentPage = doctorRepository.findAllByActiveTrue(paginator).map(DoctorListData::new);
        return ResponseEntity.ok(contentPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDetailsData> getDoctorDetails(@PathVariable Long id) {
        var doctor = doctorRepository.getReferenceById(id);
        return ResponseEntity.ok(new DoctorDetailsData(doctor));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DoctorDetailsData> updateDoctor(@RequestBody @Valid DoctorUpdateRequest updateDoctorRequest){
        var doctor = doctorRepository.getReferenceById(updateDoctorRequest.id());
        doctor.updateInformation(updateDoctorRequest); // JPA already detect changes in my entity and commits at the end of the Transaction
        return ResponseEntity.ok(new DoctorDetailsData(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteDoctor(@PathVariable Long id){
        var doctor = doctorRepository.getReferenceById(id);
        doctor.disableDoctor();
        return ResponseEntity.noContent().build();
    }
}
