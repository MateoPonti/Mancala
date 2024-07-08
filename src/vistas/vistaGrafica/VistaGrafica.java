package vistas.vistaGrafica;

import controlador.Controlador;
import modelo.clasesJuego.contenedor.IContenedor;
import modelo.clasesJuego.jugador.IJugador;
import modelo.clasesJuego.tablero.ITableroJugador;
import vistas.IVista;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/*
public class VistaGrafica implements IVista, Serializable {
        private JFrame frame;
        private JPanel principal;
        private Controlador controlador;



        public void inicializar() {
            frame=new JFrame("Mancala");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pedirNombre();
        }

        private void pedirNombre() {
            frame.setSize(400,300);
            frame.setVisible(true);
            principal= (JPanel) frame.getContentPane();
            principal.setLayout(null);

            JButton enviaBut= new JButton("Enviar");

            enviaBut.setSize(200,20);
            enviaBut.setBounds(50,190,enviaBut.getWidth(),enviaBut.getHeight());


            principal.setBackground(Color.blue);
            JTextField ingresoNombre = new JTextField();
            ingresoNombre.setText("Nombre");
            ingresoNombre.setSize(100,20);
            ingresoNombre.setBounds(50,100,ingresoNombre.getWidth(),ingresoNombre.getHeight());

            JTextField ingresoContra = new JTextField();
            ingresoContra.setText("Contraseña");
            ingresoContra.setSize(100,20);
            ingresoContra.setBounds(50,140,ingresoNombre.getWidth(),ingresoNombre.getHeight());


            enviaBut.addActionListener(e -> {
                String nombre = ingresoNombre.getText();
                String contrasenia = ingresoContra.getText();
                if (!nombre.isEmpty() && !contrasenia.isEmpty()) {
                    controlador.conectarUsuario(nombre,contrasenia);
                    enviaBut.setVisible(false);  }
            });

            principal.add(ingresoNombre);
            principal.add(ingresoContra);
            principal.add(enviaBut);

            principal.revalidate();
            principal.repaint();


        }



        public void mostrarTablero(ITableroJugador tableroJugador, ITableroJugador tableroOponente, IJugador turnoActual, IJugador nombreJugador){
            frame.setSize(820,600);
            principal.setSize(820,600);

            ArrayList<IContenedor> zonas= new ArrayList<>();
            zonas.add(tableroJugador.getZona());
            zonas.add(tableroOponente.getZona());

            ArrayList<IContenedor> tableros= tableroJugador.getAgujeros();
            tableros.addAll(tableroOponente.getAgujerosVuelta());




            String color = "#3b1b0f";
            principal.removeAll();
            principal.setBackground(Color.decode(color));

            int c = 0 ;
            int j = 0;

            int tam= 0;
            int labelWidth = 100;
            int labelHeight = 100;

            int panelWidth = principal.getWidth();
            int panelHeight = principal.getHeight();


            int x;
            int y;

            // agregar Los Agujeros
            for (int i = 0; i < tableros.size(); i++) {
                if (i==6){
                    j+=tam;
                    c=0;
                }
                //PanelConImagen p=  new PanelConImagen("src/recursos/Agujero.png",100,100,color);

                x = ( ((panelWidth - labelWidth) / 4)-80 )+c;
               // p.setSize(labelWidth,labelHeight);
                y = ((panelHeight - labelHeight) / 2)-j;
                //p.setBounds(x, y, labelWidth, labelHeight);

                tam= p.getHeight();
                c+=p.getWidth();

                principal.add(p);
            }
            //Agrega las Zonas
            PanelConImagen zonaTurno = new PanelConImagen("src/recursos/Zona.png",300,300,color);
            PanelConImagen zonaOponente= new PanelConImagen("src/recursos/Zona.png",300,300,color);



            x = zonaOponente.getWidth()/2 -150;
            y = ((panelHeight - zonaOponente.getHeight()) / 2)-50;

            zonaOponente.setBounds(x, y, zonaOponente.getWidth(), zonaOponente.getHeight());

            x=(principal.getWidth())-115;

            zonaTurno.setBounds(x,y,zonaTurno.getWidth(),zonaTurno.getHeight());

            principal.add(zonaOponente);
            principal.add(zonaTurno);

            JLabel turno  = new JLabel("Turno: "+turnoActual.getNombre());
            JLabel jugadorVista  = new JLabel( nombreJugador.getNombre());
            turno.setSize(200,320);
            jugadorVista.setSize(100,20);
            x=(principal.getWidth()/2)-100;
            y=(principal.getHeight()/4)-200;
            turno.setBounds(x,y,turno.getWidth(),turno.getHeight());

            x=(principal.getWidth()/2)-190;
            y=(principal.getHeight()/4)+150;

            jugadorVista.setBounds(x,y,turno.getWidth(),turno.getHeight());
            principal.add(turno);
            principal.add(jugadorVista);

            principal.revalidate();
            principal.repaint();

        }

        @Override
        public void mostrarInicializarPartida() {
            principal.removeAll();
            frame.setSize(600,300);
            principal.setSize(600,300);
            principal.setBackground(Color.blue);

            JButton arrancarPartida= new JButton("Inicializar Partida");
            arrancarPartida.setSize(200,20);

            int x=(principal.getWidth()/4);
            int y=(principal.getHeight()/2);
            arrancarPartida.setBounds(x,y,arrancarPartida.getWidth(),arrancarPartida.getHeight());
            arrancarPartida.addActionListener(e -> {
                controlador.inicializarPartida();
            });
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



 */
