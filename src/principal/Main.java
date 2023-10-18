package principal;


import controlador.Controlador;
import modelo.mancala.Mancala;
import vistas.IVista;
import vistas.vistaConsola.swing.VistaConsolaSwing;

import java.rmi.RemoteException;

public class Main {
    public static void main(String[] args) throws RemoteException {
        Mancala modelo= Mancala.getInstancia();
        IVista vista = new VistaConsolaSwing();
        Controlador controlador1 = new Controlador(vista);

        IVista vista2 = new VistaConsolaSwing();
        Controlador controlador2 = new Controlador(vista2);

        controlador1.setModeloRemoto(modelo);
        controlador2.setModeloRemoto(modelo);

        vista.inicializar();
        vista2.inicializar();

    }
}
