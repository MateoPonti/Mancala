package vistas.vistaConsola.swing;

import controlador.Controlador;
import modelo.contenedor.Agujero;
import modelo.contenedor.IContenedor;
import modelo.contenedor.Zona;
import vistas.IVista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VistaConsolaSwing implements IVista {
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
        switch (texto){
            case "":
                return "Jugador Anonimo";
            default:
                return texto;
        }

    }


    public void pedirJugada(){





    }



    public void mostrarTablero(ArrayList<IContenedor> tablero){

        ArrayList<IContenedor> zonas= new ArrayList<>();
        zonas.add(tablero.get(tablero.size()-2));
        zonas.add(tablero.get(tablero.size()-1));

        principal.removeAll();
        principal.setBackground(Color.decode("#3b1b0f"));

        int panelWidth = principal.getWidth();
        int panelHeight = principal.getHeight();

        int c = 0 ;
        int j = 0;


        int tam= 0;
        int labelWidth = 100;
        int labelHeight = 100;

        for (int i=0; i < tablero.size()-2; i++) {
            if (i==6){
                j+=tam;
                c=0;
            }
            JLabel l=  new JLabel("<html>" + hacerAgujero(tablero.get(i).getCantidad()) + "</html>");
            int x = ((panelWidth - labelWidth) / 4 )+c;
            l.setSize(labelWidth,labelHeight);
            int y = ((panelHeight - labelHeight) / 2)-j;
            l.setBounds(x, y, labelWidth, labelHeight);


            tam= l.getHeight();
            c+=l.getWidth();

            principal.add(l);

        }

        JLabel zonaTurno =  new JLabel("<html>" + hacerZona(zonas.get(0).getCantidad()) + "</html>");
        JLabel zonaOponente= new JLabel("<html>" + hacerZona(zonas.get(1).getCantidad()) + "</html>");

        zonaOponente.setSize(300,300);
        zonaTurno.setSize(300,300);


        int x = zonaOponente.getWidth()/2;
        int y = ((panelHeight - zonaOponente.getHeight()) / 2)-50;

        zonaOponente.setBounds(x, y, zonaOponente.getWidth(), zonaOponente.getHeight());

        x=principal.getWidth()-(zonaTurno.getWidth());

        zonaTurno.setBounds(x,y,zonaTurno.getWidth(),zonaTurno.getHeight());


        principal.add(zonaOponente);
        principal.add(zonaTurno);

        principal.revalidate();
        principal.repaint();

    }
    private String hacerAgujero(int cantidad) {
        // Define tu arte ASCII para diferentes cantidades con saltos de línea HTML
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
        formattedAsciiArt += "("+String.valueOf(cantidad)+")";
        formattedAsciiArt += "</pre>";
        return formattedAsciiArt;
    }

    private String hacerZona(int cantidad) {
        // Define tu arte ASCII para diferentes cantidades con saltos de línea HTML
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
        formattedAsciiArt += "("+String.valueOf(cantidad)+")";
        formattedAsciiArt += "</pre>";
        return formattedAsciiArt;
    }


    @Override
    public void setControlador(Controlador c) {
        this.controlador=c;
    }
}
