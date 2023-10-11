package principal;


import modelo.contenedor.Agujero;
import modelo.contenedor.Contenedor;
import modelo.contenedor.IContenedor;
import modelo.contenedor.Zona;
import modelo.haba.Haba;
import vistas.vistaGrafica.VentanaPrincipal;

import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
       VentanaPrincipal v= new VentanaPrincipal();

        ArrayList<IContenedor> zonas= new ArrayList<>();

        ArrayList<IContenedor> agujeros= new ArrayList<>();


        for(int i =0 ; i<12; i++){
            agujeros.add(new Agujero());
        }



        zonas.add(new Zona());
        zonas.add(new Zona());



    }
}
