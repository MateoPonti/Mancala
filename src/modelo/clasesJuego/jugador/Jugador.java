package modelo.clasesJuego.jugador;

import modelo.clasesJuego.tablero.TableroJugador;
import modelo.clasesJuego.usuario.IUsuario;

import java.io.Serializable;

public class Jugador implements Serializable {

    private int id;
    private String nombre;
    private TableroJugador tableroJugador;

    public Jugador(int id) {
        this.nombre=nombre;
        tableroJugador=new TableroJugador();
        this.id= id;
    }

    public TableroJugador getTableroJugador() {
        return tableroJugador;
    }


    public int getId() {
        return id;
    }

    public boolean equals(IUsuario obj) {
        return obj.getId()==getId();
    }
}
