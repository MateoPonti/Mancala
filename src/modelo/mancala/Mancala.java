package modelo.mancala;

import ar.edu.unlu.rmimvc.observer.ObservableRemoto;
import controlador.Notificacion;
import controlador.Notificador;
import modelo.clasesJuego.serializacion.administrador.AdministradorUsuarios;
import modelo.clasesJuego.jugador.IJugador;
import modelo.clasesJuego.partida.Partida;
import modelo.clasesJuego.tablero.ITableroJugador;
import modelo.clasesJuego.usuario.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Mancala extends ObservableRemoto implements IMancala{
    private final ArrayList<Usuario> usuarios;

    private ArrayList<IUsuario> preparados;
    private static final int maxJugadores=2;

    private Partida partida;

    private static Mancala instancia;

    private  AdministradorUsuarios administrador;



    public static Mancala getInstancia(){
        if (instancia==null){
            instancia= new Mancala();
        }
        return instancia;
    }

    private Mancala(){
        usuarios=new ArrayList<>();
        preparados=new ArrayList<>();
    }


    public void  desconectarJugador(IUsuario j) throws  RemoteException{
        for(Usuario u:usuarios){
            if (u.equals(j)){
                usuarios.remove(u);
                break;
            }

        }

    }

    @Override
    public ArrayList<IUsuario> obtenerRank() {
        //return AdministradorUsuarios.obtenerRank();
    }


    public IUsuario conectarJugador(String nombre,String contra) throws RemoteException {
        //Usuario nuevoJugador= administrador.buscarUsuario(nombre,contra);
        usuarios.add(nuevoJugador);
        return nuevoJugador;

    }


    public void inicializarPartida(IUsuario jugador) throws RemoteException {
        ArrayList<IUsuario> jugadores= new ArrayList<>();
        jugadores.add(jugador);


        // si la partida no esta iniciada
        if ( (partida==null  || partida.isFinalizado()) && estaJugando(jugador)){
         {
         if(preparados == null) {
             assert partida != null;
             if (partida.isFinalizado()) {
                 preparados = new ArrayList<>();
             }
         }
         agregarJugadorPreparados(jugador);
         if (isPreparados()){
            inicializarPartida();
            notificarObservadores(new Notificador(Notificacion.MOSTRARTABLEROS,preparados ));
         }
         else {
             notificarObservadores(new Notificador(Notificacion.PARTIDAESPERA,jugadores ));}
         }}
        // partidaIniciada
        else {
        notificarObservadores(new Notificador(Notificacion.PARTIDALLENA,jugadores ));
        }
    }




    public void hacerJugada(String pos, IUsuario jugador) throws RemoteException{
        try {
            int posInt= Integer.parseInt(pos.trim());
            hacerJugada(posInt, jugador);
        }
        catch (Exception ignored){
        }

    }

    public void hacerJugada(int pos, IUsuario jugador) throws  RemoteException{
        Notificacion resultado = partida.hacerJugada(pos,jugador);

        if (resultado!=Notificacion.POSICIONINVALIDA  && resultado!=null){
            notificarObservadores(new Notificador(Notificacion.MOSTRARTABLEROS,preparados));
        }
        if (resultado==Notificacion.FINALIZOJUEGO){
            IJugador ganador= partida.getGanador();
            asignarPuntos(ganador);
            notificarObservadores(new Notificador(resultado,preparados));
            preparados=null;
        }

    }


    private void  asignarPuntos(IJugador ganador){
        if (ganador.esValido()){
        if (ganador.equals(preparados.get(0))){
            Usuario usuarioGanador = encontrarUsuario(preparados.get(0));
            Usuario usuarioPerdedor = encontrarUsuario(preparados.get(1));
            assert usuarioGanador != null;
            usuarioGanador.agregarVictoria();
            assert usuarioPerdedor != null;
            usuarioPerdedor.agregarDerrota();
        }
        else{
            Usuario usuarioGanador = encontrarUsuario(preparados.get(1));
            Usuario usuarioPerdedor = encontrarUsuario(preparados.get(0));
            assert usuarioGanador != null;
            usuarioGanador.agregarVictoria();
            assert usuarioPerdedor != null;
            usuarioPerdedor.agregarDerrota();
        } }
            else {
        Usuario jugador1 = encontrarUsuario(preparados.get(1));
        Usuario jugador2 = encontrarUsuario(preparados.get(0));
        assert jugador1 != null;
        jugador1.agregarEmpate();
        assert jugador2 != null;
        jugador2.agregarEmpate();
    } }

    public ITableroJugador getTableroTurno(IUsuario jugador) throws  RemoteException {
        if (partida!=null )  {return partida.getTableroJugador(jugador); }
        return null;
    }
    public ITableroJugador getTableroOponente(IUsuario jugador) throws  RemoteException {
        if (partida!=null )  {return partida.getTableroOponente(jugador); }
        return null;
    }

    public IJugador getGanador() throws  RemoteException{
        if (partida!=null && partida.isFinalizado()){return partida.getGanador();}
        return null;
    }

    @Override
    public IJugador getTurnoActual() throws RemoteException {
        return partida.getTurnoActual();
    }

    @Override
    public IJugador getJugador(IUsuario usuario) throws RemoteException {
        return partida.getJugador(usuario);
    }


    private void inicializarPartida() throws RemoteException {
        partida= new Partida(preparados);

    }


    private boolean isPreparados(){
        return preparados.size()==maxJugadores ;
    }

    private boolean agregarJugadorPreparados(IUsuario usuario){
        if (estaJugando(usuario)){
        preparados.add(usuario);
        return true;
        }
        return false;

    }

    private Usuario encontrarUsuario(IUsuario j){
        for (Usuario u:usuarios){
            if (j.equals(u)){
                return u;
            }
        }
        return null;
    }

    private boolean estaJugando(IUsuario jugador){
        if (preparados!=null){
          for (IUsuario j: preparados)  {
            if (jugador.equals(j)){
            return false;
           }
          }
       }
       return true;
    }



}
