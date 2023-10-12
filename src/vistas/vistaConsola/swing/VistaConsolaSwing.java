package vistas.vistaConsola.swing;

import modelo.contenedor.Agujero;
import modelo.contenedor.IContenedor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VistaConsolaSwing {
    private JFrame frame;
    private JPanel principal;





    public VistaConsolaSwing(){
        inicializarVentana();
    }

    private void inicializarVentana() {
        frame=new JFrame("Mancala");
        principal= (JPanel) frame.getContentPane();

        frame.setSize(1200,700);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        ArrayList<IContenedor> zonas= new ArrayList<>();

        ArrayList<IContenedor> agujeros= new ArrayList<>();


        for(int i =0 ; i<12; i++){
            agujeros.add(new Agujero());
        }

        mostrarTablero(agujeros,zonas);
    }


    public void mostrarTablero(ArrayList<IContenedor> agujerosCont, ArrayList<IContenedor> zonas){

        principal.removeAll();
        principal.setBackground(Color.GREEN);




        int panelWidth = principal.getWidth();
        int panelHeight = principal.getHeight();

        int c = 0 ;
        int j = 0;

        principal.setLayout(null);


        int tam= 0;
        int labelWidth = 100;
        int labelHeight = 100;

        for (int i=0; i < agujerosCont.size(); i++) {
            if (i==6){
                j+=tam;
                c=0;
            }
            JLabel l=  new JLabel("<html>" + formatAsciiArt(agujerosCont.get(i).getCantidad()) + "</html>");
            int x = ((panelWidth - labelWidth) / 4 )+c;
            l.setSize(labelWidth,labelHeight);
            int y = ((panelHeight - labelHeight) / 2)-j;
            l.setBounds(x, y, labelWidth, labelHeight);
            principal.add(l);

            tam= l.getHeight();
            c+=l.getWidth();

        }
        principal.revalidate();
        principal.repaint();



    }

    private String formatAsciiArt(int cantidad) {
        // Define tu arte ASCII para diferentes cantidades con saltos de línea HTML
        String formattedAsciiArt = "<pre>";
        switch (cantidad) {
            case 1:
                formattedAsciiArt += "┏━━━━━━━━━━┑<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃    ⚫   ▕<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┗━━━━━━━━━━┛";
                break;
            case 2:
                formattedAsciiArt += "┏━━━━━━━━━━┑<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃  ⚫  ⚫ ▕<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┗━━━━━━━━━━┛";
                break;
            case 3:
                formattedAsciiArt += "┏━━━━━━━━━━┑<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃  ⚫ ⚫  ▕<br>";
                formattedAsciiArt += "┃    ⚫    ┃<br>";
                formattedAsciiArt += "┗━━━━━━━━━━┛";
                break;
            case 4:


                formattedAsciiArt += "┏━━━━━━━━━━━┑<br>";
                formattedAsciiArt += "┃ ⚫    ⚫ ▕<br>";
                formattedAsciiArt += "┃         ▕<br>";
                formattedAsciiArt += "┃ ⚫    ⚫ ▕<br>";
                formattedAsciiArt += "┗━━━━━━━━━━━┛";
                break;
            default:
                formattedAsciiArt += "┏━━━━━━━━━━┑<br>";
                formattedAsciiArt += "┃     ⚫   ┃<br>";
                formattedAsciiArt += "┃   ⚫⚫⚫▕<br>";
                formattedAsciiArt += "┃     ⚫   ┃<br>";
                formattedAsciiArt += "┗━━━━━━━━━━┛";
                break;
        }
        formattedAsciiArt += "("+String.valueOf(cantidad)+")";
        formattedAsciiArt += "</pre>";
        return formattedAsciiArt;
    }



}
