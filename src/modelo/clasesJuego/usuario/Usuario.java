package modelo.clasesJuego.usuario;

import java.io.Serializable;

public class Usuario implements IUsuario, Serializable {
    private static final long serialVersionUID=1;
    private String nickname;
    private String nombre;
    private String contrasenia;
    private int id;
    private int victorias;
    private int derrotas;
    private int empates;



    public Usuario(String nombre, String contrasenia, int id) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.nickname= nombre+"#"+id ;
        this.id = id;
    }



    @Override
    public String getNickname() {
        return nickname;
    }




    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
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

    @Override
    public boolean equals(IUsuario usuario) {
        return usuario.getId()== id;
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

    @Override
    public int getEmpates() {
        return empates;
    }

    public void agregarEmpate() {
        this.empates++;
    }

    public void agregarDerrota() {
        this.derrotas++;
    }

    public int getElo() {
        return  getVictorias()*5+getEmpates()-getDerrotas()*2;
    }


    @Override
    public String toString() {
         return "Nombre: " + getNombre() + "\n" + "Nickname: " + nickname + "\n";
    }
}
