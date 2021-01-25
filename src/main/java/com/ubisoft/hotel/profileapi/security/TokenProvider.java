package com.ubisoft.hotel.profileapi.security;

import com.ubisoft.hotel.profileapi.config.SecurityConfig;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import static java.lang.Thread.currentThread;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.eclipse.microprofile.jwt.Claims;
import org.slf4j.Logger;

public class TokenProvider {

    private PrivateKey privateKey;

    private String issuer;

    private long tokenValidityMillis;

    private long tokenValidityMillisForRememberMe;

    @Inject
    private Logger log;

    @Inject
    private SecurityConfig securityConfig;

    @PostConstruct
    public void init() {
        try {
            this.privateKey = readPrivateKey("privateKey.pem");
        } catch (Exception ex) {
            log.error("Unable to read privateKey.pem ", ex);
            throw new IllegalStateException(ex);
        }

        this.issuer = securityConfig.getIssuer();
        this.tokenValidityMillis
                = 1000 * securityConfig.getTokenValidityInSeconds();
        this.tokenValidityMillisForRememberMe
                = 1000 * securityConfig.getTokenValidityInSecondsForRememberMe();
    }

    public String createToken(String username, Set<String> groups, Boolean rememberMe) {
        long issuedTime = System.currentTimeMillis();
        long expirationTime = issuedTime
                + (rememberMe ? tokenValidityMillisForRememberMe : tokenValidityMillis);

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setId(UUID.randomUUID().toString())
                .setSubject(username)
                .claim(Claims.groups.name(), groups)
                .setIssuer(issuer)
                .setIssuedAt(new Date(issuedTime))
                .setExpiration(new Date(expirationTime))
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    private PrivateKey readPrivateKey(String resourceName) throws Exception {
        byte[] byteBuffer = new byte[16384];
        int length = currentThread().getContextClassLoader()
                .getResource(resourceName)
                .openStream()
                .read(byteBuffer);

        String key = new String(byteBuffer, 0, length)
                .replaceAll("-----BEGIN (.*)-----", "")
                .replaceAll("-----END (.*)----", "")
                .replaceAll("\r\n", "")
                .replaceAll("\n", "")
                .trim();

        return KeyFactory.getInstance("RSA")
                .generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(key)));
    }

}
