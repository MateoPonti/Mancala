package modelo.clasesJuego.serializacion;

import modelo.clasesJuego.serializacion.dominio.AdministradorUsuarios;
import modelo.clasesJuego.serializacion.servicios.Serializador;
import modelo.clasesJuego.usuario.Usuario;

import java.util.HashMap;

public class SerializadorUsuarios {
    private static AdministradorUsuarios administrador;
    private HashMap<String,Integer> posicionHash;
    private static Serializador serializadorUsuarios;
    private static Serializador serializadorPosicionUsuarios;

    public SerializadorUsuarios(){
        administrador = AdministradorUsuarios.getInstancia();
        serializadorUsuarios= new Serializador("Usuarios.dat");
        serializadorPosicionUsuarios= new Serializador("PosicionUsuario.dat");
        cargarUsuarios();
        posicionHash = cargarPosicion();
    }
    public Usuario agregarUsuario(String nombre, String contra) {
        if (administrador!=null){
        Usuario nuevoUsuario = new Usuario(nombre,contra,administrador.getTam());
        for (int i=0 ; i<administrador.getTam();i++){
           if(administrador.get(i).equals(nuevoUsuario)){
               return administrador.get(i);
           }
        }
        administrador.add(nuevoUsuario);
        escribirUsuarios();
        return nuevoUsuario;
        }
        return null;
    }
    public void actualizarUsuario(Usuario usuario ) {
        cargarPosicion();
        if (posicionHash != null) {
        administrador.cambiar(usuario,posicionHash.get(usuario.getNombre()+usuario.getContrasenia()));
        escribirUsuarios();
       }
    }



    public void mostrarTodo(){
        cargarPosicion();
        if (administrador!= null && posicionHash != null){
            System.out.println("Usuarios : ");
            for(int i = 0 ; i<administrador.getTam();i++){
                System.out.println(administrador.get(i));
            }

            System.out.println("PosicionesHash: ");
            System.out.println(posicionHash);

        }
    }



    private void escribirUsuarios(){

        if (!administrador.estaVacio()) {
            HashMap<String,Integer> posicionUsuarios = new HashMap<>();
            Usuario u = administrador.get(0);
            posicionUsuarios.put(u.getNombre()+u.getContrasenia(),0);
            serializadorUsuarios.writeOneObject(u);
            for (int i=1 ; i<administrador.getTam();i++){
                u= administrador.get(i);
                posicionUsuarios.put(u.getNombre()+u.getContrasenia(),i);
                serializadorUsuarios.addOneObject(u);
            }
            serializadorPosicionUsuarios.writeOneObject(posicionUsuarios);
        }
    }
    private  Usuario buscarUsuario(String nombre,String contra){
       Usuario u = null;
        if (posicionHash!=null){
            Integer posicion = posicionHash.get(nombre+contra);
            u = administrador.get(posicion);
        }
        return u;
    }
    private void cargarUsuarios(){
        try {
            Object[] usuarios = serializadorUsuarios.readObjects();
            for(Object o : usuarios){
                administrador.add((Usuario) o);
            }
        } catch (Exception ignored) {
        }
    }
    private HashMap<String, Integer> cargarPosicion(){
        HashMap<String,Integer> posicion = null;
        try {
            posicion  = (HashMap<String,Integer>) serializadorPosicionUsuarios.readFirstObject();
        } catch (Exception ignored) {
        }
        return posicion;
    }
}
