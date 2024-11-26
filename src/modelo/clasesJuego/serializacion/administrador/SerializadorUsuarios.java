package modelo.clasesJuego.serializacion.administrador;

import modelo.clasesJuego.serializacion.administrador.dominio.AdministradorUsuarios;
import modelo.clasesJuego.serializacion.administrador.servicios.Serializador;
import modelo.clasesJuego.usuario.Usuario;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class SerializadorUsuarios {
    private static AdministradorUsuarios administrador;
    private static Serializador serializador;


    public SerializadorUsuarios(){
        administrador = AdministradorUsuarios.getInstancia();
        serializador= new Serializador("Usuarios.dat");
        cargarUsuarios();
    }

    public void cargarUsuarios(){
        try {
        Object[] objetoRecuperado = serializador.readObjects();
        for(Object o : objetoRecuperado){
            administrador.add((Usuario) o);
        }} catch (Exception ignored) {
        }
    }

    public void mostrarUsuarios(){
        for (int i=0 ; i<administrador.getTam();i++){
            System.out.println(administrador.get(i));
        }
    }

    public void agregarUsuario(Usuario usuario) {
        administrador.add(usuario);
    }

    public void escribirUsuarios(){
        for (int i=0 ; i<administrador.getTam();i++){
            serializador.writeOneObject(administrador.get(i));

        }
    }

}
