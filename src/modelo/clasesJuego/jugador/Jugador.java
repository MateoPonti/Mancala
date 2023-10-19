package modelo.clasesJuego.jugador;

import modelo.clasesJuego.tablero.TableroJugador;

import java.io.Serializable;

public class Jugador implements IJugador, Serializable {

    private int id;
    private static int idTotal=1;
    private String nombre;
    private TableroJugador tableroJugador;

    public Jugador(String nombre) {
        this.nombre=nombre;
        tableroJugador=new TableroJugador();
        this.id= idTotal;
        idTotal++;
    }

    public TableroJugador getTableroJugador() {
        return tableroJugador;
    }


    public int getId() {
        return id;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean equals(Object obj) {
        IJugador jugador2= (IJugador) obj;
        return jugador2.getId()==getId();
    }
}
