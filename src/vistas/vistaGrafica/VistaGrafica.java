package vistas.vistaGrafica;

import controlador.Controlador;
import modelo.clasesJuego.jugador.IJugador;
import modelo.clasesJuego.tablero.ITableroJugador;
import vistas.IVista;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;



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

