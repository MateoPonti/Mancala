package modelo.clasesJuego.administrador;

import modelo.clasesJuego.usuario.IUsuario;
import modelo.clasesJuego.usuario.Usuario;

import java.util.ArrayList;

public class AdministradorUsuarios {

    public static  ArrayList<IUsuario> obtenerRank(){
       return null;
    }

    public Usuario buscarUsuario(String nombre , String contra){
        Usuario u=  new Usuario(nombre,contra,3);

        return u;
    }

    public static void agregarUsuario(IUsuario usuario){
    }


}

