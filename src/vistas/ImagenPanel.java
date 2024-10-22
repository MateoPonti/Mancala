package  vistas;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class ImagenPanel extends JPanel {
    private BufferedImage imagen;
    private int x;
    private int y;

    public ImagenPanel() {
        this.imagen = null;
        this.x = 0;
        this.y = 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagen != null) {
            g.drawImage(imagen, x, y, this);
        }
    }

    public void dibujarImagen(BufferedImage nuevaImagen) {
        this.imagen = nuevaImagen;
        repaint();
    }
}