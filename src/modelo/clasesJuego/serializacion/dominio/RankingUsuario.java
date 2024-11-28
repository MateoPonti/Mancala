package modelo.clasesJuego.serializacion.dominio;

import modelo.clasesJuego.usuario.IUsuario;
import modelo.clasesJuego.usuario.Usuario;

import java.util.ArrayList;
import java.util.Comparator;

public class RankingUsuario {
    private ArrayList<Usuario> ranking;
    public RankingUsuario() {
        ranking=new ArrayList<>();
    }

    public ArrayList<Usuario> getRanking() {
        return new ArrayList<>(ranking);
    }



    public boolean compararRanking(Usuario usuario){
        boolean cambio = false;
        if(ranking.size()<5){
            ranking.add(usuario);
            cambio=true;
            ordenarRanking();
        }
        else{
            if (usuario.getElo()>ranking.get(ranking.size()-1).getElo()){
                ranking.set(ranking.size()-1, usuario);
                cambio=true;
                ordenarRanking();
            }
        }
        return cambio;
    }


    private void ordenarRanking(){
        ranking.sort(Comparator.comparingInt(Usuario::getElo).reversed());
    }
}
