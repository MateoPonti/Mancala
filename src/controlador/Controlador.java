package controlador;

import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import modelo.clasesJuego.usuario.IUsuario;
import modelo.mancala.IMancala;
import vistas.IVista;

import java.io.Serializable;
import java.rmi.RemoteException;

public class Controlador implements IControladorRemoto, Serializable {

    private final IVista vista;
    private IUsuario jugador;
    private IMancala modelo;

    public Controlador(IVista vista) {
        this.vista=vista;
        vista.setControlador(this);
    }





    public void conectarUsuario(String nombre,String contrasenia)  {
        try {
            this.jugador= modelo.conectarJugador(nombre,contrasenia);
            vista.mostrarInicializarPartida();
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

    public void desconectarJugador() {
        modelo.desconectarJugador(jugador);
    }





    @Override
    public void actualizar(IObservableRemoto IObserver, Object cambio) throws RemoteException {
        if (jugador!=null){
        if (cambio instanceof Notificacion) {
        if (cambio == Notificacion.MOSTRARTABLEROS) {
            try {
                    vista.mostrarTablero(modelo.getTableroTurno(jugador),modelo.getTableroOponente(jugador),modelo.getTurnoActual(), modelo.getJugador(this.jugador));
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }}
         if (cambio == Notificacion.FINALIZOJUEGO) {
            try {
                vista.mostrarGanador(modelo.getGanador());
                vista.mostrarInicializarPartida();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        } }
    }

    @Override
    public <T extends IObservableRemoto> void setModeloRemoto(T modeloRemoto) throws  RemoteException  {
        this.modelo= (IMancala) modeloRemoto;
    }


}
