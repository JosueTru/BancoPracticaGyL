import java.util.ArrayList;
import java.util.List;

public class Sucursal {


    String nombre;
    List<Cuenta> cuentas;


    public Sucursal(String nombre) {
        this.nombre  = nombre;
        this.cuentas = new ArrayList<Cuenta>();
    }




    public Cuenta buscarCuenta(String dni) {
        for (Cuenta c : cuentas) {
            if (c.dni.equalsIgnoreCase(dni)) {
                return c;
            }
        }
        return null;
    }



    // Muestra el nombre de la sucursal y el resumen de cada cuenta
    public void mostrar() {
        System.out.println("Sucursal: " + nombre + " (" + cuentas.size() + " cuentas)");

        // Recorre todas las cuentas y muestra su resumen
        for (Cuenta c : cuentas) {
            c.verResumen();    // Delega la impresión a cada Cuenta
        }
    }
}