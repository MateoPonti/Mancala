package principal;


import modelo.clasesJuego.serializacion.SerializadorUsuarios;
import modelo.clasesJuego.usuario.Usuario;

import java.rmi.RemoteException;

public class Main {
    public static void main(String[] args) throws RemoteException {
        /*
        Mancala modelo= Mancala.getInstancia();

        IVista vista = new Vista();
        Controlador controlador1 = new Controlador(vista);

        IVista vista2 = new Vista();
        Controlador controlador2 = new Controlador(vista2);


        IVista vista3 = new Vista();
        Controlador controlador3 = new Controlador(vista3);

        modelo.agregarObservador(controlador1);
        modelo.agregarObservador(controlador2);
        modelo.agregarObservador(controlador3);


        controlador1.setModeloRemoto((IMancala)modelo);
        controlador2.setModeloRemoto((IMancala)modelo);
        controlador3.setModeloRemoto((IMancala)modelo);


        vista.inicializar();
        vista2.inicializar();
        vista3.inicializar();


         */

        SerializadorUsuarios s = new SerializadorUsuarios();
        s.mostrarTodo();
    }
}

