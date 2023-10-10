package modelo.mancala.partida;

import modelo.jugador.IJugador;
import modelo.jugador.Jugador;
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
        tablero=new Tablero();
    }


    public void hacerJugada(Posicion posicion, IJugador jugador){
        if (turno.equals(jugador)){
            ArrayList<TableroJugador> tablerosJugadores = new ArrayList<>();

            int indiceTurno=0;
            int indiceOponente=1;

            if(jugadores.get(1).equals(jugador)){
                indiceTurno=1;
                indiceOponente=0;
            }

            tablerosJugadores.add(jugadores.get(indiceTurno).getTableroJugador());
            tablerosJugadores.add(jugadores.get(indiceOponente).getTableroJugador());

            tablero.hacerJugada(tablerosJugadores,posicion);

        }

    }
}
