public class Usuario {

    String nombre;
    String apellido;
    String email;
    String contrasena;

    public Usuario(String nombre, String apellido, String email, String contrasena) {
        this.nombre     = nombre;
        this.apellido   = apellido;
        this.email      = email;
        this.contrasena = contrasena;
    }

    public void mostrar() {
        System.out.println("  Titular : " + nombre + " " + apellido);
        System.out.println("  Email   : " + email);
    }
}