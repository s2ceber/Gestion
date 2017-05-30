package s2.gestion.model.ficheros;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.base.Documentable;

/**
 * @author Alberto
 * Modelo para los articulos
 *
 */
@Entity
@Table(name = "articulo")
public @Getter @Setter class  Articulo extends Documentable{
    private String nombre;
}
