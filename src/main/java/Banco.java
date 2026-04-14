import java.util.ArrayList;
import java.util.List;

public class Banco {

    String nombre;
    List<Sucursal> sucursales = new ArrayList<Sucursal>();

    public Banco(String nombre) {
        this.nombre = nombre;
    }

    // Busca una sucursal por nombre
    public Sucursal buscarSucursal(String nombre) {
        for (Sucursal s : sucursales) {
            if (s.nombre.equalsIgnoreCase(nombre)) {
                return s;
            }
        }
        return null;
    }

    // Busca una cuenta en TODAS las sucursales
    public Cuenta buscarCuenta(String id) {
        for (Sucursal s : sucursales) {
            Cuenta c = s.buscarCuenta(id);
            if (c != null) return c;
        }
        return null;
    }

    public void mostrarTodo() {
        System.out.println("===== " + nombre + " =====");
        if (sucursales.isEmpty()) {
            System.out.println("Sin sucursales registradas.");
            return;
        }
        for (Sucursal s : sucursales) {
            s.mostrar();
        }
    }
}
