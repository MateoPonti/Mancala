package modelo.mancala;

import ar.edu.unlu.rmimvc.observer.ObservableRemoto;
import controlador.Notificacion;
import modelo.clasesJuego.partida.Partida;
import modelo.clasesJuego.tablero.ITableroJugador;
import modelo.clasesJuego.usuario.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Mancala extends ObservableRemoto implements IMancala{
    private ArrayList<Usuario> usuarios;
    private ArrayList<IUsuario> preparados;

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
        usuarios=new ArrayList<>();
        preparados=new ArrayList<>();
    }


    public IUsuario conectarJugador(String nombre,String contra) throws RemoteException {
        Usuario nuevoJugador= new Usuario(nombre,contra, usuarios.size());
        usuarios.add(nuevoJugador);
        return nuevoJugador;

    }




    public void inicializarPartida(IUsuario jugador) throws RemoteException {
        if(preparados==null && partida.isFinalizado()){preparados=new ArrayList<>();}
        preparados.add(jugador);
        if (isPreparados()){
            inicializarPartida();
            preparados=null;
            notificarObservadores(Notificacion.MOSTRARTABLEROS);
        }
    }


    public void hacerJugada(String pos, IUsuario jugador) throws RemoteException{
        try {
            int posInt= Integer.parseInt(pos.trim());
            hacerJugada(posInt, jugador);
        }
        catch (Exception ignored){
        }

    }

    public void hacerJugada(int pos, IUsuario jugador) throws  RemoteException{
        Notificacion resultado = partida.hacerJugada(pos,jugador);
        notificarObservadores(Notificacion.MOSTRARTABLEROS);
        notificarObservadores(resultado);
    }
    public ITableroJugador getTableroTurno(IUsuario jugador) throws  RemoteException {
        if (partida!=null )  {return partida.getTableroJugador(jugador); }
        return null;
    }
    public ITableroJugador getTableroOponente(IUsuario jugador) throws  RemoteException {
        if (partida!=null )  {return partida.getTableroOponente(jugador); }
        return null;
    }

    public String getGanador() throws  RemoteException{
        if (partida!=null){return partida.getGanador();}
        return null;
    }


    private void inicializarPartida() throws RemoteException {
        partida= new Partida(preparados);

    }


    private boolean isPreparados(){
        return preparados.size()==maxJugadores;
    }



}
