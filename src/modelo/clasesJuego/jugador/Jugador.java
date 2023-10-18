package modelo.clasesJuego.jugador;

import modelo.clasesJuego.tablero.TableroJugador;

import java.io.Serializable;

public class Jugador implements IJugador, Serializable {
    private String nombre;
    private TableroJugador tableroJugador;

    public Jugador(String nombre) {
        this.nombre=nombre;
        tableroJugador=new TableroJugador();
    }

    public TableroJugador getTableroJugador() {
        return tableroJugador;
    }

    @Override
    public String getNombre() {
        return nombre;
    }
}
