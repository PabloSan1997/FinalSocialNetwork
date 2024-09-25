package com.example.demo.services.utils;

import com.example.demo.exceptions.MyBadRequestException;
import com.example.demo.models.dtos.UserSecurityDto;
import com.example.demo.models.entities.LoginRegister;
import com.example.demo.repositories.LoginRegisterRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JwtService {

    @Autowired
    private LoginRegisterRepository loginRegisterRepository;
    @Value("${secret.key.jwt}")
    private String SECRET_KEY;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String generateToken(UserSecurityDto user) throws JsonProcessingException {
        String username = user.getUsername();
        String nickname = user.getNickname();
        String authoritiesJson = objectMapper.writeValueAsString(user.getAuthorities());
        Claims claims = Jwts.claims()
                .add("nickname", nickname)
                .add("authorities", authoritiesJson).build();
        long timeExpire = (long) (1000 * 60 * 60);
        return Jwts.builder().signWith(generateKey())
                .subject(username)
                .claims(claims)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+ timeExpire))
                .compact();
    }

    public UserSecurityDto validationToken(String token) throws JsonProcessingException {
        Optional<LoginRegister> loginRegister = loginRegisterRepository.findByJwtoken(token);

        if (loginRegister.isEmpty() || !loginRegister.get().getEnabled())
            throw new MyBadRequestException();

        Claims claims = Jwts.parser().verifyWith(generateKey()).build()
                .parseSignedClaims(token).getPayload();
        String username = claims.getSubject();
        String nickname = (String) claims.get("nickname");
        String authoritiesJson = (String) claims.get("authorities");
        Collection<? extends GrantedAuthority> authorities = List.of(
                objectMapper
                        .addMixIn(GrantedAuthorityJson.class, SimpleGrantedAuthority.class)
                        .readValue(authoritiesJson, SimpleGrantedAuthority[].class)
        );
        return new UserSecurityDto(
                username,
                null,
                true,
                null,
                nickname,
                authorities
        );
    }
    private SecretKey generateKey(){
        byte[] key = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(key);
    }
}
