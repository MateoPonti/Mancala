package vistas.vistasJuego;

import controlador.Controlador;
import modelo.clasesJuego.contenedor.Agujero;
import modelo.clasesJuego.contenedor.IContenedor;
import modelo.clasesJuego.jugador.IJugador;
import modelo.clasesJuego.tablero.ITableroJugador;
import vistas.ITipo;
import vistas.ImagenPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class VistaGrafica implements ITipo, Serializable {
    private final JFrame frame;
    private  final  JPanel JPanelJuego;
    private final JPanel pAgujero;
    private final  ImagenPanel JPanelCasa;
    private final ImagenPanel JPanelCasaOp;
    private final  ArrayList<JLabel> lAgujeros;
    private  final ArrayList<JLabel> lCasas;


    public VistaGrafica() {
        //definicion
        frame = new JFrame();
        JPanelJuego = new JPanel();
        pAgujero = new JPanel();
        JPanelCasa = new ImagenPanel();
        JPanelCasaOp = new ImagenPanel();


        //layout
        frame.setLayout(new BorderLayout());


        //size
        Dimension minSize = new Dimension(800, 800);
        frame.setMinimumSize(minSize);
        JPanelJuego.setLayout(new BorderLayout());
        JPanelJuego.setMinimumSize(new Dimension(300, 300));
        pAgujero.setLayout(new GridLayout(2, 6,2,2));
        lAgujeros = new ArrayList<>();
        lCasas = new ArrayList<>();

        for (int j = 0; j < 12; j++) {
            JLabel l = new JLabel();
            lAgujeros.add(l);

            JPanel p = new JPanel();

            p.add(l);
            pAgujero.add(p);

        }

        JLabel l =  new JLabel();
        l.setSize(new Dimension(100,100));
        lCasas.add(l);
        JPanelCasa.add(l);
        l = new JLabel();
        l.setSize(new Dimension(100,100));
        lCasas.add(l);
        JPanelCasaOp.add(l);

        JPanelJuego.setPreferredSize(new Dimension(350,350));

        frame.add(JPanelCasa,BorderLayout.EAST);
        frame.add(JPanelCasaOp,BorderLayout.WEST);
        JPanelJuego.add(pAgujero,BorderLayout.CENTER);
        frame.add(JPanelJuego,BorderLayout.CENTER);


    }

    @Override
    public void mostrarTablero(ITableroJugador tableroJugador, ITableroJugador tableroOponente, IJugador turnoActual, IJugador nombreJugador) throws IOException {
        ArrayList<IContenedor> agujerosCont=tableroJugador.getAgujeros();
        ArrayList<IContenedor> agujerosContOp=tableroOponente.getAgujerosVuelta();

        setImagenAgujeros(agujerosCont,0);
        setImagenAgujeros(agujerosContOp,6);

        setImagenCasa(tableroJugador,0);
        setImagenCasa(tableroOponente,1);


        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }






    @Override
    public void mostrarGanador(IJugador ganador) {

    }

    @Override
    public void modificarInput(Controlador controlador) {
        for(int i =0 ;i<=5;i++){
            int finalI = i;
            lAgujeros.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    controlador.hacerJugada(finalI+1);
                    System.out.println(finalI+1);
                }

            });
        }
    }



    private String obtenerImagen(int habas, String contenedor) {
        String ruta= "src/vistas/imagenes/";
        if (habas == 0) {
            return ruta + contenedor + "_Vacio.png";
        }
        if (habas >= 5) {
            return ruta + contenedor+  "_5+Habas.png";
        }
        return ruta + contenedor + "_" + habas + "Habas.png";
    }

    private void setImagenAgujeros(ArrayList<IContenedor> contenedor,int parte){
        try {

            for(int i = 0 ; i <=5 ; i++) {
                int habas=contenedor.get(i).getHabas();
                String imagen = obtenerImagen(habas,"Agujero");
                JLabel l = lAgujeros.get(i+parte);

                ImageIcon icon = new ImageIcon(imagen);
                l.setText(String.valueOf(habas));
                l.setHorizontalTextPosition(SwingConstants.LEFT);
                l.setIcon(icon);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        }


    private void setImagenCasa(ITableroJugador tableroJugador,int parte){
        try {
            int habas=tableroJugador.getZona().getHabas();
            String imagen = obtenerImagen(habas,"Casa");
            JLabel l = lCasas.get(parte);
            l.setText(String.valueOf(habas));
            l.setVerticalTextPosition(SwingConstants.TOP);
            l.setHorizontalAlignment(SwingConstants.LEFT);
            l.setIcon(new ImageIcon(imagen));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
