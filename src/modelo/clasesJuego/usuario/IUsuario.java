package modelo.clasesJuego.usuario;

public interface IUsuario {
    String getNombre();
    double getWinRate();
    int getVictorias();
    int getDerrotas();

    int getEmpates();
    int getId();

    int getTotalPartidas();
}
