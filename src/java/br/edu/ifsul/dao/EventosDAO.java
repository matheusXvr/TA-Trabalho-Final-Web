package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Eventos;
import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class EventosDAO<TIPO> extends DAOGenerico<Eventos> implements Serializable {

    public EventosDAO(){
        super();
        classePersistente = Eventos.class;
        ordem = "nome";
    }
}