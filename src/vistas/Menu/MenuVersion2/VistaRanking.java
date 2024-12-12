package vistas.Menu.MenuVersion2;

import modelo.clasesJuego.usuario.IUsuario;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class VistaRanking {
    private JFrame frame;
    private PanelConFondo panelRanking;
    public VistaRanking(ArrayList<IUsuario> ranking,String nombreJugador) {
        frame = new JFrame();
        frame.setTitle("Ranking");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(new Dimension(200,300));
        frame.setLocationRelativeTo(null);


        panelRanking= new PanelConFondo("src/vistas/imagenes/Menu/ranking.jpg");
        panelRanking.setLayout(new BoxLayout(panelRanking, BoxLayout.Y_AXIS));

        frame.add(panelRanking);

        configurarRanking(ranking,nombreJugador);

    }


    private void configurarRanking(ArrayList<IUsuario> ranking, String nombreJugador) {
        for (int i = 0; i < ranking.size(); i++) {
            IUsuario usuario = ranking.get(i);
            JLabel label = new JLabel((i + 1) + ". " + usuario.getNickname() + " - Elo: " + usuario.getElo());
            label.setBorder(new LineBorder(Color.BLACK, 2, true));
            label.setOpaque(true);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new Font("Arial", Font.PLAIN, 16));

            label.setBackground(Color.WHITE);
            if (nombreJugador.equals(usuario.getNickname())){
                label.setForeground(Color.blue);
            }
            panelRanking.add(Box.createVerticalStrut(10));
            panelRanking.add(label);
        }
        frame.repaint();
        frame.revalidate();
    }
}
