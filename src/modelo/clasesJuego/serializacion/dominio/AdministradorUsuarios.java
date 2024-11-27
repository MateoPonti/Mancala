package modelo.clasesJuego.serializacion.dominio;

import modelo.clasesJuego.usuario.Usuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

public class AdministradorUsuarios {
    private  ArrayList<Usuario> usuarios;
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

    public Usuario buscarPorNickName(String nickname){
        return null;
    }

    public boolean estaVacio(){
        return usuarios.isEmpty();
    }

    public boolean remove(Objects o){
        return usuarios.remove(o);
    }

    public boolean addAll(Collection<? extends Usuario> c){
        return usuarios.addAll(c);
    }

    public void clear(){
        usuarios.clear();
    }


    public Usuario get(int index){
        return usuarios.get(index);
    }

    public int getTam(){
        return usuarios.size();
    }

    public void add(Usuario o) {
        usuarios.add(o);
    }

    public void cambiar(Usuario o , Integer posicion){
        usuarios.set(posicion,o);
    }
}

