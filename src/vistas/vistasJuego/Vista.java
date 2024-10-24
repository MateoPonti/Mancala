package vistas.vistasJuego;

import controlador.Controlador;
import modelo.clasesJuego.jugador.IJugador;
import modelo.clasesJuego.tablero.ITableroJugador;
import modelo.clasesJuego.usuario.IUsuario;
import vistas.IConectado;
import vistas.ITipo;
import vistas.IVista;
import vistas.Menu.VistaMenu;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public  class Vista implements IVista, Serializable, IConectado {
    private VistaMenu menu;
    private Controlador controlador;
    private ITipo tipo;











    @Override
    public  void mostrarTablero(ITableroJugador tableroJugador, ITableroJugador tableroOponente, IJugador turnoActual, IJugador nombreJugador) throws IOException {
         if (tipo!=null){tipo.mostrarTablero(tableroJugador,tableroOponente,turnoActual,nombreJugador);}
    }



    @Override
    public   void mostrarGanador(IJugador ganador){
        if (tipo!=null){
            tipo.mostrarGanador(ganador);
            menu.mostrarMenu();
        }
    }






    @Override
    public void inicializar() {
        menu = new VistaMenu(this);
    }

    @Override
    public void setControlador(Controlador c) {
        this.controlador=c;
    }



    @Override
    public void conectarUsuario(String nombre, String contrasenia) {
        if (controlador!= null) {
        menu.setMenuConfig(controlador.conectarUsuario(nombre,contrasenia));
        menu.mostrarMenu();
        }
    }

    @Override
    public ArrayList<IUsuario> mostrarTopRank() {
        return controlador.obtenerRank();
    }

    @Override
    public void jugar(ITipo tipo) {
        this.tipo = tipo;
        tipo.modificarInput(controlador,menu);
        menu.cerrarMenu();
        controlador.inicializarPartida();
    }





}
