package vistas;

import controlador.Controlador;
import modelo.clasesJuego.tablero.ITableroJugador;


public interface IVista {
     void setControlador(Controlador c);
     void inicializar();

     void mostrarTablero(ITableroJugador tableroJugador, ITableroJugador tableroOponente);
     
     void mostrarInicializarPartida();

     void mostrarGanador(String ganador);
}
