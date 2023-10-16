package controlador;

import modelo.jugador.IJugador;
import modelo.mancala.Mancala;
import vistas.IVista;

import java.util.Objects;

public class Controlador implements Observable {

    private IVista vista;
    private IJugador jugador;
    private Mancala modelo;

    public Controlador(IVista vista,Mancala modelo) {
        setModelo(modelo);
        setVista(vista);
    }
    private void setModelo(Mancala modelo) {
        this.modelo=modelo;
        this.modelo.agregar(this);
    }





    public void conectarUsuario(String nombre){
        this.jugador= (IJugador) modelo.conectarJugador(nombre);
    }

    public void hacerJugada(String val){
        modelo.hacerJugada(val,jugador);
    }

    public void inicializarPartida(){
        modelo.inicializarPartida(jugador);
    }




    public void setVista(IVista vista) {
        this.vista=vista;
        vista.setControlador(this);
    }


    @Override
    public void actualizar(Notificacion n) {
        if (n == Notificacion.MOSTRARTABLEROS) {
            vista.mostrarTablero(modelo.getTableroTurno(jugador),modelo.getTableroOponente(jugador));
        }
        if (n == Notificacion.FINALIZOJUEGO) {
            vista.mostrarGanador(modelo.getGanador());
        }
    }
}
