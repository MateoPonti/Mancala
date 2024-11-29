package modelo.clasesJuego.serializacion;

import modelo.clasesJuego.serializacion.dominio.AdministradorUsuarios;
import modelo.clasesJuego.serializacion.servicios.Serializador;
import modelo.clasesJuego.usuario.IUsuario;
import modelo.clasesJuego.usuario.Usuario;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class SerializadorUsuarios {
    private static AdministradorUsuarios administrador;
    private HashMap<String,Integer> posicionHash;
    private static Serializador serializadorUsuarios;
    private static Serializador serializadorPosicionUsuarios;
    private static Serializador serializadorRanking;

    public SerializadorUsuarios(){
        administrador = AdministradorUsuarios.getInstancia();
        serializadorUsuarios= new Serializador("Usuarios.dat");
        serializadorPosicionUsuarios= new Serializador("PosicionUsuario.dat");
        serializadorRanking = new Serializador("RankingUsuario.dat");

    }

    public void inicializar(){
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
        posicionHash = cargarPosicion();
        if (posicionHash != null) {
            administrador.cambiar(usuario,posicionHash.get(usuario.getNombre()+usuario.getContrasenia()));
       }
    }


    public ArrayList<IUsuario> obtenerRank() {
        if (administrador!=null){
           ArrayList<Usuario> usuariosOrdenados = (administrador.obtenerUsuarios());
           usuariosOrdenados.sort(Comparator.comparing(Usuario::getElo).reversed());
            System.out.println(usuariosOrdenados.get(0).getNombre());
           return new ArrayList<>(usuariosOrdenados);
        }
        return null;
    }


    public void escribirUsuarios(){
        try{
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
        } } catch (Exception e) {
            e.printStackTrace();
        }
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

    @SuppressWarnings("unchecked")
    private HashMap<String, Integer> cargarPosicion(){
        HashMap<String,Integer> posicion = null;
        try {
            Object  object= serializadorPosicionUsuarios.readFirstObject();
            if (object instanceof HashMap) {
                posicion = (HashMap<String, Integer>) object;
            }
        } catch (Exception ignored) {
        }
        return posicion;
    }



}
