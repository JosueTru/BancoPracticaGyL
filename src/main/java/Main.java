public class Main {
    static void main(String[] args) {

        Banco admin = new Banco("AdminRoberto");


        admin.crearCuenta(new CuentaBancaria(12345678,"Josue", "Direccion 123", "Ahorro"));
        admin.crearCuenta(new CuentaBancaria( 11122212,"Carlos", "otra direccion 123", "Ahorro"));

        System.out.println("-------------------------------------------------------------------");

        admin.cuentas[0].agregarSaldo(200);
        admin.cuentas[0].transferir(admin.cuentas[1],20);

        admin.mostrarTodasLasCuentas();




    }
}
