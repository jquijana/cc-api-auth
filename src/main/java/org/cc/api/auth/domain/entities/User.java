package org.cc.api.auth.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("users")
public class User {

    @Id
    private UUID userId;
    private String name;
    private String email;
    private String password;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private LocalDateTime lastLoginDate;
    private UUID token;
    private Boolean isActive;

    @Transient
    @OneToMany(mappedBy = "user")
    private List<Phone> phones;

}
