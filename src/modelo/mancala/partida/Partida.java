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

    private EstadoPartida estado;

    public Partida(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
        turno=jugadores.get(0);
        ArrayList<TableroJugador> tablerosJugadores = new ArrayList<>();
        tablerosJugadores.add(jugadores.get(0).getTableroJugador());
        tablerosJugadores.add(jugadores.get(1).getTableroJugador());
        tablero= new Tablero(tablerosJugadores);
        estado=EstadoPartida.EnJuego;

    }


    public ResultadoJugada hacerJugada(int posicion, IJugador jugador){
        if (turno.equals(jugador)){
           Jugador j=jugadores.get(determinarJugador(jugador));
           hacerJugada(posicion,j);

        }

        return null;
    }

    private int determinarJugador(IJugador jugador){
        if (jugadores.get(0).equals(jugador)){ return 0;}
          return 1;
    }

    public Jugador getTurno() {
        return turno;
    }



    
}
