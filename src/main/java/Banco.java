public class Banco {
    public String nombre;
    public CuentaBancaria[] cuentas;
    public int TotalCuentas;


    public CuentaBancaria crearCuenta(CuentaBancaria cuentaNueva) {
        System.out.println("Cuenta nueva de: " + cuentaNueva.nombre);

        return cuentaNueva;
    }

    public void editarCuenta(CuentaBancaria cuenta,String nombre, String direccion) {
        System.out.println("Editando datos de la cuenta de: " + cuenta.nombre);
        cuenta.nombre = nombre;
        cuenta.direccion = direccion;
    }


    public void mostrarTodasLasCuentas(CuentaBancaria cuentas) {

    }

}
