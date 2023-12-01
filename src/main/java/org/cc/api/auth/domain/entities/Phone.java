package org.cc.api.auth.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("phones")
public class Phone {

    @Id
    private String number;
    private String cityCode;
    private String contryCode;
    private UUID userId;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
