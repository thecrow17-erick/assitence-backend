package crow.uagrm.parcial.services.security;

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
  private final String SECRET_KEY = "11AV353CR37AUGU32121441241252142411232145efggrew34341fesdewrew";
  
  public String getToken(UserDetails user) {
      return getToken(new HashMap<>(), user);
  }

  private String getToken(Map<String,Object> extraClaims, UserDetails user) {
      System.out.println(user);
      return Jwts
          .builder()
          .setClaims(extraClaims)
          .setSubject(user.getUsername())
          .setIssuedAt(new Date(System.currentTimeMillis()))
          .setExpiration(new Date(System.currentTimeMillis()+1000*60*24*30))
          .signWith(getKey(), SignatureAlgorithm.HS256)
          .compact();
  }

  private Key getKey() {
    byte[] keyBytes=Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public String getUsernameFromToken(String token) {
    return getClaim(token, Claims::getSubject);
  }

  private Claims getAllClaims(String token)
  {
    return Jwts
          .parserBuilder()
          .setSigningKey(getKey())
          .build()
          .parseClaimsJws(token)
          .getBody();
  }

  public <T> T getClaim(String token, Function<Claims,T> claimsResolver)
  {
    final Claims claims=getAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private boolean isTokenExpired(String token)
  {
    return getExpiration(token).before(new Date());
  }

  private Date getExpiration(String token)
  {
    return getClaim(token, Claims::getExpiration);
  }
  
  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username=getUsernameFromToken(token);
    return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
  }

}
