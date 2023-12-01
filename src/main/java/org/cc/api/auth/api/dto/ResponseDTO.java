package org.cc.api.auth.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDTO {
    private String code;
    private String message;
    private Object data;
}


