package com.jb.couponsystem.security;

import com.jb.couponsystem.service.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Information {

    private int id;
    private String email;
    private LocalDateTime expirationTime;
    @Enumerated(EnumType.STRING)
    private ClientType clientType;

}
