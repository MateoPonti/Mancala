package vistas;

import controlador.Controlador;
import modelo.clasesJuego.tablero.ITableroJugador;


public interface IVista {
     void setControlador(Controlador c);
     void inicializar();

     void mostrarTablero(ITableroJugador tableroJugador, ITableroJugador tableroOponente,String turnoActual, String nombreJugador);
     
     void mostrarInicializarPartida();

     void mostrarGanador(String ganador);
}
