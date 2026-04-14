import java.util.Scanner;

public class Main {



    static Scanner sc    = new Scanner(System.in);
    static Banco   banco = new Banco("Banco"); // El bancp


    public static void main(String[] args) {
        System.out.println("=== BANCO ===");

        int opcion;
        do {
            System.out.println("\n¿Qué desea hacer?");
            System.out.println("1. Gestionar sucursales");
            System.out.println("2. Gestionar cuentas");
            System.out.println("3. Operaciones de cuenta");
            System.out.println("4. Ver todo el banco");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = leerInt();

            switch (opcion) {
                case 1: menuSucursales();   break;
                case 2: menuCuentas();      break;
                case 3: menuOperaciones();  break;
                case 4: banco.mostrarTodo(); break;
                case 0: System.out.println("¡Hasta luego!"); break;
                default: System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }


    //  MENÚ: SUCURSALES
    //  Permite crear, eliminar y ver sucursales del banco.


    static void menuSucursales() {
        System.out.println("\n-- SUCURSALES --");
        System.out.println("1. Crear sucursal");
        System.out.println("2. Eliminar sucursal");
        System.out.println("3. Ver sucursales");
        System.out.print("Opción: ");

        switch (leerInt()) {

            case 1:
                // ── Crear sucursal ──────────────────────────
                System.out.print("Nombre de la sucursal: ");
                String nombre = sc.nextLine().trim();
                banco.sucursales.add(new Sucursal(nombre));
                System.out.println("Sucursal '" + nombre + "' creada.");
                break;

            case 2:
                // ── Eliminar sucursal ───────────────────────
                banco.mostrarTodo();
                System.out.print("Nombre a eliminar: ");
                String nombreEliminar = sc.nextLine().trim();
                Sucursal s = banco.buscarSucursal(nombreEliminar);
                if (s == null) {
                    System.out.println("No se encontró esa sucursal.");
                } else {
                    banco.sucursales.remove(s);
                    System.out.println("Sucursal eliminada.");
                }
                break;

            case 3:
                // ── Ver todas las sucursales ────────────────
                banco.mostrarTodo();
                break;

            default:
                System.out.println("Opción inválida.");
        }
    }

    // ==========================================================
    //  MENÚ: CUENTAS
    //  Permite crear, eliminar y editar cuentas bancarias.
    // ==========================================================

    static void menuCuentas() {
        System.out.println("\n-- CUENTAS --");
        System.out.println("1. Crear cuenta");
        System.out.println("2. Eliminar cuenta");
        System.out.println("3. Editar cuenta");
        System.out.println("4. Dar de baja una cuenta");
        System.out.print("Opción: ");

        switch (leerInt()) {
            case 1: crearCuenta();      break;
            case 2: eliminarCuenta();   break;
            case 3: editarCuenta();     break;
            case 4: darDeBajaCuenta();  break;
            default: System.out.println("Opción inválida.");
        }
    }

    // ── Crear cuenta ───────────────────────────────────────────
    static void crearCuenta() {
        // Primero verificamos que haya al menos una sucursal
        if (banco.sucursales.isEmpty()) {
            System.out.println("Primero cree una sucursal.");
            return;
        }

        // El usuario elige en qué sucursal crear la cuenta
        banco.mostrarTodo();
        System.out.print("Nombre de la sucursal: ");
        Sucursal sucursal = banco.buscarSucursal(sc.nextLine().trim());
        if (sucursal == null) {
            System.out.println("Sucursal no encontrada.");
            return;
        }

        // Tipo de cuenta
        System.out.print("Tipo de cuenta (Ahorro / Corriente): ");
        String tipo = sc.nextLine().trim();

        // Saldo inicial
        System.out.print("Saldo inicial: $");
        double saldo = leerDouble();

        // DNI del titular
        System.out.print("DNI del titular: ");
        String dni = sc.nextLine().trim();

        // Validación: el DNI no puede estar ya registrado en el banco
        if (banco.buscarCuenta(dni) != null) {
            System.out.println("Ya existe una cuenta con ese DNI.");
            return;
        }

        // Resto de datos del titular
        System.out.print("Nombre del titular: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Apellido del titular: ");
        String apellido = sc.nextLine().trim();
        System.out.print("Email del titular: ");
        String email = sc.nextLine().trim();


        Usuario titular = new Usuario(nombre, apellido, email);
        Cuenta cuenta   = new Cuenta(dni, tipo, saldo, sucursal.nombre, titular);
        sucursal.cuentas.add(cuenta);

        System.out.println("Cuenta creada para DNI " + dni + " en " + sucursal.nombre);
    }

    // ── Eliminar cuenta ────────────────────────────────────────
    static void eliminarCuenta() {
        banco.mostrarTodo();
        System.out.print("DNI de la cuenta a eliminar: ");
        String dni = sc.nextLine().trim();

        // Busca la cuenta en todas las sucursales y la elimina
        for (Sucursal s : banco.sucursales) {
            Cuenta c = s.buscarCuenta(dni);
            if (c != null) {
                s.cuentas.remove(c);
                System.out.println("Cuenta de DNI " + dni + " eliminada.");
                return;
            }
        }
        System.out.println("Cuenta no encontrada.");
    }

    // ── Editar cuenta ──────────────────────────────────────────
    static void editarCuenta() {
        banco.mostrarTodo();
        System.out.print("DNI de la cuenta a editar: ");
        Cuenta cuenta = banco.buscarCuenta(sc.nextLine().trim());
        if (cuenta == null) {
            System.out.println("Cuenta no encontrada.");
            return;
        }

        // Si el usuario presiona Enter sin escribir nada, se mantiene el valor actual
        System.out.print("Nuevo tipo (Enter para mantener '" + cuenta.tipo + "'): ");
        String tipo = sc.nextLine().trim();
        if (!tipo.isEmpty()) cuenta.tipo = tipo;

        System.out.print("Nuevo nombre titular (Enter para mantener '" + cuenta.titular.nombre + "'): ");
        String nombre = sc.nextLine().trim();
        if (!nombre.isEmpty()) cuenta.titular.nombre = nombre;

        System.out.print("Nuevo apellido titular (Enter para mantener '" + cuenta.titular.apellido + "'): ");
        String apellido = sc.nextLine().trim();
        if (!apellido.isEmpty()) cuenta.titular.apellido = apellido;

        System.out.println("Cuenta actualizada.");
    }

    // ── Dar de baja cuenta ─────────────────────────────────────
    static void darDeBajaCuenta() {
        banco.mostrarTodo();
        System.out.print("DNI de la cuenta a dar de baja: ");
        Cuenta cuenta = banco.buscarCuenta(sc.nextLine().trim());
        if (cuenta == null) {
            System.out.println("Cuenta no encontrada.");
            return;
        }
        cuenta.darDeBaja();
    }


    //  El usuario ingresa un DNI y elige qué operación hacer.


    static void menuOperaciones() {
        // Primero mostramos todas las cuentas para que el usuario sepa qué DNI usar
        banco.mostrarTodo();
        System.out.print("\nDNI de la cuenta: ");
        Cuenta cuenta = banco.buscarCuenta(sc.nextLine().trim());
        if (cuenta == null) {
            System.out.println("Cuenta no encontrada.");
            return;
        }

        System.out.println("\n1. Depositar");
        System.out.println("2. Retirar");
        System.out.println("3. Transferir");
        System.out.println("4. Ver estado");
        System.out.print("Opción: ");

        switch (leerInt()) {

            case 1:
                // ── Depositar ───────────────────────────────
                System.out.print("Monto a depositar: $");
                cuenta.depositar(leerDouble());
                break;

            case 2:
                // ── Retirar ─────────────────────────────────
                System.out.print("Monto a retirar: $");
                cuenta.retirar(leerDouble());
                break;

            case 3:
                // ── Transferir ──────────────────────────────
                System.out.print("DNI de la cuenta destino: ");
                Cuenta destino = banco.buscarCuenta(sc.nextLine().trim());
                if (destino == null) {
                    System.out.println("Cuenta destino no encontrada.");
                    return;
                }
                System.out.print("Monto a transferir: $");
                cuenta.transferir(destino, leerDouble());
                break;

            case 4:
                // ── Ver estado completo ─────────────────────
                cuenta.verEstado();
                break;

            default:
                System.out.println("Opción inválida.");
        }
    }


    //  UTILIDADES DE LECTURA
    //  Evitan que el programa explote si el usuario escribe algo que no es un número.



    // Lee un número entero desde el teclado
    static int leerInt() {
        return Integer.parseInt(sc.nextLine().trim());
    }

    // Lee un número decimal desde el teclado
    static double leerDouble() {
        return Double.parseDouble(sc.nextLine().trim());
    }
}

