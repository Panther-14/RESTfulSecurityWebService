/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seguridad;

import java.util.ResourceBundle;
import java.util.StringTokenizer;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Panther
 */
public class AutorizacionBasica {

    public static boolean autenticar(String credencialesBase64) {
        if (credencialesBase64 == null) {
            return false;
        }
        try {
            String credenciales = null;
            //--------DESCIFRAR CREDENCIALES DE BASE64 A TEXTO PLANO------------//
            byte[] bytes = DatatypeConverter.parseBase64Binary(credencialesBase64);
            credenciales = new String(bytes, "UTF-8");
            //-------SEPARAR USUARIO Y CONTRASEÑA------------------------------//
            final StringTokenizer tokenizer = new StringTokenizer(credenciales, ":");
            final String username = tokenizer.nextToken();
            final String password = tokenizer.nextToken();
            //-VALIDAR LAS CREDENCIALES RECIBIDAS CON LAS ALMACENADAS----------//
            ResourceBundle bundle = ResourceBundle.getBundle("seguridad.frontend_credenciales");
            String user = bundle.getString("user");
            String pass = bundle.getString("pass");
            return user.equals(username) && pass.equals(password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
