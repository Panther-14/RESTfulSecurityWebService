/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seguridad;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;
import modelo.pojos.Respuesta;
import modelo.pojos.SesionToken;

/**
 *
 * @author Panther
 */
public class AutorizacionTokenJWT {

    public static SesionToken generarToken(SesionToken sesion) {
        //-CARGAR CONFIGURACIÓN PARA LA GENERACIÓN DE TOKENS CON JWT DESDE EL ARCHIVO DE PROPERTIES----//
        ResourceBundle bundle = ResourceBundle.getBundle("seguridad.configuracionJWT");
        String SECRET_KEY = bundle.getString("SECRET_KEY");
        Integer MINUTES_EXPIRATION_TIME = 10; //10 minutos por default
        try {
            MINUTES_EXPIRATION_TIME = Integer.parseInt(bundle.getString("MINUTES_EXPIRATION_TIME"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
        Calendar currentTime = Calendar.getInstance(TimeZone.getTimeZone("CST"));
        Date now = currentTime.getTime();
        currentTime.add(Calendar.MINUTE, MINUTES_EXPIRATION_TIME);
        Date EXPIRATION_TIME = currentTime.getTime();
        //-------GENERAR EL TOKEN JWT------------------------------------//
        String token = Jwts.builder()
                .setSubject(sesion.getNombre())
                .setIssuer(sesion.getNombre())
                .setIssuedAt(now)
                .setExpiration(EXPIRATION_TIME)
                .claim("id", sesion.getId())
                .claim("nombre", sesion.getNombre())
                .claim("email", sesion.getEmail())
                .signWith(SIGNATURE_ALGORITHM, SECRET_KEY)
                .compact();
        sesion.setTokenacceso(token);
        return sesion;
    }

    public static Respuesta validarToken(String token) {
        Respuesta r = new Respuesta();
        //-CARGAR CONFIGURACIÓN PARA LA GENERACIÓN DE TOKENS CON JWT DESDE EL ARCHIVO DE PROPERTIES----//
        ResourceBundle bundle = ResourceBundle.getBundle("seguridad.configuracionJWT");
        String SECRET_KEY = bundle.getString("SECRET_KEY");
        //-----VALIDA QUE EL TOKEN NO ESTÉ VACÍO-------//

        if (token == null || token.isEmpty()) {
            r.setError(true);
            r.setMensaje("El token es nullo o está vacío...");
        } else {
            //----------SE DESCIFRA EL TOKEN PARA ACCEDER A LOS METADATOS-----------------//
            try {
                Claims TOKEN_DESCIFRADO
                        = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
                SesionToken sesion = new SesionToken();
                sesion.setId((Integer) TOKEN_DESCIFRADO.get("id"));
                sesion.setNombre((String) TOKEN_DESCIFRADO.get("nombre"));
                sesion.setEmail((String) TOKEN_DESCIFRADO.get("email"));
                r.setSesiontoken(sesion);
                r.setError(false);
                r.setMensaje("OK");
            } catch (ExpiredJwtException expExc) {
                r.setError(true);
                r.setMensaje("El Token ha expirado");
            } catch (SignatureException e) {
                r.setError(true);
                r.setMensaje("Token con firma no válida");
            } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
                r.setError(true);
                r.setMensaje("Token no válido");
            } catch (Exception e) {
                r.setError(true);
                r.setMensaje(e.getMessage());
            }
        }
        return r;
    }
}
