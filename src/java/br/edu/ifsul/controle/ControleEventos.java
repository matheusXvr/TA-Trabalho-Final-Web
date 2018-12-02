package br.edu.ifsul.controle;

import br.edu.ifsul.dao.EventosDAO;
import br.edu.ifsul.dao.PessoasDAO;
import br.edu.ifsul.modelo.Eventos;
import br.edu.ifsul.modelo.Pessoas;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "controleEventos")
@ViewScoped
public class ControleEventos implements Serializable {

  @EJB
  private EventosDAO<Eventos> dao;
  private Eventos objeto;
  private Boolean editando;

  @EJB
  private PessoasDAO<Pessoas> daoPessoas;

  public ControleEventos() {
    editando = false;
  }

  public String listar() {
    editando = false;
    return "/privado/eventos/listar?faces-redirect=true";
  }

  public void novo() {
    editando = true;
    setObjeto(new Eventos());
  }

  public void alterar(Object id) {
    try {
      setObjeto(getDao().getObjectById(id));
      editando = true;
    } catch (Exception e) {
      Util.mensagemErro("Erro ao recuperar objeto: "
              + Util.getMensagemErro(e));
    }
  }

  public void excluir(Object id) {
    try {
      setObjeto(getDao().getObjectById(id));
      getDao().remover(getObjeto());
      Util.mensagemInformacao("Objeto removido com sucesso!");
    } catch (Exception e) {
      Util.mensagemErro("Erro ao remover objeto: "
              + Util.getMensagemErro(e));
    }
  }

  public void salvar() {
    try {
      if (getObjeto().getId() == null) {
        getDao().persist(getObjeto());
      } else {
        getDao().merge(getObjeto());
      }
      Util.mensagemInformacao("Objeto persistido com sucesso!");
      editando = false;
    } catch (Exception e) {
      Util.mensagemErro("Erro ao persistir objeto: "
              + Util.getMensagemErro(e));
    }
  }

  public Boolean getEditando() {
    return editando;
  }

  public void setEditando(Boolean editando) {
    this.editando = editando;
  }

  public EventosDAO<Eventos> getDao() {
    return dao;
  }

  public void setDao(EventosDAO<Eventos> dao) {
    this.dao = dao;
  }

  public Eventos getObjeto() {
    return objeto;
  }

  public void setObjeto(Eventos objeto) {
    this.objeto = objeto;
  }

  public PessoasDAO<Pessoas> getDaoPessoas() {
    return daoPessoas;
  }

  public void setDaoPessoas(PessoasDAO<Pessoas> daoPessoas) {
    this.daoPessoas = daoPessoas;
  }

}
