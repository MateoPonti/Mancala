package modelo.mancala;

import controlador.Notificacion;
import controlador.Observable;
import controlador.Observador;
import modelo.jugador.IJugador;
import modelo.jugador.Jugador;
import modelo.mancala.partida.Partida;
import modelo.tablero.ResultadoJugada;

import java.util.ArrayList;

public class Mancala extends Observador {
    private ArrayList<Jugador> jugadores;
    private ArrayList<IJugador> preparados;

    private final int maxJugadores=2;

    private Partida partida;



    public Mancala(){
        jugadores=new ArrayList<>();
    }


    public Jugador conectarJugador(String nombre) {
        if (getCantidadJugadores()>=maxJugadores){return null;}
        Jugador nuevoJugador= new Jugador(nombre);
        jugadores.add(nuevoJugador);
        inicializarPartida();
        return nuevoJugador;
    }
    public void desconectar(IJugador jugador, Observable obs) {
        try {
        jugadores.remove(jugador);
        eliminar(obs);}
       catch (Exception e){}

    }



    public void inicialiarPartida(IJugador jugador) {
        preparados.add(jugador);
        if (isPreparados()){
            inicializarPartida();
        }
    }


    private void inicializarPartida() {
        partida= new Partida(jugadores);
        actualizar(Notificacion.INICIARPARTIDA);
    }



    public int getCantidadJugadores(){
        return jugadores.size();
    }

    public boolean isPreparados(){
        return preparados.size()==maxJugadores;
    }










    private void hacerJugada(int pos, IJugador jugador){

        ResultadoJugada resultado = partida.hacerJugada(pos,jugador);




    }



}
