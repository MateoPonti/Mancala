package vistas.Menu;

import vistas.IConectado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class VistaMenu {



    private IConectado conectado;
    private JFrame frame;







    public VistaMenu(IConectado conectado) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.conectado=conectado;
        frame.setTitle("Mancala");
        Image icono = Toolkit.getDefaultToolkit().getImage("src/vistas/imagenes/Icono.png");
        frame.setIconImage(icono);
        pedirNombre();
    }


    private void pedirNombre(){

        frame.setMinimumSize(new Dimension(500,500));
        frame.setLayout(new BorderLayout());

        JLabel nombre = new JLabel("Nombre");
        JLabel contra = new JLabel("Contraseña");
        JTextField ingresoNombre = new JTextField(5);
        JPasswordField ingresoContra = new JPasswordField(5);
        JButton botonEnvioDatos = new JButton();



        botonEnvioDatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = ingresoNombre.getText().trim().toLowerCase();
                String contra = ingresoContra.getText().trim();

                if ( ! ((nombre.equals("Nombre")  || contra.equals("Contraseña")
                        ||  (nombre.isEmpty() || contra.isEmpty()))))
                {
                    conectado.conectarUsuario(nombre,contra);
                }

            }
        });


        botonEnvioDatos.setText("Enviar");

        JPanel panelDatos = new JPanel() {
            final ImageIcon imagenFondo = new ImageIcon("src/vistas/imagenes/Fondo.png"); // Ruta de la imagen

            // Sobrescribir el método paintComponent para dibujar la imagen
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Obtener el tamaño del panel
                Dimension size = getSize();
                // Dibujar la imagen en todo el panel, ajustando su tamaño al del panel
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



    public  void mostrarMenu(){
        frame.setVisible(true);
        frame.setSize(new Dimension(800,600));
        frame.setMinimumSize(new Dimension(800,600));
        cerrarMenu();

    }





    public  void cerrarMenu(){
        frame.setVisible(false);

    }






}
