package br.com.gpiagentini.med.voll.api.controller;

import br.com.gpiagentini.med.voll.api.domain.appointment.AppointmentData;
import br.com.gpiagentini.med.voll.api.domain.appointment.AppointmentDetailsData;
import br.com.gpiagentini.med.voll.api.domain.appointment.AppointmentScheduler;
import br.com.gpiagentini.med.voll.api.domain.doctor.Specialty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AppointmentControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AppointmentScheduler appointmentScheduler;

    @Autowired
    private JacksonTester<AppointmentData> appointmentDataJson;
    @Autowired
    private JacksonTester<AppointmentDetailsData> appointmentDetailsDataJson;

    @Test
    @DisplayName("Should return http 400 when data is invalid.")
    @WithMockUser
    void schedule_1() throws Exception {
        var response = mvc.perform(post("/appointment"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return http 200 when data is valid.")
    @WithMockUser
    void schedule_2() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        var returnData = new AppointmentDetailsData(null, 1L, 5L, date);
        when(appointmentScheduler.schedule(any())).thenReturn(returnData);
        var response = mvc
                .perform(
                        post("/appointment")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(appointmentDataJson.write(
                                        new AppointmentData(1L, 5L, date, Specialty.GINECOLOGIA)
                                ).getJson())
                )
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        var responseJson = appointmentDetailsDataJson.write(returnData).getJson();
        assertThat(response.getContentAsString()).isEqualTo(responseJson);
    }
}