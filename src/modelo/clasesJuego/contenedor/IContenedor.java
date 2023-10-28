package modelo.clasesJuego.contenedor;

import modelo.clasesJuego.haba.IHaba;

import java.util.ArrayList;

public interface IContenedor {
     ArrayList<IHaba> getHabas();

     int getCantidad();
}
