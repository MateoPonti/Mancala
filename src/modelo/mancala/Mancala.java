package modelo.mancala;

import controlador.Notificacion;
import controlador.Observable;
import controlador.Observador;
import modelo.contenedor.IContenedor;
import modelo.jugador.IJugador;
import modelo.jugador.Jugador;
import modelo.mancala.partida.Partida;
import modelo.tablero.Posicion;
import modelo.tablero.ResultadoJugada;
import modelo.tablero.Tablero;

import java.util.ArrayList;

public class Mancala extends Observador {
    private ArrayList<Jugador> jugadores;
    private ArrayList<IJugador> preparados;

    private final int maxJugadores=2;

    private Partida partida;



    public Mancala(){
        jugadores=new ArrayList<>();
        preparados=new ArrayList<>();
    }


    public Jugador conectarJugador(String nombre) {
        if (getCantidadJugadores()>=maxJugadores){return null;}
        Jugador nuevoJugador= new Jugador(nombre);
        jugadores.add(nuevoJugador);
        inicializarPartida(nuevoJugador);
        return nuevoJugador;
    }
    public void desconectar(IJugador jugador, Observable obs) {
        try {
        jugadores.remove(jugador);
        eliminar(obs);}
       catch (Exception ignored){}

    }



    public void inicializarPartida(IJugador jugador) {
        if(preparados==null){preparados=new ArrayList<>();}
        preparados.add(jugador);
        if (isPreparados()){
            inicializarPartida();
            preparados=null;
            actualizar(Notificacion.MOSTRARTABLEROS);
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







    public void hacerJugada(String pos, IJugador jugador){
        try {
            int posInt= Integer.parseInt(pos.trim());
            hacerJugada(posInt, jugador);
        }
        catch (Exception ignored){

        }

    }



    public void hacerJugada(int pos, IJugador jugador){
        Notificacion resultado = partida.hacerJugada(pos,jugador);
        actualizar(Notificacion.MOSTRARTABLEROS);
        actualizar(resultado);
    }


    public ArrayList<IContenedor> getTableroTurno(IJugador jugador) {
        if (partida!=null )  {return partida.getTableroJugador(jugador); }
        return null;
    }
    public ArrayList<IContenedor> getTableroOponente(IJugador jugador) {
        if (partida!=null )  {return partida.getTableroOponente(jugador); }
        return null;
    }

    public String getGanador() {
        if (partida!=null){return partida.getGanador();}
        return null;
    }


}
