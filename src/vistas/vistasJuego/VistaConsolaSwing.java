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
import java.io.Serializable;
import java.util.ArrayList;

public class VistaConsolaSwing implements ITipo, Serializable {
    private final JFrame frame;
    private final JPanel PAgujeros;
    private final JPanel PEnvioPosicion;
    private final JTextArea agujeros;
    private final JTextField posicionIngreso;
    private final JButton bIngresoPos;
    private final JScrollPane scrollPane;
    private final JButton botSalir;


    public VistaConsolaSwing(){
        //definicion
        frame = new JFrame();
        PAgujeros= new JPanel();
        PEnvioPosicion= new JPanel();
        agujeros= new JTextArea(30,50);
        posicionIngreso= new JTextField(5);
        bIngresoPos= new JButton();
        scrollPane= new JScrollPane(agujeros);
        botSalir= new JButton();


        //layout
        PEnvioPosicion.setLayout(new FlowLayout());
        frame.setLayout(new BorderLayout());


        //size
        Dimension minSize = new Dimension(1000 ,800);
        frame.setMinimumSize(minSize);
        bIngresoPos.setSize(new Dimension(50,50));
        posicionIngreso.setSize(new Dimension(20,100));
        botSalir.setSize(new Dimension(100,20));

        //config
        frame.setTitle("Mancala");
        Color color = Color.decode("#3b1b0f");
        agujeros.setEditable(false);
        PAgujeros.setBackground(color);
        bIngresoPos.setText("Enviar");
        botSalir.setText("Salir");
        botSalir.setVisible(false);

        PAgujeros.add(scrollPane,BorderLayout.CENTER);
        PAgujeros.add(botSalir,BorderLayout.SOUTH);
        PEnvioPosicion.add(posicionIngreso);
        PEnvioPosicion.add(bIngresoPos);
        PEnvioPosicion.add(botSalir);
        frame.add(PAgujeros);
        frame.add(PEnvioPosicion,BorderLayout.SOUTH);


    }


    public void mostrarTablero(ITableroJugador tableroJugador, ITableroJugador tableroOponente, IJugador turnoActual, IJugador nombreJugador){


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
        agujeros.append(strZonaOp+espacio+tableroJugadorTurno+espacio+strZonaTurno+"\n");
        agujeros.append(espacioTurno+ tableroJugadorOponente+"\n");
        agujeros.append("\n"+nombreJugador.getNombre()+", Turno: "+ turnoActual.getNombre()+"\n");
        agujeros.append("------------------------------------------------------"+"\n");


        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }

    @Override
    public void mostrarGanador(IJugador ganador) {
        botSalir.setVisible(true);
        bIngresoPos.setVisible(false);
        posicionIngreso.setVisible(false);
        agujeros.append("Ganador: "+ ganador.getNombre()+" !!!!!!!");
        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void modificarInput(Controlador controlador, IMenu vista) {
        bIngresoPos.addActionListener(e -> controlador.hacerJugada(posicionIngreso.getText()));
        botSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.mostrarMenu();
                frame.dispose();
            }
        });
    }
}
