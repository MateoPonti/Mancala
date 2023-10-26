package modelo.clasesJuego.usuario;

public interface IUsuario {
    String getNombre();
    double getWinRate();
    int getVictorias();
    int getDerrotas();
    int getId();

    int getTotalPartidas();
}
