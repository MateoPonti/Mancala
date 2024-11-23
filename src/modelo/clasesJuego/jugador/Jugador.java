package modelo.clasesJuego.jugador;

import modelo.clasesJuego.usuario.IUsuario;

import java.io.Serializable;

public class Jugador implements  IJugador, Serializable {

    private static final long serialVersionUID=1;

    private final int id;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private String nombre;
    public Jugador(int id,String nombre){
        this.id=id;
        this.nombre=nombre;
    }


    public Jugador(){
        this.id=-1;
        this.nombre="Empate";
    }


    public boolean esValido(){
        return id!=-1;
    }
    public int getId() {
        return id;
    }

    public boolean equals(IUsuario u) {
        return u.getId()==getId();
    }
}