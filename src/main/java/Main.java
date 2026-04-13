public class Main {
    static void main(String[] args) {

        CuentaBancaria objCuenta1 = new CuentaBancaria("Josue", "Direccion 123", "Ahorro");
        CuentaBancaria objCuenta2 = new CuentaBancaria("Carlos", "otra direccion 123", "Ahorro");

        Banco admin = new Banco();
        CuentaBancaria cuentaxd = admin.crearCuenta(new CuentaBancaria("Jose roberto", "Direccion 123", "Ahorro"));

        objCuenta1.agregarSaldo(200);

        objCuenta1.transferir(objCuenta2, 20);

        objCuenta1.mostrarDatos();
        objCuenta2.mostrarDatos();

        admin.editarCuenta(objCuenta1, "nuevo josue",  "Direccion nueva");
        objCuenta1.mostrarDatos();

    }
}
