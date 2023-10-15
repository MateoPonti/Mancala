package vistas;

import controlador.Controlador;
import modelo.contenedor.IContenedor;

import java.util.ArrayList;

public interface IVista {
    public void setControlador(Controlador c);
    public void inicializar();

    public void mostrarTablero(ArrayList<IContenedor> tableroJugador,ArrayList<IContenedor> tableroOponente);
}
