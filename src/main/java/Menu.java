public class Menu {
    private Sesion sesion;

    public Menu(Sesion sesion) {
        this.sesion = sesion;
    }

    public void mostrar(){
        System.out.println("\n══════════════════════════════");

        if (!sesion.logueado) {
            // ── Sin loguear ─────────────────────────────────
            System.out.println("  1. Iniciar sesión como usuario");
            System.out.println("  2. Iniciar sesión como admin");
            System.out.println("  3. Ver sucursales");
            System.out.println("  4. Crear cuenta");

        } else if (sesion.esAdmin()) {
            // ── Admin logueado ───────────────────────────────
            System.out.println("  [Admin logueado]");
            System.out.println("  1. Gestionar cuentas");
            System.out.println("  2. Ver todo el banco");
            System.out.println("  9. Cerrar sesión");

        } else if (sesion.esUsuario()) {
            // ── Usuario logueado ─────────────────────────────
            System.out.println("  [Usuario: " + sesion.cuentaActiva.titular.nombre + "]");
            System.out.println("  1. Ver mi cuenta");
            System.out.println("  2. Depositar");
            System.out.println("  3. Retirar");
            System.out.println("  4. Transferir");
            System.out.println("  9. Cerrar sesión");
        }

        System.out.println("  0. Salir");
        System.out.println("══════════════════════════════");
    }


}
