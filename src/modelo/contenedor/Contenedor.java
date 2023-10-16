package modelo.contenedor;

import modelo.haba.Haba;
import modelo.haba.IHaba;

import java.util.ArrayList;

public class Contenedor implements IContenedor {
    private ArrayList<IHaba> habas;
    private TipoContenedor tipo;

    public Contenedor(TipoContenedor tipo) {
        this.tipo=tipo;
        habas=new ArrayList<>();
    }

    public void agregar(IHaba haba){
        this.habas.add(haba);
    }
    public void agregar(ArrayList<IHaba> habas){
        this.habas.addAll(habas);
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

    public ArrayList<IHaba> getHabas(){
        return habas;
    }



    public ArrayList<IHaba> sacarHabas() {
        ArrayList<IHaba> arrayHabas=new ArrayList<>();
        for(IHaba haba :habas){
            Haba h=new Haba();
            h.setColor(haba.getColor());
            arrayHabas.add(h);
        }
        habas.clear();
        return arrayHabas;
    }

}