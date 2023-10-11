package vistas.vistaGrafica;

import javax.swing.*;
import java.awt.*;

public class Login {

    private JFrame frame;



    private void inicializarVentana() {
        frame=new JFrame("Login");
        frame.setSize(new Dimension(600,600));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



}

