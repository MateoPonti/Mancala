package vistas.vistasJuego;

import controlador.Controlador;
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
    private final ArrayList<ImagenPanel> pAgujeros;
    private final  ArrayList<JLabel> lAgujeros;
    private  final ArrayList<JLabel> lCasas;


    public VistaGrafica(){
        //definicion
        frame = new JFrame();
        JPanelJuego= new JPanel();
        pAgujero = new JPanel();
        pAgujeros= new ArrayList<>();
        JPanelCasa=new ImagenPanel();
        JPanelCasaOp= new ImagenPanel();


        //layout
        frame.setLayout(new BorderLayout());


        //size
        Dimension minSize = new Dimension(800, 800);
        frame.setMinimumSize(minSize);
        Color color = Color.decode("#3b1b0f");
        JPanelJuego.setLayout(new BorderLayout());
        JPanelJuego.setMinimumSize(new Dimension(300,300));
        pAgujero.setBackground(color);
        pAgujero.setLayout(new GridLayout(2,6));
        lAgujeros = new ArrayList<>();
        lCasas= new ArrayList<>();

        int c = 0;
        for (int j = 0 ; j<12; j++){
            JLabel l =  new JLabel();
            ImagenPanel i = new ImagenPanel();
            if ((j+c)%2==0){i.setBackground(Color.BLACK);}
            else { i.setBackground(Color.white);}
            if (j ==5){c++;}
            i.add(l);
            lAgujeros.add(l);
            pAgujeros.add(i);
            pAgujero.add(i);


        }

        JLabel l =  new JLabel();
        lCasas.add(l);
        JPanelCasa.add(l);
        l = new JLabel();
        lCasas.add(l);
        JPanelCasaOp.add(l);

        JPanelJuego.add(JPanelCasa,BorderLayout.EAST);
        JPanelJuego.add(JPanelCasaOp,BorderLayout.WEST);
        JPanelJuego.add(pAgujero,BorderLayout.CENTER);
        frame.add(JPanelJuego,BorderLayout.CENTER);


    }

    @Override
    public void mostrarTablero(ITableroJugador tableroJugador, ITableroJugador tableroOponente, IJugador turnoActual, IJugador nombreJugador) throws IOException {
        ArrayList<IContenedor> agujerosContOp=tableroOponente.getAgujerosVuelta();
        ArrayList<IContenedor> agujerosCont=tableroJugador.getAgujeros();


        for(int i = 0 ; i <=5 ; i++) {
            int habas=agujerosCont.get(i).getHabas();
            String imagen = obtenerImagen(habas,"Agujero");
            ImagenPanel panel = pAgujeros.get(i);
            panel.dibujarImagen(ImageIO.read(new File(imagen)));
            lAgujeros.get(i).setText(String.valueOf(habas));
        }


        for(int i = 0 ; i <=5 ; i++) {
            int habas=agujerosContOp.get(i).getHabas();
            String imagen = obtenerImagen(habas,"Agujero");
            ImagenPanel panel = pAgujeros.get(i+6);
            panel.dibujarImagen(ImageIO.read(new File(imagen)));
            lAgujeros.get(i+6).setText(String.valueOf(habas));
        }

        int habas=tableroOponente.getZona().getHabas();
        String imagen = obtenerImagen(tableroOponente.getZona().getHabas(),"Casa");
        lCasas.get(1).setText(String.valueOf(habas));
        JPanelCasaOp.dibujarImagen(ImageIO.read(new File(imagen)));

        habas=tableroJugador.getZona().getHabas();
        imagen = obtenerImagen(habas,"Casa");
        lCasas.get(0).setText(String.valueOf(habas));
        JPanelCasa.dibujarImagen(ImageIO.read(new File(imagen)));


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
            pAgujeros.get(i).addMouseListener(new MouseAdapter() {
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
}
