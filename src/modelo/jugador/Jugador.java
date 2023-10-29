package modelo.jugador;

import modelo.clasesJuego.usuario.IUsuario;
import modelo.clasesJuego.usuario.Usuario;

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
