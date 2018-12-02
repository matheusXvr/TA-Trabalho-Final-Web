package br.edu.ifsul.converter;

import br.edu.ifsul.modelo.Tipo;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@FacesConverter(value = "converterTipo")
public class ConverterTipo implements Serializable, Converter {

  @PersistenceContext(unitName = "TA-Trabalho-Final-WebPU")
  private EntityManager em;

  @Override
  public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
    if (string == null || string.equals("Selecione um registro")) {
      return null;
    }
    return em.find(Tipo.class, string);
  }

  @Override
  public String getAsString(FacesContext fc, UIComponent uic, Object o) {
    if (o == null) {
      return null;
    }
    Tipo obj = (Tipo) o;
    return obj.getNome();
  }

}
