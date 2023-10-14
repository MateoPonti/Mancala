package controlador;

import java.util.ArrayList;

public class Observador {
    private ArrayList<Observable> observables;


    public void  agregar(Observable observable){
        this.observables.add(observable);
    }

    public void actualizar(Notificacion n){
        for (Observable o:observables){
            o.actualizar(n);
        }
    }

    protected void eliminar(Observable controlador){
        this.observables.remove(controlador);

    }
}
