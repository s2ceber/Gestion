package s2.gestion.model.ficheros;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.base.Documentable;

/**
 * @author Alberto
 * Modelo para las categorias de los articulos
 *
 */
@Entity
@Table(name = "categoria")
public @Getter @Setter class Categoria extends Documentable{
    private String nombre;
}
