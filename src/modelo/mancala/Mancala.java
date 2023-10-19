package modelo.mancala;

import ar.edu.unlu.rmimvc.observer.ObservableRemoto;
import controlador.Notificacion;
import modelo.clasesJuego.contenedor.IContenedor;
import modelo.clasesJuego.jugador.IJugador;
import modelo.clasesJuego.jugador.Jugador;
import modelo.clasesJuego.partida.Partida;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Mancala extends ObservableRemoto implements IMancala{
    private ArrayList<Jugador> jugadores;
    private ArrayList<IJugador> preparados;

    private static final int maxJugadores=2;

    private Partida partida;

    private static Mancala instancia;



    public static Mancala getInstancia(){
        if (instancia==null){
            instancia= new Mancala();
        }
        return instancia;
    }

    private Mancala(){
        jugadores=new ArrayList<>();
        preparados=new ArrayList<>();
    }


    public Jugador conectarJugador(String nombre) throws RemoteException {
        if (getCantidadJugadores()>=maxJugadores){return null;}
        Jugador nuevoJugador= new Jugador(nombre);
        jugadores.add(nuevoJugador);
        return nuevoJugador;

    }




    public void inicializarPartida(IJugador jugador) throws RemoteException {
        if(preparados==null){preparados=new ArrayList<>();}
        preparados.add(jugador);
        if (isPreparados()){
            inicializarPartida();
            preparados=null;
            notificarObservadores(Notificacion.MOSTRARTABLEROS);
        }
    }


    public void hacerJugada(String pos, IJugador jugador) throws RemoteException{
        try {
            int posInt= Integer.parseInt(pos.trim());
            hacerJugada(posInt, jugador);
        }
        catch (Exception ignored){
        }

    }

    public void hacerJugada(int pos, IJugador jugador) throws  RemoteException{
        Notificacion resultado = partida.hacerJugada(pos,jugador);
        notificarObservadores(Notificacion.MOSTRARTABLEROS);
        notificarObservadores(resultado);
    }
    public ArrayList<IContenedor> getTableroTurno(IJugador jugador) throws  RemoteException {
        if (partida!=null )  {return partida.getTableroJugador(jugador); }
        return null;
    }
    public ArrayList<IContenedor> getTableroOponente(IJugador jugador) throws  RemoteException {
        if (partida!=null )  {return partida.getTableroOponente(jugador); }
        return null;
    }

    public String getGanador() throws  RemoteException{
        if (partida!=null){return partida.getGanador();}
        return null;
    }


    private void inicializarPartida() throws RemoteException {
        partida= new Partida(jugadores);

    }



    private int getCantidadJugadores(){
        return jugadores.size();
    }

    private boolean isPreparados(){
        return preparados.size()==maxJugadores;
    }



}
