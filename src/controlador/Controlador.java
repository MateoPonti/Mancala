package controlador;

import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import modelo.clasesJuego.jugador.IJugador;
import modelo.mancala.IMancala;
import modelo.mancala.Mancala;
import vistas.IVista;

import java.io.Serializable;
import java.rmi.RemoteException;

public class Controlador implements IControladorRemoto, Serializable {

    private IVista vista;
    private IJugador jugador;
    private IMancala modelo;

    public Controlador(IVista vista) {
        this.vista=vista;
        vista.setControlador(this);
    }





    public void conectarUsuario(String nombre)  {
        try {
            this.jugador= modelo.conectarJugador(nombre);
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






    @Override
    public void actualizar(IObservableRemoto IObserver, Object cambio) throws RemoteException  {
        if (cambio instanceof Notificacion) {
        if (cambio == Notificacion.MOSTRARTABLEROS) {
            vista.mostrarTablero(modelo.getTableroTurno(jugador),modelo.getTableroOponente(jugador));
        }
        if (cambio == Notificacion.FINALIZOJUEGO) {
            vista.mostrarGanador(modelo.getGanador());
        }

        }
    }

    @Override
    public <T extends IObservableRemoto> void setModeloRemoto(T modeloRemoto) throws  RemoteException  {
        this.modelo= (IMancala) modeloRemoto;
        modelo.agregarObservador(this);
    }


}
