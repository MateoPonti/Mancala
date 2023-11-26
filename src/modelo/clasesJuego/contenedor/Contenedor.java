package modelo.clasesJuego.contenedor;
import java.io.Serializable;

public abstract class Contenedor implements IContenedor, Serializable {
    private int habas;


    public void agregar(){
        this.habas++;
    }
    public void agregar(int cantidad){
        this.habas+=cantidad;
    }





    public boolean estaVacio(){
        return habas==0;
    }


    public int getHabas(){
        return habas;
    }



    public int sacarHabas() {
        int habas = this.habas;
        this.habas=0;
        return habas;
    }

}