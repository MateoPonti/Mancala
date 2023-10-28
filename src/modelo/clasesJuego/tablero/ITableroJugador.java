package modelo.clasesJuego.tablero;

import modelo.clasesJuego.contenedor.IContenedor;

import java.util.ArrayList;

public interface ITableroJugador {
    ArrayList<IContenedor>  getTablero();
    ArrayList<IContenedor> getAgujeros();

    ArrayList<IContenedor> getAgujerosVuelta();

    IContenedor getZona();
}
