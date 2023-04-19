/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import modelo.pojos.Empleado;

/**
 *
 * @author Panther
 */
public class EmpleadoDAO {

    public static Empleado getEmpleadoPrueba() {
        Empleado e = null;
        try {
            Faker df = new Faker(new Locale("es-MX"));
            e = new Empleado(
                    df.number().numberBetween(1, 100000),
                    df.name().firstName(),
                    df.name().lastName(),
                    df.phoneNumber().cellPhone(),
                    df.internet().emailAddress(),
                    df.address().fullAddress(),
                    df.internet().password(8, 16, true, true, true)
            );
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return e;
    }

    public static List<Empleado> getEmpleadosPrueba(int numemp) {
        List<Empleado> l = new ArrayList<>();
        try {
            Faker df = new Faker(new Locale("es-MX"));
            for (int i = 0; i < numemp; i++) {
                Empleado e = new Empleado(
                        df.number().numberBetween(1, 100000),
                        df.name().firstName(),
                        df.name().lastName(),
                        df.phoneNumber().cellPhone(),
                        df.internet().emailAddress(),
                        df.address().fullAddress(),
                        df.internet().password(8, 16, true, true, true)
                );
                l.add(e);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return l;
    }

}
