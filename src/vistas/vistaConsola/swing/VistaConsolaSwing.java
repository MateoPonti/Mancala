package vistas.vistaConsola.swing;

import modelo.contenedor.Agujero;
import modelo.contenedor.IContenedor;
import modelo.contenedor.Zona;

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

        zonas.add(new Zona());
        zonas.add(new Zona());

        mostrarTablero(agujeros,zonas);
    }


    public void mostrarTablero(ArrayList<IContenedor> agujerosCont, ArrayList<IContenedor> zonas){

        principal.removeAll();
        principal.setBackground(Color.decode("#3b1b0f"));

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
            JLabel l=  new JLabel("<html>" + hacerAgujero(agujerosCont.get(i).getCantidad()) + "</html>");
            int x = ((panelWidth - labelWidth) / 4 )+c;
            l.setSize(labelWidth,labelHeight);
            int y = ((panelHeight - labelHeight) / 2)-j;
            l.setBounds(x, y, labelWidth, labelHeight);


            tam= l.getHeight();
            c+=l.getWidth();

            principal.add(l);

        }

        JLabel zonaTurno =  new JLabel("<html>" + hacerZona(zonas.get(0).getCantidad()) + "</html>");
        JLabel zonaOponente= new JLabel("<html>" + hacerZona(zonas.get(1).getCantidad()) + "</html>");

        zonaOponente.setSize(300,300);
        zonaTurno.setSize(300,300);


        int x = zonaOponente.getWidth()/2;
        int y = ((panelHeight - zonaOponente.getHeight()) / 2)-50;

        zonaOponente.setBounds(x, y, zonaOponente.getWidth(), zonaOponente.getHeight());

        x=principal.getWidth()-(zonaTurno.getWidth());

        zonaTurno.setBounds(x,y,zonaTurno.getWidth(),zonaTurno.getHeight());


        principal.add(zonaOponente);
        principal.add(zonaTurno);

        principal.revalidate();
        principal.repaint();

    }

    private String hacerAgujero(int cantidad) {
        // Define tu arte ASCII para diferentes cantidades con saltos de línea HTML
        String formattedAsciiArt = "<pre>";
        switch (cantidad) {
            case 0:
                formattedAsciiArt += "┏━━━━━━━━━━┑<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┗━━━━━━━━━━┛";
                break;
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
                formattedAsciiArt += "┃    ⚫    ┃<br>";
                formattedAsciiArt += "┃  ⚫⚫⚫ ▕<br>";
                formattedAsciiArt += "┃    ⚫    ┃<br>";
                formattedAsciiArt += "┗━━━━━━━━━━┛";
                break;
        }
        formattedAsciiArt += "("+String.valueOf(cantidad)+")";
        formattedAsciiArt += "</pre>";
        return formattedAsciiArt;
    }


    private String hacerZona(int cantidad) {
        // Define tu arte ASCII para diferentes cantidades con saltos de línea HTML
        String formattedAsciiArt = "<pre>";
        switch (cantidad) {
            case 0:
                formattedAsciiArt += "┏━━━━━━━━━━┑<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┗━━━━━━━━━━┛";
                break;

            case 1:
                formattedAsciiArt += "┏━━━━━━━━━━┑<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃    ⚫   ▕<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┗━━━━━━━━━━┛";
                break;
            case 2:
                formattedAsciiArt += "┏━━━━━━━━━━┑<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃  ⚫  ⚫ ▕<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┗━━━━━━━━━━┛";
                break;
            case 3:
                formattedAsciiArt += "┏━━━━━━━━━━┑<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃  ⚫ ⚫  ▕<br>";
                formattedAsciiArt += "┃    ⚫    ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┗━━━━━━━━━━┛";
                break;
            case 4:
                formattedAsciiArt += "┏━━━━━━━━━━━┑<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃ ⚫    ⚫ ▕<br>";
                formattedAsciiArt += "┃         ▕<br>";
                formattedAsciiArt += "┃ ⚫    ⚫ ▕<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┗━━━━━━━━━━━┛";
                break;
            default:
                formattedAsciiArt += "┏━━━━━━━━━━┑<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃    ⚫    ┃<br>";
                formattedAsciiArt += "┃  ⚫⚫⚫ ▕<br>";
                formattedAsciiArt += "┃    ⚫    ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┃          ┃<br>";
                formattedAsciiArt += "┗━━━━━━━━━━┛";
                break;
        }
        formattedAsciiArt += "("+String.valueOf(cantidad)+")";
        formattedAsciiArt += "</pre>";
        return formattedAsciiArt;
    }


}
