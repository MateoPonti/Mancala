package controlador;

import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import modelo.clasesJuego.usuario.IUsuario;
import modelo.mancala.IMancala;
import vistas.IVista;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Controlador implements IControladorRemoto, Serializable {

    private final IVista vista;
    private IUsuario jugador;
    private IMancala modelo;

    public Controlador(IVista vista) {
        this.vista=vista;
        vista.setControlador(this);
    }


    public String conectarUsuario(String nombre,String contrasenia)  {
        try {
            this.jugador= modelo.conectarJugador(nombre,contrasenia);
            return jugador.getNickname();
        } catch (RemoteException e) {
            throw new RuntimeException(e);

        }
    }

    public void hacerJugada(int val)  {
        try {
            modelo.hacerJugada(val,jugador);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void hacerJugada(String val)  {
        try {
            modelo.hacerJugada(val,jugador);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void inicializarPartida()  {
        try {
            modelo.inicializarPartida(jugador);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public void desconectarJugador() throws  RemoteException {
        modelo.desconectarJugador(jugador);
    }




    public ArrayList<IUsuario> obtenerRank() throws RemoteException {
        return  modelo.obtenerRank();
    }

    @Override
    public void actualizar(IObservableRemoto IObserver, Object cambio) throws RemoteException {
        if (jugador!=null){
        if (cambio instanceof Notificador)   {
            if ( ((Notificador) cambio).esAfectado(jugador) ){
                Notificacion notificacion = ((Notificador)cambio).getNotificacion();
                if (notificacion == Notificacion.MOSTRARTABLEROS) {
                    try {
                        vista.mostrarTablero(modelo.getTableroTurno(jugador),modelo.getTableroOponente(jugador),modelo.getTurnoActual(), modelo.getJugador(this.jugador));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (notificacion == Notificacion.FINALIZOJUEGO) {
                    try {
                        vista.mostrarGanador(modelo.getGanador());
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (notificacion == Notificacion.PARTIDAESPERA) {
                    try {
                        vista.mostrarPartidaEspera();
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (notificacion == Notificacion.PARTIDALLENA) {
                    try {
                        vista.mostrarPartidaLLena();
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }}}}}
    }


    @Override
    public <T extends IObservableRemoto> void setModeloRemoto(T modeloRemoto) throws  RemoteException  {
        this.modelo= (IMancala) modeloRemoto;
    }


}
