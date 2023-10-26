package modelo.mancala;

import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import modelo.clasesJuego.contenedor.IContenedor;
import modelo.clasesJuego.usuario.IUsuario;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IMancala extends IObservableRemoto {
     void hacerJugada(int pos, IUsuario jugador) throws RemoteException;
     void hacerJugada(String pos, IUsuario jugador) throws RemoteException;
     void inicializarPartida(IUsuario jugador) throws RemoteException;

    IUsuario conectarJugador(String nombre, String eqw) throws RemoteException;

     ArrayList<IContenedor> getTableroOponente(IUsuario jugador) throws  RemoteException;

     ArrayList<IContenedor> getTableroTurno(IUsuario jugador) throws  RemoteException;

     String getGanador() throws  RemoteException;


}
