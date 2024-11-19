create table appointment(

    id bigint not null auto_increment,
    doctor_id bigint not null,
    patient_id bigint not null,
    data datetime not null,

    primary key(id),
    constraint fk_doctor_id foreign key(doctor_id) references doctor(id),
    constraint fk_patient_id foreign key(patient_id) references patient(id)

);