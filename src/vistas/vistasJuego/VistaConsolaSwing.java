package vistas.vistasJuego;

import controlador.Controlador;
import modelo.clasesJuego.contenedor.IContenedor;
import modelo.clasesJuego.jugador.IJugador;
import modelo.clasesJuego.tablero.ITableroJugador;
import vistas.ITipo;
import vistas.IVista;

import javax.swing.*;
import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class VistaConsolaSwing implements ITipo {
    private JFrame frame;
    private JPanel PAgujeros;
    private JPanel PEnvioPosicion;

    private JTextArea agujeros;

    private JTextField posicionIngreso;

    private JButton bIngresoPos;




    private    JScrollPane scrollPane;

    private  final Dimension minSize = new Dimension(800,800);



    public VistaConsolaSwing(){
        //definicion
        frame = new JFrame();
        PAgujeros= new JPanel();
        PEnvioPosicion= new JPanel();
        agujeros= new JTextArea();
        posicionIngreso= new JTextField(5);
        bIngresoPos= new JButton();


        //layout
        PEnvioPosicion.setLayout(new FlowLayout());
        frame.setLayout(new BorderLayout());


        //size
        frame.setMinimumSize(minSize);
        bIngresoPos.setSize(new Dimension(50,50));
        posicionIngreso.setSize(new Dimension(20,100));
        agujeros.setSize(new Dimension(300,300));

        //config
        Color color = Color.decode("#3b1b0f");
        agujeros.setEditable(false);
        PAgujeros.setBackground(color);
        bIngresoPos.setText("Enviar");


    }


    public void mostrarTablero(ITableroJugador tableroJugador, ITableroJugador tableroOponente, IJugador turnoActual, IJugador nombreJugador){
        int x;
        int y;

        x=(PAgujeros.getWidth()/2)-100;
        y=(PAgujeros.getHeight()/4)+300;


        String strZonaTurno= "("+ tableroJugador.getZona().getHabas()+")";
        String strZonaOp= "("+ tableroOponente.getZona().getHabas()+")";


        ArrayList<IContenedor> agujerosContOp=tableroOponente.getAgujerosVuelta();
        ArrayList<IContenedor> agujerosCont=tableroJugador.getAgujeros();



        StringBuilder tableroJugadorTurno =new StringBuilder();
        StringBuilder tableroJugadorOponente = new StringBuilder();

        for (int i =0 ; i<agujerosCont.size();i++){
            tableroJugadorOponente.append("(").append(agujerosContOp.get(i).getHabas()).append(")");
            tableroJugadorTurno.append("(").append(agujerosCont.get(i).getHabas()).append(")");
        }


        agujeros.append(strZonaOp+"   "+tableroJugadorOponente.toString()+"   "+strZonaTurno+"\n");
        agujeros.append("        "+ tableroJugadorTurno.toString()+"\n");
        agujeros.append("\n"+nombreJugador.getNombre()+", Turno: "+ turnoActual.getNombre()+"\n");
        agujeros.append("------------------------------------------------------"+"\n");
        x=(PAgujeros.getWidth()/2)-100;
        y=(PAgujeros.getHeight()/4)-200;
        x=(PAgujeros.getWidth()/2)-190;
        y=(PAgujeros.getHeight()/4)+150;

        PAgujeros.add(agujeros);
        PEnvioPosicion.add(posicionIngreso);
        PEnvioPosicion.add(bIngresoPos);

        frame.add(PAgujeros,BorderLayout.CENTER);
        frame.add(PEnvioPosicion,BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();

        frame.setVisible(true);



    }

    @Override
    public void mostrarGanador(IJugador ganador) {
        JOptionPane.showMessageDialog(null, ganador.getNombre(), "Ganador", JOptionPane.INFORMATION_MESSAGE);
        frame.setVisible(false);

    }

    @Override
    public void setControlador(Controlador c) {

    }



    /*
    @Override
    public void mostrarInicializarPartida() {
        frame.setSize(600,300);
        principal.setSize(600,300);
        principal.setBackground(Color.blue);

        arrancarPartida.setVisible(true);
        arrancarPartida.setSize(200,20);
        ingresoContra.setVisible(false);
        ingresoNombre.setVisible(false);
        butIngresoPos.setVisible(false);
        agujeros.setVisible(false);
        butIngresoPos.setVisible(false);
        scrollPane.setVisible(false);
        pIngreso.setVisible(false);
        agujeros.setText("");
        posicionIngreso.setText("      ");


        int x=(principal.getWidth()/4);
        int y=(principal.getHeight()/2);
        arrancarPartida.setBounds(x,y,arrancarPartida.getWidth(),arrancarPartida.getHeight());

        principal.add(arrancarPartida);
    }
   */




}
