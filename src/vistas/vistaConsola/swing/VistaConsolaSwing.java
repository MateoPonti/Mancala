package vistas.vistaConsola.swing;

import controlador.Controlador;
import modelo.clasesJuego.contenedor.IContenedor;
import modelo.clasesJuego.jugador.IJugador;
import modelo.clasesJuego.tablero.ITableroJugador;
import modelo.clasesJuego.tablero.TableroJugador;
import vistas.IVista;

import javax.swing.*;
import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;
import java.util.ArrayList;

public class VistaConsolaSwing implements IVista, Serializable {
    private JFrame frame;
    private JPanel principal;
    private Controlador controlador;

    private JButton enviaBut;

    private JTextField ingresoNombre;

    private JTextField ingresoContra;

    private JTextArea agujeros;

    private JTextField posicionIngreso;

    private JButton butIngresoPos;



    private JPanel pIngreso;

    private   JButton arrancarPartida;

    private    JScrollPane scrollPane;
    public VistaConsolaSwing() {



    }

    public void inicializar() {
        frame=new JFrame("Mancala");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inicializarComp();
        pedirNombre();
    }

    public void inicializarComp(){

    enviaBut= new JButton("Enviar");
    ingresoNombre = new JTextField();
    ingresoContra = new JTextField();
    agujeros = new JTextArea();
    posicionIngreso=new JTextField();
    butIngresoPos=new JButton();
    pIngreso= new JPanel();
    arrancarPartida= new JButton("Inicializar Partida");
    scrollPane=new JScrollPane(agujeros);



    frame=new JFrame();
    principal= (JPanel) frame.getContentPane();
    principal.setLayout(null);



    butIngresoPos.setVisible(false);
    posicionIngreso.setVisible(false);
    agujeros.setVisible(false);
    enviaBut.setVisible(false);
    ingresoNombre.setVisible(false);
    ingresoContra.setVisible(false);
    pIngreso.setVisible(false);
    scrollPane.setVisible(false);

    enviaBut.addActionListener(e -> {
        String nombre = ingresoNombre.getText();
        String contrasenia = ingresoContra.getText();
        if (!nombre.isEmpty() && !contrasenia.isEmpty()) {
            controlador.conectarUsuario(nombre,contrasenia);
            enviaBut.setVisible(false);  }
    });

    butIngresoPos.addActionListener(e -> controlador.hacerJugada( posicionIngreso.getText()));


    ingresoNombre.setText("Nombre");
    ingresoContra.setText("Contraseña");
    butIngresoPos.setText("Enviar");
    posicionIngreso.setText("             ");


    posicionIngreso.setSize(100,20);
    butIngresoPos.setSize(100,20);


    principal.add(ingresoNombre);
    principal.add(ingresoContra);
    principal.add(scrollPane);
    principal.add(enviaBut);


    arrancarPartida.addActionListener(e -> {
            controlador.inicializarPartida();
            arrancarPartida.setVisible(false);
        });


    posicionIngreso.addKeyListener(new KeyListener(){


        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode()== KeyEvent.VK_ENTER){
                butIngresoPos.doClick();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }


    });
    }


    private void pedirNombre() {
        frame.setSize(400,300);
        frame.setVisible(true);


        arrancarPartida.setVisible(false);
        pIngreso.setVisible(false);

        butIngresoPos.setVisible(false);
        agujeros.setVisible(false);
        enviaBut.setVisible(true);
        ingresoNombre.setVisible(true);
        ingresoContra.setVisible(true);
        posicionIngreso.setVisible(false);
        scrollPane.setVisible(false);

        enviaBut.setSize(200,20);
        enviaBut.setBounds(50,190,enviaBut.getWidth(),enviaBut.getHeight());
        principal.setBackground(Color.blue);

        ingresoNombre.setSize(100,20);
        ingresoNombre.setBounds(50,100,ingresoNombre.getWidth(),ingresoNombre.getHeight());

        ingresoContra.setSize(100,20);
        ingresoContra.setBounds(50,140,ingresoNombre.getWidth(),ingresoNombre.getHeight());

        principal.add(arrancarPartida);

        pIngreso.setLayout(new FlowLayout());
        pIngreso.add(posicionIngreso);
        pIngreso.add(butIngresoPos);


    }



    public void mostrarTablero(ITableroJugador tableroJugador, ITableroJugador tableroOponente, IJugador turnoActual, IJugador nombreJugador){

        final Color color = Color.decode("#3b1b0f");
        final Dimension tam= new Dimension(600,600);
        final Dimension tamCasa= new Dimension(50,50);

        principal.setBackground(color);



        if (!agujeros.isVisible()){

            scrollPane.setVisible(true);
            principal= (JPanel) frame.getContentPane();
            principal.setSize(1100,700);
            principal.setLayout(new BorderLayout());
            principal.setBackground(color);
            butIngresoPos.setVisible(true);
            posicionIngreso.setVisible(true);
            agujeros.setVisible(true);
            enviaBut.setVisible(false);
            ingresoNombre.setVisible(false);
            ingresoContra.setVisible(false);
            agujeros.setSize(tam);
            agujeros.setEditable(false);
            pIngreso.setVisible(true);
            arrancarPartida.setVisible(false);
            pIngreso.setBackground(color);
            scrollPane.setVisible(true);
            scrollPane.setSize(tam);
            principal.add(pIngreso,BorderLayout.SOUTH);
            principal.add(scrollPane,BorderLayout.CENTER);
        }


        int x;
        int y;

        x=(principal.getWidth()/2)-100;
        y=(principal.getHeight()/4)+300;


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


        x=(principal.getWidth()/2)-100;
        y=(principal.getHeight()/4)-200;

        x=(principal.getWidth()/2)-190;
        y=(principal.getHeight()/4)+150;

    }

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


        int x=(principal.getWidth()/4);
        int y=(principal.getHeight()/2);
        arrancarPartida.setBounds(x,y,arrancarPartida.getWidth(),arrancarPartida.getHeight());

        principal.add(arrancarPartida);
    }

    @Override
    public void mostrarGanador(IJugador ganador) {
        JOptionPane.showMessageDialog(null, ganador.getNombre(), "Ganador", JOptionPane.INFORMATION_MESSAGE);
    }


    @Override
    public void setControlador(Controlador c) {
        this.controlador=c;
    }
}
