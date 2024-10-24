package vistas;

import modelo.clasesJuego.usuario.IUsuario;

import java.util.ArrayList;

public interface IConectado {


     void conectarUsuario(String nombre,String contrasenia);
     ArrayList<IUsuario> mostrarTopRank();
     void jugar(ITipo tipo);


}
