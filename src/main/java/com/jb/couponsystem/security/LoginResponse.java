package com.jb.couponsystem.security;

import com.jb.couponsystem.service.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {

    private UUID token;
    private int id;
    private String email;
    private String name;
    @Enumerated(EnumType.STRING)
    private ClientType clientType;
}
