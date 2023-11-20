package modelo.clasesJuego.usuario;

import java.io.Serializable;

public class Usuario implements IUsuario, Serializable {
    private String nombre;
    private String contrasenia;
    private int id;
    private int victorias;
    private int derrotas;


    public Usuario(String nombre, String contrasenia, int id) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.id = id;
    }



    public String getNombre() {
        return nombre;
    }

    @Override
    public double getWinRate() {
        return  victorias==0 ? 0: (double) (getTotalPartidas() * 100) /victorias;
    }



    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public int getId() {
        return id;
    }

    @Override
    public int getTotalPartidas() {
        return getVictorias()+getDerrotas();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVictorias() {
        return victorias;
    }

    public void agregarVictoria() {
        this.victorias++;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void agregarDerrota() {
        this.derrotas++;
    }

    public boolean equals(IUsuario usuario) {
        return getId()==usuario.getId();
    }
}
