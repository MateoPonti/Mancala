package modelo.clasesJuego.serializacion;

import modelo.clasesJuego.partida.Partida;
import modelo.clasesJuego.serializacion.dominio.AdministradorPartidas;
import modelo.clasesJuego.serializacion.servicios.Serializador;
import modelo.clasesJuego.usuario.Usuario;


public class SerializadorPartidas {
    private static Serializador serializadorPartida;
    private static AdministradorPartidas partidas;

    public SerializadorPartidas() {
        partidas = AdministradorPartidas.getInstancia();
        serializadorPartida= new Serializador("Partidas.dat");
    }


    public void inicializar() {
        cargarPartidas();
    }
    public Partida buscarPartida(Usuario usuario, Usuario usuario2) {
        Partida partida = null;
        for (int i = 0 ; i<partidas.getTam(); i++){
          if (partidas.get(i).esta(usuario) && partidas.get(i).esta(usuario2) ) {
              partida = partidas.get(i);
              partidas.remove(partida);
          }
        }
        return partida;
    }


    public void guardarPartida(Partida partida){
        partidas.add(partida);
        escribirPartida(partida);
    }

    private void escribirPartida(Partida partida){
        try{
        if (!partidas.estaVacio()) {
            Partida p = partidas.get(0);
            serializadorPartida.writeOneObject(p);
            for (int i=1 ; i<partidas.getTam();i++){
                p = partidas.get(i);
                serializadorPartida.addOneObject(p);
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cargarPartidas(){
        try {
            Object[] partidas = serializadorPartida.readObjects();
            for(Object p : partidas){
                SerializadorPartidas.partidas.add((Partida) p);
            }
        } catch (Exception ignored) {
        }
    }

}
