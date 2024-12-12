package vistas.Menu.menu2;

import javax.swing.*;
import java.awt.*;



public class PanelConFondo extends JPanel {
    private Image imagenFondo;

    public PanelConFondo(String rutaImagen) {
        ImageIcon icono = new ImageIcon(rutaImagen);
        this.imagenFondo = icono.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenFondo != null) {
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}