package vistas;

import controlador.Controlador;
import modelo.clasesJuego.jugador.IJugador;
import modelo.clasesJuego.tablero.ITableroJugador;
import vistas.vistasJuego.IMenu;
import vistas.vistasJuego.Vista;

import java.io.IOException;

public interface ITipo {

    void mostrarTablero(ITableroJugador tableroJugador, ITableroJugador tableroOponente, IJugador turnoActual, IJugador nombreJugador) throws IOException;
    void mostrarGanador(IJugador ganador);
    // darle los action listener y establecer que funcion hacer con el controlador pasado por parametro
    void modificarInput(Controlador controlador, IMenu vista);
    void asignarAbandono(Vista vista);
    void mostrarAbandonar();
}
