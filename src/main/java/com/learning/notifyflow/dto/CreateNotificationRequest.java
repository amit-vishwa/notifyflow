package com.learning.notifyflow.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateNotificationRequest(@Email @NotBlank @Size(max = 254) String recipient,
                                        @NotBlank @Size(max = 500) String message) {
}
