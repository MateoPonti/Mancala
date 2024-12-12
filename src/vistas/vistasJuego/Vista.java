package vistas.vistasJuego;

import controlador.Controlador;
import modelo.clasesJuego.jugador.IJugador;
import modelo.clasesJuego.tablero.ITableroJugador;
import modelo.clasesJuego.usuario.IUsuario;
import vistas.IConectado;
import vistas.ITipo;
import vistas.IVista;
import vistas.Menu.MenuVersion2.MenuV2;
import vistas.Menu.VistaMenu;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

public  class Vista implements IVista, Serializable, IConectado {
    private MenuV2 menu;
    private Controlador controlador;
    private ITipo tipo;



    @Override
    public  void mostrarTablero(ITableroJugador tableroJugador, ITableroJugador tableroOponente, IJugador turnoActual, IJugador nombreJugador) throws IOException {
        menu.cerrarMenu();
        tipo.mostrarTablero(tableroJugador,tableroOponente,turnoActual,nombreJugador);

    }


    @Override
    public   void mostrarGanador(IJugador ganador){
        tipo.mostrarGanador(ganador);
    }

    @Override
    public void mostrarPartidaLLena() throws RemoteException {
        menu.mostrarPartidaLLena();
    }

    @Override
    public void mostrarPartidaEspera() throws RemoteException {
        menu.mostrarPartidaEspera();
    }

    @Override
    public void abandonarPartida() {
        tipo.mostrarAbandonar();
    }



    @Override
    public void inicializar() {
        if (controlador!=null){
        menu = new MenuV2(this);
        tipo = new VistaConsolaSwing();
        tipo.asignarAbandono(this);
        tipo.modificarInput(controlador,menu);
        }
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
    public ArrayList<IUsuario> mostrarTopRank() throws RemoteException {
        return controlador.obtenerRank();
    }

    @Override
    public void jugar(ITipo tipo) {
        this.tipo = tipo;
        tipo.asignarAbandono(this);
        tipo.modificarInput(controlador,menu);
        controlador.inicializarPartida();
    }


    public void desconectar() throws RemoteException {
        controlador.desconectarJugador();
        menu.desconectar();
        tipo.desconectar();
        controlador=null;
        menu=null;
        tipo=null;

    }
}
