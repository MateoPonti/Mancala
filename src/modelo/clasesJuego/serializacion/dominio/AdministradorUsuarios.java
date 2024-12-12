package modelo.clasesJuego.serializacion.dominio;

import modelo.clasesJuego.usuario.IUsuario;
import modelo.clasesJuego.usuario.Usuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

public class AdministradorUsuarios {
    private static ArrayList<Usuario> usuarios;
    private static AdministradorUsuarios instancia;


    public static AdministradorUsuarios getInstancia(){
        if (instancia==null){
            instancia=new AdministradorUsuarios();
        }
        return instancia;
    }



    private AdministradorUsuarios(){
        super();
        usuarios=new ArrayList<>();
    }


    public boolean estaVacio(){
        return usuarios.isEmpty();
    }

    public Usuario get(int index){
        return usuarios.get(index);
    }

    public int getTam(){
        return usuarios.size();
    }

    public void add(Usuario o) {
        usuarios.add(o) ;
    }

    public void cambiar(Usuario o , Integer posicion){
        usuarios.set(posicion,o);
    }

    public ArrayList<IUsuario> obtenerRank() {
        ArrayList<IUsuario> usuariosOrdenados = new ArrayList<> (usuarios);
        usuariosOrdenados.sort(Comparator.comparing(IUsuario::getElo).reversed());
        return new ArrayList<>(usuariosOrdenados.subList(0, Math.min(5, usuariosOrdenados.size())));
    }
}