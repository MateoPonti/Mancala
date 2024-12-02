package modelo.mancala;

import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import modelo.clasesJuego.jugador.IJugador;
import modelo.clasesJuego.tablero.ITableroJugador;
import modelo.clasesJuego.usuario.IUsuario;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IMancala extends IObservableRemoto {
     void hacerJugada(int pos, IUsuario jugador) throws RemoteException;
     void hacerJugada(String pos, IUsuario jugador) throws RemoteException;
     void inicializarPartida(IUsuario jugador) throws RemoteException;

    IUsuario conectarJugador(String nombre, String eqw) throws RemoteException;


     ITableroJugador getTableroOponente(IUsuario jugador) throws  RemoteException;

     ITableroJugador getTableroTurno(IUsuario jugador) throws  RemoteException;

     IJugador getGanador() throws  RemoteException;

    IJugador getTurnoActual() throws  RemoteException;

    IJugador getJugador(IUsuario usuario) throws  RemoteException;

    void desconectarJugador(IUsuario u, IControladorRemoto c) throws RemoteException;


    ArrayList<IUsuario> obtenerRank() throws  RemoteException;

    void guardarCambios() throws  RemoteException;
}
