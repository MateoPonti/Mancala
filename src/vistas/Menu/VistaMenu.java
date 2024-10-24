package vistas.Menu;

import modelo.clasesJuego.usuario.IUsuario;
import vistas.IConectado;
import vistas.vistasJuego.IMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class VistaMenu implements IMenu {
    private final IConectado conectado;
    private final JFrame frame;
    private final JButton botJugar;
    private final JButton botRank;
    private final JLabel nombreJugador;
    private JPanel panelDatos;
    private final JTextArea textRank;


    // metodos publicos

    public VistaMenu(IConectado conectado) {
        // definicion
        this.conectado=conectado;
        frame = new JFrame();
        botJugar = new JButton();
        botRank = new JButton();
        nombreJugador= new JLabel();
        textRank= new JTextArea(30,30);


        //size
        botJugar.setPreferredSize(new Dimension(100,40));
        botRank.setPreferredSize(new Dimension(100,40));


        //config

        botJugar.setVisible(false);
        botRank.setVisible(false);
        textRank.setVisible(false);
        nombreJugador.setVisible(false);
        textRank.setEditable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Mancala");
        Image icono = Toolkit.getDefaultToolkit().getImage("src/vistas/imagenes/Icono.png");
        frame.setIconImage(icono);
        pedirNombre();



        botJugar.setText("Jugar");
        botRank.setText("Rank");

        botJugar.addActionListener(e -> conectado.Jugar());



        botRank.addActionListener(e -> mostrarRank());


    }



    public  void mostrarMenu(){
        frame.setVisible(true);

    }


    public void setMenuConfig(String nombreJugador) {
        this.nombreJugador.setText("Nickname: "+nombreJugador);
        this.nombreJugador.setForeground(Color.white);
        frame.setSize(new Dimension(800,600));
        frame.setMinimumSize(new Dimension(800,600));
        panelDatos.setVisible(false);
        botJugar.setVisible(true);
        botRank.setVisible(true);
        this.nombreJugador.setVisible(true);




        JPanel panel= new JPanel();
        panel.setBackground( new Color(108,50,37));
        panel.add(this.nombreJugador);
        panel.add(botJugar);
        panel.add(botRank);
        frame.setLayout(new BorderLayout());
        frame.add(panel,BorderLayout.CENTER);
        frame.add(textRank,BorderLayout.SOUTH);

    }

    public    void cerrarMenu(){
        frame.setVisible(false);
    }



    // metodos privados




    private void pedirNombre(){

        frame.setMinimumSize(new Dimension(500,500));
        frame.setLayout(new BorderLayout());

        JLabel nombre = new JLabel("Nombre");
        JLabel contra = new JLabel("Contraseña");
        JTextField ingresoNombre = new JTextField(5);
        JPasswordField ingresoContra = new JPasswordField(5);
        JButton botonEnvioDatos = new JButton();

        botonEnvioDatos.addActionListener(e -> {
            String nombre1 = ingresoNombre.getText().trim().toLowerCase();
            String contra1 = ingresoContra.getText().trim();

            if (!(nombre1.isEmpty() || contra1.isEmpty()))
            {
                conectado.conectarUsuario(nombre1, contra1);
            }
        });



        botonEnvioDatos.setText("Enviar");

        panelDatos = new JPanel() {
            final ImageIcon imagenFondo = new ImageIcon("src/vistas/imagenes/Fondo.png"); // Ruta de la imagen

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension size = getSize();
                g.drawImage(imagenFondo.getImage(), 0, 0, size.width, size.height, this);
            }
        };
        panelDatos.setBackground(new Color(255, 255, 224));
        panelDatos.setLayout(new FlowLayout());

        nombre.setSize(new Dimension(20,20));
        contra.setSize(new Dimension(20,20));
        ingresoContra.setSize(new Dimension(100,20));
        ingresoNombre.setSize(new Dimension(100,20));
        botonEnvioDatos.setSize(new Dimension(60,60));

        panelDatos.add(nombre);
        panelDatos.add(ingresoNombre);
        panelDatos.add(contra);
        panelDatos.add(ingresoContra);
        panelDatos.add(botonEnvioDatos);

        frame.add(panelDatos,BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }

    private void mostrarRank() {
        textRank.setVisible(true);
        ArrayList<IUsuario> usuarios = conectado.mostrarTopRank();
        textRank.setText("");
        int i  = 1;
        try {
            for (IUsuario u : usuarios){
                String puesto  = i + ") " +u.getNickname()  + ",Victorias: "+u.getVictorias()+ " ,Derrotas: "+u.getDerrotas()+ ",Empates: "+ u.getEmpates();
                textRank.append(puesto);
                i++;
            }
        } catch (Exception ignored) {

        }

    }


}
