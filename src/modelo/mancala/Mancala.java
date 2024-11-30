package modelo.mancala;

import ar.edu.unlu.rmimvc.observer.ObservableRemoto;
import controlador.Notificacion;
import controlador.Notificador;
import modelo.clasesJuego.serializacion.SerializadorPartidas;
import modelo.clasesJuego.serializacion.SerializadorUsuarios;
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

    private SerializadorUsuarios serializadorUsuarios;
    private SerializadorPartidas serializadorPartidas;



    public static Mancala getInstancia(){
        if (instancia==null){
            instancia= new Mancala();
        }
        return instancia;
    }

    private Mancala(){
        usuarios=new ArrayList<>();
        preparados=new ArrayList<>();
        serializadorUsuarios=new SerializadorUsuarios();
        serializadorUsuarios.inicializar();
        serializadorPartidas= new SerializadorPartidas();
        serializadorPartidas.inicializar();
    }



    @Override
    public ArrayList<IUsuario> obtenerRank() {
       return serializadorUsuarios.obtenerRank();
    }

    @Override
    public void guardarCambios() {
        serializadorPartidas.escribirPartidas();
        serializadorUsuarios.escribirUsuarios();
    }


    public IUsuario conectarJugador(String nombre,String contra) throws RemoteException {
        Usuario nuevoJugador= serializadorUsuarios.agregarUsuario(nombre, contra);
        usuarios.add(nuevoJugador);
        return nuevoJugador;

    }

    public void inicializarPartida(IUsuario jugador) throws RemoteException {
        ArrayList<IUsuario> jugadores = new ArrayList<>();
        jugadores.add(jugador);

        // Verifica si el jugador puede unirse a la partida
        if (partida == null || partida.isFinalizado() || estaJugando(jugador)) {
            // Inicializa la lista de jugadores preparados si no existe
            if (preparados == null) {
                preparados = new ArrayList<>();
            }

            // Agrega al jugador a la lista de preparados
            agregarJugadorPreparados(jugador);

            // Si los jugadores están listos, inicia la partida
            if (isPreparados()) {
                inicializarPartida(); // Método para configurar la partida
                notificarObservadores(new Notificador(Notificacion.MOSTRARTABLEROS, preparados));
            } else {
                // Notifica que la partida está esperando más jugadores
                notificarObservadores(new Notificador(Notificacion.PARTIDAESPERA, jugadores));
            }
        } else {
            // Notifica que la partida está llena o en progreso
            notificarObservadores(new Notificador(Notificacion.PARTIDALLENA, jugadores));
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
        Usuario jugador1 = encontrarUsuario(preparados.get(0));
        Usuario jugador2= encontrarUsuario(preparados.get(1));
        try{
        if (ganador.esValido()){
        if (ganador.equals(preparados.get(0))){
            jugador1.agregarVictoria();
            jugador2.agregarDerrota();
        }
        else{
            jugador1.agregarDerrota();
            jugador2.agregarVictoria();
        } }
        else {
         jugador1.agregarEmpate();
         jugador2.agregarEmpate();
         }
        serializadorUsuarios.actualizarUsuario(jugador1);
        serializadorUsuarios.actualizarUsuario(jugador2);
        } catch (Exception ignored) {
        }

    }

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
        partida = serializadorPartidas.buscarPartida(preparados);

    }


    private boolean isPreparados(){
        return preparados.size()==maxJugadores ;
    }

    private boolean agregarJugadorPreparados(IUsuario usuario){
        if ( ! estaJugando(usuario)){
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
            return true;
           }
          }
       }
       return false;
    }


    public void  desconectarJugador(IUsuario j) throws  RemoteException{
        guardarPartida(j);
        for(Usuario u:usuarios){
            if (u.equals(j)){
                usuarios.remove(u);
                break;
            }
        }
        preparados=null;
    }

    private void guardarPartida(IUsuario j) throws  RemoteException {
        if  (  (partida != null) && (! partida.isFinalizado()) && (estaJugando(j))){
            serializadorPartidas.guardarPartida(partida);
            partida=null;
            preparados.remove(j);
            notificarObservadores(new Notificador(Notificacion.JUGADORSEFUE,preparados));
        }

    }



}
