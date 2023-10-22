package vistas;

import controlador.Controlador;
import modelo.clasesJuego.contenedor.IContenedor;

import java.util.ArrayList;

public interface IVista {
     void setControlador(Controlador c);
     void inicializar();

     void mostrarTablero(ArrayList<IContenedor> tableroJugador,ArrayList<IContenedor> tableroOponente);
     
     void mostrarInicializarPartida();

     void mostrarGanador(String ganador);
}
