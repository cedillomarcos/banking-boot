package com.bankexample.banking.application.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EntityIdResponse {

    private UUID uuid;

    public EntityIdResponse(UUID uuid) {
        this.uuid = uuid;
    }

}
