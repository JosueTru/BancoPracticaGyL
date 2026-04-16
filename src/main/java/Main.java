import java.util.Scanner;

public class Main {



    static Scanner sc     = new Scanner(System.in);
    static Banco   banco  = new Banco("Banco");
    static Sesion  sesion = new Sesion();

    static Menu menu = new Menu(sesion);

    static final String ADMIN_USUARIO   = "admin";
    static final String ADMIN_CONTRASENA = "1234";

    public static void main(String[] args) {
        System.out.println("=== BANCO ===");

        int opcion;
        do {

            menu.mostrar();

            System.out.print("Opción: ");
            opcion = leerInt();
            manejarOpcion(opcion);

        } while (opcion != 0);
    }



    // Decide qué hacer según la opción y el rol actual
    static void manejarOpcion(int opcion) {
        if (opcion == 0) {
            System.out.println("¡Hasta luego!");
            return;
        }

        if (opcion == 9 && sesion.logueado) {
            sesion.cerrar();
            return;
        }

        if (!sesion.logueado) {
            manejarSinLoguear(opcion);

        } else if (sesion.esAdmin()) {
            manejarAdmin(opcion);

        } else if (sesion.esUsuario()) {
            manejarUsuario(opcion);
        }
    }



    static void manejarSinLoguear(int opcion) {
        switch (opcion) {
            case 1: loginUsuario(); break;
            case 2: loginAdmin();   break;
            case 3: banco.mostrarSoloSucursales(); break;  // Solo nombres, sin datos de cuentas
            case 4: crearCuenta();  break;
            default: System.out.println("Opción inválida.");
        }
    }

    // ── Login usuario ──────────────────────────────────────────
    static void loginUsuario() {
        System.out.print("DNI: ");
        String dni = sc.nextLine().trim();

        Cuenta cuenta = banco.buscarCuenta(dni);
        if (cuenta == null) {
            System.out.println("No existe una cuenta con ese DNI.");
            return;
        }
        if (!cuenta.activa) {
            System.out.println("Esa cuenta está dada de baja.");
            return;
        }

        System.out.print("Contraseña: ");
        String contrasena = sc.nextLine().trim();

        if (!cuenta.titular.contrasena.equals(contrasena)) {
            System.out.println("Contraseña incorrecta.");
            return;
        }

        sesion.iniciarComoUsuario(cuenta);
        System.out.println("Bienvenido, " + cuenta.titular.nombre + "!");
    }

    // ── Login admin ────────────────────────────────────────────
    static void loginAdmin() {
        System.out.print("Usuario: ");
        String usuario = sc.nextLine().trim();
        System.out.print("Contraseña: ");
        String contrasena = sc.nextLine().trim();

        if (!usuario.equals(ADMIN_USUARIO) || !contrasena.equals(ADMIN_CONTRASENA)) {
            System.out.println("Credenciales incorrectas.");
            return;
        }

        sesion.iniciarComoAdmin();
        System.out.println("Bienvenido, Admin!");
    }


    //  Opciones del admin

    static void manejarAdmin(int opcion) {
        switch (opcion) {
            case 1: menuCuentas();       break;
            case 2: banco.mostrarTodo(); break;
            default: System.out.println("Opción inválida.");
        }
    }

    // ── Opciones de la gestion cuentas ──────────────────────────────────────

    static void menuCuentas() {
        System.out.println("\n-- CUENTAS --");
        System.out.println("1. Crear  2. Editar  3. Dar de baja");
        System.out.print("Opción: ");

        switch (leerInt()) {
            case 1: crearCuenta();     break;
            case 2: editarCuenta();    break;
            case 3: darDeBajaCuenta(); break;
            default: System.out.println("Opción inválida.");
        }
    }

    static void crearCuenta() {
        if (banco.sucursales.isEmpty()) {
            System.out.println("Primero cree una sucursal.");
            return;
        }

        banco.mostrarTodo();
        System.out.print("Nombre de la sucursal: ");
        Sucursal sucursal = banco.buscarSucursal(sc.nextLine().trim());
        if (sucursal == null) {
            System.out.println("Sucursal no encontrada.");
            return;
        }

        System.out.print("Tipo de cuenta (Ahorro / Corriente): ");
        String tipo = sc.nextLine().trim();



        System.out.print("DNI del titular: ");
        String dni = sc.nextLine().trim();
        if (banco.buscarCuenta(dni) != null) {
            System.out.println("Ya existe una cuenta con ese DNI.");
            return;
        }

        System.out.print("Nombre: ");      String nombre    = sc.nextLine().trim();
        System.out.print("Apellido: ");    String apellido  = sc.nextLine().trim();
        System.out.print("Email: ");       String email     = sc.nextLine().trim();
        System.out.print("Contraseña: ");  String contrasena = sc.nextLine().trim();

        Usuario titular = new Usuario(nombre, apellido, email, contrasena);
        Cuenta cuenta   = new Cuenta(dni, tipo, sucursal.nombre, titular);
        sucursal.cuentas.add(cuenta);

        System.out.println("Cuenta creada para DNI " + dni + " en " + sucursal.nombre);
    }

    static void editarCuenta() {
        banco.mostrarTodo();
        System.out.print("DNI de la cuenta a editar: ");
        Cuenta cuenta = banco.buscarCuenta(sc.nextLine().trim());
        if (cuenta == null) {
            System.out.println("Cuenta no encontrada.");
            return;
        }

        System.out.print("Nuevo tipo (Enter para mantener '" + cuenta.tipo + "'): ");
        String tipo = sc.nextLine().trim();
        if (!tipo.isEmpty()) cuenta.tipo = tipo;

        System.out.print("Nuevo nombre (Enter para mantener '" + cuenta.titular.nombre + "'): ");
        String nombre = sc.nextLine().trim();
        if (!nombre.isEmpty()) cuenta.titular.nombre = nombre;

        System.out.print("Nuevo apellido (Enter para mantener '" + cuenta.titular.apellido + "'): ");
        String apellido = sc.nextLine().trim();
        if (!apellido.isEmpty()) cuenta.titular.apellido = apellido;

        System.out.println("Cuenta actualizada.");
    }

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

    //  OPCIONES USUARIO


    static void manejarUsuario(int opcion) {
        // La cuenta sobre la que opera es siempre la del usuario logueado
        Cuenta cuenta = sesion.cuentaActiva;

        switch (opcion) {
            case 1:
                // Ver su propia cuenta
                cuenta.verEstado();
                break;

            case 2:
                // Depositar en su cuenta
                System.out.print("Monto a depositar: $");
                cuenta.depositar(leerDouble());
                break;

            case 3:
                // Retirar de su cuenta
                System.out.print("Monto a retirar: $");
                cuenta.retirar(leerDouble());
                break;

            case 4:
                // Transferir a otra cuenta (busca por DNI destino)
                System.out.print("DNI de la cuenta destino: ");
                Cuenta destino = banco.buscarCuenta(sc.nextLine().trim());
                if (destino == null) {
                    System.out.println("Cuenta destino no encontrada.");
                    return;
                }
                if (destino.dni.equals(cuenta.dni)) {
                    System.out.println("No puede transferir a su propia cuenta.");
                    return;
                }
                System.out.print("Monto a transferir: $");
                cuenta.transferir(destino, leerDouble());
                break;

            default:
                System.out.println("Opción inválida.");
        }
    }


    //  utilidades leerInt y Double limpio

    static int leerInt() {
        return Integer.parseInt(sc.nextLine().trim());
    }

    static double leerDouble() {
        return Double.parseDouble(sc.nextLine().trim());
    }
}

