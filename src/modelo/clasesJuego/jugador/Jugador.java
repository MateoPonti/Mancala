package modelo.clasesJuego.jugador;

import modelo.clasesJuego.usuario.IUsuario;

public class Jugador {
    private final int id;
    public Jugador(int id){
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public boolean equals(IUsuario u) {
        return u.getId()==getId();
    }
}