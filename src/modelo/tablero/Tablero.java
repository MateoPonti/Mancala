package modelo.tablero;

import java.util.ArrayList;

public class Tablero {
    private ArrayList<TableroJugador> tableroJugadores;

    public Tablero(ArrayList<TableroJugador> tablerosJugadores){
        this.tableroJugadores=tablerosJugadores;
    }

    public void  hacerJugada(int posicion,int jugadorTurno){
        TableroJugador tableroTurno= tableroJugadores.get(0);
        TableroJugador tableroOponente=tableroJugadores.get(1);


    }







}
