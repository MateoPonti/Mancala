package modelo.mancala.partida;

import modelo.jugador.IJugador;
import modelo.jugador.Jugador;
import modelo.tablero.ResultadoJugada;
import modelo.tablero.Tablero;
import modelo.tablero.TableroJugador;

import java.util.ArrayList;

public class Partida {
    private Jugador turno;

    private ArrayList<Jugador> jugadores;

    private Tablero tablero;

    public Partida(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
        turno=jugadores.get(0);
        ArrayList<TableroJugador> tablerosJugadores = new ArrayList<>();
        tablerosJugadores.add(jugadores.get(0).getTableroJugador());
        tablerosJugadores.add(jugadores.get(1).getTableroJugador());
        tablero= new Tablero(tablerosJugadores);
    }


    public ResultadoJugada hacerJugada(int posicion, IJugador jugador){
        if (turno.equals(jugador)){
            if (jugadores.get(0).equals(jugador)){
              tablero.hacerJugada(posicion,0); }
            else {
                tablero.hacerJugada(posicion,1);
            }

        }

        return null;
    }

    public Jugador getTurno() {
        return turno;
    }
}
