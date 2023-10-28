package vistas;

import controlador.Controlador;
import modelo.clasesJuego.contenedor.IContenedor;
import modelo.clasesJuego.tablero.ITableroJugador;

import java.util.ArrayList;

public interface IVista {
     void setControlador(Controlador c);
     void inicializar();

     void mostrarTablero(ITableroJugador tableroJugador, ITableroJugador tableroOponente);
     
     void mostrarInicializarPartida();

     void mostrarGanador(String ganador);
}
