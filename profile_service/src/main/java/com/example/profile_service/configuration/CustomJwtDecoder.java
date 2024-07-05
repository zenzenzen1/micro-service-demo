package com.example.profile_service.configuration;

import java.text.ParseException;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;

import com.nimbusds.jwt.SignedJWT;

@Component
public class CustomJwtDecoder implements JwtDecoder {

    // @Autowired
    // private AuthenticationService authenticationService;
    // private NimbusJwtDecoder nimbusJwtDecoder = null;

    @Override
    public Jwt decode(String token) throws JwtException {
        // try {
        // var response = authenticationService.introspect(
        // IntrospectRequest.builder().token(token).build());

        // if (!response.isValid()) {
        // // throw new JwtException("Token invalid");
        // System.out.println("Invalid token");
        // throw new AppException(ErrorCode.INVALID_TOKEN_JSON_OBJECT);
        // }
        // } catch (JOSEException | ParseException e) {
        // throw new JwtException(e.getMessage());
        // }

        // if (Objects.isNull(nimbusJwtDecoder)) {
        // SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(),
        // "HS512");
        // nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec)
        // .macAlgorithm(MacAlgorithm.HS512)
        // .build();
        // }

        // return nimbusJwtDecoder.decode(token);

        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            System.out.println("Token: " + token);
            return new Jwt(
                    token,
                    signedJWT.getJWTClaimsSet().getIssueTime().toInstant(),
                    signedJWT.getJWTClaimsSet().getExpirationTime().toInstant(),
                    signedJWT.getHeader().toJSONObject(),
                    signedJWT.getJWTClaimsSet().toJSONObject());

        } catch (ParseException e) {
            System.out.println("Invalid token");
            return null;
            // throw new JwtException("Invalid token");
        }
    }
}
