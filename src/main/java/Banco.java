// ============================================================
//  CLASE: Banco
//  Es la raíz del patrón Composite. Contiene sucursales,
//  que a su vez contienen cuentas.
//
//  También actúa como "Repositorio" central: es el único
//  punto desde donde se puede buscar cualquier sucursal
//  o cuenta de todo el sistema.
//
//  Estructura del árbol:
//    Banco
//    ├── Sucursal A
//    │     ├── Cuenta (DNI: 11111111)
//    │     └── Cuenta (DNI: 22222222)
//    └── Sucursal B
//          └── Cuenta (DNI: 33333333)
// ============================================================

import java.util.ArrayList;
import java.util.List;

public class Banco {

    // ----------------------------------------------------------
    //  ATRIBUTOS
    // ----------------------------------------------------------

    String nombre;                      // Nombre del banco (ej: "Banco DINO")
    List<Sucursal> sucursales;          // Lista de todas las sucursales

    // ----------------------------------------------------------
    //  CONSTRUCTOR
    // ----------------------------------------------------------

    public Banco(String nombre) {
        this.nombre     = nombre;
        this.sucursales = new ArrayList<Sucursal>();

        // Las sucursales vienen predefinidas — no se crean desde el menú
        sucursales.add(new Sucursal("Sucursal 1"));
        sucursales.add(new Sucursal("Sucursal 2"));
        sucursales.add(new Sucursal("Sucursal 3"));
    }

    // ----------------------------------------------------------
    //  BÚSQUEDAS (actúa como repositorio central)
    // ----------------------------------------------------------

    // Busca una sucursal por nombre dentro del banco
    // Devuelve la sucursal o null si no existe
    public Sucursal buscarSucursal(String nombre) {
        for (Sucursal s : sucursales) {
            if (s.nombre.equalsIgnoreCase(nombre)) {
                return s;
            }
        }
        return null;
    }

    // Busca una cuenta por DNI en TODAS las sucursales
    // Esto permite transferencias entre sucursales distintas
    public Cuenta buscarCuenta(String dni) {
        for (Sucursal s : sucursales) {
            Cuenta c = s.buscarCuenta(dni);   // Le pregunta a cada sucursal
            if (c != null) return c;           // Si la encontró, la devuelve
        }
        return null;   // No se encontró en ninguna sucursal
    }



    // Muestra solo los nombres de las sucursales, sin info de cuentas
    // Se usa en el menú público para no exponer datos privados
    public void mostrarSoloSucursales() {
        System.out.println("===== " + nombre + " =====");
        System.out.println("Sucursales disponibles:");
        for (Sucursal s : sucursales) {
            System.out.println("  • " + s.nombre);
        }
    }

    // Muestra toda la estructura del banco: sucursales y sus cuentas
    // Gracias al Composite, con un solo llamado se recorre todo el árbol
    public void mostrarTodo() {
        System.out.println("===== " + nombre + " =====");

        if (sucursales.isEmpty()) {
            System.out.println("Sin sucursales registradas.");
            return;
        }

        // Delega la impresión a cada sucursal,
        // que a su vez delega a cada cuenta
        for (Sucursal s : sucursales) {
            s.mostrar();
        }
    }
}