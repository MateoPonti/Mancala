package modelo.clasesJuego.contenedor;

import modelo.clasesJuego.haba.Haba;
import modelo.clasesJuego.haba.IHaba;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Contenedor implements IContenedor, Serializable {
    private ArrayList<IHaba> habas;

    public Contenedor() {
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