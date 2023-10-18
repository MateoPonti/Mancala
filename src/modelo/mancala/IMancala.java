package modelo.mancala;

import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import modelo.clasesJuego.contenedor.IContenedor;
import modelo.clasesJuego.jugador.IJugador;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IMancala extends IObservableRemoto {
     void hacerJugada(int pos, IJugador jugador) throws RemoteException;
     void hacerJugada(String pos, IJugador jugador) throws RemoteException;
     void inicializarPartida(IJugador jugador) throws RemoteException;

    IJugador conectarJugador(String nombre) throws RemoteException;

     ArrayList<IContenedor> getTableroOponente(IJugador jugador) throws  RemoteException;

     ArrayList<IContenedor> getTableroTurno(IJugador jugador) throws  RemoteException;

     String getGanador() throws  RemoteException;


}
