package org.cc.api.auth.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRq {

    @NotNull
    @NotEmpty
    private String name;

    @NotEmpty
    @NotNull
    @Pattern(regexp = "([a-zA-Z0-9])+@dominio.cl", message = "{email.pattern.mail}")
    private String email;

    @Pattern(regexp = "^[a-zA-Z0-9]{8}", message = "{email.pattern.password}")
    private String password;
    private List<PhoneRq> phones;

}




