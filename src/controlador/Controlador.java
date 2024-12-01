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

import static controlador.UtilHash.hashearContrasena;

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
            this.jugador= modelo.conectarJugador(nombre,hashearContrasena(contrasenia));
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
        if (jugador != null && cambio instanceof Notificador notificador) {
            if (notificador.esAfectado(jugador)) {
                Notificacion notificacion = notificador.getNotificacion();
                try {
                    switch (notificacion) {
                        case MOSTRARTABLEROS:
                            mostrarTableros();
                            break;

                        case FINALIZOJUEGO:
                            mostrarGanador();
                            break;

                        case PARTIDAESPERA:
                            mostrarPartidaEspera();
                            break;

                        case PARTIDALLENA:
                            mostrarPartidaLlena();
                            break;
                        case JUGADORSEFUE:
                            abandonarPartida();
                            break;

                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    private void abandonarPartida() {
        vista.abandonarPartida();
    }


    private void mostrarTableros() throws IOException {
        vista.mostrarTablero(
                modelo.getTableroTurno(jugador),
                modelo.getTableroOponente(jugador),
                modelo.getTurnoActual(),
                modelo.getJugador(this.jugador)
        );
    }

    private void mostrarGanador() throws RemoteException {
        vista.mostrarGanador(modelo.getGanador());
    }

    private void mostrarPartidaEspera() throws RemoteException {
        vista.mostrarPartidaEspera();
    }

    private void mostrarPartidaLlena() throws RemoteException {
        vista.mostrarPartidaLLena();
    }

    @Override
    public <T extends IObservableRemoto> void setModeloRemoto(T modeloRemoto) throws  RemoteException  {
        this.modelo= (IMancala) modeloRemoto;
    }


}
