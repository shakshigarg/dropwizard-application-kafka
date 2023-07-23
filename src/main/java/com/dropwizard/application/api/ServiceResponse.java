package com.dropwizard.application.api;

import lombok.*;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class ServiceResponse {
    String status;
    String message;
}
