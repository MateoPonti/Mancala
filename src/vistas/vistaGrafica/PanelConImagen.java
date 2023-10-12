package vistas.vistaGrafica;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class PanelConImagen extends JPanel {
    private BufferedImage imagenDeFondo;
    private int newWidth;
    private int newHeight;

    public PanelConImagen(String rutaImagen,int w,int h) {
        try {
            imagenDeFondo = ImageIO.read(new File(rutaImagen));
            this.newHeight=h;
            this.newWidth=w;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenDeFondo != null) {

            BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = scaledImage.createGraphics();

            // Ajusta la escala de la imagen
            g2d.drawImage(imagenDeFondo, 0, 0, newWidth, newHeight, null);

            g2d.dispose();

            // Dibuja la imagen escalada
            g.drawImage(scaledImage, 0, 0, this);
        }
    }}