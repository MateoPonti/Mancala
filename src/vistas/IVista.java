package vistas;

import controlador.Controlador;

public interface IVista {
    public void setControlador(Controlador c);
    public void inicializar();
}
