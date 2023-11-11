package modelo.mancala;

import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import modelo.clasesJuego.tablero.ITableroJugador;
import modelo.clasesJuego.usuario.IUsuario;

import java.rmi.RemoteException;

public interface IMancala extends IObservableRemoto {
     void hacerJugada(int pos, IUsuario jugador) throws RemoteException;
     void hacerJugada(String pos, IUsuario jugador) throws RemoteException;
     void inicializarPartida(IUsuario jugador) throws RemoteException;

    IUsuario conectarJugador(String nombre, String eqw) throws RemoteException;

    IUsuario conectarAnonimo() throws RemoteException;

     ITableroJugador getTableroOponente(IUsuario jugador) throws  RemoteException;

     ITableroJugador getTableroTurno(IUsuario jugador) throws  RemoteException;

     String getGanador() throws  RemoteException;

    String getTurnoActual() throws  RemoteException;

    String getJugador(IUsuario usuario) throws  RemoteException;


}
