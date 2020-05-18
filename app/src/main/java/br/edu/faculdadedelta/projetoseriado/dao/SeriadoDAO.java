package br.edu.faculdadedelta.projetoseriado.dao;

import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.projetoseriado.modelo.Seriado;

public class SeriadoDAO {

    private static List<Seriado> listaSeriado = new ArrayList<>();
    private static Long idGerador = 1L;

    public void incluir(Seriado seriado){
        seriado.setId(idGerador++);
        listaSeriado.add(seriado);
    }

    public void excluir (Seriado seriado){
        listaSeriado.remove(seriado);
    }

    public List<Seriado> listar() {
        return listaSeriado;
    }

    public void alterar(Seriado seriado){
        for (Seriado seriadoAlter : listaSeriado){
            long idSeriado = seriado.getId();
            long idSeriadoAlter = seriadoAlter.getId();
            if (idSeriado == idSeriadoAlter){
                listaSeriado.remove(seriadoAlter);
                listaSeriado.add(seriado);
                break;
            }
        }
    }

}
