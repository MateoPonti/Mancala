package vistas.vistasJuego;

import controlador.Controlador;
import modelo.clasesJuego.jugador.IJugador;
import modelo.clasesJuego.tablero.ITableroJugador;
import vistas.IConectado;
import vistas.ITipo;
import vistas.IVista;
import vistas.Menu.VistaMenu;

import javax.swing.*;
import java.io.Serializable;

public  class Vista implements IVista, Serializable, IConectado {
    private VistaMenu menu;
    private Controlador controlador;
    private ITipo tipo;







    public Vista(){
       menu = new VistaMenu(this);

    }



    @Override
    public  void mostrarTablero(ITableroJugador tableroJugador, ITableroJugador tableroOponente, IJugador turnoActual, IJugador nombreJugador){
         if (tipo!=null){tipo.mostrarTablero(tableroJugador,tableroOponente,turnoActual,nombreJugador);}
    };



    @Override
    public   void mostrarGanador(IJugador ganador){
        if (tipo!=null){
            tipo.mostrarGanador(ganador);
            menu.mostrarMenu();
        }
    };



    @Override
    public  void mostrarInicializarPartida(){
        menu.mostrarMenu();
        tipo = new VistaConsolaSwing();
    };


    @Override
    public void setControlador(Controlador c) {
        this.controlador=c;
    }



    @Override
    public void conectarUsuario(String nombre, String contrasenia) {
        this.controlador.conectarUsuario(nombre,contrasenia);
    }

    @Override
    public String mostrarTopRank() {
        return "";
    }

    @Override
    public void Jugar() {

    }





}
