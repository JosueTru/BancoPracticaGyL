public class Usuario {

    String nombre;
    String apellido;
    String email;

    public Usuario(String nombre, String apellido, String email) {
        this.nombre   = nombre;
        this.apellido = apellido;
        this.email    = email;
    }

    // Muestra los datos del titular por pantalla
    public void mostrar() {
        System.out.println("  Titular : " + nombre + " " + apellido);
        System.out.println("  Email   : " + email);
    }
}