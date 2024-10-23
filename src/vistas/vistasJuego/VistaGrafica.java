package vistas.vistasJuego;

import controlador.Controlador;
import modelo.clasesJuego.contenedor.IContenedor;
import modelo.clasesJuego.jugador.IJugador;
import modelo.clasesJuego.tablero.ITableroJugador;
import vistas.ITipo;
import vistas.Menu.VistaMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class VistaGrafica implements ITipo, Serializable {
    private final JFrame frame;
    private  final  JPanel jPanelJuego;
    private final JPanel pAgujero;
    private final  JLabel lCasa;
    private final JLabel lCasaOp;
    private final  ArrayList<JLabel> lAgujeros;
    private  final  ArrayList<JLabel> lAgujH;
    private  final JButton botSalir;
    private final    JLabel lTurno;


    public VistaGrafica() {
        //definicion
        frame = new JFrame();
        jPanelJuego = new JPanel();
        pAgujero = new JPanel();
        lCasa = new JLabel();
        lCasaOp = new JLabel();
        botSalir= new JButton();
        lTurno= new JLabel();


        //layout
        frame.setLayout(new BorderLayout());
        jPanelJuego.setLayout(new BorderLayout());


        //size
        Color color = new Color(50,20,30);
        Dimension minSize = new Dimension(950,700);
        frame.setMinimumSize(minSize);
        jPanelJuego.setPreferredSize(new Dimension(700,600));
        botSalir.setPreferredSize(new Dimension(100,20));
        pAgujero.setPreferredSize(new Dimension(600,600));


        //config
        frame.setTitle("Mancala");
        pAgujero.setLayout(new GridLayout(2, 6,0,0));
        lAgujeros = new ArrayList<>();
        lAgujH=new ArrayList<>();
        botSalir.setText("Salir");
        pAgujero.setBackground(color);
        jPanelJuego.setBackground(color);
        lTurno.setForeground(Color.white);
        frame.setBackground(color);
        lCasa.setForeground(Color.white);
        lCasaOp.setForeground(Color.white);




        for (int j = 0; j < 12; j++) {
            JLabel lImagen = new JLabel();
            JLabel lHabas = new JLabel();

            lHabas.setForeground(Color.white);

            lAgujeros.add(lImagen);
            lAgujH.add(lHabas);

            JPanel p = new JPanel(new FlowLayout());
            p.add(lHabas);
            p.add(lImagen);
            p.setBackground(color);
            p.setPreferredSize(new Dimension(30,30));
            pAgujero.add(p);

        }



        jPanelJuego.add(lCasa,BorderLayout.EAST);
        jPanelJuego.add(pAgujero,BorderLayout.CENTER);
        jPanelJuego.add(lCasaOp,BorderLayout.WEST);
        jPanelJuego.add(lTurno,BorderLayout.NORTH);
        frame.add(jPanelJuego,BorderLayout.CENTER);
    }

    @Override
    public void mostrarTablero(ITableroJugador tableroJugador, ITableroJugador tableroOponente, IJugador turnoActual, IJugador nombreJugador) throws IOException {
        ArrayList<IContenedor> agujerosCont=tableroJugador.getAgujeros();
        ArrayList<IContenedor> agujerosContOp=tableroOponente.getAgujerosVuelta();

        setImagenAgujeros(agujerosCont,0);
        setImagenAgujeros(agujerosContOp,6);

        setImagenCasa(tableroJugador,lCasa);
        setImagenCasa(tableroOponente,lCasaOp);

        lTurno.setText(nombreJugador.getNombre()+" ,Turno Actual:"+ turnoActual.getNombre());

        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }






    @Override
    public void mostrarGanador(IJugador ganador) {
        JPanel panelGanador  = new JPanel();
        JLabel lGanador = new JLabel("Ganador: "+ganador.getNombre());
        panelGanador.add(lGanador);
        panelGanador.add(botSalir);
        frame.add(panelGanador,BorderLayout.SOUTH);
    }

    @Override
    public void modificarInput(Controlador controlador, VistaMenu vista) {
        for(int i =0 ;i<=5;i++){
            int finalI = i;
            lAgujeros.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    controlador.hacerJugada(finalI+1);
                }

            });
        }
        botSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.mostrarMenu();
                frame.dispose();
            }
        });
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
                l.setIcon(icon);

                l = lAgujH.get(i+parte);
                l.setText(String.valueOf(habas));

            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        }


    private void setImagenCasa(ITableroJugador tableroJugador,JLabel l){
        try {
            int habas=tableroJugador.getZona().getHabas();
            String imagen = obtenerImagen(habas,"Casa");
            l.setText(String.valueOf(habas));
            l.setVerticalTextPosition(SwingConstants.TOP);
            l.setHorizontalAlignment(SwingConstants.LEFT);
            l.setIcon(new ImageIcon(imagen));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
