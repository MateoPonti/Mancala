package vistas.vistasJuego;

import controlador.Controlador;
import modelo.clasesJuego.contenedor.IContenedor;
import modelo.clasesJuego.jugador.IJugador;
import modelo.clasesJuego.tablero.ITableroJugador;
import vistas.ITipo;

import javax.swing.*;
import java.awt.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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



    public VistaConsolaSwing(Controlador c){
        //definicion
        frame = new JFrame();
        PAgujeros= new JPanel();
        PEnvioPosicion= new JPanel();
        agujeros= new JTextArea();
        posicionIngreso= new JTextField(5);
        bIngresoPos= new JButton();
        scrollPane= new JScrollPane(agujeros);


        //layout
        PEnvioPosicion.setLayout(new FlowLayout());
        frame.setLayout(new BorderLayout());


        //size
        frame.setMinimumSize(minSize);
        bIngresoPos.setSize(new Dimension(50,50));
        posicionIngreso.setSize(new Dimension(20,100));

        //config
        Color color = Color.decode("#3b1b0f");
        agujeros.setEditable(false);
        PAgujeros.setBackground(color);
        bIngresoPos.setText("Enviar");
        bIngresoPos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.hacerJugada(posicionIngreso.getText());
            }
        });

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


        String espacio = "   ";
        String espacioTurno="        ";
        agujeros.append(strZonaOp+espacio+tableroJugadorOponente.toString()+espacio+strZonaTurno+"\n");
        agujeros.append(espacioTurno+ tableroJugadorTurno.toString()+"\n");
        agujeros.append("\n"+nombreJugador.getNombre()+", Turno: "+ turnoActual.getNombre()+"\n");
        agujeros.append("------------------------------------------------------"+"\n");
        x=(PAgujeros.getWidth()/2)-100;
        y=(PAgujeros.getHeight()/4)-200;
        x=(PAgujeros.getWidth()/2)-190;
        y=(PAgujeros.getHeight()/4)+150;

        scrollPane.setVisible(true);
        PAgujeros.add(scrollPane);
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
        bIngresoPos.setVisible(false);
        posicionIngreso.setVisible(false);

        agujeros.append("Ganador: "+ ganador.getNombre()+" !!!!!!!");



        frame.revalidate();
        frame.repaint();

    }






}
