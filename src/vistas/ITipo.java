package vistas;

import controlador.Controlador;
import modelo.clasesJuego.jugador.IJugador;
import modelo.clasesJuego.tablero.ITableroJugador;

public interface ITipo {

    void mostrarTablero(ITableroJugador tableroJugador, ITableroJugador tableroOponente, IJugador turnoActual, IJugador nombreJugador);
    void mostrarGanador(IJugador ganador);
}
