package vistas;

import controlador.Controlador;
import modelo.clasesJuego.jugador.IJugador;
import modelo.clasesJuego.tablero.ITableroJugador;

import java.io.IOException;


public interface IVista {
     void setControlador(Controlador c);
     void mostrarInicializarPartida();

     void mostrarTablero(ITableroJugador tableroJugador, ITableroJugador tableroOponente,IJugador turnoActual, IJugador nombreJugador) throws IOException;
     void mostrarGanador(IJugador ganador);
}
