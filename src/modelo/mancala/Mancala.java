package modelo.mancala;

import modelo.jugador.IJugador;
import modelo.jugador.Jugador;
import modelo.mancala.partida.Partida;
import modelo.tablero.Posicion;

import java.util.ArrayList;

public class Mancala {
    private ArrayList<Jugador> jugadores;

    private final int maxJugadores=2;

    private Partida partida;



    public Mancala(){
    }


    public Jugador conectarJugador(String nombre) {
        if (getCantidadJugadores()>=maxJugadores){return null;}
        Jugador nuevoJugador= new Jugador(nombre);
        jugadores.add(nuevoJugador);
        return nuevoJugador;
    }
    public void desconectar(IJugador jugador) {
        jugadores.remove(jugador);
    }

    public int getCantidadJugadores(){
        return jugadores.size();
    }








    private boolean inicializarPartida() {
        if (! (getCantidadJugadores()==maxJugadores)){return false;}
        partida= new Partida(jugadores);
        return true;
    }

    private void hacerJugada(Posicion pos, IJugador jugador){

        ResultadoJugada resultado = partida.hacerJugada(pos,jugador);

        notify(resultado,partida.getTurno);




    }








}
