package modelo.jugador;

import modelo.tablero.TableroJugador;

public class Jugador implements IJugador{
    private String nombre;
    private TableroJugador tableroJugador;

    public Jugador(String nombre) {
        this.nombre=nombre;
        tableroJugador=new TableroJugador();
    }

    public TableroJugador getTableroJugador() {
        return tableroJugador;
    }
}
