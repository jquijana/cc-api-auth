package org.cc.api.auth.api.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class UserRs {
    private UUID userId;
    private String name;
    private String email;
    private String password;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private LocalDateTime lastLoginDate;
    private UUID token;
    private Boolean isActive;
    private List<PhoneRq> phones;

    public static class PhoneRq {
        private String number;
        private String cityCode;
        private String countryCode;
    }
}


