package modelo.contenedor;

import modelo.haba.IHaba;

import java.util.ArrayList;

public interface IContenedor {
    public TipoContenedor getTipo();
    public ArrayList<IHaba> getHabas();

    public int getCantidad();
}
