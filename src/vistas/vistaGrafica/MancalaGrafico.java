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

    private JPanel tablero;

    private JPanel agujeros;

    private JPanel zonaOponente;

    private JPanel zonaTurno;


    public MancalaGrafico(){
       inicializarVentana();
    }

    private void inicializarVentana() {
        frame=new JFrame("Mancala");
        principal= (JPanel) frame.getContentPane();
        principal.setLayout(new BorderLayout());

        frame.setSize(1600,800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        agujeros=new JPanel();
        zonaOponente=new JPanel();
        zonaTurno=new JPanel();
        tablero = new JPanel();


        ArrayList<IContenedor> zonas= new ArrayList<>();

        ArrayList<IContenedor> agujeros= new ArrayList<>();


        for(int i =0 ; i<12; i++){
            agujeros.add(new Agujero());
        }

        mostrarTablero(agujeros,zonas);
    }


    public void mostrarTablero(ArrayList<IContenedor> agujerosCont, ArrayList<IContenedor> zonas){

        principal.removeAll();

        principal.setBackground(Color.GREEN);


        agujeros.setLayout(new GridBagLayout());

        tablero.setLayout(new BorderLayout());

        JLabel habaUno;
        JLabel habaDos;
        JLabel habaTres;
        JLabel habaCuatro;



        PanelConImagen panelAgujero = null;

        int i=0;
        int j=0;

        for (int c = 0; c < agujerosCont.size(); c++) {
            if (c == 6) {
                i++;
                j = 0;
            }

            panelAgujero = new PanelConImagen("src/recursos/Agujero.png", 500, 300);
            panelAgujero.setLayout(new GridBagLayout());

            habaUno = new JLabel();
            habaDos = new JLabel();
            habaTres = new JLabel();
            habaCuatro = new JLabel();

            habaUno.setIcon(new ImageIcon("src/recursos/AMARILLO.png"));
            habaDos.setIcon(new ImageIcon("src/recursos/AMARILLO.png"));
            habaTres.setIcon(new ImageIcon("src/recursos/AMARILLO.png"));
            habaCuatro.setIcon(new ImageIcon("src/recursos/AMARILLO.png"));

            panelAgujero.add(habaUno, new GridBagConstraints());
            panelAgujero.add(habaDos, new GridBagConstraints());
            panelAgujero.add(habaTres, new GridBagConstraints());
            panelAgujero.add(habaCuatro, new GridBagConstraints());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = j;
            gbc.gridy = i;
            gbc.weightx = 1;
            gbc.weighty = 1;
            gbc.insets = new Insets(0, 0, 0, 0);

            agujeros.add(panelAgujero, gbc);
            j++;
        }


        PanelConImagen zonaTurno = new PanelConImagen("src/recursos/Zona.png",200,200);
        PanelConImagen zonaOponente = new PanelConImagen("src/recursos/Zona.png",200,200);


        zonaTurno.setOpaque(false);
        zonaOponente.setOpaque(false);


        principal.add(zonaTurno,BorderLayout.WEST);
        principal.add(zonaOponente,BorderLayout.EAST);




        tablero.setOpaque(false);
        agujeros.setOpaque(false);


        tablero.setSize(new Dimension(600,600));

        tablero.add(agujeros,BorderLayout.CENTER);
        principal.add(tablero,BorderLayout.CENTER);

        principal.revalidate();
        principal.repaint();





    }

}
