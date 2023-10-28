package vistas.vistaConsola.swing;

import controlador.Controlador;
import modelo.clasesJuego.contenedor.IContenedor;
import modelo.clasesJuego.tablero.ITableroJugador;
import vistas.IVista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

public class VistaConsolaSwing implements IVista, Serializable {
    private JFrame frame;
    private JPanel principal;

    private Controlador controlador;


    public VistaConsolaSwing() {
    }

    public void inicializar() {
        frame=new JFrame("Mancala");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pedirNombre();
    }

    private void pedirNombre() {
        frame.setSize(500,200);
        frame.setVisible(true);
        principal= (JPanel) frame.getContentPane();
        principal.setLayout(null);

        JButton enviaBut= new JButton("Enviar");

        enviaBut.setSize(200,20);
        enviaBut.setBounds(150,100,enviaBut.getWidth(),enviaBut.getHeight());


        principal.setBackground(Color.lightGray);
        JTextField ingresoNombre = new JTextField();
        ingresoNombre.setText("NOMBRE");
        ingresoNombre.setSize(100,20);
        ingresoNombre.setBounds(50,100,ingresoNombre.getWidth(),ingresoNombre.getHeight());


        enviaBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    controlador.conectarUsuario(getNombre(ingresoNombre));
                    enviaBut.setVisible(false);
            }
        });

        principal.add(ingresoNombre);
        principal.add(enviaBut);

        principal.revalidate();
        principal.repaint();


    }

    private String getNombre(JTextField j){
        String texto=j.getText();
        if (texto.isEmpty()) {
            return "Jugador Anonimo";
        }
        return texto;

    }



    public void mostrarTablero(ITableroJugador tableroJugador, ITableroJugador tableroOponente){
        int x;
        int y;

        frame.setSize(820,600);
        principal.setSize(820,600);

        ArrayList<IContenedor> zonas= new ArrayList<>();
        zonas.add(tableroJugador.getZona());
        zonas.add(tableroOponente.getZona());

        ArrayList<IContenedor> tableros= tableroJugador.getAgujeros();
        tableros.addAll(tableroOponente.getAgujerosVuelta());


        principal.removeAll();
        principal.setBackground(Color.decode("#3b1b0f"));


        int panelWidth = principal.getWidth();
        int panelHeight = principal.getHeight();

        int c = 0 ;
        int j = 0;


        int tam= 0;
        int labelWidth = 100;
        int labelHeight = 100;

        // agregar Los Agujeros
        for (int i = 0; i < tableros.size(); i++) {
            if (i==6){
                j+=tam;
                c=0;
            }
            JLabel l=  new JLabel("<html>" + hacerAgujero(tableros.get(i).getCantidad()) + "</html>");
            x = ( ((panelWidth - labelWidth) / 4)-80 )+c;
            l.setSize(labelWidth,labelHeight);
            y = ((panelHeight - labelHeight) / 2)-j;
            l.setBounds(x, y, labelWidth, labelHeight);


            tam= l.getHeight();
            c+=l.getWidth();

            principal.add(l);


        }

        //Agrega las Zonas
        JLabel zonaTurno =  new JLabel("<html>" + hacerZona(zonas.get(0).getCantidad()) + "</html>");
        JLabel zonaOponente= new JLabel("<html>" + hacerZona(zonas.get(1).getCantidad()) + "</html>");

        zonaOponente.setSize(300,300);
        zonaTurno.setSize(300,300);


        x = zonaOponente.getWidth()/2 -150;
        y = ((panelHeight - zonaOponente.getHeight()) / 2)-50;

        zonaOponente.setBounds(x, y, zonaOponente.getWidth(), zonaOponente.getHeight());

        x=(principal.getWidth())-115;

        zonaTurno.setBounds(x,y,zonaTurno.getWidth(),zonaTurno.getHeight());


        principal.add(zonaOponente);
        principal.add(zonaTurno);



        // Agrega Edit para ingresar Posicion
        JTextField posicionIngreso = new JTextField();
        x=(principal.getWidth()/2)-100;
        y=(principal.getHeight()/4)+300;
        posicionIngreso.setSize(100,20);
        JButton butIngresoPos= new JButton();
        butIngresoPos.setSize(100,20);
        butIngresoPos.setText("Enviar");


        posicionIngreso.setBounds(x,y,posicionIngreso.getWidth(),posicionIngreso.getHeight());
        butIngresoPos.setBounds(x+100,y,posicionIngreso.getWidth(),posicionIngreso.getHeight());
        butIngresoPos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controlador.hacerJugada( posicionIngreso.getText());
            }
        });
        principal.add(posicionIngreso);
        principal.add(butIngresoPos);

        principal.revalidate();
        principal.repaint();

    }

    @Override
    public void mostrarInicializarPartida() {
        principal.removeAll();
        frame.setSize(600,300);
        principal.setSize(600,300);
        principal.setBackground(Color.CYAN);

        JButton arrancarPartida= new JButton("Inicializar Partida");
        arrancarPartida.setSize(200,20);

        int x=(principal.getWidth()/2);
        int y=(principal.getHeight()/2);
        arrancarPartida.setBounds(x,y,arrancarPartida.getWidth(),arrancarPartida.getHeight());
        arrancarPartida.addActionListener(e -> {
            controlador.inicializarPartida();
        });
        principal.add(arrancarPartida);
    }

    @Override
    public void mostrarGanador(String ganador) {
        JOptionPane.showMessageDialog(null, ganador, "Ganador", JOptionPane.INFORMATION_MESSAGE);
    }

    private String hacerAgujero(int cantidad) {
        String formattedAsciiArt = "<pre>";
        switch (cantidad) {
            case 0:
                formattedAsciiArt += "┏━━━━━━━━━━┑<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┗━━━━━━━━━━┛";
                break;
            case 1:
                formattedAsciiArt += "┏━━━━━━━━━━┑<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃    ⚫   ▕<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┗━━━━━━━━━━┛";
                break;
            case 2:
                formattedAsciiArt += "┏━━━━━━━━━━┑<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃  ⚫  ⚫ ▕<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┗━━━━━━━━━━┛";
                break;
            case 3:
                formattedAsciiArt += "┏━━━━━━━━━━┑<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃  ⚫ ⚫  ▕<br>";
                formattedAsciiArt += "┃    ⚫    ┃<br>";
                formattedAsciiArt += "┗━━━━━━━━━━┛";
                break;
            case 4:


                formattedAsciiArt += "┏━━━━━━━━━━━┑<br>";
                formattedAsciiArt += "┃ ⚫    ⚫ ▕<br>";
                formattedAsciiArt += "┃         ▕<br>";
                formattedAsciiArt += "┃ ⚫    ⚫ ▕<br>";
                formattedAsciiArt += "┗━━━━━━━━━━━┛";
                break;
            default:
                formattedAsciiArt += "┏━━━━━━━━━━┑<br>";
                formattedAsciiArt += "┃    ⚫    ┃<br>";
                formattedAsciiArt += "┃  ⚫⚫⚫ ▕<br>";
                formattedAsciiArt += "┃    ⚫    ┃<br>";
                formattedAsciiArt += "┗━━━━━━━━━━┛";
                break;
        }
        formattedAsciiArt += "("+(cantidad)+")";
        formattedAsciiArt += "</pre>";
        return formattedAsciiArt;
    }

    private String hacerZona(int cantidad) {
        String formattedAsciiArt = "<pre>";
        switch (cantidad) {
            case 0:
                formattedAsciiArt += "┏━━━━━━━━━━┑<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┗━━━━━━━━━━┛";
                break;

            case 1:
                formattedAsciiArt += "┏━━━━━━━━━━┑<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃    ⚫   ▕<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┗━━━━━━━━━━┛";
                break;
            case 2:
                formattedAsciiArt += "┏━━━━━━━━━━┑<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃  ⚫  ⚫ ▕<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┗━━━━━━━━━━┛";
                break;
            case 3:
                formattedAsciiArt += "┏━━━━━━━━━━┑<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃  ⚫ ⚫  ▕<br>";
                formattedAsciiArt += "┃    ⚫    ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┗━━━━━━━━━━┛";
                break;
            case 4:
                formattedAsciiArt += "┏━━━━━━━━━━━┑<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃ ⚫    ⚫ ▕<br>";
                formattedAsciiArt += "┃         ▕<br>";
                formattedAsciiArt += "┃ ⚫    ⚫ ▕<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┗━━━━━━━━━━━┛";
                break;
            default:
                formattedAsciiArt += "┏━━━━━━━━━━┑<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃    ⚫    ┃<br>";
                formattedAsciiArt += "┃  ⚫⚫⚫ ▕<br>";
                formattedAsciiArt += "┃    ⚫    ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┗━━━━━━━━━━┛";
                break;
        }
        formattedAsciiArt += "("+cantidad+")";
        formattedAsciiArt += "</pre>";
        return formattedAsciiArt;
    }


    @Override
    public void setControlador(Controlador c) {
        this.controlador=c;
    }
}
