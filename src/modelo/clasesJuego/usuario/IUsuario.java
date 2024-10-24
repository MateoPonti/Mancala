package modelo.clasesJuego.usuario;

public interface IUsuario {
    String getNickname();
    double getWinRate();
    int getVictorias();
    int getDerrotas();

    int getEmpates();
    int getId();

    int getTotalPartidas();


    boolean equals(IUsuario usuario);
}
