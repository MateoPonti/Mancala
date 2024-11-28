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
import java.rmi.RemoteException;
import java.util.ArrayList;

public  class Vista implements IVista, Serializable, IConectado {
    private VistaMenu menu;
    private Controlador controlador;
    private ITipo tipo;



    @Override
    public  void mostrarTablero(ITableroJugador tableroJugador, ITableroJugador tableroOponente, IJugador turnoActual, IJugador nombreJugador) throws IOException {
         if (tipo!=null){
             menu.cerrarMenu();
             tipo.mostrarTablero(tableroJugador,tableroOponente,turnoActual,nombreJugador);}
    }

    @Override
    public   void mostrarGanador(IJugador ganador){
        if (tipo!=null){
            tipo.mostrarGanador(ganador);
        }
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
    }
}
