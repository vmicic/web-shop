package com.ftn.webshop.security;

import com.ftn.webshop.domain.Authority;
import com.ftn.webshop.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Component
public class TokenUtils {

    private static Logger logger = LoggerFactory.getLogger(TokenUtils.class);

    @Value("web-shop")
    private String APP_NAME;

    @Value("secret_test")
    public String SECRET;

    @Value("300000000000")
    private Long EXPIRES_IN;

    @Value("Authorization")
    private String AUTH_HEADER;


    static final String AUDIENCE_UNKNOWN = "unknown";
    static final String AUDIENCE_WEB = "web";
    static final String AUDIENCE_MOBILE = "mobile";
    static final String AUDIENCE_TABLET = "tablet";

    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    public String generateToken(String username) {
        Date date = new Date();

    /*
        final String authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        System.out.println("ovo su role" + authorities);


     */

        //temporary fix
        List<Authority> authorities = (List<Authority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        logger.info(authorities.toString());

        final String authority = authorities.get(0).getName();

        return Jwts.builder()
                .setIssuer(APP_NAME)
                .setSubject(username)
                .setAudience(generateAudience())
                .setIssuedAt(date)
                .setExpiration(generateExpirationDate())
                .claim("role", authority) //postavljanje proizvoljnih podataka u telo JWT tokena
                .signWith(SIGNATURE_ALGORITHM, SECRET).compact();
    }

    private String generateAudience() {
//		Moze se iskoristiti org.springframework.mobile.device.Device objekat za odredjivanje tipa uredjaja sa kojeg je zahtev stigao.

//		String audience = AUDIENCE_UNKNOWN;
//		if (device.isNormal()) {
//			audience = AUDIENCE_WEB;
//		} else if (device.isTablet()) {
//			audience = AUDIENCE_TABLET;
//		} else if (device.isMobile()) {
//			audience = AUDIENCE_MOBILE;
//		}
        return AUDIENCE_WEB;
    }

    private Date generateExpirationDate() {
        return new Date(new Date().getTime() + EXPIRES_IN);
    }

    public Long getExpiredIn() {
        return EXPIRES_IN;
    }

    // Function for getting token from request
    public String getToken(HttpServletRequest request) {

        String authHeader = request.getHeader(AUTH_HEADER);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;
    }

    public String getUsernameFromToken(String token) {
        String username;

        final Claims claims = this.getAllClaimsFromToken(token);
        username = claims.getSubject();

        return username;

    }

    // Function for reading all data from token
    private Claims getAllClaimsFromToken(String token) {
        Claims claims;

        claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();

        return claims;


    }

    //Validating JWT token
    public Boolean validateToken(String token, UserDetails userDetails) {
        User user = (User) userDetails;
        final String username = getUsernameFromToken(token);
        final Date created = getIssuedAtDateFromToken(token);

        return (username != null && username.equals(userDetails.getUsername())
                && !isCreatedBeforeLastPasswordReset(created, user.getLastPasswordResetDate()));
    }

    public Date getIssuedAtDateFromToken(String token) {
        Date issueAt;

        final Claims claims = this.getAllClaimsFromToken(token);
        issueAt = claims.getIssuedAt();

        return issueAt;
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    private Date getExpirationDateFromToken(String token) {
        Date expiration;

        final Claims claims = this.getAllClaimsFromToken(token);
        expiration = claims.getExpiration();

        return expiration;

    }

    public Boolean isTokenExpired(String token) {
        Date date = new Date();

        try {
            final Date expiration = this.getExpirationDateFromToken(token);
            return expiration.before(date);
        } catch (Exception e) {
            return true;
        }
    }
}
