package controlador;

import modelo.jugador.IJugador;
import modelo.mancala.Mancala;
import vistas.IVista;

import java.util.Objects;

public class Controlador implements Observable {

    private IVista vista;
    private IJugador jugador;
    private Mancala modelo;

    public Controlador(Mancala modelo) {
        setModelo(modelo);
    }
    private void setModelo(Mancala modelo) {
        this.modelo=modelo;
        this.modelo.agregar(this);
    }





    public void conectarUsuario(String nombre){
        this.jugador= (IJugador) modelo.conectarJugador(nombre);
    }

    public void setVista(IVista vista) {
        this.vista=vista;
    }


    @Override
    public void actualizar(Notificacion n) {
        if (Objects.requireNonNull( n) == Notificacion.MOSTRARTABLEROS) {
            vista.mostrarTablero(modelo.getTableros(this.jugador));
        }
    }
}
