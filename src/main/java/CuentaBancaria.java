public class CuentaBancaria {
    public String nombre;
    public String direccion;
    public String tipoDeCuenta;
    public double saldo;

    public CuentaBancaria(String nombre, String direccion, String tipoDeCuenta) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.tipoDeCuenta = tipoDeCuenta;
        this.saldo = saldo;

    }

    public CuentaBancaria(String nombre, String direccion, String tipoDeCuenta, double saldo) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.tipoDeCuenta = tipoDeCuenta;
        this.saldo = saldo;

    }

    public void agregarSaldo(double monto) {
        this.saldo += monto;
    }

    public void transferir(CuentaBancaria cuenta, double valor) {
        if (valor <= 0) {
            System.out.println("Saldo insuficiente");
        }  else {
            this.saldo -= valor;
            cuenta.agregarSaldo(valor);
            System.out.println("Se ha transferido " + valor + "$ a " + cuenta.nombre);
        }
    }


    public void mostrarDatos() {
        System.out.println("--------------------------------------------------------");
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Direccion: " + this.direccion);
        System.out.println("El saldo de " + this.nombre + " es: " + this.saldo);
        System.out.println("Tipo de cuenta: " + this.tipoDeCuenta);
        System.out.println("--------------------------------------------------------");
    }






}
