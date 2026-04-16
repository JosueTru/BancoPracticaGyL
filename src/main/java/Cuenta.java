import java.util.ArrayList;
import java.util.List;

public class Cuenta {



    String  dni;
    String  tipo;
    double  saldo;
    boolean activa;
    String  nombreSucursal;
    Usuario titular;

    // Lista historial
    List<String> movimientos = new ArrayList<String>();



    // Se crea la cuenta con un DNI, tipo, saldo inicial, sucursal y titular
    public Cuenta(String dni, String tipo, String nombreSucursal, Usuario titular) {
        this.dni            = dni;
        this.tipo           = tipo;
        this.saldo          = 0;
        this.nombreSucursal = nombreSucursal;
        this.titular        = titular;
        this.activa         = true;  // Toda cuenta empieza activa

        // El primer movimiento registrado es la apertura de la cuenta
        movimientos.add("Apertura de cuenta.");
    }



    // Depositar: suma dinero al saldo y registra el movimiento
    public void depositar(double monto) {
        // Validación: la cuenta debe estar activa
        if (!activa) {
            System.out.println("La cuenta está dada de baja. No se puede operar.");
            return;
        }
        saldo += monto;
        movimientos.add("Depósito: +$" + monto + "  |  Saldo: $" + saldo);
        System.out.println("Depósito exitoso. Saldo actual: $" + saldo);
    }

    // Retirar: resta dinero del saldo, si hay suficiente
    public void retirar(double monto) {
        // Validación: la cuenta debe estar activa
        if (!activa) {
            System.out.println("La cuenta está dada de baja. No se puede operar.");
            return;
        }
        // Validación: no se puede retirar más de lo que hay
        if (monto > saldo) {
            System.out.println("Saldo insuficiente. Saldo actual: $" + saldo);
            return;
        }
        saldo -= monto;
        movimientos.add("Retiro: -$" + monto + "  |  Saldo: $" + saldo);
        System.out.println("Retiro exitoso. Saldo actual: $" + saldo);
    }

    // Transferir: mueve dinero de esta cuenta a otra
    public void transferir(Cuenta destino, double monto) {

        if (!activa) {
            System.out.println("La cuenta origen está dada de baja. No se puede transferir.");
            return;
        }
        if (!destino.activa) {
            System.out.println("La cuenta destino está dada de baja. No se puede transferir.");
            return;
        }
        // Validación
        if (monto > saldo) {
            System.out.println("Saldo insuficiente para transferir.");
            return;
        }

        // Se descuenta de esta cuenta y se acredita en la destino
        saldo         -= monto;
        destino.saldo += monto;

        // Se registra el movimiento en ambas cuentas
        movimientos.add("Transferencia a DNI " + destino.dni + ": -$" + monto + "  |  Saldo: $" + saldo);
        destino.movimientos.add("Transferencia de DNI " + dni + ": +$" + monto + "  |  Saldo: $" + destino.saldo);

        System.out.println("Transferencia exitosa de $" + monto + " a DNI " + destino.dni);
    }

    // Dar de baja: desactiva la cuenta
    public void darDeBaja() {
        if (!activa) {
            System.out.println("La cuenta ya estaba dada de baja.");
            return;
        }
        activa = false;
        movimientos.add("Cuenta dada de baja.");
        System.out.println("Cuenta de DNI " + dni + " dada de baja.");
    }



    // Muestra todos los datos de la cuenta y su historial completo
    public void verEstado() {
        System.out.println("-----------------------------");
        System.out.println("DNI      : " + dni);
        System.out.println("Sucursal : " + nombreSucursal);
        System.out.println("Tipo     : " + tipo);
        System.out.println("Saldo    : $" + saldo);
        System.out.println("Estado   : " + (activa ? "Activa" : "Dada de baja"));
        titular.mostrar();     // Delega la impresión del titular a Usuario
        System.out.println("Movimientos:");
        for (String m : movimientos) {
            System.out.println("  - " + m);
        }
        System.out.println("-----------------------------");
    }

    // Muestra solo la info esencial
    public void verResumen() {
        System.out.println("  [DNI: " + dni + "] " + tipo + " | $" + saldo
                + " | " + titular.nombre + " " + titular.apellido
                + " | " + (activa ? "Activa" : "BAJA"));
    }
}