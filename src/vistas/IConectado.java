package vistas;

import modelo.clasesJuego.usuario.IUsuario;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IConectado {


     void conectarUsuario(String nombre,String contrasenia);
     ArrayList<IUsuario> mostrarTopRank() throws RemoteException;
     void jugar(ITipo tipo);


    void desconectar() throws  RemoteException;
}
