package vistas.vistaGrafica;

import modelo.contenedor.Agujero;
import modelo.contenedor.IContenedor;
import modelo.haba.IHaba;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class MancalaGrafico {

    private JFrame frame;

    private JPanel principal;

    private PanelConImagen tablero;

    private JPanel agujeros;

    private JPanel zonaOponente;

    private JPanel zonaTurno;


    public MancalaGrafico(){
       inicializarVentana();
    }

    private void inicializarVentana() {
        frame=new JFrame("Mancala");
        principal= (JPanel) frame.getContentPane();

        frame.setSize(1200,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        agujeros=new JPanel();
        zonaOponente=new JPanel();
        zonaTurno=new JPanel();
        tablero=new PanelConImagen("src/recursos/Tablero.png");


        ArrayList<IContenedor> zonas= new ArrayList<>();

        ArrayList<IContenedor> agujeros= new ArrayList<>();


        for(int i =0 ; i<12; i++){
            agujeros.add(new Agujero());
        }

        mostrarTablero(agujeros,zonas);
    }


    public void mostrarTablero(ArrayList<IContenedor> agujerosCont, ArrayList<IContenedor> zonas){

        principal.removeAll();


        tablero=new PanelConImagen("src/recursos/Tablero.png");

        agujeros.setLayout(new GridLayout(2,6));
        tablero.setLayout(new BorderLayout());

        JLabel habaUno;
        JLabel habaDos;
        JLabel habaTres;
        JLabel habaCuatro;



        PanelConImagen panelAgujero = null;

        

        for (int i = 0; i <agujerosCont.size(); i++) {
            panelAgujero= new PanelConImagen("src/recursos/Agujero.png");
            panelAgujero.setPreferredSize(new Dimension(60,60));

            habaUno= new JLabel();
            habaDos= new JLabel();
            habaTres= new JLabel();
            habaCuatro= new JLabel();

            habaUno.setIcon(new ImageIcon("src/recursos/AMARILLO.png"));
            habaDos.setIcon(new ImageIcon("src/recursos/AMARILLO.png"));
            habaTres.setIcon(new ImageIcon("src/recursos/AMARILLO.png"));
            habaCuatro.setIcon(new ImageIcon("src/recursos/AMARILLO.png"));


            panelAgujero.add(habaUno);
            panelAgujero.add(habaDos);
            panelAgujero.add(habaTres);
            panelAgujero.add(habaCuatro);


            agujeros.add(panelAgujero);

        }
        tablero.add(agujeros,BorderLayout.CENTER);


        principal.add(tablero,BorderLayout.CENTER);


        principal.revalidate();
        principal.repaint();




    }

}
