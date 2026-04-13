public class Banco {
    public String nombre;
    public CuentaBancaria[] cuentas;
    public int TotalCuentas;

    public Banco(String nombre) {
        this.nombre = nombre;
        this.cuentas = new CuentaBancaria[10];
    }


    public void crearCuenta(CuentaBancaria cuentaNueva) {
        cuentas[TotalCuentas] = cuentaNueva;
        TotalCuentas++;
        System.out.println("Se ha creado la cuenta de: " + cuentaNueva.nombre);

    }

    public void editarCuenta(CuentaBancaria cuenta,String nombre, String direccion) {
        System.out.println("Editando datos de la cuenta de: " + cuenta.nombre);
        cuenta.nombre = nombre;
        cuenta.direccion = direccion;
    }


    public void mostrarTodasLasCuentas() {
        System.out.println("Listado de todas las cuentas:");
        for (int i = 0; i < TotalCuentas; i++){
            cuentas[i].mostrarDatos();

        }
    }

}
