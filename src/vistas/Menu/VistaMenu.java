package vistas.Menu;

import modelo.clasesJuego.usuario.IUsuario;
import vistas.IConectado;
import vistas.vistasJuego.VistaConsolaSwing;
import vistas.vistasJuego.VistaGrafica;

import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;


import java.rmi.RemoteException;
import java.util.ArrayList;


public class VistaMenu implements IMenu {
    private final IConectado conectado;
    private final JFrame frame;
    private final JPanel panel;
    private final JButton botJugar;
    private final JButton botRank;
    private final JLabel nombreJugador;
    private String nombreUsuario;
    private JPanel panelDatos;
    private final JTextPane textRank;
    private final JScrollPane scrollpane;
    private final JRadioButton radGrafica;
    private final JRadioButton radConsola;



    // metodos publicos

    public VistaMenu(IConectado conectado) {
        // definicion
        this.conectado=conectado;
        frame = new JFrame();
        panel=new JPanel(new BorderLayout());
        botJugar = new JButton();
        botRank = new JButton();
        nombreJugador= new JLabel();
        textRank = new JTextPane();
        scrollpane = new JScrollPane();
        radGrafica= new JRadioButton("Vista Gráfica");
        radConsola=new JRadioButton("Vista Consola");
        radConsola.setSelected(true);
        ButtonGroup bgSelec  = new ButtonGroup();
        bgSelec.add(radGrafica);
        bgSelec.add(radConsola);
        nombreUsuario= "";

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
        scrollpane.setVisible(false);
        textRank.setEditable(false);
        radConsola.setVisible(false);
        radGrafica.setVisible(false);

        scrollpane.setViewportView(textRank);
        scrollpane.setPreferredSize(new Dimension(100,100));


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
        nombreUsuario=nombreJugador;
        this.nombreJugador.setText("Nickname: "+nombreUsuario);
        this.nombreJugador.setForeground(new Color(255, 255, 128));
        frame.setSize(new Dimension(800,600));
        frame.setMinimumSize(new Dimension(800,600));
        panelDatos.setVisible(false);
        scrollpane.setVisible(false);
        botJugar.setVisible(true);
        botRank.setVisible(true);
        this.nombreJugador.setVisible(true);
        radGrafica.setVisible(true);
        radConsola.setVisible(true);

        JPanel panelBot = new JPanel();
        JPanel panelNombreJugador = new JPanel(new BorderLayout());


        Color color=new Color(108,50,37);
        panel.setBackground( color);
        panelBot.setBackground(color);
        panelNombreJugador.setBackground(color);


        panelNombreJugador.add(this.nombreJugador,BorderLayout.WEST);
        panelBot.add(radConsola);
        panelBot.add(radGrafica);
        panelBot.add(botJugar);
        panelBot.add(botRank);



        panel.add(panelBot,BorderLayout.CENTER);
        panel.add(panelNombreJugador,BorderLayout.NORTH);

        frame.setLayout(new BorderLayout());
        frame.add(panel,BorderLayout.CENTER);
        frame.add(scrollpane,BorderLayout.SOUTH);



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

    private void mostrarRank() throws RemoteException {
        scrollpane.setVisible(true);
        textRank.setVisible(true);
        frame.revalidate();
        frame.repaint();

        ArrayList<IUsuario> usuarios = conectado.mostrarTopRank();
        textRank.setText("");
        int i = 1;
        String cantEspacios = "                           ";

        StyledDocument doc = textRank.getStyledDocument();
        Style styleNick = textRank.addStyle("Nickname", null);
        StyleConstants.setForeground(styleNick, new Color(255, 140, 0));
        try {
            for (IUsuario u : usuarios) {
                String nombreNick = u.getNickname();
                String puesto = i + ") " + nombreNick + cantEspacios + "Victorias: " + u.getVictorias() + " , Derrotas: " + u.getDerrotas() + " , Empates: " + u.getEmpates() + ", Partidas: " + u.getTotalPartidas() + ", Elo: " + u.getElo() + "\n";

                if (nombreNick.equals(this.nombreUsuario)) {
                    doc.insertString(doc.getLength(), puesto, styleNick);
                } else {
                    doc.insertString(doc.getLength(), puesto, null);
                }

                i++;
            }
        } catch (Exception ignored) {
        }
    }


    private  void  configurarVisibilidad(boolean b){
        botJugar.setVisible(b);
        botRank.setVisible(b);
        radConsola.setVisible(b);
        radGrafica.setVisible(b);

    }
}
