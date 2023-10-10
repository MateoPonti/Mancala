package modelo.contenedor;

import modelo.haba.Haba;
import modelo.haba.IHaba;

import java.util.ArrayList;

public class Contenedor implements IContenedor {
    private ArrayList<Haba> habas;
    private TipoContenedor tipo;

    public Contenedor(TipoContenedor tipo) {
        this.tipo=tipo;
        habas=new ArrayList<>();
    }

    public void agregar(Haba haba){
        this.habas.add(haba);
    }



    public int getCantidad(){
        return habas.size();
    }

    public boolean estaVacio(){
        return getCantidad()==0;
    }

    public TipoContenedor getTipo() {
        return tipo;
    }

    protected ArrayList<Haba> getHabas(){
        return habas;
    }


    @Override
    public ArrayList<IHaba> obtenerHabas() {
        return null;
    }

}