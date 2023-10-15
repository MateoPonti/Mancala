package principal;


import controlador.Controlador;
import modelo.mancala.Mancala;
import vistas.IVista;
import vistas.vistaConsola.swing.VistaConsolaSwing;

public class Main {
    public static void main(String[] args) {
        Mancala modelo= new Mancala();
        IVista vista = new VistaConsolaSwing();
        Controlador controlador1 = new Controlador(vista,modelo);

        IVista vista2 = new VistaConsolaSwing();
        Controlador controlador2 = new Controlador(vista2,modelo);


        vista.inicializar();
        vista2.inicializar();

    }
}
