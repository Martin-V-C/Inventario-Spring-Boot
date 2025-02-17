package com.sensores.inventario.inventario.service.securityService;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String secretKey = "bfa72e31678895ea6234df00caaaf6b21b4dda74802d63ee9e9ee546c8089cf3b7a93668a651c7afeaeff88af97b679e27ad73c240ec7583d684bca9f210c9d4";

    /**
     * Dado un token JWT, devuelve el username asociado a ese token.
     *
     * @param token el token JWT
     * @return el username asociado al token
     */
    public String getUserName(String token) {
        return getClaim(token, Claims::getSubject);
    }

    /**
     * Dado un token JWT y un resolutor de claims, devuelve el claim
     * especificado por el resolutor.
     *
     * @param token     el token JWT
     * @param claimsResolver
     *                  el resolutor de claims que se encarga de extraer el
     *                  claim
     * @return el claim especificado por el resolutor
     */
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClamims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Dado un token JWT, devuelve un objeto Claims con todos los claims
     * asociados a ese token.
     *
     * @param token el token JWT
     * @return el objeto Claims con todos los claims asociados al token
     */
    private Claims getAllClamims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    /**
     * Dado la llave secreta, devuelve la llave para firmar los tokens JWT.
     *
     * @return la llave para firmar los tokens JWT
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Valida que un token sea valido para un usuario en particular.
     *
     * @param token      el token JWT a validar
     * @param userDetails los detalles del usuario que se esta intentando
     *                    autenticar
     * @return true si el token es valido para el usuario, false en otro caso
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

    }

    /**
     * Determina si un token JWT ha expirado.
     *
     * @param token el token JWT a evaluar
     * @return true si el token ha expirado, false en otro caso
     */
    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    /**
     * Dado un token JWT, devuelve la fecha de expiraci n asociada a ese
     * token.
     *
     * @param token el token JWT
     * @return la fecha de expiraci n asociada al token
     */
    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    /**
     * Genera un token JWT para el usuario especificado.
     *
     * @param userDetails los detalles del usuario que se esta intentando
     *                    autenticar
     * @return el token JWT generado
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Genera un token JWT para el usuario especificado, con los claims adicionales
     * especificados.
     *
     * @param extraClaims los claims adicionales a incluir en el token
     * @param userDetails los detalles del usuario que se esta intentando
     *                    autenticar
     * @return el token JWT generado
     */
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return buildToken(extraClaims, userDetails);
    }

    /**
     * Construye un token JWT para el usuario especificado, con los claims
     * adicionales especificados.
     *
     * @param extraClaims los claims adicionales a incluir en el token
     * @param userDetails los detalles del usuario que se esta intentando
     *                    autenticar
     * @return el token JWT generado
     */
    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
