package br.edu.ifsul.controle;

import br.edu.ifsul.dao.PessoasDAO;
import br.edu.ifsul.modelo.Pessoas;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named(value = "controleLogin")
@SessionScoped
public class ControleLogin implements Serializable {

  private Pessoas pessoaAutenticada;
  @EJB
  private PessoasDAO<Pessoas> dao;
  private String email;
  private String senha;

  public ControleLogin() {
  }

  public String paginaLogin() {
    return "/login?faces-redirect=true";
  }

  public String efetuarLogin() {
    try {
      HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
      request.login(this.email, this.senha);
      if (request.getUserPrincipal() != null) {
        pessoaAutenticada = dao.localizaPorNomePessoa(request.getUserPrincipal().getName());
        Util.mensagemInformacao("Login efetuado com sucesso!");
        email = "";
        senha = "";
      }
      return "/index";
    } catch (Exception e) {
      e.printStackTrace();
      Util.mensagemErro("Usuário ou senha inválidos! "
              + Util.getMensagemErro(e));
      return "/login";
    }
  }

  public String efetuarLogout() {
    try {
      pessoaAutenticada = null;
      HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
      request.logout();
    } catch (Exception e) {
      Util.mensagemErro("Erro: " + Util.getMensagemErro(e));
    }
    return "/index";
  }

  public Pessoas getPessoaAutenticada() {
    return pessoaAutenticada;
  }

  public void setPessoaAutenticada(Pessoas pessoaAutenticada) {
    this.pessoaAutenticada = pessoaAutenticada;
  }

  public void setDao(PessoasDAO<Pessoas> dao) {
    this.dao = dao;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public PessoasDAO<Pessoas> getDao() {
    return dao;
  }

}