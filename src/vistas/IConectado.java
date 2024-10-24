package vistas;

import modelo.clasesJuego.jugador.IJugador;
import modelo.clasesJuego.usuario.IUsuario;

import java.util.ArrayList;

public interface IConectado {


    public void conectarUsuario(String nombre,String contrasenia);
    public ArrayList<IUsuario> mostrarTopRank();
    public void Jugar();


}
