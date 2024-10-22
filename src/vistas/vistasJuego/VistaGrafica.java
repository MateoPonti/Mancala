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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class VistaGrafica implements ITipo, Serializable {
    private final JFrame frame;
    private final JPanel pAgujero;
    private final ArrayList<ImagenPanel> pAgujeros;


    public VistaGrafica(){
        //definicion
        frame = new JFrame();
        pAgujero = new JPanel();
        pAgujeros= new ArrayList<>();

        //layout
        frame.setLayout(new BorderLayout());


        //size
        Dimension minSize = new Dimension(800, 800);
        frame.setMinimumSize(minSize);
        Color color = Color.decode("#3b1b0f");
        pAgujero.setBackground(color);

        pAgujero.setLayout(new GridLayout(2,6));

        for (int i = 1 ; i<12; i++){
            pAgujeros.add(new ImagenPanel());
        }

    }

    @Override
    public void mostrarTablero(ITableroJugador tableroJugador, ITableroJugador tableroOponente, IJugador turnoActual, IJugador nombreJugador) throws IOException {
        ArrayList<IContenedor> agujerosContOp=tableroOponente.getAgujerosVuelta();
        ArrayList<IContenedor> agujerosCont=tableroJugador.getAgujeros();


        for(int i = 0 ; i <=6 ; i++) {
            String imagen = obtenerImagen(agujerosCont.get(0).getHabas(),"Agujero");
            pAgujeros.get(i).dibujarImagen(ImageIO.read(new File(imagen)));
            System.out.println(imagen);

        }

        frame.setVisible(true);


    }





    @Override
    public void mostrarGanador(IJugador ganador) {

    }

    @Override
    public void modificarInput(Controlador controlador) {


        for(int i =1 ;i<=6;i++){
            int finalI = i;
            pAgujeros.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    controlador.hacerJugada(finalI);
                }

            });
        }


    }



    private String obtenerImagen(int habas, String contenedor) {
        contenedor= "src/vistas/imagenes/"+contenedor;
        if (habas == 0) {
            return contenedor + "_Vacio.png";
        }
        if (habas >= 5) {
            return contenedor + "_5+Habas.png";
        }
        return contenedor + "_" + habas + "Habas.png";
    }
}
