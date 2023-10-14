package principal;


import controlador.Controlador;
import modelo.mancala.Mancala;
import vistas.IVista;
import vistas.vistaConsola.swing.VistaConsolaSwing;

public class Main {
    public static void main(String[] args) {
        Mancala modelo= new Mancala();
        Controlador controlador1 = new Controlador(modelo);
        IVista vista = new VistaConsolaSwing();
        vista.setControlador(controlador1);

        Controlador controlador2 = new Controlador(modelo);

        IVista vista2 = new VistaConsolaSwing();
        vista2.setControlador(controlador2);

        vista.inicializar();
        vista2.inicializar();

    }
}
