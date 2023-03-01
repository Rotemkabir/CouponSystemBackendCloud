package com.jb.couponsystem.security;

import com.jb.couponsystem.exception.CouponSystemException;
import com.jb.couponsystem.exception.ErrMsg;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenManager {

    private final Map<UUID, Information> tokens;

    public UUID addToken(Information information) {
        deleteToken(information.getId());
        UUID token = UUID.randomUUID();
        tokens.put(token, information);
        return token;
    }

    public void deleteToken(int id) {
        tokens.entrySet().removeIf((token) -> token.getValue().getId() == id);
    }

    public int getUserId(UUID token) throws CouponSystemException {
        Information information = tokens.get(token);
        if (information == null) {
            throw new CouponSystemException(ErrMsg.NOT_VALID_TOKEN);
        }
        return information.getId();
    }

    public boolean isAuthorize(UUID token)throws CouponSystemException{
        tokens.get(token).getClientType();
        return true;
    }

    @Scheduled(fixedRate = 1000*60)
    public void deleteExpiredTokens(){
        tokens.entrySet().removeIf((token)->token.getValue().getExpirationTime().isBefore(LocalDateTime.now()));
    }

}
