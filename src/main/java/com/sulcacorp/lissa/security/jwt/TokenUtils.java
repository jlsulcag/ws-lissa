package com.sulcacorp.lissa.security.jwt;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.Date;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TokenUtils {

	private final static String ACCESS_TOKEN_SECRET = "E)H@McQfTjWnZr4t7w!z%C*F-JaNdRgU";

	private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L;

	public static String createToken(String usuario) {

		System.out.println("Entra a crear Token");
		String token;

		long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1_000;
		Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);
		
		/*
		 * Map<String, Object> extra = new HashMap<>(); extra.put("nombre", nombre);
		 */
		token = Jwts.builder()
				.setSubject(usuario)
				.setExpiration(expirationDate)
				.signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes())).compact();

		//Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes())
		System.out.println("................... token " + token);

		return token;

	}

	public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
		try {
			Claims claims = Jwts.parserBuilder().setSigningKey(ACCESS_TOKEN_SECRET.getBytes()).build()
					.parseClaimsJws(token).getBody();

			String usuario = claims.getSubject();

			return new UsernamePasswordAuthenticationToken(usuario, null, Collections.emptyList());
		} catch (JwtException e) {
			return null;
		}

	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(ACCESS_TOKEN_SECRET.getBytes()).build()
			.parseClaimsJws(token);
			return true;
		} catch (MalformedJwtException e) {
			log.error("Token mal formado");
		} catch (UnsupportedJwtException e) {
			log.error("Token no soportado");
		}catch (ExpiredJwtException e) {
			log.error("Token expirado");
		}catch (IllegalArgumentException e) {
			log.error("Token vacio");
		}catch (SignatureException e) {
			log.error("Error en la firma del token");
		}
		return false;
	}
	
	private String generateSafeToken() {
	    SecureRandom random = new SecureRandom();
	    byte[] bytes = new byte[36]; // 36 bytes * 8 = 288 bits, a little bit more than
	                                 // the 256 required bits 
	    random.nextBytes(bytes);
	    //byte encoder = Base64.getUrlEncoder().withoutPadding();
	    //return encoder.encodeToString(bytes);
	    return null;
	}

}
