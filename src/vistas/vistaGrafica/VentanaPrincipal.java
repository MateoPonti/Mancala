package vistas.vistaGrafica;

import modelo.clasesJuego.contenedor.IContenedor;

import java.util.ArrayList;

public class VentanaPrincipal {

    private MancalaGrafico ventanaJuego;
    public Login ventanaLogin;



    public VentanaPrincipal() {
        ventanaJuego=new MancalaGrafico();
    }






    public void mostrarTablero(ArrayList<IContenedor> agujeros, ArrayList<IContenedor> zona){
        ventanaJuego.mostrarTablero(agujeros,zona);

    }
}
