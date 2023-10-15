package modelo.tablero;

import modelo.haba.IHaba;

import java.util.ArrayList;

public class Tablero {
    private ArrayList<TableroJugador> tableroJugadores;

    public Tablero(ArrayList<TableroJugador> tablerosJugadores){
        this.tableroJugadores=tablerosJugadores;
    }

    public void  hacerJugada(int posicion,int jugadorTurno){
        TableroJugador tableroTurno= tableroJugadores.get(0);
        TableroJugador tableroOponente=tableroJugadores.get(1);
        ResultadoJugada resultado = ResultadoJugada.Correcta;

        ArrayList<IHaba> habasRepartidas=tableroTurno.repartirHabasTurno(posicion);
        while (!habasRepartidas.isEmpty()){
            tableroOponente.repartirHabasOponente(habasRepartidas);
            tableroTurno.repartirHabasTurno(habasRepartidas);
        }
        if (tableroTurno.isUltimaCayoVacio()){
            RobarPuntos(tableroTurno.getPosCayoVacio(),tableroTurno,tableroOponente);
        }
        if (tableroTurno.isUltimaCayoZona()){
            resultado=ResultadoJugada.OtroTurno;
        }
        if(comprobarVictoria(tableroTurno,tableroOponente)!=null){
            resultado=ResultadoJugada.Victoria;
        }
    }

    private void RobarPuntos(int posCayoVacio,TableroJugador turno,TableroJugador oponente) {
        ArrayList<IHaba> habas= oponente.obtenerContenedor(turno.getPosCayoVacio());
        turno.sumarPuntos(habas);
    }


    private TableroJugador comprobarVictoria(TableroJugador turno,TableroJugador oponete){
        if (turno.noHayHabas()) {
            return turno;
        }
        if (oponete.noHayHabas()) {
            return oponete;
        }
        return null;
    }


}
