package principal;


import controlador.Controlador;
import modelo.mancala.IMancala;
import modelo.mancala.Mancala;
import vistas.IVista;
import vistas.vistasJuego.Vista;

import java.rmi.RemoteException;

public class Main {
    public static void main(String[] args) throws RemoteException {

        Mancala modelo= Mancala.getInstancia();

        IVista vista = new Vista();
        Controlador controlador1 = new Controlador(vista);

        IVista vista2 = new Vista();
        Controlador controlador2 = new Controlador(vista2);



        modelo.agregarObservador(controlador1);
        modelo.agregarObservador(controlador2);

        controlador1.setModeloRemoto((IMancala)modelo);
        controlador2.setModeloRemoto((IMancala)modelo);

        vista.inicializar();
        vista2.inicializar();








    }
}

