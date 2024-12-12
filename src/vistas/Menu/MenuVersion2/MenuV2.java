package vistas.Menu.MenuVersion2;

import vistas.IConectado;
import vistas.Menu.IMenu;
import vistas.vistasJuego.VistaConsolaSwing;
import vistas.vistasJuego.VistaGrafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;

public class MenuV2 implements IMenu {
    private final IConectado conectado;
    private final JFrame frame;
    private final PanelConFondo panelJuego;
    private final JButton botJugar;
    private final JButton botRank;
    private final JLabel nombreJugador;
    private String nombreUsuario;
    private PanelConFondo panelDatos;
    private final JRadioButton radGrafica;
    private final JRadioButton radConsola;



    // metodos publicos

    public MenuV2(IConectado conectado) {
        // definicion
        this.conectado=conectado;
        frame = new JFrame();
        frame.setLocationRelativeTo(null);
        panelJuego=new PanelConFondo("src/vistas/imagenes/Menu/Lobby.jpg");
        panelJuego.setLayout(new BorderLayout());
        panelJuego.setAlignmentX(Component.CENTER_ALIGNMENT);
        botJugar = new JButton();
        botRank = new JButton();
        nombreJugador= new JLabel();
        radGrafica= new JRadioButton("Vista Gráfica");
        radConsola=new JRadioButton("Vista Consola");
        radConsola.setSelected(true);
        ButtonGroup bgSelec  = new ButtonGroup();
        bgSelec.add(radGrafica);
        bgSelec.add(radConsola);
        nombreUsuario= "";

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    conectado.desconectar();
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        iniciarVista();
    }


    private   void iniciarVista(){
        //size
        botJugar.setPreferredSize(new Dimension(100,40));
        botRank.setPreferredSize(new Dimension(100,40));

        //config

        botJugar.setVisible(false);
        botRank.setVisible(false);
        nombreJugador.setVisible(false);
        radConsola.setVisible(false);
        radGrafica.setVisible(false);



        frame.setTitle("Mancala");
        Image icono = Toolkit.getDefaultToolkit().getImage("src/vistas/imagenes/Icono.png");
        frame.setIconImage(icono);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        pedirNombre();

        botJugar.setText("Jugar");
        botRank.setText("Rank");

        botJugar.addActionListener(e -> {
            if (radConsola.isSelected()){
                conectado.jugar(new VistaConsolaSwing());
            }
            else {
                conectado.jugar(new VistaGrafica());
            }
        });

        botRank.addActionListener(e -> {
            try {
                mostrarRank();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        });



    }

    public  void mostrarMenu(){
        frame.setVisible(true);
        configurarVisibilidad(true);
    }

    @Override
    public void desconectar() {

        frame.dispose();
    }




    public void setMenuConfig(String nombreJugador) {
        nombreUsuario = nombreJugador;
        this.nombreJugador.setText("Nickname: " + nombreUsuario);
        this.nombreJugador.setForeground(new Color(255, 230, 128));
        frame.setSize(new Dimension(800, 600));
        frame.setMinimumSize(new Dimension(800, 600));
        panelDatos.setVisible(false);

        botJugar.setVisible(true);
        botRank.setVisible(true);
        this.nombreJugador.setVisible(true);
        radGrafica.setVisible(true);
        radConsola.setVisible(true);

        botJugar.setBackground(new Color(Color.white.getRGB()));
        botRank.setBackground(new Color(Color.white.getRGB()));
        radGrafica.setBackground(new Color(Color.WHITE.getRGB()));
        radConsola.setBackground(new Color(Color.WHITE.getRGB()));
        // Paneles
        JPanel panelBot = new JPanel(new GridBagLayout());
        JPanel panelNombreJugador = new JPanel(new BorderLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        panelNombreJugador.add(this.nombreJugador, BorderLayout.WEST);

        gbc.gridy = 0;
        panelBot.add(botJugar, gbc);
        gbc.gridy = 1;
        panelBot.add(radConsola, gbc);
        gbc.gridy = 2;
        panelBot.add(radGrafica, gbc);
        gbc.gridy = 3;
        panelBot.add(botRank, gbc);

        panelBot.setOpaque(false);
        panelNombreJugador.setOpaque(false);

        panelJuego.add(panelBot, BorderLayout.CENTER);
        panelJuego.add(panelNombreJugador, BorderLayout.NORTH);

        frame.setLayout(new BorderLayout());
        frame.add(panelJuego, BorderLayout.CENTER);
    }


    public    void cerrarMenu(){
        frame.setVisible(false);
    }

    public void mostrarPartidaLLena() {
        mostrarMenu();
        JOptionPane.showMessageDialog(null, " Intente mas tarde , ya hay una partida en juego", "Partida Llena", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarPartidaEspera() {
        mostrarMenu();
        configurarVisibilidad(false);
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
            String nombreS = ingresoNombre.getText().trim();
            String contraS = ingresoContra.getText().trim();

            if (!(nombreS.isEmpty() || contraS.isEmpty()))
            {
                conectado.conectarUsuario(nombreS, contraS);
            }
        });



        botonEnvioDatos.setText("Enviar");

        panelDatos = new PanelConFondo("src/vistas/imagenes/Menu/inicioSesion.jpg");

        panelDatos.setBackground(new Color(255, 255, 224));
        panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));


        nombre.setSize(new Dimension(20,20));
        contra.setSize(new Dimension(20,20));
        ingresoContra.setSize(new Dimension(100,20));
        ingresoNombre.setSize(new Dimension(100,20));
        botonEnvioDatos.setSize(new Dimension(60,60));

        ingresoContra.setMaximumSize(new Dimension(100,20));
        ingresoNombre.setMaximumSize(new Dimension(100,20));


        nombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        contra.setAlignmentX(Component.CENTER_ALIGNMENT);
        ingresoNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonEnvioDatos.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonEnvioDatos.setBackground(Color.white);
        ingresoNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        ingresoContra.setAlignmentX(Component.CENTER_ALIGNMENT);


        panelDatos.add(Box.createVerticalStrut(100));
        panelDatos.add(nombre);
        panelDatos.add(Box.createVerticalStrut(10));
        panelDatos.add(ingresoNombre);
        panelDatos.add(Box.createVerticalStrut(10));
        panelDatos.add(contra);
        panelDatos.add(Box.createVerticalStrut(10));
        panelDatos.add(ingresoContra);
        panelDatos.add(Box.createVerticalStrut(10));

        panelDatos.add(botonEnvioDatos);

        frame.add(panelDatos,BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }

    private void mostrarRank() throws RemoteException {
        new VistaRanking(conectado.mostrarTopRank(),nombreUsuario);

    }


    private  void  configurarVisibilidad(boolean b){
        botJugar.setVisible(b);
        botRank.setVisible(b);
        radConsola.setVisible(b);
        radGrafica.setVisible(b);

    }
}