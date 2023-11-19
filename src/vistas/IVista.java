package vistas;

import controlador.Controlador;
import modelo.clasesJuego.jugador.IJugador;
import modelo.clasesJuego.tablero.ITableroJugador;


public interface IVista {
     void setControlador(Controlador c);
     void inicializar();

     void mostrarTablero(ITableroJugador tableroJugador, ITableroJugador tableroOponente,IJugador turnoActual, IJugador nombreJugador);
     
     void mostrarInicializarPartida();

     void mostrarGanador(IJugador ganador);
}
