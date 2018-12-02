package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Tipo;
import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class TipoDAO<TIPO> extends DAOGenerico<Tipo> implements Serializable {

  public TipoDAO() {
    super();
    classePersistente = Tipo.class;
    ordem = "nome"; // define a ordem padrão do DAO
    maximoObjetos = 3;
  }
}
