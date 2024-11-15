package br.com.gpiagentini.med.voll.api.user;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationData(
        @NotBlank String login,
        @NotBlank String password) {
}
