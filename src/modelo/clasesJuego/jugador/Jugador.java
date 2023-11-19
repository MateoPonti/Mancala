package modelo.clasesJuego.jugador;

import modelo.clasesJuego.usuario.IUsuario;

public class Jugador {
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
        this.nombre="Empate";
        this.id=-1;
    }

    public int getId() {
        return id;
    }

    public boolean equals(IUsuario u) {
        return u.getId()==getId();
    }
}