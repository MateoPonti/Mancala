package controlador;

import modelo.clasesJuego.usuario.IUsuario;

import java.util.ArrayList;

public class Notificador {
    private Notificacion notificacion;
    private ArrayList<IUsuario> jugadoresAfectados;


    public Notificador(Notificacion notificacion, ArrayList<IUsuario> jugadoresAfectados) {
        this.notificacion = notificacion;
        this.jugadoresAfectados = jugadoresAfectados;
    }

    public boolean esAfectado(IUsuario usuario){
        for(IUsuario u : jugadoresAfectados){
            if (u.equals(usuario)){
                return true;
            }
        }
        return false;
    }


    public Notificacion getNotificacion() {
        return notificacion;
    }
}
